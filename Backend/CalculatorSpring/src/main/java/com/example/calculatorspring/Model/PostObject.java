package com.example.calculatorspring.Model;

public class PostObject {


    private String a;
    private String b;
    private String operator;

    public PostObject(String a, String b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public PostObject(){

    }

    public String getA() {
        return a;
    }


    public String getB() {
        return b;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
