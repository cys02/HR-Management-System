package com.neo.web.StaffViewsController;

import com.neo.mapper.StaffMapper;
import com.neo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class StaffController {

    @Autowired
    private StaffMapper staffMapper;

    @RequestMapping("/clearUserSalary")
    public void clearUserSalary(){
        staffMapper.clearUserSalary();
    }

    @RequestMapping("/getStaff")
    public List<Map<String,Object>> getStaff(){return staffMapper.getStaff();}

    @RequestMapping("/addStaff")
    public void addStaff(@RequestBody Map<String, Object> myData) {

        String id = myData.get("id").toString();
        String password  = myData.get("password").toString();
        String name = myData.get("name").toString();
        Boolean sex = Objects.equals(myData.get("sex").toString(), "1");
        String departmentName  = myData.get("departmentName").toString();
        String telephone = myData.get("telephone").toString();
        String email = myData.get("email").toString();
        String address = myData.get("address").toString();
        Integer baseSalary = Integer.parseInt(myData.get("baseSalary").toString());

        staffMapper.addStaff(id,password,name,sex,departmentName,telephone,email,address,baseSalary);
    }

    @RequestMapping("/updateStaff")
    public void updateStaff(@RequestBody Map<String, Object> myData) {

        String id = myData.get("id").toString();
        String name = myData.get("name").toString();
        String departmentName  = myData.get("departmentName").toString();
        String telephone = myData.get("telephone").toString();
        String email = myData.get("email").toString();
        String address = myData.get("address").toString();
        Integer baseSalary = Integer.parseInt(myData.get("baseSalary").toString());

        staffMapper.updateStaff(id,name,departmentName,telephone,email,address,baseSalary);
    }

    @RequestMapping("/updateStaffRecord")
    public void updateStaffRecord(@RequestBody Map<String, Object> myData) {
        String id = myData.get("id").toString();
        Integer lateNumber = Integer.parseInt(myData.get("lateNumber").toString());
        Integer bunkNumber = Integer.parseInt(myData.get("bunkNumber").toString());
        Integer offNumber = Integer.parseInt(myData.get("offNumber").toString());
        Integer overNumber = Integer.parseInt(myData.get("overNumber").toString());
        Integer evecNumber = Integer.parseInt(myData.get("evecNumber").toString());
        staffMapper.updateStaffRecord(id,lateNumber,bunkNumber,offNumber,overNumber,evecNumber);
    }

    @RequestMapping("/getOneStaffById")
    public Map<String,Object> getOneStaffById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        return staffMapper.getOneById(id);
    }

    @RequestMapping("/getOneStaffSalaryById")
    public Map<String,Object> getOneStaffSalaryById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        Map<String,Object> staffSalary = new HashMap<>();
        Map<String,Object> oneStaff = staffMapper.getOneById(id);
        staffSalary.put("baseSalary",oneStaff.get("baseSalary"));
        int reward = Integer.parseInt(oneStaff.get("overNumber").toString())*200+Integer.parseInt(oneStaff.get("evecNumber").toString())*200;
        staffSalary.put("reward",reward);
        int punish = Integer.parseInt(oneStaff.get("lateNumber").toString())*200+Integer.parseInt(oneStaff.get("bunkNumber").toString())*200+Integer.parseInt(oneStaff.get("offNumber").toString())*200;
        staffSalary.put("punish",punish);
        float finalSalary = Float.parseFloat(staffSalary.get("baseSalary").toString())+reward-punish;
        staffSalary.put("finalSalary",finalSalary);
        return staffSalary;
    }

    @RequestMapping("/getStaffSalary")
    public List<Map<String ,Object>> getStaffSalary(){
        List<Map<String,Object>> staffSalary = new ArrayList<>();
        List<Map<String,Object>> staffList = staffMapper.getStaff();

        for(Map<String,Object> i:staffList){
            Map<String,Object> oneStaffSalary = new HashMap<>();
            oneStaffSalary.put("id",i.get("id").toString());
            oneStaffSalary.put("name",i.get("name").toString());
            oneStaffSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneStaffSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneStaffSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneStaffSalary.get("baseSalary").toString())+reward-punish;
            oneStaffSalary.put("finalSalary",finalSalary);

            staffSalary.add(oneStaffSalary);
        }
        return staffSalary;
    }

    @RequestMapping("/changeOneStaffById")
    public void changeOneStaffById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        staffMapper.changeOneStaffById(id);
    }

    @RequestMapping("/deleteStaffById")
    public void deleteStaffById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        staffMapper.deleteStaffById(id);
    }

    @RequestMapping("/getStaffById")
    public List<Map<String,Object>> getStaffById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        return staffMapper.getStaffById(id);
    }

    @RequestMapping("/getStaffByName")
    public List<Map<String,Object>> getStaffByName(@RequestBody Map<String, Object> myData){
        String name = myData.get("name").toString();
        return staffMapper.getStaffByName(name);
    }

    @RequestMapping("/getStaffByDepartmentName")
    public List<Map<String,Object>> getStaffByDepartmentName(@RequestBody Map<String, Object> myData){
        String departmentName = myData.get("departmentName").toString();
        return staffMapper.getStaffByDepartmentName(departmentName);
    }

    @RequestMapping("/getStaffSalaryById")
    public List<Map<String ,Object>> getStaffSalaryById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        List<Map<String,Object>> staffSalary = new ArrayList<>();
        List<Map<String,Object>> staffList = staffMapper.getStaffById(id);

        for(Map<String,Object> i:staffList){
            Map<String,Object> oneStaffSalary = new HashMap<>();
            oneStaffSalary.put("id",i.get("id").toString());
            oneStaffSalary.put("name",i.get("name").toString());
            oneStaffSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneStaffSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneStaffSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneStaffSalary.get("baseSalary").toString())+reward-punish;
            oneStaffSalary.put("finalSalary",finalSalary);

            staffSalary.add(oneStaffSalary);
        }
        return staffSalary;
    }

    @RequestMapping("/getStaffSalaryByName")
    public List<Map<String ,Object>> getStaffSalaryByName(@RequestBody Map<String, Object> myData){
        String name = myData.get("name").toString();
        List<Map<String,Object>> staffSalary = new ArrayList<>();
        List<Map<String,Object>> staffList = staffMapper.getStaffByName(name);

        for(Map<String,Object> i:staffList){
            Map<String,Object> oneStaffSalary = new HashMap<>();
            oneStaffSalary.put("id",i.get("id").toString());
            oneStaffSalary.put("name",i.get("name").toString());
            oneStaffSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneStaffSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneStaffSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneStaffSalary.get("baseSalary").toString())+reward-punish;
            oneStaffSalary.put("finalSalary",finalSalary);

            staffSalary.add(oneStaffSalary);
        }
        return staffSalary;
    }

    @RequestMapping("/getStaffSalaryByDepartmentName")
    public List<Map<String ,Object>> getStaffSalaryByDepartmentName(@RequestBody Map<String, Object> myData){
        String departmentName = myData.get("departmentName").toString();
        List<Map<String,Object>> staffSalary = new ArrayList<>();
        List<Map<String,Object>> staffList = staffMapper.getStaffByDepartmentName(departmentName);

        for(Map<String,Object> i:staffList){
            Map<String,Object> oneStaffSalary = new HashMap<>();
            oneStaffSalary.put("id",i.get("id").toString());
            oneStaffSalary.put("name",i.get("name").toString());
            oneStaffSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneStaffSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneStaffSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneStaffSalary.get("baseSalary").toString())+reward-punish;
            oneStaffSalary.put("finalSalary",finalSalary);

            staffSalary.add(oneStaffSalary);
        }
        return staffSalary;
    }
}
