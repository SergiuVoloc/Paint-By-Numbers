package com.ecommerce.ecommerce.modules.fileStorage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("file")
public class FileStorageGuestController {
    
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/{id}")
    public InputStreamResource FileSystemResource (@PathVariable(value = "id") UUID id,HttpServletResponse response) throws IOException {
        FileStorage f = fileStorageService.read(id);
        response.setContentType(f.getMime_type());
        response.setHeader("Content-Disposition", "attachment; filename=\""+f.getId()+ "." + f.getExtension() +"\"");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(f.getPath()));
        return resource;
    }

}
