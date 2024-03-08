package com.example.finalprojetback.Reposetories;

import com.example.finalprojetback.Models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel, String> {


}
