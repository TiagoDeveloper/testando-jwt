package com.tiagodeveloper.springbootwithjwtsecurity.utils;

import com.tiagodeveloper.springbootwithjwtsecurity.domains.OverdueStatement;
import com.tiagodeveloper.springbootwithjwtsecurity.repositorys.OverdueStatementRepository;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Objects;

public final class StatementUtils {

    private static OverdueStatementRepository repository;

    private static List<OverdueStatement> firstTen;

    private StatementUtils() {}


    public static void configureRepository(OverdueStatementRepository repository) {
        StatementUtils.repository = repository;
    }

    public static List<OverdueStatement> firstTen() {

        if(Objects.isNull(StatementUtils.repository)) throw new RuntimeException("is required");

        if(Objects.isNull(StatementUtils.firstTen)){
            StatementUtils.firstTen = repository.firstTen();
        }

        return StatementUtils.firstTen;
    }
}
