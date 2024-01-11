package com.tiagodeveloper.springbootwithjwtsecurity.repositorys;

import com.tiagodeveloper.springbootwithjwtsecurity.domains.OverdueStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OverdueStatementRepository extends JpaRepository<OverdueStatement, UUID> {

    @Query(nativeQuery = true, value = "SELECT * from overdue_statement LIMIT 10")
    List<OverdueStatement> firstTen();
}
