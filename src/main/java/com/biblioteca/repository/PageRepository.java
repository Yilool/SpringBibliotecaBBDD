package com.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Page;

@Repository
public interface PageRepository extends CrudRepository<Page, Integer>{

	
	public Page findPageById(Integer id);

	public default List<Page> getAllOrderById() {
		List<Page> resultList = new ArrayList<>();
		findAll().forEach(resultList::add);
		
		return resultList.stream().sorted().collect(Collectors.toList());
	}
	
	@Query(value = "select * from Pages order by number desc", nativeQuery = true)
	public List<Page> getAllOrderedByNumber();
	
	public static final EntityManager em = null;	
	
	@Query(value = "SELECT * FROM Pages WHERE id \n-- #p\n",
			countQuery = "SELECT count(*) FROM Pages",
			nativeQuery = true)
	public Page getByH2Pagination(Page p);
	
	@Query(value = "SELECT * FROM Pages p WHERE p.Id = ?1 and p.Number = ?2", nativeQuery = true)
	public Page getByIndexed(Integer pageId, int number);
	
	@Query(value = "SELECT * FROM Pages p WHERE p.Id = :id and p.Number = :num", nativeQuery = true)
	public Page getByParam(@Param("id") Integer pageId, @Param("num") int number);
	
//	public default Page getByEM(Integer pageId) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Page> query = cb.createQuery(Page.class);
//        Root<Page> page = query.from(Page.class);
// 
//        Path<Integer> pagesId = page.get("id");
// 
//        Predicate predicates = new Predicate();
// 
//        predicates.add(cb.like());
//    
//        query.select(page).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
// 
//        return em.createQuery(query);
//    }
}
