package com.tiagodeveloper.springbootwithjwtsecurity.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "overdue_statement", schema = "public")
@Data
public class OverdueStatement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;
    private String customerId;
    private String statementId;
    private LocalDate realDueDate;

}
