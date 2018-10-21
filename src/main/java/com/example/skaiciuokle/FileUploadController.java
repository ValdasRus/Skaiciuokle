package com.example.skaiciuokle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @RequestMapping("/")
    public String uploadingForm() {
        return "mainUploadingForm";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String mainUploadingPost(@RequestParam("uploadFiles") MultipartFile[] uploadedFiles,
                                    FileUploadService fileUploadService,
                                    Model model) {
        fileUploadService.toFile(uploadedFiles);
        model.addAttribute("isNotTxt", fileUploadService.isNotTxt(uploadedFiles));
        return "mainUploadingForm";
    }

}
