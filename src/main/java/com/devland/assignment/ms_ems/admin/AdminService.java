package com.devland.assignment.ms_ems.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devland.assignment.ms_ems.admin.model.Admin;
import com.devland.assignment.ms_ems.authentication.model.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = this.adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));

        return UserPrincipal.build(admin);
    }

    public Page<Admin> getAll(Optional<String> optionalName, Pageable pageable) {
        if (optionalName.isPresent()) {
            return this.adminRepository.findAllByNameContainsIgnoreCase(optionalName.get(), pageable);
        }

        return this.adminRepository.findAll(pageable);
    }

    public Admin getOne(Long id) {
        return this.adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
    }

    public Admin add(Admin newAdmin) {
        Optional<Admin> existingUser = this.adminRepository.findByUsername(newAdmin.getUsername());
        if (existingUser.isPresent()) {
            throw new AdminAlreadyExistException("Admin Already Exist");
        }

        String rawPassword = newAdmin.getPassword();
        String encodedPassword = this.bCryptPasswordEncoder.encode(rawPassword);
        newAdmin.setPassword(encodedPassword);

        return this.adminRepository.save(newAdmin);
    }

    public Admin update(Admin updatedAdmin) {
        Admin existingAdmin = this.getOne(updatedAdmin.getId());
        updatedAdmin.setId(existingAdmin.getId());

        return this.adminRepository.save(updatedAdmin);
    }

    public void delete(Long id) {
        long adminCount = this.adminRepository.count();
        if (adminCount <= 1) {
            throw new LastAdminCannotBeDeletedException("Cannot delete the last admin");
        }

        Admin existingAdmin = this.getOne(id);
        this.adminRepository.deleteById(existingAdmin.getId());
    }

}
