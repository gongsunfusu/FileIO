package com.gongsunfusu.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class indexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/FileUpLoad")
    public String FileUpLoad(@RequestParam("file") MultipartFile file, Model model, String fileNames){
        if(file == null){
            model.addAttribute("tips", "文件为空");
            return "index";
        }
        try{
            //将文件上传到指定位置
            String path = "D://0_dtd/";
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //创建File
            File toFile = new File(path + fileName);
            //判断父目录是否存在
            if (toFile.getParentFile().exists()){
                //不存在就创建一个父目录
                toFile.getParentFile().mkdir();
            }
            file.transferTo(toFile);
            model.addAttribute("tips", "上传文件成功！");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("tips", "上传文件失败！");
        }
        return "index";
    }
}
