package com.neo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE id = #{id}")
    public Map<String, Object> getOneById(String id);

    @Select("SELECT * FROM admin")
    public List<Map<String,Object>> getAdmin();

    @Insert("INSERT INTO admin(id,password) VALUE(#{id},#{password})")
    public void addAdmin(String id,String password);
}
