package org.testtask.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("org.testtask");
        Runner runner = ctx.getBean(Runner.class);
        runner.runApp(args[0]);
    }
}
