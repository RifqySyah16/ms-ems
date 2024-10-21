package com.devland.assignment.ms_ems.tag;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.ms_ems.tag.model.Tag;
import com.devland.assignment.ms_ems.tag.model.dto.TagRequestDTO;
import com.devland.assignment.ms_ems.tag.model.dto.TagResponseDTO;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<Page<TagResponseDTO>> getAll(
            @RequestParam("name") Optional<String> optionalName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Tag> pageTags = this.tagService.getAll(optionalName, pageable);
        Page<TagResponseDTO> tagResponseDTOs = pageTags.map(Tag::covertToResponse);

        return ResponseEntity.ok(tagResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> getOne(@PathVariable("id") Long id) {
        Tag existingTag = this.tagService.getOne(id);
        TagResponseDTO tagResponseDTO = existingTag.covertToResponse();

        return ResponseEntity.ok(tagResponseDTO);
    }

    @PostMapping
    public ResponseEntity<TagResponseDTO> create(@RequestBody @Valid TagRequestDTO tagRequestDTO) {
        Tag newTag = tagRequestDTO.convertToEntity();

        Tag savedTag = this.tagService.create(newTag);
        TagResponseDTO tagResponseDTO = savedTag.covertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(tagResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDTO> update(@PathVariable Long id, @RequestBody TagRequestDTO tagRequestDTO) {
        Tag updatedTag = tagRequestDTO.convertToEntity();
        updatedTag.setId(id);
        Tag savedTag = this.tagService.update(updatedTag);
        TagResponseDTO tagResponseDTO = savedTag.covertToResponse();

        return ResponseEntity.ok(tagResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.tagService.delete(id);

        return ResponseEntity.ok().build();
    }
}
