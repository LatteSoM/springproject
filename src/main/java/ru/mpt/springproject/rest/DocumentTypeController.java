package ru.mpt.springproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mpt.springproject.entity.DocumentType;
import ru.mpt.springproject.service.DocumentTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeService.getAllDocumentTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentType> getDocumentTypeById(@PathVariable Long id) {
        return documentTypeService.getDocumentTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DocumentType createDocumentType(@Valid @RequestBody DocumentType documentType) {
        return documentTypeService.createDocumentType(documentType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentType> updateDocumentType(@PathVariable Long id, @Valid @RequestBody DocumentType documentTypeDetails) throws Exception {
        return ResponseEntity.ok(documentTypeService.updateDocumentType(id, documentTypeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Long id) throws Exception {
        documentTypeService.deleteDocumentType(id);
        return ResponseEntity.noContent().build();
    }
}

