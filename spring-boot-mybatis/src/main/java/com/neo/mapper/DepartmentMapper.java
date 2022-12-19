package com.neo.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {

    @Select("select * from department")
    @Results({
            @Result(property = "departmentName", column = "name"),
            @Result(property = "id", column = "manager_user_id")
    })
    public List<Map<String,Object>>getDepartment();


    @Select("select department.name department_name, user_a.name manager_name, count(user_b.user_id) staff_count\n" +
            "from department left join user user_b on department.name = user_b.department_name, user user_a\n" +
            "where department.manager_user_id = user_a.user_id\n" +
            "and user_a.user_type = 2\n" +
            "and (user_b.user_type = 1 or user_b.user_type is null)\n" +
            "group by department.name")
    @Results({
            @Result(property = "departmentName", column = "department_name"),
            @Result(property = "managerName", column = "manager_name"),
            @Result(property = "staffCount", column = "staff_count")
    })
    public List<Map<String,Object> >getDepartmentInfo();


    @Select("select * from department where name=#{departmentName}")
    @Results({
            @Result(property = "departmentName", column = "name"),
            @Result(property = "id", column = "manager_user_id")
    })
    public Map<String,Object>getOneDepartmentByName(String departmentName);

    @Insert("INSERT INTO department(name,manager_user_id)" +
            "VALUES(#{departName},#{id})")
    void addDepartment(String departName,String id);

    @Update("update department set manager_user_id=#{id} where name=#{departmentName}")
    void updateDepartment(String departmentName,String id);

    @Delete("DELETE FROM department WHERE name =#{departmentName}")
    void deleteDepartmentByDepartmentName(String departmentName);


    @Select("select department.name department_name, user_a.name manager_name, count(user_b.user_id) staff_count\n" +
            "from department left join user user_b on department.name = user_b.department_name, user user_a\n" +
            "where department.manager_user_id = user_a.user_id\n" +
            "and user_a.user_type = 2\n" +
            "and (user_b.user_type = 1 or user_b.user_type is null)\n" +
            "and department.name=#{departmentName}\n" +
            "group by department.name")
    @Results({
            @Result(property = "departmentName", column = "department_name"),
            @Result(property = "managerName", column = "manager_name"),
            @Result(property = "staffCount", column = "staff_count")
    })
    public List<Map<String,Object> >getDepartmentInfoByDepartmentName(String departmentName);

    @Select("select department.name department_name, user_a.name manager_name, count(user_b.user_id) staff_count\n" +
            "from department left join user user_b on department.name = user_b.department_name, user user_a\n" +
            "where department.manager_user_id = user_a.user_id\n" +
            "and user_a.user_type = 2\n" +
            "and (user_b.user_type = 1 or user_b.user_type is null)\n" +
            "and user_a.name=#{name}\n" +
            "group by department.name")
    @Results({
            @Result(property = "departmentName", column = "department_name"),
            @Result(property = "managerName", column = "manager_name"),
            @Result(property = "staffCount", column = "staff_count")
    })
    public List<Map<String,Object> >getDepartmentInfoByManagerName(String name);



}
