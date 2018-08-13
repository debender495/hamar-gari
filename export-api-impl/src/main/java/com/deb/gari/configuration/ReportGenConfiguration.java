package com.deb.gari.configuration;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;

/**
 * @author Debender Prasad
 */
@org.springframework.context.annotation.Configuration
public class ReportGenConfiguration {

    /**
     * Creates the default configuration for the Freemarker
     *
     * @return the configuration created
     */
    @Bean(name = "reportFreemarkerConfig")
    public Configuration freemarkerConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }
}
