package com.example.calculatorspring.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.calculatorspring.Model.PostObject;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);


    private String log;
    private String result;

    public String calculate(PostObject postObject){

        double result;
        double current = Integer.parseInt(postObject.getA());
        double previous = Integer.parseInt(postObject.getB());
        String operator = postObject.getOperator();

        switch (operator) {
            case "+" -> {
                result = (current) + (previous);
                this.log = previous + " + " + current + " = " + result;
                this.result = String.valueOf(result);
                return String.valueOf(result);
            }
            case "-" -> {
                result = (previous) - (current);
                this.log = previous + " - " + current + " = " + result;
                return String.valueOf(result);
            }
            case "*" -> {
                result = (current) * (previous);
                this.log = previous + " * " + current + " = " + result;
                return String.valueOf(result);
            }
            default -> {
                result = (previous) / (current);
                this.log = previous + " / " + current + " = " + result;
                return String.valueOf(result);
            }
        }
    }

    public String getLog(){
        logToLog();
        return this.log;
    }

    public void logToLog(){
        LOGGER.info("This calculation was done " + " " + this.log);
        LOGGER.info("This is the result that it gave" + " " + this.result);
    }

}
