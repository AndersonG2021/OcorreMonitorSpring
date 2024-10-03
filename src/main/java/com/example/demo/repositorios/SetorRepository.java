package com.example.demo.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Setor;

public class SetorRepository implements Repositorio<Setor, Integer> {

    public static final SetorRepository current = new SetorRepository();

    private SetorRepository() {}

    @Override
    public void create(Setor setor) throws SQLException {
        String sql = "INSERT INTO setor (setor_nome) VALUES (?)";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, setor.getSetorNome());  
        pstm.execute();
    }

    @Override
    public Setor read(Integer id) throws SQLException {
        String sql = "SELECT * FROM setor WHERE setor_id = ?";
        Setor setor = null;
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            setor = new Setor();
            setor.setSetorId(result.getInt("setor_id"));
            setor.setSetorNome(result.getString("setor_nome"));  
        }
        return setor;
    }

    public List<Setor> readAll() throws SQLException {
        String sql = "SELECT * FROM setor";
        List<Setor> setores = new ArrayList<>();
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();

        while (result.next()) {
            Setor setor = new Setor();
            setor.setSetorId(result.getInt("setor_id"));  
            setor.setSetorNome(result.getString("setor_nome")); 
            setores.add(setor);
        }

        return setores;
    }

    @Override
    public void update(Setor setor) throws SQLException {
        String sql = "UPDATE setor SET setor_nome = ? WHERE setor_id = ?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, setor.getSetorNome());  
        pstm.setInt(2, setor.getSetorId());  
        pstm.execute();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM setor WHERE setor_id = ?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  
        pstm.execute();
    }
}
