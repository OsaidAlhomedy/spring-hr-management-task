package com.osaid.taqneenhrtask.repositries;

import com.osaid.taqneenhrtask.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {
}
