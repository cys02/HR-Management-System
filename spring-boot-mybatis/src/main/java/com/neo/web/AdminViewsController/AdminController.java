package com.neo.web.AdminViewsController;


import com.neo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping("/getOneAdminById")
    public Map<String,Object> getOneAdminById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        return adminMapper.getOneById(id);
    }

    @RequestMapping("/addAdmin")
    public void addAdmin(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        String password = myData.get("password").toString();
        adminMapper.addAdmin(id,password);
    }
}
