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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase Libro
 * 
 * @author yixiangch
 *
 */
@Entity
@Table(name = "Books")
public class Book implements Comparable<Book>, Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Title")
	private String title;
	@JoinColumn(name = "LibraryId")
	private Integer library;
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Page> pages;
	
	public Book() {
		this.pages = new ArrayList<>();
	}
	
	public Book(String title, Integer library) {
		this.title = title;
		this.library = library;
		this.pages = new ArrayList<>();
	}
	
	public boolean addPage(Page p) {
		return pages.add(p);
	}
	
	public boolean rmPage(Page p) {
		return pages.remove(p);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Integer getLibrary() {
		return library;
	}

	public void setLibrary(Integer library) {
		this.library = library;
	}

	public ArrayList<Page> getPages() {
		ArrayList<Page> aux = new ArrayList<>();
		
		aux.addAll(pages);
		
		return aux;
	}

	@Override
	public int compareTo(Book other) {
		return Integer.valueOf(this.id).compareTo(other.getId());
	}
}
