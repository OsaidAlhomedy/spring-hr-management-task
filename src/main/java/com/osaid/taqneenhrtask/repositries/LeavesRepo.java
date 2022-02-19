package com.osaid.taqneenhrtask.repositries;

import com.osaid.taqneenhrtask.models.Employee;
import com.osaid.taqneenhrtask.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeavesRepo extends JpaRepository<Leave,Long> {
    List<Leave> findAllByDateBetween(Date from , Date to);
}
