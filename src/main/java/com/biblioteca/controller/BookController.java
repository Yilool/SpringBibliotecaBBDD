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

import com.biblioteca.entity.Book;
import com.biblioteca.service.BookService;

/**
 * 
 * @author yxiangch
 *
 */
@RestController
@RequestMapping(path = "/biblioteca")
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping(path = "/book")
	public ResponseEntity<?> postBook(@RequestBody Book b) {
		return bookService.crearLibro(b);
	}

	@GetMapping(path = "/book")
	public ResponseEntity<?> getAllBooks() {
		return bookService.obtenerLibros();
	}

	@GetMapping(path = "/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable Integer id) {
		return bookService.obtenerLibro(id);
	}

	@PutMapping(path = "/book")
	public ResponseEntity<?> putBook(@RequestBody Book book) {
		return bookService.actualizarLibro(book);
	}

	@DeleteMapping(path = "/book/{id}")
	public ResponseEntity<?> delBook(@PathVariable Integer id) {
		return bookService.borrarLibro(id);
	}

	// Añadir pagina al libro
	@PutMapping(path = "/book/page/{bookId}&&{pageId}")
	public ResponseEntity<?> putPage(@PathVariable Integer bookId, @PathVariable Integer pageId) {
		return bookService.anniadirPagina(bookId, pageId);
	}

	// Borrar pagina al libro
	@DeleteMapping(path = "/book/page/{pageId}")
	public ResponseEntity<?> delPage(@PathVariable Integer pageId) {
		return bookService.borrarPagina(pageId);
	}
}
