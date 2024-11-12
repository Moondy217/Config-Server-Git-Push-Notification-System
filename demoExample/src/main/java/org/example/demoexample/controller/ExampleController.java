package org.example.demoexample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ExampleController {

    @Value("${example.name}")
    private String name;

    @Value("${example.type}")
    private String type;

    @GetMapping("")
    public String hello() {
        return "name: " + name + ", type: " + type;
    }
}
