package com.neo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MailMapper {
    @Select("SELECT * FROM message")
    public List<Map<String,Object>> getMail();

    @Insert("INSERT INTO message(title,article) VALUE(#{title},#{article})")
    public void addMail(String title,String article);

    @Select("SELECT * FROM document")
    public List<Map<String,Object>> getDocument();

    @Insert("INSERT INTO document(url) VALUE(#{url})")
    public void addDocument(String url);


}


