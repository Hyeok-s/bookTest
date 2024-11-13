package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.BookStoreDTO;

@Mapper
public interface BookStoreRepository {

	@Delete("TRUNCATE TABLE bookStore")
	void truncateTable();

	@Insert("INSERT INTO bookStore(name) VALUE(#{name})")
	void makeStore(String name);

	@Select("SELECT name FROM bookStore")
	List<BookStoreDTO> findAllStoreName();

	@Update("UPDATE bookStore SET name = #{afterName} WHERE name = #{beforeName}")
	void updateStoreName(String beforeName, String afterName);

	@Select("SELECT id FROM bookStore WHERE name = #{name}")
	int findStoreIdByName(String name);

}
