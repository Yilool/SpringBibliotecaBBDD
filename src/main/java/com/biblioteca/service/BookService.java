package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Page;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.repository.PageRepository;

/**
 * 
 * @author yxiangch
 *
 */
@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PageRepository pageRepository;

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void setPageRepository(PageRepository pageRepository) {
		this.pageRepository = pageRepository;
	}

	/**
	 * Crea un libro
	 * @param b
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> crearLibro(Book b) {
		ResponseEntity<?> res = null;

		b.getPages().forEach(p -> pageRepository.save(p));
		bookRepository.save(b);
		res = ResponseEntity.status(HttpStatus.OK).body(b);

		return res;
	}

	/**
	 * Obtiene todos los libros de la base de datos y ordena por id
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> obtenerLibros() {
		return ResponseEntity.status(HttpStatus.OK).body(bookRepository.getAllOrderById());
	}

	/**
	 * Obtiene un libro apartir del id
	 * @param id
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> obtenerLibro(Integer id) {
		ResponseEntity<?> res = null;

		if (bookRepository.existsById(id)) {
			res = ResponseEntity.status(HttpStatus.OK).body(bookRepository.findBookById(id));
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + id);
		}

		return res;
	}

	/**
	 * Actualiza el libro
	 * @param b
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> actualizarLibro(Book b) {
		ResponseEntity<?> res = null;

		if (bookRepository.existsById(b.getId())) {
			Book b1 = bookRepository.findBookById(b.getId());
			b1.setTitle(b.getTitle());
			b1.setLibrary(b.getLibrary());
			b1.setPages(b.getPages());
			bookRepository.save(b1);
			res = ResponseEntity.status(HttpStatus.OK).body(b1);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + b.getId());
		}

		return res;
	}

	/**
	 * Borra un libro
	 * @param id
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> borrarLibro(Integer id) {
		ResponseEntity<?> res = null;

		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			res = ResponseEntity.status(HttpStatus.OK).body("Se ha borrado libro con id: " + id);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + id);
		}

		return res;
	}

	/**
	 * Añade una pagina al libro
	 * @param bookId
	 * @param pageId
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> anniadirPagina(Integer bookId, Integer pageId) {
		ResponseEntity<?> res = null;

		if (bookRepository.existsById(bookId)) {
			if (pageRepository.existsById(pageId)) {
				Page p = pageRepository.findPageById(pageId);
				Book b = bookRepository.findBookById(bookId);
				p.setBook(b.getId());
				pageRepository.save(p);
				res = ResponseEntity.status(HttpStatus.OK).body(p);
			} else {
				res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: " + pageId);
			}
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra libro con id: " + bookId);
		}

		return res;
	}

	/**
	 * Borra una pagina del libro
	 * @param pageId
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> borrarPagina(Integer pageId) {
		ResponseEntity<?> res = null;

		if (pageRepository.existsById(pageId)) {
			Page p = pageRepository.findPageById(pageId);
			p.setBook(null);
			pageRepository.save(p);
			res = ResponseEntity.status(HttpStatus.OK).body(p);
		} else {
			res = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra pagina con id: " + pageId);
		}

		return res;
	}
}
