package com.example.crud.service;

import com.example.crud.entity.Category;
import com.example.crud.entity.Product;

public interface CategoryService {

    Category saveCategory(Category category);

    Product saveProduct(Product product);
}
