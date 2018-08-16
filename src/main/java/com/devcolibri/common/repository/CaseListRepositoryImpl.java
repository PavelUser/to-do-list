package com.devcolibri.common.repository;

import com.devcolibri.common.domain.Case;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CaseListRepositoryImpl implements CaseListRepository {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/do_list"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection = null;
    private Statement statement = null;

    public CaseListRepositoryImpl() {

    }

    @Override
    public void save(Case aCase) {
        connectDatabase();
        try {
            statement.executeUpdate("INSERT INTO doList VALUES (" + aCase.getId() + ", '" + aCase.getDescription() + "')");
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Case aCase) {
        connectDatabase();
        try {
            statement.executeUpdate("DELETE FROM doList WHERE idCase = " + aCase.getId());
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ResultSet getAll() {
        connectDatabase();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM doList");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public Case getById(Integer id) {
        connectDatabase();
        ResultSet resultSet = null;
        Case aCase = new Case();
        aCase.setId(id);
        try {
            resultSet = statement.executeQuery("SELECT description FROM doList WHERE idCase = " + id);
            aCase.setDescription(resultSet.getString(1));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return aCase;
    }

    private void connectDatabase(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
        }catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }
}