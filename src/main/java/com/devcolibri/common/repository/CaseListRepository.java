package com.devcolibri.common.repository;


import com.devcolibri.common.domain.Case;

import java.sql.ResultSet;

public interface CaseListRepository {

    void save(Case aCase);
    void delete(Case aCase);
    ResultSet getAll();
    Case getById(Integer id);

}
