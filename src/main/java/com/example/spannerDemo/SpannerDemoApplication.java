package com.example.spannerDemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Mono;


import java.util.Arrays;

@SpringBootApplication
public class SpannerDemoApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpannerDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
