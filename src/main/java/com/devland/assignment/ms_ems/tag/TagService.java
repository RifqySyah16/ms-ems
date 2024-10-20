package com.devland.assignment.ms_ems.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devland.assignment.ms_ems.tag.model.Tag;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Page<Tag> getAll(Optional<String> optionalName, Pageable pageable) {
        if (optionalName.isPresent()) {
            return this.tagRepository.findAllByNameContainsIgnoreCase(optionalName.get(), pageable);
        }

        return this.tagRepository.findAll(pageable);
    }

    public Tag getOne(Long id) {
        return this.tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException("Tag not found"));
    }

    public Tag create(Tag newTag) {
        Optional<Tag> existingTag = this.tagRepository.findByName(newTag.getName());
        if (existingTag.isPresent()) {
            throw new TagAlreadyExistException("Tag already exist");
        }

        return this.tagRepository.save(newTag);
    }

    public Tag update(Tag updatedTag) {
        Tag existingTag = this.getOne(updatedTag.getId());
        updatedTag.setId(existingTag.getId());

        return this.tagRepository.save(updatedTag);
    }

    public void delete(Long id) {
        Tag existinTag = this.getOne(id);
        this.tagRepository.deleteById(existinTag.getId());
    }

    public List<Tag> findAllIn(List<Tag> tags) {
        List<Tag> existingTags = new ArrayList<>();
        for (Tag tag : tags) {
            Tag existingTag = this.getOne(tag.getId());
            existingTags.add(existingTag);
        }

        return existingTags;
    }
}
