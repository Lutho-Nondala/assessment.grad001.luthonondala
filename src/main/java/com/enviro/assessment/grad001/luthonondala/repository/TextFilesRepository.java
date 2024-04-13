package com.enviro.assessment.grad001.luthonondala.repository;

import com.enviro.assessment.grad001.luthonondala.entity.TextFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextFilesRepository extends JpaRepository<TextFiles, Long> {
}
