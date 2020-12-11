package com.biblioteca.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositorio de Libro
 * 
 * @author yxiangch
 *
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	/**
	 * Busca en la base de dato apartir del id
	 * 
	 * @param id
	 * @return Book
	 */
    public Book findBookById(Integer id);

    /**
     * Obtiene todos los libros 
     * 
     * @return List<Book>
     */
    public default List<Book> getAllOrderById() {
        List<Book> resultList = new ArrayList<>();

        findAll().forEach(resultList::add);

        return resultList.stream().sorted().collect(Collectors.toList());
    }
    
    /**
     * Obtiene todos los libros por titulo
     * @return List<Page>
     */
    @Query(value = "select * from page order by title desc", nativeQuery = true)
	public List<Page> getAllOrderedByTitle();
}
