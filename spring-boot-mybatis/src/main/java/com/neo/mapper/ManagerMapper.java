package com.neo.mapper;

import com.neo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    @Select("SELECT * FROM user WHERE user_type = 2")
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
    public List<Map<String ,Object>>getManager();

    @Insert("INSERT INTO user(user_id,password,name,sex,user_type,telephone,email,address,base_salary) VALUES(#{id}, #{password}, #{name}, #{sex}, 2, #{telephone}, #{email}, #{address},#{baseSalary})")
    void addManager(String id,String password,String name,Boolean sex,String telephone,String email,String address,Integer baseSalary);

    @Select("SELECT * FROM user WHERE user_type = 2 " +
            "and user_id = #{id}")
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
    public Map<String ,Object>getOneById(String id);


    @Update("UPDATE user " +
            "SET name=#{name},telephone=#{telephone},email=#{email}," +
            "address=#{address},base_salary=#{baseSalary} " +
            "WHERE user_id = #{id}")
    void updateManager(String id,String name,String telephone,String email,String address,Integer baseSalary);

    @Update("update user " +
            "set late_number=#{lateNumber},bunk_number=#{bunkNumber}," +
            "off_number=#{offNumber},over_Number=#{overNumber}," +
            "evec_Number=#{evecNumber} " +
            "WHERE user_id = #{id}")
    void updateManagerRecord(String id,Integer lateNumber,Integer bunkNumber,Integer offNumber,Integer overNumber,Integer evecNumber);

    @Update("update user " +
            "set user_type = 1 " +
            "where user_id = #{id}")
    public void changeOneManagerById(String id);

    @Delete("DELETE FROM user WHERE user_id =#{id}")
    void deleteStaffById(String id);

    @Select("SELECT * FROM user WHERE user_type = 2 " +
            "and user_id = #{id}")
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
    public List<Map<String ,Object>> getManagerById(String id);

    @Select("SELECT * FROM user WHERE user_type = 2 " +
            "and name = #{name}")
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
    public List<Map<String ,Object>> getManagerByName(String Name);
}
