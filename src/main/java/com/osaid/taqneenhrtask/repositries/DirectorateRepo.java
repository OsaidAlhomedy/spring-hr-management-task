package com.osaid.taqneenhrtask.repositries;

import com.osaid.taqneenhrtask.models.Directorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorateRepo extends JpaRepository<Directorate,Long> {
}
