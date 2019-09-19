package internetshop.controller;

import internetshop.lib.Injector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InjectIniyializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("Dependency injection started ...");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
