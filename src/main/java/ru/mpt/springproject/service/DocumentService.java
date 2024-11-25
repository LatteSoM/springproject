package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.Document;
import ru.mpt.springproject.exception.ResourceNotFoundException;
import ru.mpt.springproject.repository.DocumentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(Long id, Document documentDetails) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Document not exist with id :" + id));
        document.setEmployee(documentDetails.getEmployee());
        document.setDocumentType(documentDetails.getDocumentType());
        document.setDocumentNumber(documentDetails.getDocumentNumber());
        document.setIssueDate(documentDetails.getIssueDate());
        document.setExpiryDate(documentDetails.getExpiryDate());
        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Document not exist with id :" + id));
        documentRepository.delete(document);
    }
}
