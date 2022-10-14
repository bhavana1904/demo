package com.example.project.Payload;

import com.example.project.Domain.User;



public class JWTLoginSucessReponse {
      private Long userid;
    private boolean success;
    private String token;

    public JWTLoginSucessReponse() {
    }

    public JWTLoginSucessReponse(Long userid, boolean success, String token) {
       this.userid=userid;
        this.success = success;
        this.token = token;

    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "userid=" + userid +
                ", success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}