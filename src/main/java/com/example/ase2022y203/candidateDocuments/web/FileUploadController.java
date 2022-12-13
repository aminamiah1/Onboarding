package com.example.ase2022y203.candidateDocuments.web;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.Files.probeContentType;
import static java.nio.file.Files.write;


@Controller
@SessionAttributes({"passportFile, IDFile"})
public class FileUploadController {

    private final CandidateService candidateService;

    public FileUploadController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    //Directory where the candidate documents are stored
    @Value("${candidate.documents.directory}")
    private String candidateDocumentPathSuffix;

    @GetMapping("candidateDocuments/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        String homePath = System.getProperty("user.dir");
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

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

        Path path = Paths.get(candidateDocumentPath + "ID_" + "C" + candidate.get().getId().toString() + ".jpg");
        System.out.println("Writing file: " + candidateDocumentPath + idFile.getOriginalFilename());
        try{
            write(path, bytes);
            model.addAttribute("lastIDFile", idFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/candidate/document-portal";
    }

    @PostMapping("/uploadPassport")
    public String savePassport(@RequestParam("passportFile") MultipartFile passportFile, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        String homePath = System.getProperty("user.dir");
        String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
        File documentDirectory = new File(candidateDocumentPath);

        if(!documentDirectory.exists()){
            documentDirectory.mkdir();
        }

        byte[] bytes = new byte[0];
        try{
            bytes = passportFile.getBytes();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        Path path = Paths.get(candidateDocumentPath + "PP_" + "C" + candidate.get().getId().toString() + ".jpg");
        System.out.println("Writing file: " + candidateDocumentPath + passportFile.getOriginalFilename());
        try{
            write(path, bytes);
            model.addAttribute("lastPassportFile", passportFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/candidate/document-portal";
    }

}
