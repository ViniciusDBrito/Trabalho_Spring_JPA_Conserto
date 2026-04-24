package br.edu.ifsp.trabalho.api_rest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TESTEController {

    @GetMapping
    public String olaMundo(){
        return "Hello World SPRING >:^)";
    }
}