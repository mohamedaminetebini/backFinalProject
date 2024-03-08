package com.example.finalprojetback.Reposetories;

import com.example.finalprojetback.Models.FinePerDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FinePerDayRepo  extends JpaRepository<FinePerDay, String> {


    @Transactional
    @Query("select f from FinePerDay f order by f.date desc limit 1")
    List<FinePerDay> getlastfine();
}
