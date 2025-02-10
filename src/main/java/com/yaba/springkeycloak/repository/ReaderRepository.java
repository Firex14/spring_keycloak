package com.yaba.springkeycloak.repository;

import com.yaba.springkeycloak.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReaderRepository extends JpaRepository<Reader, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
