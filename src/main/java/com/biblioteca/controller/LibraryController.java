package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Library;
import com.biblioteca.service.LibraryService;

/**
 * 
 * @author yxiangch
 *
 */
@RestController
@RequestMapping(path = "/biblioteca")
public class LibraryController {
	@Autowired
	private LibraryService libraryService;

	@PostMapping(path = "/library")
	public ResponseEntity<?> postLibrary(@RequestBody Library library) {
		return libraryService.crearBiblioteca(library);
	}

	@GetMapping(path = "/library")
	public ResponseEntity<?> getAllLibraries() {
		return libraryService.obtenerBibliotecas();
	}

	@GetMapping(path = "/library/{id}")
	public ResponseEntity<?> getLibrary(@PathVariable Integer id) {
		return libraryService.obtenerBiblioteca(id);
	}

	@PutMapping(path = "/library")
	public ResponseEntity<?> putLibrary(@RequestBody Library library) {
		return libraryService.actualizarBiblioteca(library);
	}

	@DeleteMapping(path = "/library/{id}")
	public ResponseEntity<?> delLibrary(@PathVariable Integer id) {
		return libraryService.borrarBiblioteca(id);
	}

	// Añadir libro a la biblioteca
	@PutMapping(path = "/library/book/{libraryId}&&{bookId}")
	public ResponseEntity<?> putBook(@PathVariable Integer libraryId, @PathVariable Integer bookId) {
		return libraryService.anniadirLibro(libraryId, bookId);
	}

	// Borrar libro a la biblioteca
	@DeleteMapping(path = "/library/book/{bookId}")
	public ResponseEntity<?> delBook(@PathVariable Integer bookId) {
		return libraryService.borrarLibro(bookId);
	}

}
