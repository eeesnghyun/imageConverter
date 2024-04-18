package com.app.imageconverter.api;

import com.app.imageconverter.utils.ImageUtil;
import java.io.File;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class FileApi {

    @PostMapping(value="/convert/base64")
    public String convertBase64(@RequestBody Map<String, String> fileMap) throws Exception {
        File file = ImageUtil.base64ToFile(fileMap.get("copyName"), fileMap.get("fileName"));

		return file.getName();
	}

    @PostMapping(value="/convert/file")
    public long convertFile(@RequestBody String fileName) throws Exception {
        MultipartFile multipartFile = ImageUtil.fileToMultipartFile(fileName);

		return multipartFile.getSize();
	}


}
