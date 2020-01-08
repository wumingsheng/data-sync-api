package cn.com.boe.cms.datasyncapi.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BeansConfig {
	
	
	
	@Bean
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

}
