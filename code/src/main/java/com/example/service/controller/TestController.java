package com.example.service.controller;

import com.example.service.model.TestObject;
import com.example.service.repository.TestObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestObjectRepo testObjectRepo;

    @GetMapping("/hello")
    public String test(){
        return "Hello";
    }

    @GetMapping("/testdata/{email}")
    public String testdata(@PathVariable(value = "email") String email){
        return testObjectRepo.findTestObjectByEmail(email).get().getMessage();
    }

    @PostMapping("/testdata")
    public String testdata(@RequestBody TestObject testObject){
        System.out.println(testObject.getMessage());
        testObjectRepo.save(testObject);
        return "SUCCESS!!!";
    }
}
