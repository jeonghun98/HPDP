package com.stn.hpdp.model.repository;

import com.stn.hpdp.model.entity.Budget;
import com.stn.hpdp.model.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    void deleteAllByFunding_Id(Long fundingId);
    List<Budget> findAllByFunding_Id(Long fundinsId);
}