package com.devcolibri.common.service;

import com.devcolibri.common.domain.Case;
import com.devcolibri.common.repository.CaseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseListRepository caseListRepository;

    @Override
    public void save(Case aCase) {
        if (aCase != null) {
            ResultSet resultSet = caseListRepository.getAll();
            if(resultSet != null) {
                int id = 0;
                try {
                    resultSet.afterLast();
                    if (resultSet.previous())
                        id = resultSet.getInt(1);
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
                aCase.setId(id + 1);
                caseListRepository.save(aCase);
            }
            else{
                aCase.setId(1);
                caseListRepository.save(aCase);
            }
        }
    }

    @Override
    public void delete(Case aCase) {
        if (aCase != null) {
            caseListRepository.delete(aCase);
        }
    }

    @Override
    public List<Case> getAll() {
        List<Case> cases = new ArrayList<>();
        ResultSet resultSet = caseListRepository.getAll();
        try {
            while (resultSet.next()){
                Case aCase = new Case();
                aCase.setId(resultSet.getInt(1));
                aCase.setDescription(resultSet.getString(2));
                cases.add(aCase);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return cases;
    }

    @Override
    public Case getById(Integer id) {
        if (id != null) {
            return caseListRepository.getById(id);
        }
        return null;
    }
}