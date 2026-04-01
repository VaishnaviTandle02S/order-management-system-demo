package com.eomps.config;

import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Bean
    public Configuration freemarkerConfig() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        // Loads from webapp/WEB-INF/ftl/ correctly
        cfg.setTemplateLoader(new WebappTemplateLoader(servletContext, "/WEB-INF/ftl/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        return cfg;
    }
}
