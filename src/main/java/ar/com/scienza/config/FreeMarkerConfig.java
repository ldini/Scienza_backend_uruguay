package ar.com.scienza.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@Component
public class FreeMarkerConfig {

    private Configuration cfg;

    public FreeMarkerConfig() {}


    @PostConstruct
    public void initialize(){
        setCfg(new Configuration(Configuration.VERSION_2_3_22));
        getCfg().setClassForTemplateLoading(this.getClass(), "/");
        getCfg().setDefaultEncoding("UTF-8");
        getCfg().setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }


    public Configuration getCfg() {
        return cfg;
    }

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }
}
