package com.example.project.Domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String docId;

    private String name;

    private String type;

    @Lob
    private byte[] data;
   private Long docSize;


@ManyToOne
@JoinColumn(name = "userId",referencedColumnName = "userId")
private User user;


    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data) {

        this.name = name;
        this.type = type;
        this.data = data;
    }

    public FileDB(String name, String type, byte[] data ,Long docSize,User user) {

        this.name = name;
        this.type = type;
        this.data = data;
this.docSize=docSize;
        this.user = user;
    }



    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getDocSize() {
        return docSize;
    }

    public void setDocSize(Long docSize) {
        this.docSize = docSize;
    }
}