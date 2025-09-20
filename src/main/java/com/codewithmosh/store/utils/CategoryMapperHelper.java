package com.codewithmosh.store.utils;

import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapperHelper{
    private final CategoryRepository categoryRepository;

    public Category map(Byte id) {
        if (id == null) return null;
        return categoryRepository.findById(id)
                .orElse(null);
    }
}
