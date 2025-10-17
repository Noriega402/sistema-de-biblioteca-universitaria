    package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.model.Book;
import com.umg.catalog_service.domain.repository.BookRepository;
import com.umg.catalog_service.domain.search.SearchFilter;
import com.umg.catalog_service.web.dto.SearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookQueryService {

    private final BookRepository libroRepo;

    public Page<SearchResultDTO> search(String q, SearchFilter filter, int page, int size) {
        q = q == null ? "" : q.trim();
        if (q.length() < 2) {
            return Page.empty(); // o cambiarlo por un IllegalArgumentException
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("titulo").ascending());

        Page<Book> p;
        if (filter == null) {
            p = libroRepo.searchAll(q, pageable);
        } else {
            switch (filter) {
                case TITULO ->
                    p = libroRepo.searchByTitle(q, pageable);
                case AUTOR ->
                    p = libroRepo.searchByAuthor(q, pageable);
                case CATEGORIA ->
                    p = libroRepo.searchByCategory(q, pageable);
                case ISBN ->
                    p = libroRepo.searchByIsbn(q, pageable);
                default ->
                    p = libroRepo.searchAll(q, pageable);
            }
        }

        return p.map(l -> {
            String autores = l.getAuthors().stream()
                    .map(a -> a.getApellidos() + " " + a.getNombres())
                    .sorted().collect(Collectors.joining("; "));
            String extra = "Aut.: " + autores + " | Cat.: " + l.getCategory().getNombre();
            return new SearchResultDTO("LIBRO", l.getIsbn(), l.getTitulo(), extra);
        });
    }
}
