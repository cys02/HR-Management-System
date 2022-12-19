package com.neo.web.DepartmentController;

import com.neo.mapper.ManagerMapper;
import com.neo.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AddDepartmentController {
    @Autowired
    private ManagerMapper managerMapper;

    @RequestMapping("/judgeManagerId")
    public Boolean judgeId(@RequestBody Map<String,Object> myData){
        List<Map<String,Object>> userList = managerMapper.getManager();
        for(Map<String, Object> i :userList){
            if(i.get("id").toString().equals(myData.get("id").toString())){
                return false;
            }
        }
        return true;
    }
}
