package com.example.calculatorspring.Model;
import jakarta.persistence.*;


@Entity
public class PostObject {

    @Id
    @GeneratedValue
    private int id;
    private String a;
    private String b;
    private String operator;
    private String expression;
    private double result;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_username")
    private User user;

    public PostObject() {
    }

    public PostObject(String a, String b, String operator, String expression, double result, User user) {
        this.a = a;
        this.b = b;
        this.operator = operator;
        this.expression = expression;
        this.result = result;
        this.user = user;
    }



    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getUserName(){
        return getUser().getUsername();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEquation() {
        return a + " " + operator + " " + b;
    }

    @Override
    public String toString() {
        return "PostObject{" +
                "id=" + id +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", operator='" + operator + '\'' +
                ", expression='" + expression + '\'' +
                ", result=" + result +
                ", user=" + user.toString() +
                '}';
    }
}
