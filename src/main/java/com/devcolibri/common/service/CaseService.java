package com.devcolibri.common.service;

import com.devcolibri.common.domain.Case;

import java.util.List;

public interface CaseService {

    void save(Case aCase);
    void delete(Case aCase);
    List<Case> getAll();
    Case getById(Integer id);
}
