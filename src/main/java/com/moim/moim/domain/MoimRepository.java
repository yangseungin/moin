package com.moim.moim.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimRepository extends JpaRepository<Moim, Long> {
    boolean existsByTitle(String title);
}
