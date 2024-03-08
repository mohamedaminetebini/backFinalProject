package com.example.finalprojetback.Reposetories;

import com.example.finalprojetback.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRpo extends JpaRepository<Categories, String> {
}
