package com.zhenmei.wsc.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("upload")
@CrossOrigin
@ResponseBody
public class UploadController {


    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public String test1(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String fileDir = request.getSession().getServletContext().getRealPath("/upload/");
            System.out.println("------->>" + fileDir);
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            File upload_file = new File(fileDir + fileName);
            file.transferTo(upload_file);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }


}
