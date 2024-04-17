package com.app.imageconverter.api;

import com.app.imageconverter.utils.ImageUtil;
import java.io.File;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileApi {

    @PostMapping(value="/convert")
    public String convert(@RequestBody Map<String, String> fileMap) throws Exception {
        File file = ImageUtil.base64ToFile(fileMap.get("copyName"), fileMap.get("fileName"));

		return file.getName();
	}


}
