package com.app.imageconverter.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

@Slf4j
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

        try (OutputStream outputStream = new BufferedOutputStream((new FileOutputStream(file)))) {
            outputStream.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return file;
    }
}