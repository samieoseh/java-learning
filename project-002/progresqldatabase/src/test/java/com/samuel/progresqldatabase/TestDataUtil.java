package com.samuel.progresqldatabase;

import com.samuel.progresqldatabase.domain.Author;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Samuel Oseh")
                .age(89)
                .build();

    }
}
