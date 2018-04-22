package com.example.springbootjpa.dao;

import com.example.springbootjpa.pojo.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * JpaRepository接口（SpringDataJPA提供的简单数据操作接口）
 * JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口）
 * Serializable（序列化接口）。
 */
public interface BookInfoJPA extends JpaRepository<BookInfo, Long>, JpaSpecificationExecutor<BookInfo>, Serializable {

    List<BookInfo> findAll();

    @Modifying
    @Transactional
    @Query(value = "delete from book_info where book_id=:id", nativeQuery = true)
    void deleteById(@Param(value = "id") Long id);

    boolean exists(Long id);

    BookInfo findOne(Long id);

    long count();

    BookInfo save(BookInfo bookInfo);

    @Modifying
    @Transactional
    @Query(value = "update book_info b " +
            "set b.book_code=:#{#book.book_code}," +
            "b.book_name=:#{#book.book_name}," +
            "b.book_type=:#{#book.book_type}," +
            "b.book_atuthor=:#{#book.book_atuthor}," +
            "b.publish_press=:#{#book.publish_press}," +
            "b.publish_date=:#{#book.publish_date}," +
            "b.createdBy=:#{#book.createdBy}," +
            "b.creation_time=:#{#book.creation_time}," +
            "b.last_updatetime=:#{#book.last_updatetime} " +
            "where b.book_id=:#{#book.book_id}", nativeQuery = true)
    int update(@Param(value = "book") BookInfo bookInfo);
    /*int findCount(Integer book_type, String book_name, Integer is_borrow);

    Users findByUser(String code, String pwd);

    int delById(Integer id);

    int update(BookInfo bookInfo);

    int add(BookInfo bookInfo);*/
}
