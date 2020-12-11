package com.biblioteca.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Biblioteca
 * 
 * @author yixiangch
 *
 */
@Entity
@Table(name = "Libraries")
public class Library implements Comparable<Library>, Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Name")
	private String name;
	@OneToMany(mappedBy = "library", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 	private List<Book> books;
	
	public Library() {
		this.books = new ArrayList<>();
	}
	
	public Library(String name) {
		this.name = name;
		this.books = new ArrayList<>();
	}
	
	public boolean addBook(Book b) {
		return books.add(b);
	}
	
	public boolean rmBook(Book b) {
		return books.remove(b);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		ArrayList<Book> aux = new ArrayList<>();
		
		aux.addAll(books);
		
		return aux;
	}

	@Override
	public int compareTo(Library other) {
		return Integer.valueOf(this.id).compareTo(other.getId());
	}
}
