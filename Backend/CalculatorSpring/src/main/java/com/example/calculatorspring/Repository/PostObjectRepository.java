package com.example.calculatorspring.Repository;


import com.example.calculatorspring.Model.PostObject;
import com.example.calculatorspring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostObjectRepository extends JpaRepository<PostObject,Integer> {


    List<PostObject> findByUser(User user);
}