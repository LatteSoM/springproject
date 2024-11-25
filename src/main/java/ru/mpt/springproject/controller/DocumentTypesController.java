package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.DocumentType;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.DocumentTypeService;

import java.util.List;

@Controller
@RequestMapping("/document-types")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping
    public String getAllDocumentTypes(Model model) {
        List<DocumentType> documentTypes = documentTypeService.getAllDocumentTypes();
        model.addAttribute("documentTypes", documentTypes);
        model.addAttribute("documentType", new DocumentType());
        return "DocumentType";
    }

    @PostMapping
    public String createDocumentType(@ModelAttribute DocumentType documentType) {
        documentTypeService.createDocumentType(documentType);
        return "redirect:/document-types";
    }

    @GetMapping("/edit/{id}")
    public String editDocumentType(@PathVariable Long id, Model model) {
        DocumentType documentType = documentTypeService.getDocumentTypeById(id).orElseThrow(() -> new ResourceNotFoundException("DocumentType not exist with id :" + id));
        model.addAttribute("documentType", documentType);
        return "DocumentType";
    }

    @PostMapping("/edit/{id}")
    public String updateDocumentType(@PathVariable Long id, @ModelAttribute DocumentType documentTypeDetails) throws Exception {
        documentTypeService.updateDocumentType(id, documentTypeDetails);
        return "redirect:/document-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteDocumentType(@PathVariable Long id) throws Exception {
        documentTypeService.deleteDocumentType(id);
        return "redirect:/document-types";
    }
}
