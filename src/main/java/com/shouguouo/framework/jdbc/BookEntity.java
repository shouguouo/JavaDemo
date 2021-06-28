package com.shouguouo.framework.jdbc;

import java.util.Date;

/**
 * @author swj
 * @date 2018/3/26
 */
public class BookEntity {
    private Long bsm;
    private Long book_id;
    private String book_name;
    private String book_author;
    private String book_pub;
    private String book_price;
    private String book_sort;
    private String book_status;
    private Date book_record;
    private Long book_times;
    private String book_publication_date;

    public BookEntity(Long bsm, Long book_id, String book_name, String book_author, String book_pub, String book_price, String book_sort, String book_status, Date book_record, Long book_times, String book_publication_date) {
        this.bsm = bsm;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_pub = book_pub;
        this.book_price = book_price;
        this.book_sort = book_sort;
        this.book_status = book_status;
        this.book_record = book_record;
        this.book_times = book_times;
        this.book_publication_date = book_publication_date;
    }

    public Long getBsm() {
        return bsm;
    }

    public void setBsm(Long bsm) {
        this.bsm = bsm;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_pub() {
        return book_pub;
    }

    public void setBook_pub(String book_pub) {
        this.book_pub = book_pub;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getBook_sort() {
        return book_sort;
    }

    public void setBook_sort(String book_sort) {
        this.book_sort = book_sort;
    }

    public String getBook_status() {
        return book_status;
    }

    public void setBook_status(String book_status) {
        this.book_status = book_status;
    }

    public Date getBook_record() {
        return book_record;
    }

    public void setBook_record(Date book_record) {
        this.book_record = book_record;
    }

    public Long getBook_times() {
        return book_times;
    }

    public void setBook_times(Long book_times) {
        this.book_times = book_times;
    }

    public String getBook_publication_date() {
        return book_publication_date;
    }

    public void setBook_publication_date(String book_publication_date) {
        this.book_publication_date = book_publication_date;
    }
}
