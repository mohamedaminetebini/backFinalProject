package com.example.finalprojetback.Reposetories;

import com.example.finalprojetback.Models.IssuedBooks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssuedBooksRepo extends JpaRepository<IssuedBooks, String> {



    @Transactional
    @Query("select i from IssuedBooks i where  i.issued = false")
    List<IssuedBooks> findNotIssuedBooks();
    Long countByReturnedTrue();
    Long countByIssuedTrue();

}
