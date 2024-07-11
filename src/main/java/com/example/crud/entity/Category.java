package com.example.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "category_id")
    private Long categoryId;
    @Column( name = "category_name")
    private String categoryName;

    //@ManyToMany(mappedBy =  "categoryList", cascade = CascadeType.ALL)         // Once we save categories , product will be saved automatically.
    @ManyToMany(mappedBy = "categoryList", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList= new ArrayList<>();


//Category category = categoryRepo findById("cid").get();
 //   s.o.p (category.getproducts().size();
}
