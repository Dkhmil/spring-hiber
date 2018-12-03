package khmil.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfiguration implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext annotationConfigApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigApplicationContext.scan("khmil");


        servletContext.addListener(new ContextLoaderListener(annotationConfigApplicationContext));

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",
                new DispatcherServlet(new GenericWebApplicationContext()));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }
}
