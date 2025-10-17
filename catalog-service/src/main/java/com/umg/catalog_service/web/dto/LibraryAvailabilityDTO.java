package com.umg.catalog_service.web.dto;

public class LibraryAvailabilityDTO {

    private final Long libraryId;
    private final String libraryName;
    private final Long availableCount;

    public LibraryAvailabilityDTO(Long libraryId, String libraryName, Long availableCount) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.availableCount = availableCount;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public Long getAvailableCount() {
        return availableCount;
    }
}
