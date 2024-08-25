package com.mefp.appdeclaration.config;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

@Component
public class utils {

    public static String saveUploadFileToServer(String uplaodStorage, String userStorage,
                                                MultipartFile file) throws Exception {

        try {
            byte[] bytes = file.getBytes();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uplaodStorage + (StringUtils.hasText(userStorage) ? File.separatorChar + userStorage : "" ) + File.separatorChar + fileName);
            File dir = new File(path.toString());
            if (!dir.exists()) {
                dir.getParentFile().mkdirs();
            }
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            //log.error("Failed to write file on server", e);
            throw new Exception("Failed to write file on server " + e.getMessage());
        }

    }

    public static int getCurrentYear() {
        Date  date = new Date();
        Calendar  cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.YEAR);
    }


}
