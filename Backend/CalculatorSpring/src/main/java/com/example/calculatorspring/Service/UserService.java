package com.example.calculatorspring.Service;

import com.example.calculatorspring.Model.PostObject;
import com.example.calculatorspring.Model.User;
import com.example.calculatorspring.Repository.PostObjectRepository;
import com.example.calculatorspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private String log;
    private UserRepository userRepository;
    private PostObjectRepository postObjectRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCalculationRepository(PostObjectRepository postObjectRepository){
        this.postObjectRepository = postObjectRepository;
    }

    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        return user.map(value -> Objects.equals(value.getPassword(), password)).orElse(false);
    }

    public Boolean getUser(String username, String password){
        Optional<User> user = userRepository.findById(username);
        return user.map(value -> Objects.equals(value.getPassword(), password)).orElse(false);
    }


    public String calculate(PostObject postObject){
        User user = postObject.getUser();
        String username = user.getUsername();
        double result;
        double current = Integer.parseInt(postObject.getA());
        double previous = Integer.parseInt(postObject.getB());
        String operator = postObject.getOperator();
        UserService userService = new UserService();

        switch (operator) {
            case "+" -> {
                result = (current) + (previous);
                this.log = previous + " + " + current + " = " + result;
                postObject.setExpression(this.log);
                postObject.setResult(Double.parseDouble(String.valueOf(result)));
                Optional<User> optionalUser = userRepository.findById(postObject.getUserName());
                if (optionalUser.isPresent()) {
                    User user1 = optionalUser.get();
                    postObject.setUser(user1);
                    postObjectRepository.save(postObject);
                }
                return String.valueOf(result);
            }
            case "-" -> {
                result = (previous) - (current);
                this.log = previous + " - " + current + " = " + result;
                postObject.setExpression(this.log);
                postObject.setResult(Double.parseDouble(String.valueOf(result)));
                Optional<User> optionalUser = userRepository.findById(postObject.getUserName());
                if (optionalUser.isPresent()) {
                    User user1 = optionalUser.get();
                    postObject.setUser(user1);
                    postObjectRepository.save(postObject);
                }
                return String.valueOf(result);
            }
            case "*" -> {
                result = (current) * (previous);
                this.log = previous + " * " + current + " = " + result;
                postObject.setExpression(this.log);
                postObject.setResult(Double.parseDouble(String.valueOf(result)));
                Optional<User> optionalUser = userRepository.findById(postObject.getUserName());
                if (optionalUser.isPresent()) {
                    User user1 = optionalUser.get();
                    postObject.setUser(user1);
                    postObjectRepository.save(postObject);
                }
                return String.valueOf(result);
            }
            default -> {
                result = (previous) / (current);
                this.log = previous + " / " + current + " = " + result;
                postObject.setExpression(this.log);
                postObject.setResult(Double.parseDouble(String.valueOf(result)));
                Optional<User> optionalUser = userRepository.findById(postObject.getUserName());
                if (optionalUser.isPresent()) {
                    User user1 = optionalUser.get();
                    postObject.setUser(user1);
                    postObjectRepository.save(postObject);
                }
                return String.valueOf(result);
            }
        }
    }

    public boolean createUser(String username, String password) {
        Optional<User> existingUser = userRepository.findById(username);
        if (existingUser.isPresent()) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return true;
    }


    public boolean deleteUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    public List<String> getEquations(String username) {
        List<PostObject> u = postObjectRepository.findAll();
        List<String> userCalculations = new ArrayList<>();
        int count = 0;
        for (int i = u.size()-1; i > 0; i--) {
            if(u.get(i).getUser().getUsername().equals(username)){
                if (count < 10) {
                    userCalculations.add(u.get(i).getExpression());
                    count++;
                } else {
                    break;
                }
            }
        }
        return userCalculations;
    }
    public String getLog(){
        return this.log;
    }
}
