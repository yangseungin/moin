package com.moim.moim.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoimRepository extends JpaRepository<Moim, Long> {
    boolean existsByTitle(String title);

    Optional<Moim> findByTitle(String encodedMoimTitle);
}
