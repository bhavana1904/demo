package com.example.project.Repository;

import com.example.project.Domain.FileDB;
import com.example.project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface FileDBRepository extends JpaRepository<FileDB, String> {
    @Query("select s from FileDB s where s.user.userId=:userId")
   public List<FileDB> filebyUserId(Long userId);

    @Query("select sum(docSize) as totalfilessize from FileDB  where user.userId=?1")
    public Long totalsize(Long userId);

}