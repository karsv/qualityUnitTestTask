package org.testtask.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.testtask.exceptions.DataIsNotValidException;
import org.testtask.models.Bike;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;
import org.testtask.service.BikeService;
import org.testtask.service.impl.EbikeServiceImpl;
import org.testtask.service.impl.FoldingbikeServiceImpl;
import org.testtask.service.impl.SpeedelecBikeServiceImpl;
import org.testtask.util.DataService;
import org.testtask.util.FileService;

@Component
public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);
    private static final String MAIN_MENU = "Please make your choice:\n" +
            "1 - Show the entire EcoBike catalog\n" +
            "2 – Add a new folding bike\n" +
            "3 – Add a new speedelec\n" +
            "4 – Add a new e-bike\n" +
            "5 – Find the first item of a particular brand\n" +
            "6 – Write to file\n" +
            "7 – Stop the program\n";

    private static final String MENU_OF_PAGINATION = "Enter:\n " +
            "n - next page \n " +
            "b - previous page \n " +
            "m - back to main menu";

    private static final Integer POSITIONS_ON_PAGE = 10;

    private static final String ADD_MSG_FOR_SEARCH = "or type 0, if you want skip parameter.";

    private static final String SEARCH_MENU = "Please choose type of searching bike:\n" +
            "1 - Folding bike \n" +
            "2 - Speedelec \n" +
            "3 - Ebike \n";

    private final DataService dataService;
    private final FileService fileService;
    private final FoldingbikeServiceImpl foldingbikeService;
    private final SpeedelecBikeServiceImpl speedelecBikeService;
    private final EbikeServiceImpl ebikeService;

    private boolean savedData = true;


    public Runner(DataService dataService,
                  FileService fileService,
                  FoldingbikeServiceImpl foldingbikeService,
                  SpeedelecBikeServiceImpl speedelecBikeService,
                  EbikeServiceImpl ebikeService) {
        this.dataService = dataService;
        this.fileService = fileService;
        this.foldingbikeService = foldingbikeService;
        this.speedelecBikeService = speedelecBikeService;
        this.ebikeService = ebikeService;
    }

    public void runApp(String path) {
        initApp(path);

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!"7".equals(input)) {
            System.out.println(MAIN_MENU);
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    positionOne(dataService.getAllBikes(), scanner);
                    break;
                case "2":
                    FoldingBike foldingBike = positionTwo(scanner);
                    saveBike(scanner, foldingbikeService, foldingBike);
                    break;
                case "3":
                    SpeedelecBike speedelecBike = positionThree(scanner);
                    saveBike(scanner, speedelecBikeService, speedelecBike);
                    break;
                case "4":
                    Ebike ebike = positionFour(scanner);
                    saveBike(scanner, ebikeService, ebike);
                    break;
                case "5":
                    positionFive(scanner);
                    break;
                case "6":
                    positionSix(path);
                    break;
                case "7":
                    positionSeven(path, scanner);
                    break;
            }
        }
    }

    private void initApp(String path) {
        List<String> strings = null;
        try {
            strings = fileService.readFromFileToList(path);
            dataService.parseListOfStrings(strings);
        } catch (IOException e) {
            System.out.println("Error when get input data! " +
                    "Check path to data or check if file is valid!");
            System.exit(0);
        } catch (DataIsNotValidException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void positionOne(List<? extends Bike> bikes, Scanner scanner) {
        listPagination(bikes, scanner);
    }

    private FoldingBike positionTwo(Scanner scanner) {
        changeMarkerOfSavedDataWhenAddNewData();
        return formFoldingBike(scanner, "");
    }

    private SpeedelecBike positionThree(Scanner scanner) {
        changeMarkerOfSavedDataWhenAddNewData();
        return formSpeedelecBike(scanner, "");
    }

    private Ebike positionFour(Scanner scanner) {
        changeMarkerOfSavedDataWhenAddNewData();
        return formEbike(scanner, "");
    }

    private void positionFive(Scanner scanner) {
        Integer input = -1;
        int positionOfSearchingBike;
        while (!input.equals(0)) {
            System.out.println(SEARCH_MENU);
            input = getIntegerFromInput(scanner);
            switch (input) {
                case 1:
                    FoldingBike bike = formFoldingBike(scanner, ADD_MSG_FOR_SEARCH);
                    positionOfSearchingBike = Collections.binarySearch(foldingbikeService.getAll(), bike);
                    System.out.println(foldingbikeService.getById(Math.abs(positionOfSearchingBike)));
                    break;
                case 2:
                    SpeedelecBike speedelecBike = formSpeedelecBike(scanner, ADD_MSG_FOR_SEARCH);
                    positionOfSearchingBike = Collections.binarySearch(speedelecBikeService.getAll(), speedelecBike);
                    System.out.println(speedelecBikeService.getById(positionOfSearchingBike));
                    break;
                case 3:
                    Ebike ebike = formEbike(scanner, ADD_MSG_FOR_SEARCH);
                    positionOfSearchingBike = Collections.binarySearch(ebikeService.getAll(), ebike);
                    System.out.println(ebikeService.getById(positionOfSearchingBike));
                    break;
                default:
                    System.out.println("Error input, please try again! \n");
            }
        }
    }

    private void positionSix(String path) {
        List<String> list = new ArrayList<>();
        list.addAll(dataService.getListOfDataEbike(ebikeService.getAll()));
        list.addAll(dataService.getListOfDataFoldingbikes(foldingbikeService.getAll()));
        list.addAll(dataService.getListOfDataSpeedelec(speedelecBikeService.getAll()));
        fileService.writeToFile(list, path);
        changeMarkerOfSavedDataWhenItSaved();
    }

    private void positionSeven(String path, Scanner scanner) {
        if (!isSavedData()) {
            String input = "";
            while (true) {
                System.out.println("There is no saved data. Do you want save it? Y/y or else N/n :");
                input = scanner.nextLine();
                if (input.equals("y")) {
                    positionSix(path);
                    System.exit(0);
                }
                if (input.equals("n")) {
                    System.exit(0);
                }
                System.out.println("Incorrect input! Please try again!");
            }
        }
    }

    private FoldingBike formFoldingBike(Scanner scanner, String message) {
        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setType("FOLDING");
        foldingBike.setBrand(getBrandFromInput(scanner, message));
        Double weight = getWeightFromInput(scanner, message);
        if (weight > 0) {
            foldingBike.setWeight(weight);
        }
        foldingBike.setAvailableLights(getIsLightFromInput(scanner));
        foldingBike.setColor(getColorFromInput(scanner, message));
        Double price = getPriceFromInput(scanner, message);
        if (price > 0) {
            foldingBike.setPrice(price);
        }
        Double wheelSize = getWheelSizeFromInput(scanner, message);
        if (wheelSize > 0) {
            foldingBike.setWheelsSize(wheelSize);
        }
        Integer numberOfGears = getNumberOfGearsFromInput(scanner, message);
        if (numberOfGears > 0) {
            foldingBike.setNumberOfGears(numberOfGears);
        }
        return foldingBike;
    }

    private SpeedelecBike formSpeedelecBike(Scanner scanner, String message) {
        SpeedelecBike speedelecBike = new SpeedelecBike();
        speedelecBike.setType("SPEEDELEC");
        speedelecBike.setBrand(getBrandFromInput(scanner, message));
        Double weight = getWeightFromInput(scanner, message);
        if (weight > 0) {
            speedelecBike.setWeight(weight);
        }
        speedelecBike.setAvailableLights(getIsLightFromInput(scanner));
        speedelecBike.setColor(getColorFromInput(scanner, message));
        Double price = getPriceFromInput(scanner, message);
        if (price > 0) {
            speedelecBike.setPrice(price);
        }
        Double maxSpeed = getMaxSpeedFromInput(scanner, message);
        if (maxSpeed > 0) {
            speedelecBike.setMaxSpeed(maxSpeed);
        }
        Double batteryCapacity = getBatteryCapacityFromInput(scanner, message);
        if (batteryCapacity > 0) {
            speedelecBike.setBatteryCapacity(batteryCapacity);
        }
        return speedelecBike;
    }

    private Ebike formEbike(Scanner scanner, String message) {
        Ebike ebike = new Ebike();
        ebike.setType("E-BIKE");
        ebike.setBrand(getBrandFromInput(scanner, message));
        Double weight = getWeightFromInput(scanner, message);
        if (weight > 0) {
            ebike.setWeight(weight);
        }
        ebike.setAvailableLights(getIsLightFromInput(scanner));
        ebike.setColor(getColorFromInput(scanner, message));
        Double price = getPriceFromInput(scanner, message);
        if (price > 0) {
            ebike.setPrice(price);
        }
        Double maxSpeed = getMaxSpeedFromInput(scanner, message);
        if (maxSpeed > 0) {
            ebike.setMaxSpeed(maxSpeed);
        }
        Double batteryCapacity = getBatteryCapacityFromInput(scanner, message);
        if (batteryCapacity > 0) {
            ebike.setBatteryCapacity(batteryCapacity);
        }
        return ebike;
    }

    private void listPagination(List<? extends Bike> bikes, Scanner scanner) {
        int page = 0;
        List<? extends Bike> pageBikes = null;
        String input = "";
        while (!"m".equals(input)) {
            try {
                pageBikes = bikes.subList(page * POSITIONS_ON_PAGE,
                        page * POSITIONS_ON_PAGE
                                + POSITIONS_ON_PAGE > bikes.size() ? bikes.size()
                                : page * POSITIONS_ON_PAGE + POSITIONS_ON_PAGE);
                pageBikes.stream().forEach(System.out::println);
            } catch (IllegalArgumentException e) {
                System.out.println("\n This is last page! \n");
                page--;
            }

            System.out.println(MENU_OF_PAGINATION);

            input = scanner.nextLine();

            if (input.equalsIgnoreCase("n")) {
                page++;
                continue;
            }

            if (input.equalsIgnoreCase("b")) {
                if (page > 0) {
                    page--;
                    continue;
                } else {
                    System.out.println("It's first page!\n");
                    continue;
                }
            }
        }
    }

    private boolean changeMarkerOfSavedDataWhenAddNewData() {
        if (savedData) {
            savedData = false;
        }
        return savedData;
    }

    private boolean changeMarkerOfSavedDataWhenItSaved() {
        savedData = true;
        return savedData;
    }

    private void saveBike(Scanner scanner, BikeService bikeService, Bike bike) {
        String input = "";
        while (true) {
            System.out.println(bike.showAllParameters());
            System.out.println("Save this bike Y/y or else N/n :");
            input = scanner.nextLine();
            if (input.equals("y")) {
                bikeService.save(bike);
                return;
            }
            if (input.equals("n")) {
                return;
            }
            System.out.println("Please try again!");
        }
    }

    private String getBrandFromInput(Scanner scanner, String message) {
        System.out.println("Type brand of bike " + message + ":");
        return scanner.nextLine();
    }

    private Double getWeightFromInput(Scanner scanner, String message) {
        System.out.println("Type weight of bike " + message + ":");
        return getDoubleFromInput(scanner);
    }

    private boolean getIsLightFromInput(Scanner scanner) {
        boolean marker = false;
        String input = "";
        while (true) {
            System.out.println("If light on bike is available type Y/y or else N/n :");
            input = scanner.nextLine();
            if (input.equals("y")) {
                marker = true;
                return marker;
            }
            if (input.equals("n")) {
                return marker;
            }
            System.out.println("Please try again!");
        }
    }

    private String getColorFromInput(Scanner scanner, String message) {
        System.out.println("Type color of bike " + message + ":");
        return scanner.nextLine();
    }

    private Double getPriceFromInput(Scanner scanner, String message) {
        System.out.println("Type price of bike " + message + ":");
        return getDoubleFromInput(scanner);
    }

    private Double getWheelSizeFromInput(Scanner scanner, String message) {
        System.out.println("Type size of wheels of bike " + message + ":");
        return getDoubleFromInput(scanner);
    }

    private Integer getNumberOfGearsFromInput(Scanner scanner, String message) {
        System.out.println("Type bikes number of gears " + message + ":");
        return getIntegerFromInput(scanner);
    }

    private Double getMaxSpeedFromInput(Scanner scanner, String message) {
        System.out.println("Type max speed of bike " + message + ":");
        return getDoubleFromInput(scanner);
    }

    private Double getBatteryCapacityFromInput(Scanner scanner, String message) {
        System.out.println("Type battery capacity of bike " + message + ":");
        return getDoubleFromInput(scanner);
    }

    private Double getDoubleFromInput(Scanner scanner) {
        Double input;
        while (true) {
            try {
                input = Double.valueOf(scanner.nextLine());
                if (input < 0) {
                    System.out.println("The parameter can't be less than zero!");
                    throw new ArithmeticException();
                }
                return input;
            } catch (RuntimeException e) {
                System.out.println("Error input, please try again!");
            }
        }
    }

    private Integer getIntegerFromInput(Scanner scanner) {
        Integer input;
        while (true) {
            try {
                input = Integer.valueOf(scanner.nextLine());
                if (input < 0) {
                    System.out.println("The parameter can't be less than zero!");
                    throw new ArithmeticException();
                }
                return input;
            } catch (RuntimeException e) {
                System.out.println("Error input, please try again!");
            }
        }
    }

    public boolean isSavedData() {
        return savedData;
    }
}
