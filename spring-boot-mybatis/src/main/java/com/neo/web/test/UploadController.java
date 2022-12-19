package com.neo.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})

@RestController
public class UploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    @RequestMapping("/upload")
    public Map<String,Object> fileupload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();

        String originName = file.getOriginalFilename();
        if(!originName.endsWith(".pdf")){
            result.put("status","error");
            result.put("msg","文件类型不对");
            return result;
        }

        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName = UUID.randomUUID().toString() + ".pdf";
        try {
            file.transferTo(new File(folder, newName));
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + format + newName;
            result.put("status", "success");
            result.put("url", url);
        } catch (IOException e) {
            result.put("status", "ERROR");
            result.put("msg", e.getMessage());
        }
        System.out.println(result);
        return result;
    }
}
