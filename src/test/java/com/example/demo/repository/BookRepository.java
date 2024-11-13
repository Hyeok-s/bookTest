package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.BookDTO;

@Mapper
public interface BookRepository {

	@Delete("TRUNCATE TABLE book")
	void truncateTable();

	@Insert("INSERT INTO book(title, author, storeId) VALUE(#{title}, #{author}, #{storeId})")
	void makeBook(String title, String author, int storeId);

	@Select("SELECT title FROM book")
	List<BookDTO> findAllBookTitle();

	@Select("SELECT b.title FROM book b INNER JOIN bookStore bs ON bs.id = b.storeId WHERE bs.name = #{name}")
	List<BookDTO> findBookTitlesByStoreName(String name);

	@Delete("DELETE FROM book WHERE author = #{author}")
	void deleteBookByAuthor(String author);

}
