package com.syj.olb.common;

import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//@Configuration
public class MyConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DispatcherServlet ds=new DispatcherServlet();
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("DispatcherServlet", ds);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/temp/img");
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setMultipartConfig(multipartConfigElement);
    }
    @Bean
    public MultipartResolver multipartResolver(){
        //这个解析器依赖于servlet3.0对multipart请求的支持,从spring3.1开始支持
        return new StandardServletMultipartResolver();
    }
}
class MyWebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
   // AbstractDispatcherServletInitializer
    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createRootApplicationContext() root application context}.
     *
     * @return the configuration for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createServletApplicationContext() Servlet application context}.
     *
     * @return the configuration for the Servlet application context, or
     * {@code null} if all configuration is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash;
     * for example {@code "/"}, {@code "/app"}, etc.
     *
     * @see #registerDispatcherServlet(ServletContext)
     */
    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    /**
     * Optionally perform further registration customization once
     * {@link #registerDispatcherServlet(ServletContext)} has completed.
     *
     * @param registration the {@code DispatcherServlet} registration to be customized
     * @see #registerDispatcherServlet(ServletContext)
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
     //   registration.setMultipartConfig(new MultipartConfigElement(""));
    }
}