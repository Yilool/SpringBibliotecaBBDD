package com.biblioteca.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Library;
import com.biblioteca.entity.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositorio de Biblioteca
 * 
 * @author yxiangch
 *
 */
@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer>{
    public Library findLibraryById(Integer id);

    public default List<Library> getAllOrderById() {
        List<Library> resultList = new ArrayList<>();

        findAll().forEach(resultList::add);

        return resultList.stream().sorted().collect(Collectors.toList());
    }
    
    @Query(value = "select * from page order by name desc", nativeQuery = true)
	public List<Page> getAllOrderedByName();
}
