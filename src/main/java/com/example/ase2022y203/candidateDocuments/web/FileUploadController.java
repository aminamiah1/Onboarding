package com.example.ase2022y203.candidateDocuments.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.probeContentType;
import static java.nio.file.Files.write;


@Controller
@SessionAttributes({"passportFile, IDFile"})
public class FileUploadController {
    //Directory where the candidate documents are stored
    @Value("${candidate.documents.directory}")
    private String candidateDocumentPathSuffix;

    @GetMapping("candidateDocuments/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        String homePath = "/resources";
        String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
        Path path = Paths.get(candidateDocumentPath + fileName);

        try{
            System.out.println("File path to file:" + path.toAbsolutePath().toString());
            String type = probeContentType(path);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(type))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.toAbsolutePath().toString()
                    + "\"")
                    .body(new ByteArrayResource(Files.readAllBytes(path)));
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/uploadID")
    public String saveID(@RequestParam("idFile") MultipartFile idFile, Model model){
        String homePath = System.getProperty("user.dir");
        String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
        File documentDirectory = new File(candidateDocumentPath);

        if(!documentDirectory.exists()){
            documentDirectory.mkdir();
        }

        byte[] bytes = new byte[0];
        try{
            bytes = idFile.getBytes();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        Path path = Paths.get(candidateDocumentPath + idFile.getOriginalFilename());
        System.out.println("Writing file: " + candidateDocumentPath + idFile.getOriginalFilename());
        try{
            write(path, bytes);
            model.addAttribute("lastFile", idFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/candidate/document-portal";
    }

}
