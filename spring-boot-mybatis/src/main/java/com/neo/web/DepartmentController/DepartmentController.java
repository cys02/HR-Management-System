package com.neo.web.DepartmentController;

import com.neo.mapper.DepartmentMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentMapper departmentMapper;

//    @RequestMapping("/getDepartment")
//    public List<Map<String,Object>> getDepartment(){return departmentMapper.getDepartment();}

    @RequestMapping("/getDepartmentInfo")
    public List<Map<String,Object>> getDepartmentInfo(){
        return departmentMapper.getDepartmentInfo();
    }

    @RequestMapping("/getOneDepartmentByName")
    public Map<String,Object> getOneDepartmentByName(@RequestBody Map<String, Object> myData){
        String departmentName = myData.get("departmentName").toString();
        return departmentMapper.getOneDepartmentByName(departmentName);
    }

    @RequestMapping("/updateDepartment")
    public void updateDepartment(@RequestBody Map<String,Object> myData){
        String departmentName = myData.get("departmentName").toString();
        String id = myData.get("id").toString();
        departmentMapper.updateDepartment(departmentName,id);
    }

    @RequestMapping("/addDepartment")
    public void addDepartment(@RequestBody Map<String,Object> myData){
        String departmentName = myData.get("departmentName").toString();
        String id = myData.get("id").toString();
        departmentMapper.addDepartment(departmentName,id);
    }

    @RequestMapping("/judgeManagerIdInDepartment")
    public Boolean judgeManagerIdInDepartment(@RequestBody Map<String,Object> myData){
        List<Map<String,Object>> departmentList = departmentMapper.getDepartment();
        for(Map<String,Object> i:departmentList){
            if(i.get("id").toString().equals(myData.get("id").toString())){
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/deleteDepartmentByDepartmentName")
    public void deleteDepartmentByDepartmentName(@RequestBody Map<String, Object> myData){
        String departmentName = myData.get("departmentName").toString();
        departmentMapper.deleteDepartmentByDepartmentName(departmentName);
    }

    @RequestMapping("/getDepartmentInfoByDepartmentName")
    public List<Map<String,Object>> getDepartmentInfoByDepartmentName(@RequestBody Map<String, Object> myData){
        String departmentName = myData.get("departmentName").toString();
        return departmentMapper.getDepartmentInfoByDepartmentName(departmentName);
    }

    @RequestMapping("/getDepartmentInfoByManagerName")
    public List<Map<String,Object>> getDepartmentInfoByManagerName(@RequestBody Map<String, Object> myData){
        String name = myData.get("name").toString();
        return departmentMapper.getDepartmentInfoByManagerName(name);
    }

    @RequestMapping("/getDepartmentNameById")
    public List<Map<String,Object>> getDepartmentNameById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        List<Map<String,Object>> returnData = new ArrayList<>();
        List<Map<String,Object>> departmentList = departmentMapper.getDepartment();
        for(Map<String,Object> i:departmentList){
            if(i.get("id").toString().equals(id)){
                returnData.add(i);
            }
        }
        return returnData;
    }


}
