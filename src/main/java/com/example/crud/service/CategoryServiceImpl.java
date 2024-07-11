package com.example.crud.service;


import com.example.crud.entity.Address;
import com.example.crud.entity.Category;
import com.example.crud.entity.Product;
import com.example.crud.repository.CategoryRepository;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

//    @Override
//    public Category saveCategory(Category category) {
//        if (category.getProductList() != null) {
//            for (Product product : category.getProductList()) {
//
//                List<Category> categories = product.getCategoryList();
//                categories.add(category);
//                product.setCategoryList(categories);
//
//                productRepository.save(product);
//
////                product.getCategoryList().add(category);
//            }
//        }
//        categoryRepository.save(category);
//        return category;
//    }
//}

    public Category saveCategory(Category category) {
        if (category.getProductList() != null) {
            for (Product product : category.getProductList()) {
                if (!productRepository.existsById(product.getProductId())) {
                    productRepository.save(product);
                }
            }
        }
        return categoryRepository.save(category);
    }

    public Product saveProduct(Product product) {
        if (product.getCategoryList() != null) {
            for (Category category : product.getCategoryList()) {
                if (!categoryRepository.existsById(category.getCategoryId())) {
                    categoryRepository.save(category);
                }
            }
        }
        return productRepository.save(product);
    }

}


