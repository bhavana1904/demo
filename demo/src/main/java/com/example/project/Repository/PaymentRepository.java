package com.example.project.Repository;
import com.example.project.Domain.FileDB;
import com.example.project.Domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    @Query("select p from Payment p where p.user.userId=:userId")
    List<Payment> PaymentbyUserId(Long userId);


    @Query("select status from Payment where user.userId=:userId")
    public String paymentCheck(Long userId);

    @Query("select amount from Payment where user.userId=:userId")
    public Integer amountCheck(Long userId);
    @Query("select count(user_id) from Payment where user.userId=:userId")
    public Integer countpay(Long userId);
}