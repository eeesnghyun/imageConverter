package com.app.imageconverter.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Base64;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageUtil {

    public static File base64ToFile(String copyName, String base64) throws MimeTypeException {
        int colon = base64.indexOf(":");
        int semicolon = base64.indexOf(";");
        String mimeType = base64.substring(colon + 1, semicolon);
        String base64WithoutHeader = base64.substring(semicolon + 8);
        String extension = MimeTypes.getDefaultMimeTypes().forName(mimeType).getExtension();

        byte[] bytes = Base64.getDecoder().decode(base64WithoutHeader);
        copyName = copyName + extension;
        File file = new File("C:/example/" + copyName);

        // 파일 변환
        try (OutputStream outputStream = new BufferedOutputStream((new FileOutputStream(file)))) {
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    public static MultipartFile fileToMultipartFile(String fileName) throws IOException {
        MultipartFile multipartFile = null;
        File file = new File("C:/example/" + fileName);
        DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length() , file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);

            multipartFile = new CommonsMultipartFile(fileItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return multipartFile;
    }
}