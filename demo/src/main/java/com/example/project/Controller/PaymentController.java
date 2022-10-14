package com.example.project.Controller;

import com.example.project.Domain.FileDB;
import com.example.project.Domain.Payment;
import com.example.project.Domain.User;
import com.example.project.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders={"Accept"})
@RestController
@RequestMapping("/api/users")
public class PaymentController {

    @Autowired
    private PaymentService paymentService ;
    @PostMapping("/registercard/{userId}")
    public ResponseEntity<?> registerCard( @RequestBody Payment userCard,@PathVariable("userId") User userId){

       // Payment newCard = PaymentService.saveCard(userCard);

        return  new ResponseEntity<Payment>(paymentService.saveCard(userCard,userId), HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    public ResponseEntity<Boolean> checkPayment(@RequestBody Payment payment){
        return new ResponseEntity<Boolean>(paymentService.checkPayment(payment), HttpStatus.OK);
    }

    @GetMapping("/payment/user/{userId}")
    public List<Payment> getPayByUserId(@PathVariable("userId") Long userId) {


        return paymentService.PaymentbyUserId(userId);


    }

}