package com.example.springbootjpa.service;

import com.example.springbootjpa.pojo.BookInfo;

import java.util.List;

public interface BookInfoService {
    List<BookInfo> findAll();

    boolean deleteById(Long id);

    boolean exists(Long id);

    BookInfo findOne(Long id);

    long count();

    BookInfo save(BookInfo bookInfo);
    int update(BookInfo bookInfo);
	/*int findCount(Integer book_type, String book_name, Integer is_borrow);

	Users findByUser(String code, String pwd);

	int delById(Integer id);

	int update(BookInfo bookInfo);

	int add(BookInfo bookInfo);*/

}
