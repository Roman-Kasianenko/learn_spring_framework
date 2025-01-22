package com.amigoscode;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class SpringAndSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
                SpringAndSpringBootApplication.class,
                args
        );

        int beanDefinitionCount = context.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
    }

//    @Bean(name = "red-bean")
    @Bean()
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    @SessionScope
//    @ApplicationScope
    public String redBean() {
        return "from redBean";
    }

    @Bean
    public String blueBean() {
        return "from blueBean";
    }

    @Bean
    CommandLineRunner commandLineRunner(
//            @Qualifier("red-bean")
            String redBean, String blueBean) {
        return args -> {
            System.out.println("Hello World");
            System.out.println(redBean);
            System.out.println(blueBean);
        };
    }

}
