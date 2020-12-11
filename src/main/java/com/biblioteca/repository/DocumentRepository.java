package com.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer>{
	public Document findDocumentById(Integer id);
}
