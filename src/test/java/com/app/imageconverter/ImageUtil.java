package com.app.imageconverter;

import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

@Slf4j
public class ImageUtil {

    public static MultipartFile base64ToMultipartFile(String name, String base64) {
        int colon = base64.indexOf(":");
        int semicolon = base64.indexOf(";");
        String mimeType = base64.substring(colon + 1, semicolon);
        String base64WithoutHeader = base64.substring(semicolon + 8);

        byte[] bytes = Base64.getDecoder().decode(base64WithoutHeader);

        String extension = ".jpg";

        try {
            extension = MimeTypes.getDefaultMimeTypes().forName(mimeType).getExtension();
        } catch (MimeTypeException e) {
            log.error("Can't get extension from mimeType [{}]", mimeType);
        }

        String filename = name + extension;

        return new MockMultipartFile(filename, filename, mimeType, bytes);
    }
}