package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.repository.EjemplarRepository;
import com.umg.catalog_service.web.dto.AvailableCopyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EjemplarQueryService {

    private final EjemplarRepository repo;

    public List<AvailableCopyDTO> availableByIsbn(String isbn) {
        return repo.availableCopies(isbn);
    }
}
