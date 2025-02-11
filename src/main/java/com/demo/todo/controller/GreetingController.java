package com.demo.todo.controller;

import com.demo.todo.entity.Product;
import com.demo.todo.entity.Task;
import com.demo.todo.repository.ProductRepository;
import com.demo.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        productRepository.save(new Product(name));
        taskRepository.save(new Task(name, "desc"));
        return "greeting " + name;
    }


}