package com.neo.web.test;

import com.neo.mapper.AdminMapper;
import com.neo.mapper.DepartmentMapper;
import com.neo.mapper.ManagerMapper;
import com.neo.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @RequestMapping("/adminLogin")
    public boolean adminLogin(@RequestBody Map<String, Object> myData){
        Map<String,Object> rightAdmin =adminMapper.getOneById(myData.get("id").toString());
        if(rightAdmin == null){
            return false;
        }
        String rightPassword = rightAdmin.get("password").toString();
        return rightPassword.equals(myData.get("password").toString());
    }

    @RequestMapping("/managerLogin")
    public boolean managerLogin(@RequestBody Map<String, Object> myData){
        Map<String,Object> rightManager =managerMapper.getOneById(myData.get("id").toString());
        if(rightManager == null){
            return false;
        }
        String rightPassword = rightManager.get("password").toString();
        return rightPassword.equals(myData.get("password").toString());
    }

    @RequestMapping("/staffLogin")
    public boolean staffLogin(@RequestBody Map<String, Object> myData){
        Map<String,Object> rightStaff =staffMapper.getOneById(myData.get("id").toString());
        if(rightStaff == null){
            return false;
        }
        String rightPassword = rightStaff.get("password").toString();
        return rightPassword.equals(myData.get("password").toString());
    }
}
