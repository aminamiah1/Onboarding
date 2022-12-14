package com.example.ase2022y203.candidateDocuments.web;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidateDocuments.domain.Documents;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTO;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTOSave;
import com.example.ase2022y203.candidateDocuments.service.DocumentsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.Files.write;


@Controller
@SessionAttributes({"passportFile, IDFile"})
public class DocumentController {

    private final CandidateService candidateService;
    private final DocumentsService documentsService;

    public DocumentController(CandidateService candidateService, DocumentsService documentsService) {
        this.candidateService = candidateService;
        this.documentsService = documentsService;
    }

    //Directory where the candidate documents are stored
    @Value("${candidate.documents.directory}")
    private String candidateDocumentPathSuffix;

    @PostMapping("/uploadID")
    public String saveID(@RequestParam("idFile") MultipartFile idFile, Model model, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        String homePath = System.getProperty("user.dir");
        String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
        File documentDirectory = new File(candidateDocumentPath);

        if(!documentDirectory.exists()){
            documentDirectory.mkdir();
        }

        if(!idFile.getContentType().equals("image/jpeg")) {
            redirectAttributes.addFlashAttribute("wrongIDFileError",
                    "Only JPEG files allowed for uploading documents, use a JPEG file");
            return "redirect:/candidate/document-portal";
        }

        byte[] bytes = new byte[0];
        try{
            bytes = idFile.getBytes();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        String fileName = "ID_" + "C" + candidate.get().getId().toString() + ".jpg";
        Path path = Paths.get(candidateDocumentPath + fileName);
        System.out.println("Writing file: " + candidateDocumentPath + idFile.getOriginalFilename());
        try{
            write(path, bytes);

            DocumentsDTOSave documentsDTOSave = new DocumentsDTOSave(
                    candidateService.getCandidateEntityByID(candidate.get().getId()).get(),
                    fileName,
                    "ID",
                    "Pending"
            );

            try{
                documentsService.save(documentsDTOSave);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

            model.addAttribute("lastIDFile", idFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/candidate/document-portal";
    }

    @PostMapping("/uploadPassport")
    public String savePassport(@RequestParam("passportFile") MultipartFile passportFile, Model model,
                               RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        String homePath = System.getProperty("user.dir");
        String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
        File documentDirectory = new File(candidateDocumentPath);

        if(!documentDirectory.exists()){
            documentDirectory.mkdir();
        }

        if(!passportFile.getContentType().equals("image/jpeg")) {
            redirectAttributes.addFlashAttribute("wrongPPFileError",
                    "Only JPEG files allowed for uploading documents, use a JPEG file");
            return "redirect:/candidate/document-portal";
        }

        byte[] bytes = new byte[0];
        try{
            bytes = passportFile.getBytes();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        String fileName = "PP_" + "C" + candidate.get().getId().toString() + ".jpg";
        Path path = Paths.get(candidateDocumentPath + fileName);
        System.out.println("Writing file: " + candidateDocumentPath + passportFile.getOriginalFilename());
        try{
            write(path, bytes);

            DocumentsDTOSave documentsDTOSave = new DocumentsDTOSave(
                    candidateService.getCandidateEntityByID(candidate.get().getId()).get(),
                    fileName,
                    "Passport",
                    "Pending"
            );

            try{
                documentsService.save(documentsDTOSave);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            model.addAttribute("lastPassportFile", passportFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/candidate/document-portal";
    }

    @PostMapping("/deleteCandidateID/{id}")
    public ModelAndView deleteCandidateID(@PathVariable("id") Integer id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        if(candidate.isPresent()){
            Optional<DocumentsDTO> document = documentsService.getDocumentByID(id);
            String homePath = System.getProperty("user.dir");
            String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
            Path path = Paths.get(candidateDocumentPath + document.get().getDocumentName());
            path.toFile().delete();
            documentsService.deleteDocument(document.get());
        } else{
            return new ModelAndView("redirect:/404");
        }

        var mv = new ModelAndView("redirect:/candidate/document-portal", model.asMap());
        return mv;
    }

    @PostMapping("/deleteCandidatePassport/{id}")
    public ModelAndView deleteCandidatePassport(@PathVariable("id") Integer id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        if(candidate.isPresent()){
            Optional<DocumentsDTO> document = documentsService.getDocumentByID(id);
            String homePath = System.getProperty("user.dir");
            String candidateDocumentPath = homePath.concat(candidateDocumentPathSuffix);
            Path path = Paths.get(candidateDocumentPath + document.get().getDocumentName());
            path.toFile().delete();
            documentsService.deleteDocument(document.get());
        } else{
            return new ModelAndView("redirect:/404");
        }

        var mv = new ModelAndView("redirect:/candidate/document-portal", model.asMap());
        return mv;
    }
}
