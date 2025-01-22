package com.amigoscode;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            String redBean, String blueBean, UserService userService) {
        return args -> {
            System.out.println("Hello World");
            System.out.println(redBean);
            System.out.println(blueBean);
            System.out.println(userService.getUserByID(1));
        };
    }

    public record User(int id, String name) {
    }

    @Service
    public class UserService {

        public UserService() {
            System.out.println("UserService constructor");
        }

        @PostConstruct
        public void init(){
            System.out.println("init, fill cache");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy cache");
        }

        @Bean
        public List<User> getUsers() {
            return List.of(
                    new User(1, "John Doe"),
                    new User(2, "Jane Doe"),
                    new User(3, "Jack Doe"));
        }

        public Optional<User> getUserByID(int id) {
            return getUsers().stream().filter(user -> user.id == id).findFirst();
        }
    }
}
