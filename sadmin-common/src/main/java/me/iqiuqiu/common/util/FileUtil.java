package me.iqiuqiu.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FileUtil {


    public static String getFileUrl(String filePath) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        StringBuffer requestURL = request.getRequestURL();
        String host = requestURL.substring(0, requestURL.indexOf(request.getServletPath()));

        return host + "/" + filePath;
    }

}
