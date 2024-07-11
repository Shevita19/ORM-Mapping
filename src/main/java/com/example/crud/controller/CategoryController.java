package com.example.crud.controller;

import com.example.crud.entity.Category;
import com.example.crud.entity.Product;
import com.example.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @PostMapping("/savedate")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category saveCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product saveCategory = categoryService.saveProduct(product);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }



}


