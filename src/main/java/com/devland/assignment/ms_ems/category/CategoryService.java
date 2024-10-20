package com.devland.assignment.ms_ems.category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.ms_ems.category.model.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Page<Category> getAll(Optional<String> optionalName, Pageable pageable) {
        if (optionalName.isPresent()) {
            return this.categoryRepository.findAllByNameContainsIgnoreCase(optionalName.get(), pageable);
        }

        return this.categoryRepository.findAll(pageable);
    }

    public Category getOne(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    public Category create(Category newCategory) {
        Optional<Category> existingCategory = this.categoryRepository.findByName(newCategory.getName());
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistException("Category already exist");
        }

        return this.categoryRepository.save(newCategory);
    }

    public Category update(Category updatedCategory) {
        Category existinCategory = this.getOne(updatedCategory.getId());
        updatedCategory.setId(existinCategory.getId());

        return this.categoryRepository.save(updatedCategory);
    }

    public void delete(Long id) {
        Category existinCategory = this.getOne(id);
        this.categoryRepository.deleteById(existinCategory.getId());
    }

}
