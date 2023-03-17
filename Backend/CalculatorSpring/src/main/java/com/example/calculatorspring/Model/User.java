package com.example.calculatorspring.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostObject> equations;

    public User(String username, String password, List<PostObject> equations) {
        this.username = username;
        this.password = password;
        this.equations = equations;
    }

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostObject> getEquations() {
        return equations;
    }

    public void setEquations(List<PostObject> equations) {
        this.equations = equations;
    }

    public void addEquation(PostObject postObject) {
        if (!equations.contains(postObject))
            equations.add(postObject);
    }

    public void removeEquation(PostObject postObject) {
        equations.removeIf(eq -> eq.equals(postObject));
    }
}
