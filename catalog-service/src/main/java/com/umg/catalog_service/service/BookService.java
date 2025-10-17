package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.model.Author;
import com.umg.catalog_service.domain.model.Book;
import com.umg.catalog_service.domain.model.Category;
import com.umg.catalog_service.domain.repository.AuthorRepository;
import com.umg.catalog_service.domain.repository.BookRepository;
import com.umg.catalog_service.domain.repository.CategoryRepository;
import com.umg.catalog_service.web.dto.BookDTO;
import com.umg.catalog_service.util.NotFoundException;
import com.umg.catalog_service.web.mapper.BookMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepo;
    private final CategoryRepository categoryRepo;
    private final AuthorRepository authorRepo;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepo,
            CategoryRepository categoryRepo,
            AuthorRepository authorRepo,
            BookMapper mapper) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
        this.mapper = mapper;
    }

    @Transactional
    public BookDTO create(BookDTO dto) {
        if (dto.categoriaId() == null) {
            throw new IllegalArgumentException("categoriaId es obligatorio");
        }

        Book entity = mapper.toEntity(dto);

        Category category = categoryRepo.findById(dto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + dto.categoriaId()));
        entity.setCategory(category);

        if (dto.autoresIds() != null && !dto.autoresIds().isEmpty()) {
            Set<Author> authors = new HashSet<>(dto.autoresIds().size());
            for (Long id : dto.autoresIds()) {
                Author author = authorRepo.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado: " + id));
                authors.add(author);
            }
            entity.setAuthors(authors);
        } else {
            entity.setAuthors(new HashSet<>());
        }

        Book saved = bookRepo.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    public BookDTO update(String isbn, BookDTO dto) {
        Book entity = bookRepo.findById(isbn)
                .orElseThrow(() -> new NotFoundException("Libro no encontrado: " + isbn));

        // campos simples
        entity.setIsbn(dto.isbn());
        entity.setTitulo(dto.titulo());
        entity.setEditorial(dto.editorial());
        entity.setAnio(dto.anio());

        // relaciones
        if (dto.categoriaId() == null) {
            throw new IllegalArgumentException("categoriaId es obligatorio");
        }
        Category category = categoryRepo.findById(dto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + dto.categoriaId()));
        entity.setCategory(category);

        if (dto.autoresIds() != null) {
            Set<Author> authors = new HashSet<>(dto.autoresIds().size());
            for (Long aid : dto.autoresIds()) {
                Author author = authorRepo.findById(aid)
                        .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado: " + aid));
                authors.add(author);
            }
            entity.setAuthors(authors);
        }

        Book saved = bookRepo.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    public void delete(String isbn) {
        Book entity = bookRepo.findById(isbn)
                .orElseThrow(() -> new NotFoundException("Libro no encontrado: " + isbn));
        bookRepo.delete(entity);
    }

    @Transactional
    public BookDTO findById(String isbn) {
        Book entity = bookRepo.findById(isbn)
                .orElseThrow(() -> new NotFoundException("Libro no encontrado: " + isbn));
        return mapper.toDto(entity);
    }

    // listado con paginación simple
    @Transactional
    public Page<BookDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return bookRepo.findAll(pageable).map(mapper::toDto);
    }
}
