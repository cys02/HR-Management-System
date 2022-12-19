package com.neo.web.test;

import com.neo.mapper.MailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MailController {
    @Autowired
    private MailMapper mailMapper;


    @RequestMapping("/getMail")
    public List<Map<String,Object>> getStaff(){return mailMapper.getMail();}

    @RequestMapping("/addMail")
    public void addStaff(@RequestBody Map<String, Object> myData) {
        String title = myData.get("title").toString();
        String article  = myData.get("article").toString();

        mailMapper.addMail(title,article);
    }

    @RequestMapping("/getDocument")
    public List<Map<String,Object>> getDocument(){
        List<Map<String,Object>> returnData = new ArrayList<>();
        List<Map<String,Object>>documentList = mailMapper.getDocument();
        for(Map<String,Object> i:documentList){
            Map<String,Object> data = new HashMap<>();
            data.put("name","文件名称");
            data.put("url",i.get("url").toString());

            returnData.add(data);
        }
        return returnData;
    }

    @RequestMapping("/addDocument")
    public void addDocument(@RequestBody Map<String, Object> myData){
        String url = myData.get("url").toString();
        mailMapper.addDocument(url);
    }
}
