package ru.mpt.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpt.springproject.entity.DocumentType;
import ru.mpt.springproject.repository.DocumentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    public Optional<DocumentType> getDocumentTypeById(Long id) {
        return documentTypeRepository.findById(id);
    }

    public DocumentType createDocumentType(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public DocumentType updateDocumentType(Long id, DocumentType documentTypeDetails) throws Exception {
        DocumentType documentType = documentTypeRepository.findById(id).orElseThrow(() -> new Exception("DocumentType not exist with id :" + id));
        documentType.setName(documentTypeDetails.getName());
        return documentTypeRepository.save(documentType);
    }

    public void deleteDocumentType(Long id) throws Exception {
        DocumentType documentType = documentTypeRepository.findById(id).orElseThrow(() -> new Exception("DocumentType not exist with id :" + id));
        documentTypeRepository.delete(documentType);
    }
}

