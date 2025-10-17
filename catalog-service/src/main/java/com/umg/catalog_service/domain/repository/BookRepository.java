package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.Book;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {    

    boolean existsByIsbn(String isbn);

    @Query("""
        select distinct b from Book b
        left join b.authors a
        left join b.category c
        where lower(b.titulo) like lower(concat('%', :q, '%'))
           or lower(a.apellidos) like lower(concat('%', :q, '%'))
           or lower(a.nombres) like lower(concat('%', :q, '%'))
           or lower(c.nombre) like lower(concat('%', :q, '%'))
           or lower(b.isbn) like lower(concat('%', :q, '%'))
        """)
    Page<Book> searchAll(@Param("q") String q, Pageable pageable);

    @Query("select b from Book b where lower(b.titulo) like lower(concat('%', :q, '%'))")
    Page<Book> searchByTitle(@Param("q") String q, Pageable pageable);

    @Query("""
        select distinct b from Book b
        join b.authors a
        where lower(a.apellidos) like lower(concat('%', :q, '%'))
           or lower(a.nombres) like lower(concat('%', :q, '%'))
      """)
    Page<Book> searchByAuthor(@Param("q") String q, Pageable pageable);

    @Query("""
        select b from Book b
        join b.category c
        where lower(c.nombre) like lower(concat('%', :q, '%'))
      """)
    Page<Book> searchByCategory(@Param("q") String q, Pageable pageable);

    @Query("select b from Book b where lower(b.isbn) like lower(concat('%', :q, '%'))")
    Page<Book> searchByIsbn(@Param("q") String q, Pageable pageable);
}
