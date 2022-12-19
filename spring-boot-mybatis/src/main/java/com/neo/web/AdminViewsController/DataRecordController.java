package com.neo.web.AdminViewsController;

import com.neo.mapper.AdminMapper;
import com.neo.mapper.DepartmentMapper;
import com.neo.mapper.ManagerMapper;
import com.neo.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataRecordController {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping("/getStaffCountGroupByDepartment")
    public Map<String ,Integer> getStaffCountGroupByDepartment(){
        Map<String, Integer> returnData = new HashMap<>();
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        int int4 = 0;
        int int5 = 0;

        List<Map<String,Object>> departmentList = departmentMapper.getDepartmentInfo();
        for(Map<String,Object> i:departmentList){
            int num = Integer.parseInt(i.get("staffCount").toString());
            if(num==0)int1++;
            else if(num==1)int2++;
            else if(num==2)int3++;
            else if(num==3)int4++;
            else int5++;
        }
        returnData.put("int1",int1);
        returnData.put("int2",int2);
        returnData.put("int3",int3);
        returnData.put("int4",int4);
        returnData.put("int5",int5);
        return returnData;
    }

    @RequestMapping("/getUserCount")
    public Map<String,Integer> getUserCount(){
        Map<String, Integer> returnData = new HashMap<>();
        List<Map<String,Object>> adminList = adminMapper.getAdmin();
        List<Map<String,Object>> managerList = managerMapper.getManager();
        List<Map<String,Object>> staffList = staffMapper.getStaff();
        int adminCount = adminList.size();
        int managerCount = managerList.size();
        int staffCount = staffList.size();

        returnData.put("adminCount",adminCount);
        returnData.put("managerCount",managerCount);
        returnData.put("staffCount",staffCount);
        return returnData;
    }

    @RequestMapping("/getStaffSalaryCount")
    public Map<String,Integer> getStaffSalaryCount(){
        Map<String, Integer> returnData = new HashMap<>();
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        int int4 = 0;
        int int5 = 0;

        List<Map<String,Object>> staffList = staffMapper.getStaff();
        for(Map<String,Object> i:staffList){
            float num = Float.parseFloat(i.get("baseSalary").toString());
            if(num<6000)int1++;
            else if(num<7000)int2++;
            else if(num<8000)int3++;
            else if(num<9000)int4++;
            else int5++;
        }
        returnData.put("int1",int1);
        returnData.put("int2",int2);
        returnData.put("int3",int3);
        returnData.put("int4",int4);
        returnData.put("int5",int5);
        return returnData;
    }

    @RequestMapping("/getStaffManagerCount")
    public Map<String,Integer> getStaffManagerCount(){
        Map<String, Integer> returnData = new HashMap<>();
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        int int4 = 0;
        int int5 = 0;

        List<Map<String,Object>> managerList = managerMapper.getManager();
        for(Map<String,Object> i:managerList){
            float num = Float.parseFloat(i.get("baseSalary").toString());
            if(num<9000)int1++;
            else if(num<10000)int2++;
            else if(num<11000)int3++;
            else if(num<12000)int4++;
            else int5++;
        }
        returnData.put("int1",int1);
        returnData.put("int2",int2);
        returnData.put("int3",int3);
        returnData.put("int4",int4);
        returnData.put("int5",int5);
        return returnData;
    }
}
