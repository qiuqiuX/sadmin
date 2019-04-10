package me.iqiuqiu.controller;

import me.iqiuqiu.common.util.BaseResponse;
import me.iqiuqiu.common.util.FileUtil;
import me.iqiuqiu.util.UploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class CommonController {


    @PostMapping("/upload/images")
    public BaseResponse uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = UploadUtil.upload(file);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("path", filePath);
        hashMap.put("url", FileUtil.getFileUrl(filePath));

        return BaseResponse.success(hashMap);
    }

}
