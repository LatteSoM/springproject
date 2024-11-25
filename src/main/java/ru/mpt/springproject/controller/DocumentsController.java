package ru.mpt.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.Document;
import ru.mpt.springproject.entity.DocumentType;
import ru.mpt.springproject.entity.Employee;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.service.DocumentService;
import ru.mpt.springproject.service.DocumentTypeService;
import ru.mpt.springproject.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentsController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping
    public String getAllDocuments(Model model) {
        List<Document> documents = documentService.getAllDocuments();
        List<Employee> employees = employeeService.getAllEmployees();
        List<DocumentType> documentTypes = documentTypeService.getAllDocumentTypes();
        model.addAttribute("documents", documents);
        model.addAttribute("employees", employees);
        model.addAttribute("documentTypes", documentTypes);
        model.addAttribute("document", new Document());
        return "Document";
    }

    @PostMapping
    public String createDocument(@ModelAttribute Document document) {
        documentService.createDocument(document);
        return "redirect:/documents";
    }

    @GetMapping("/edit/{id}")
    public String editDocument(@PathVariable Long id, Model model) {
        Document document = documentService.getDocumentById(id).orElseThrow(() -> new ResourceNotFoundException("Document not exist with id :" + id));
        List<Employee> employees = employeeService.getAllEmployees();
        List<DocumentType> documentTypes = documentTypeService.getAllDocumentTypes();
        model.addAttribute("document", document);
        model.addAttribute("employees", employees);
        model.addAttribute("documentTypes", documentTypes);
        return "Document";
    }

    @PostMapping("/edit/{id}")
    public String updateDocument(@PathVariable Long id, @ModelAttribute Document documentDetails) {
        documentService.updateDocument(id, documentDetails);
        return "redirect:/documents";
    }

    @GetMapping("/delete/{id}")
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return "redirect:/documents";
    }
}

