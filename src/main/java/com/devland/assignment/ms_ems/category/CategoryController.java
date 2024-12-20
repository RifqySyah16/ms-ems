package com.devland.assignment.ms_ems.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.ms_ems.category.model.Category;
import com.devland.assignment.ms_ems.category.model.dto.CategoryRequestDTO;
import com.devland.assignment.ms_ems.category.model.dto.CategoryResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> getAll(
            @RequestParam("name") Optional<String> optionalName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Category> pageCategories = this.categoryService.getAll(optionalName, pageable);
        Page<CategoryResponseDTO> categoryResponseDTOs = pageCategories.map(Category::convertToResponse);

        return ResponseEntity.ok(categoryResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getOne(@PathVariable("id") Long id) {
        Category existingCategory = this.categoryService.getOne(id);
        CategoryResponseDTO categoryResponseDTO = existingCategory.convertToResponse();

        return ResponseEntity.ok(categoryResponseDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        Category newCategory = categoryRequestDTO.convertToEntity();

        Category savedCategory = this.categoryService.create(newCategory);
        CategoryResponseDTO categoryResponseDTO = savedCategory.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody CategoryRequestDTO categoryRequestDTO) {
        Category updatedCategory = categoryRequestDTO.convertToEntity();
        updatedCategory.setId(id);
        Category savedCategory = this.categoryService.update(updatedCategory);
        CategoryResponseDTO categoryResponseDTO = savedCategory.convertToResponse();

        return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.categoryService.delete(id);

        return ResponseEntity.ok().build();
    }
}
