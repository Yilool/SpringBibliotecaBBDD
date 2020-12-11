package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import com.biblioteca.entity.Document;
import com.biblioteca.entity.Page;
import com.biblioteca.repository.DocumentRepository;
import com.biblioteca.repository.PageRepository;

@Service
public class PageService extends AbstractServiceUtils {
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private FileHandlerService fileHandlerService;

    public ResponseEntity<?> crearPagina(Page p) {
        ResponseEntity<?> res = null;

        pageRepository.save(p);
        res = ResponseEntity.status(HttpStatus.OK).body(p);

        return res;
    }

    public ResponseEntity<?> obtenerPaginas() {
        return ResponseEntity.status(HttpStatus.OK).body(pageRepository.getAllOrderById());
    }

    public ResponseEntity<?> obtenerPagina(Integer id) {
        ResponseEntity<?> res = null;

        if (pageRepository.existsById(id)) {
            res = ResponseEntity.status(HttpStatus.OK).body(pageRepository.findPageById(id));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: " + id);
        }

        return res;
    }

    public ResponseEntity<?> actualizarPagina(Page p) {
        ResponseEntity<?> res = null;

        if (pageRepository.existsById(p.getId())) {
            Page p1 = pageRepository.findPageById(p.getId());
            p1.setNumber(p.getNumber());
            p1.setBook(p.getBook());
            pageRepository.save(p1);
            res = ResponseEntity.status(HttpStatus.OK).body(p1);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: " + p.getId());
        }

        return res;
    }

    public ResponseEntity<?> borrarPagina(Integer id) {
        ResponseEntity<?> res = null;

        if (pageRepository.existsById(id)) {
            pageRepository.deleteById(id);
            res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado pagina con id: " + id);
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: " + id);
        }

        return res;
    }

    public ResponseEntity<?> anniadirDocumento(Integer pageId, MultipartFile document) {
        ResponseEntity<?> res = null;

        Page p = pageRepository.findPageById(pageId);

        if (p != null) {
            Document d = documentRepository.save(new Document(fileHandlerService.createBlob(document),
                    document.getName(), Integer.valueOf((int) document.getSize()), pageId));

            p.setDocuments(d);
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body(pageRepository.save(p));
        } else {
            res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: " + pageId);
        }

        return res;
    }

//    public ResponseEntity<?> bajarDocumento(Integer pageId) {
//        ResponseEntity<?> res = null;
//
//        Page p = pageRepository.findPageById(pageId);
//
//        if (p != null) {
//    		Document pageDoc = p.getDocuments();
//    		if (pageDoc != null) {
//				res = ResponseEntity.ok()
//					     .contentType(MediaType.parseMediaType(f.getFileType()))
//					     .header("Descarga imagen", "attachment; filename=\"" + f.getFileName() +
//					     "\"")
//					     .body(new ByteArrayResource(f.getFile().getBytes(1L,
//					     (int)f.getFile().length())));
//			} else {
//				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: "+pageId);
//			}
//    	} else {
//    		res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: "+pageId);
//        }
//        
//        return res;
//    }


}
