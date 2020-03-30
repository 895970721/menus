package com.crf.menu.utils;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {
    /**
     * 文件移动工具
     * @param file    被移动的文件
     * @param Path    移动后的路径
     * @return
     */
    public static String MupFileMoveTo(MultipartFile file ,String Path){
        String uuid = UUID.randomUUID().toString();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));    // 取得后缀
        String filePath = Path + uuid +suffix;
        File image = new File(filePath);
        if(image.getParentFile().exists())
        {
            image.getParentFile().mkdir();
        }
        try
        {
            file.transferTo(image);
        }
        catch (IOException e)
        {
            throw new UserException(StatusCode.UploadImageError);
        }
        return uuid +suffix;
    }
}
