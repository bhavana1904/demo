package com.example.project.Repository;

import com.example.project.Domain.FileDB;
import com.example.project.Domain.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepository extends JpaRepository<Total,Long> {

}
