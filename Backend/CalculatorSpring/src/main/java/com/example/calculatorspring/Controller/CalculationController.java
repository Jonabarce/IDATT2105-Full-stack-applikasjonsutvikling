package com.example.calculatorspring.Controller;


import com.example.calculatorspring.Service.CalculatorService;
import com.example.calculatorspring.Model.PostObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://192.168.0.193:8000/")
public class CalculationController {


    @Autowired
    private CalculatorService service;



    @GetMapping("/")
    public String testConnection(){
        return "Yes";
    }

    @PostMapping("/Calculate")
    public String calculate(@RequestBody PostObject postObject){
        return service.calculate(postObject);
    }


    @PostMapping("/Log")
    public String getLog(@RequestBody PostObject postObject){
        return service.getLog();
    }

}
