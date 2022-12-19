package com.neo.web.ManagerViewsController;

import com.neo.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;

    @RequestMapping("/getManager")
    public List<Map<String,Object>> getManager(){return managerMapper.getManager();}

    @RequestMapping("/addManager")
    public void addManager(@RequestBody Map<String,Object> myData){
        String id = myData.get("id").toString();
        String password  = myData.get("password").toString();
        String name = myData.get("name").toString();
        Boolean sex = Objects.equals(myData.get("sex").toString(), "1");
        String telephone = myData.get("telephone").toString();
        String email = myData.get("email").toString();
        String address = myData.get("address").toString();
        Integer baseSalary = Integer.parseInt(myData.get("baseSalary").toString());

        managerMapper.addManager(id,password,name,sex,telephone,email,address,baseSalary);
    }

    @RequestMapping("/updateManager")
    public void updateManager(@RequestBody Map<String, Object> myData) {

        String id = myData.get("id").toString();
        String name = myData.get("name").toString();
        String telephone = myData.get("telephone").toString();
        String email = myData.get("email").toString();
        String address = myData.get("address").toString();
        Integer baseSalary = Integer.parseInt(myData.get("baseSalary").toString());

        managerMapper.updateManager(id,name,telephone,email,address,baseSalary);
    }

    @RequestMapping("/updateManagerRecord")
    public void updateManagerRecord(@RequestBody Map<String, Object> myData) {
        String id = myData.get("id").toString();
        Integer lateNumber = Integer.parseInt(myData.get("lateNumber").toString());
        Integer bunkNumber = Integer.parseInt(myData.get("bunkNumber").toString());
        Integer offNumber = Integer.parseInt(myData.get("offNumber").toString());
        Integer overNumber = Integer.parseInt(myData.get("overNumber").toString());
        Integer evecNumber = Integer.parseInt(myData.get("evecNumber").toString());
        managerMapper.updateManagerRecord(id,lateNumber,bunkNumber,offNumber,overNumber,evecNumber);
    }

    @RequestMapping("/getOneManagerById")
    public Map<String,Object>getOneManagerById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        return managerMapper.getOneById(id);
    }

    @RequestMapping("/getOneManagerSalaryById")
    public Map<String,Object> getOneManagerSalaryById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        Map<String,Object> managerSalary = new HashMap<>();
        Map<String,Object> oneManager = managerMapper.getOneById(id);
        managerSalary.put("baseSalary",oneManager.get("baseSalary"));
        int reward = Integer.parseInt(oneManager.get("overNumber").toString())*200+Integer.parseInt(oneManager.get("evecNumber").toString())*200;
        managerSalary.put("reward",reward);
        int punish = Integer.parseInt(oneManager.get("lateNumber").toString())*200+Integer.parseInt(oneManager.get("bunkNumber").toString())*200+Integer.parseInt(oneManager.get("offNumber").toString())*200;
        managerSalary.put("punish",punish);
        float finalSalary = Float.parseFloat(managerSalary.get("baseSalary").toString())+reward-punish;
        managerSalary.put("finalSalary",finalSalary);
        return managerSalary;
    }

    @RequestMapping("/getManagerSalary")
    public List<Map<String ,Object>> getManagerSalary(){
        List<Map<String,Object>> managerSalary = new ArrayList<>();
        List<Map<String,Object>> managerList = managerMapper.getManager();

        for(Map<String,Object> i:managerList){
            Map<String,Object> oneManagerSalary = new HashMap<>();
            oneManagerSalary.put("id",i.get("id").toString());
            oneManagerSalary.put("name",i.get("name").toString());
            oneManagerSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneManagerSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneManagerSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneManagerSalary.get("baseSalary").toString())+reward-punish;
            oneManagerSalary.put("finalSalary",finalSalary);

            managerSalary.add(oneManagerSalary);
        }
        return managerSalary;
    }

    @RequestMapping("/changeOneManagerById")
    public void changeOneManagerById(@RequestBody Map<String,Object> myData){
        String id = myData.get("id").toString();
        managerMapper.changeOneManagerById(id);
    }

    @RequestMapping("/deleteManagerById")
    public void deleteManagerById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        managerMapper.deleteStaffById(id);
    }

    @RequestMapping("/getManagerById")
    public List<Map<String,Object>> getManagerById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        return managerMapper.getManagerById(id);
    }

    @RequestMapping("/getManagerByName")
    public List<Map<String,Object>> getManagerByName(@RequestBody Map<String, Object> myData){
        String name = myData.get("name").toString();
        return managerMapper.getManagerByName(name);
    }

    @RequestMapping("/getManagerSalaryById")
    public List<Map<String ,Object>> getManagerSalaryById(@RequestBody Map<String, Object> myData){
        String id = myData.get("id").toString();
        List<Map<String,Object>> managerSalary = new ArrayList<>();
        List<Map<String,Object>> managerList = managerMapper.getManagerById(id);

        for(Map<String,Object> i:managerList){
            Map<String,Object> oneManagerSalary = new HashMap<>();
            oneManagerSalary.put("id",i.get("id").toString());
            oneManagerSalary.put("name",i.get("name").toString());
            oneManagerSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneManagerSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneManagerSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneManagerSalary.get("baseSalary").toString())+reward-punish;
            oneManagerSalary.put("finalSalary",finalSalary);

            managerSalary.add(oneManagerSalary);
        }
        return managerSalary;
    }

    @RequestMapping("/getManagerSalaryByName")
    public List<Map<String ,Object>> getManagerSalaryByName(@RequestBody Map<String, Object> myData){
        String name = myData.get("name").toString();
        List<Map<String,Object>> managerSalary = new ArrayList<>();
        List<Map<String,Object>> managerList = managerMapper.getManagerByName(name);

        for(Map<String,Object> i:managerList){
            Map<String,Object> oneManagerSalary = new HashMap<>();
            oneManagerSalary.put("id",i.get("id").toString());
            oneManagerSalary.put("name",i.get("name").toString());
            oneManagerSalary.put("baseSalary",i.get("baseSalary"));
            int reward = Integer.parseInt(i.get("overNumber").toString())*200+Integer.parseInt(i.get("evecNumber").toString())*200;
            oneManagerSalary.put("reward",reward);
            int punish = Integer.parseInt(i.get("lateNumber").toString())*200+Integer.parseInt(i.get("bunkNumber").toString())*200+Integer.parseInt(i.get("offNumber").toString())*200;
            oneManagerSalary.put("punish",punish);
            float finalSalary = Float.parseFloat(oneManagerSalary.get("baseSalary").toString())+reward-punish;
            oneManagerSalary.put("finalSalary",finalSalary);

            managerSalary.add(oneManagerSalary);
        }
        return managerSalary;
    }


}
