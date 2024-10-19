package com.devland.assignment.ms_ems.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devland.assignment.ms_ems.admin.model.Admin;
import com.devland.assignment.ms_ems.admin.model.dto.AdminRequestDTO;
import com.devland.assignment.ms_ems.admin.model.dto.AdminResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<Page<AdminResponseDTO>> getAll(
            @RequestParam("name") Optional<String> optionalName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortString), "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Admin> pageAdmins = this.adminService.getAll(optionalName, pageable);
        Page<AdminResponseDTO> adminResponseDTOs = pageAdmins.map(Admin::convertToResponse);

        return ResponseEntity.ok(adminResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> getOne(@PathVariable("id") Long id) {
        Admin existingAdmin = this.adminService.getOne(id);
        AdminResponseDTO adminResponseDTO = existingAdmin.convertToResponse();

        return ResponseEntity.ok(adminResponseDTO);
    }

    @PostMapping
    public ResponseEntity<AdminResponseDTO> add(@RequestBody @Valid AdminRequestDTO adminRequestDTO) {
        Admin newAdmin = adminRequestDTO.convertToEntity();

        Admin savedAdmin = this.adminService.add(newAdmin);

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminRequestDTO.getUsername(),
                        adminRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AdminResponseDTO adminResponseDTO = savedAdmin.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> update(@PathVariable("id") Long id,
            @RequestBody AdminRequestDTO adminRequestDTO) {
        Admin updatedAdmin = adminRequestDTO.convertToEntity();
        updatedAdmin.setId(id);
        Admin savedAdmin = this.adminService.update(updatedAdmin);
        AdminResponseDTO adminResponseDTO = savedAdmin.convertToResponse();

        return ResponseEntity.ok(adminResponseDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.adminService.delete(id);

        return ResponseEntity.ok().build();
    }

}
