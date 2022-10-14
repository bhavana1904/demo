package com.example.project.Service;

import com.example.project.Domain.FileDB;
import com.example.project.Domain.Payment;
import com.example.project.Domain.User;
import com.example.project.Exceptions.UsernameAlreadyExistsException;
import com.example.project.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private  PaymentRepository paymentRepository;
    public Payment saveCard(Payment newCard,User userId){

        try{

            newCard.setCardNumber(newCard.getCardNumber());
            newCard.setCustomerName(newCard.getCustomerName());
            newCard.setExpireDate(newCard.getExpireDate());
            newCard.setCvv(newCard.getCvv());
            newCard.setAmount(newCard.getAmount());
            newCard.setStatus("Paid");
            newCard.setUser(userId);

        }catch (Exception e){
            System.out.println(e);
        }
        return paymentRepository.save(newCard);

    }
    public String checkingPayment(Long userId){
        return paymentRepository.paymentCheck(userId);
    }
    public Integer amountPayment(Long userId){
        return paymentRepository.amountCheck(userId);
    }
public Integer countPayment(Long userId){
        return paymentRepository.countpay(userId);
}

    public List<Payment> PaymentbyUserId(Long userpayid){
        return paymentRepository.PaymentbyUserId(userpayid);
    }

    public boolean checkPayment(Payment admin) {
        if(paymentRepository.existsById(admin.getCardNumber())) {
            Payment currentAdmin = paymentRepository.findById(admin.getCardNumber()).get();

            if(currentAdmin.getCustomerName().equals(admin.getCustomerName()) && currentAdmin.getCvv().equals(admin.getCvv())
                    && currentAdmin.getExpireDate().equals(admin.getExpireDate())) {

                return true;
            }
            else {
                return false;
            }
        }else {

            return false;
        }

    }
}