package com.example.project.Domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private Long cardNumber;
    private String customerName;
    private String expireDate;
    private Integer cvv;
    private Integer amount;
    private Date transactionDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;
    public Payment() {
        super();
    }
    public Payment(Integer amount,String Status){
        this.amount=amount;
        this.status=status;
    }
    public Payment(Integer paymentId,Long cardNumber, String customerName, String expireDate, Integer cvv,Integer amount,User user,String status) {
        super();
        this.paymentId=paymentId;
        this.cardNumber = cardNumber;
        this.customerName = customerName;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.amount=amount;
        this.user=user;
        this.status=status;


    }



    public Long getCardNumber() {
        return cardNumber;
    }



    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }



    public String getCustomerName() {
        return customerName;
    }



    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



    public String getExpireDate() {
        return expireDate;
    }


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }



    public Integer getCvv() {
        return cvv;
    }



    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    @PrePersist
    protected void onCreate(){
        this.transactionDate = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

/*  public Long getUserpayid() {
        return userpayid;
    }

    public void setUserpayid(Long userpayid) {
        this.userpayid = userpayid;
    }*/
}