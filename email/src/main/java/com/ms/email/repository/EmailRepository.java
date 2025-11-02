package com.ms.email.repository;

import com.ms.email.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface EmailRepository extends JpaRepository<Email, Long> {
}
