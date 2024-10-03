package com.example.demo.repositorios;

import java.sql.SQLException;
public interface Repositorio  <C, Key>{
    public void create(C c) throws SQLException;
    public void update(C c) throws SQLException;
    public C read(Key k) throws SQLException;
    public void delete(Key k) throws SQLException;
}
