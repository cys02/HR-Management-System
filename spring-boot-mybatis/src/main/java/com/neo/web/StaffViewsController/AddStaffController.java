package com.neo.web.StaffViewsController;

import com.neo.mapper.DepartmentMapper;
import com.neo.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AddStaffController {
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @RequestMapping("/judgeId")
    public Boolean judgeId(@RequestBody Map<String,Object> myData){
        List<Map<String,Object>> userList = staffMapper.getUser();
        for(Map<String, Object> i :userList){
            if(i.get("id").toString().equals(myData.get("id").toString())){
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/judgeDepartmentName")
    public Boolean judgeDepartmentName(@RequestBody Map<String,Object> myData){
        List<Map<String,Object>> departmentList = departmentMapper.getDepartment();
        for(Map<String, Object> i :departmentList){
            if(i.get("departmentName").toString().equals(myData.get("departmentName").toString())){
                return true;
            }
        }
        return false;
    }
}
