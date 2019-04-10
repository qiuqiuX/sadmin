package me.iqiuqiu.util;

import me.iqiuqiu.config.ServerCustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class UploadUtil {

    private static ServerCustomConfig serverCustomConfig;

    @Autowired
    public void setServerCustomConfig(ServerCustomConfig serverCustomConfig) {
        UploadUtil.serverCustomConfig = serverCustomConfig;
    }

    public static String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String fileName = UUID.randomUUID().toString() + suffixName;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        String savePath = "static/" + simpleDateFormat.format(new Date());
        String filePath = savePath + File.separator + fileName;
        String realPath = serverCustomConfig.getResourceSavePath()  + filePath;
        File realPathFile = new File(realPath);
        if (!realPathFile.getParentFile().exists()) {
            realPathFile.getParentFile().mkdirs();
        }

        file.transferTo(realPathFile);

        return filePath;
    }

}
