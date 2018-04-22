package com.example.springbootjpa.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class BookInfo implements Serializable {

    private static final long serialVersionUID = -5710445457195368131L;
    @Id
    @GeneratedValue
    private Long book_id;
    @Column
    private String book_code;
    @Column
    private String book_name;
    @Column
    private Integer book_type;
    @Column
    private String book_atuthor;
    @Column
    private String publish_press;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publish_date;
    @Column
    private Integer is_borrow;
    @Column(name = "createdby")
    private String createdBy;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creation_time;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date last_updatetime;

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long bookId) {
        book_id = bookId;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String bookCode) {
        book_code = bookCode;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String bookName) {
        book_name = bookName;
    }

    public Integer getBook_type() {
        return book_type;
    }

    public void setBook_type(Integer bookType) {
        book_type = bookType;
    }

    public String getBook_atuthor() {
        return book_atuthor;
    }

    public void setBook_atuthor(String bookAtuthor) {
        book_atuthor = bookAtuthor;
    }

    public String getPublish_press() {
        return publish_press;
    }

    public void setPublish_press(String publishPress) {
        publish_press = publishPress;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publishDate) {
        publish_date = publishDate;
    }

    public Integer getIs_borrow() {
        return is_borrow;
    }

    public void setIs_borrow(Integer isBorrow) {
        is_borrow = isBorrow;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creationTime) {
        creation_time = creationTime;
    }

    public Date getLast_updatetime() {
        return last_updatetime;
    }

    public void setLast_updatetime(Date lastUpdatetime) {
        last_updatetime = lastUpdatetime;
    }

}
