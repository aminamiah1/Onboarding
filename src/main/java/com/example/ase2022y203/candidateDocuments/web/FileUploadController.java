package com.example.ase2022y203.candidateDocuments.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.probeContentType;


@Controller
@SessionAttributes({"passportFile, IDFile"})
public class FileUploadController {
    //Directory where the candidate documents are stored
    @Value("${candidate.documents.directory}")
    private String candidateDocumentPathSuffix;

    @GetMapping("{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        String homePath = System.getProperty("user.home");
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

}
