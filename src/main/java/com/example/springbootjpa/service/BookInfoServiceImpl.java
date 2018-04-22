package com.example.springbootjpa.service;

import com.example.springbootjpa.dao.BookInfoJPA;
import com.example.springbootjpa.pojo.BookInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "bookInfoService")
public class BookInfoServiceImpl implements BookInfoService {
    @Resource(name = "bookInfoJPA")
    BookInfoJPA bookInfoJPA;

    public List<BookInfo> findAll() {
        // TODO Auto-generated method stub
        return bookInfoJPA.findAll();
    }
    @Override
    public boolean deleteById(Long id) {
        boolean bool = false;
        Long rst = 0l;
        if(bookInfoJPA.exists(id)){
            bool=true;
            bookInfoJPA.deleteById(id);
        }else{
            bool=false;
            rst=0l;
        }
        return bool;
    }

    @Override
    public boolean exists(Long id) {
        return bookInfoJPA.exists(id);
    }

    @Override
    public BookInfo findOne(Long id) {
        return bookInfoJPA.findOne(id);
    }

    @Override
    public long count() {
        return bookInfoJPA.count();
    }

    @Override
    public BookInfo save(BookInfo bookInfo) {
        return bookInfoJPA.save(bookInfo);
    }

    @Override
    public int update(BookInfo bookInfo) {
        return bookInfoJPA.update(bookInfo);
    }

    //public Users findByUser(String code, String pwd) {
    //	// TODO Auto-generated method stub
    //	return bookInfoJPA.findByUser(code, pwd);
    //}
    //
    //public int findCount(Integer book_type, String book_name,
    //		Integer is_borrow) {
    //	// TODO Auto-generated method stub
    //	return bookInfoJPA.findCount(book_type,book_name,is_borrow);
    //}
    //
    //@Override
    //public int delById(Integer id) {
    //	// TODO Auto-generated method stub
    //	return bookInfoJPA.delById(id);
    //}
    //
    //@Override
    //public int update(BookInfo bookInfo) {
    //	// TODO Auto-generated method stub
    //	return bookInfoJPA.update(bookInfo);
    //}
    //
    //@Override
    //public int add(BookInfo bookInfo) {
    //	// TODO Auto-generated method stub
    //	return bookInfoJPA.add(bookInfo);
    //}


}
