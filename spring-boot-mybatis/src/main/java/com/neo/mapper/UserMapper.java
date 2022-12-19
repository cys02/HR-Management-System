package com.neo.mapper;

import java.util.List;

import com.neo.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface UserMapper {

    @Select("SELECT * FROM user")
    @Results({
			@Result(property = "id", column = "user_id"),
            @Result(property = "userType", column = "user_type"),
            @Result(property = "departmentName", column = "department_name"),
            @Result(property = "lateNumber", column = "late_number"),
            @Result(property = "bunkNumber", column = "bunk_number"),
            @Result(property = "offNumber", column = "off_number"),
            @Result(property = "overNumber", column = "over_number"),
            @Result(property = "evecNumber", column = "evec_number"),
            @Result(property = "baseSalary", column = "base_salary")
    })
    List<User> getAll();


    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}