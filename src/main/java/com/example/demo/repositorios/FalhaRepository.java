package com.example.demo.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Falha;
import com.example.demo.entidades.Setor;

public class FalhaRepository implements Repositorio<Falha, Integer> {

    public static final FalhaRepository current = new FalhaRepository();
    private FalhaRepository() {
    }

    @Override
    public void create(Falha falha) throws SQLException {
        String sql = "INSERT INTO falha (tipo_falha, data_ocorrido, id_setor) VALUES (?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, falha.getTipoFalha());  
        pstm.setDate(2, new java.sql.Date(falha.getDataOcorrido().getTime()));  
        pstm.setInt(3, falha.getSetor().getSetorId());  

        pstm.execute(); 
    }

    public List<Falha> readAll() throws SQLException {
        String sql = "SELECT f.*, s.setor_nome AS setor_nome FROM falha f "
                + "LEFT JOIN setor s ON f.id_setor = s.setor_id"; 

        List<Falha> falhas = new ArrayList<>();  

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();

        while (result.next()) {
            Falha f = new Falha();
            f.setIdFalha(result.getInt("id_falha"));  
            f.setTipoFalha(result.getString("tipo_falha")); 
            f.setDataOcorrido(result.getDate("data_ocorrido"));  

            // Mapear os dados de setor
            Setor s = new Setor();
            s.setSetorId(result.getInt("id_setor"));  
            s.setSetorNome(result.getString("setor_nome"));  

            f.setSetor(s);  

            falhas.add(f);  
        }

        return falhas;  
    }

    @Override
    public void update(Falha f) throws SQLException {
        String sql = "UPDATE falha SET tipo_falha = ?, data_ocorrido = ?, id_setor = ? WHERE id_falha = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, f.getTipoFalha()); 
        pstm.setDate(2, new java.sql.Date(f.getDataOcorrido().getTime()));  
        pstm.setInt(3, f.getSetor().getSetorId());  
        pstm.setInt(4, f.getIdFalha());  

        pstm.execute();
    }

    @Override
    public Falha read(Integer id) throws SQLException {
        String sql = "SELECT f.*, s.setor_nome AS setor_nome FROM falha f "
                + "LEFT JOIN setor s ON f.id_setor = s.setor_id WHERE f.id_falha = ?";

        Falha f = null;

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            f = new Falha();
            f.setIdFalha(result.getInt("id_falha"));  
            f.setTipoFalha(result.getString("tipo_falha"));  
            f.setDataOcorrido(result.getDate("data_ocorrido"));  

            
            Setor s = new Setor();
            s.setSetorId(result.getInt("id_setor")); 
            s.setSetorNome(result.getString("setor_nome")); 

            f.setSetor(s); 
        }

        return f;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM falha WHERE id_falha = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  

        pstm.execute();
    }

}
