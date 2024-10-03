package com.example.demo.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidades.Empregado;
import com.example.demo.entidades.Setor;

public class EmpregadoRepository implements Repositorio<Empregado, Integer> {

    public static final EmpregadoRepository current = new EmpregadoRepository();

    private EmpregadoRepository() {}

    @Override
    public void create(Empregado empregado) throws SQLException {
        String sql = "INSERT INTO empregado (nome_empregado, matricula_empregado, email_empregado, id_setor) VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, empregado.getNomeEmpregado());  
        pstm.setString(2, empregado.getMatriculaEmpregado());  
        pstm.setString(3, empregado.getEmailEmpregado());  
        pstm.setInt(4, empregado.getSetor().getSetorId());  
        pstm.execute();
    }

    @Override
    public Empregado read(Integer id) throws SQLException {
        String sql = "SELECT e.*, s.setor_nome AS setor_nome FROM empregado e "
                   + "LEFT JOIN setor s ON e.id_setor = s.setor_id WHERE e.id_empregado = ?";
        Empregado empregado = null;
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  
        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            empregado = new Empregado();
            empregado.setIdEmpregado(result.getInt("id_empregado"));  
            empregado.setNomeEmpregado(result.getString("nome_empregado"));  
            empregado.setMatriculaEmpregado(result.getString("matricula_empregado")); 
            empregado.setEmailEmpregado(result.getString("email_empregado")); 

            Setor setor = new Setor();
            setor.setSetorId(result.getInt("id_setor"));  
            setor.setSetorNome(result.getString("setor_nome")); 

            empregado.setSetor(setor);
        }

        return empregado;
    }

    public List<Empregado> readAll() throws SQLException {
        String sql = "SELECT e.*, s.setor_nome AS setor_nome FROM empregado e "
                   + "LEFT JOIN setor s ON e.id_setor = s.setor_id";
        List<Empregado> empregados = new ArrayList<>();
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();

        while (result.next()) {
            Empregado empregado = new Empregado();
            empregado.setIdEmpregado(result.getInt("id_empregado"));  
            empregado.setNomeEmpregado(result.getString("nome_empregado"));  
            empregado.setMatriculaEmpregado(result.getString("matricula_empregado")); 
            empregado.setEmailEmpregado(result.getString("email_empregado")); 

            Setor setor = new Setor();
            setor.setSetorId(result.getInt("id_setor")); 
            setor.setSetorNome(result.getString("setor_nome"));  

            empregado.setSetor(setor);
            empregados.add(empregado);
        }

        return empregados;
    }

    @Override
    public void update(Empregado empregado) throws SQLException {
        String sql = "UPDATE empregado SET nome_empregado = ?, matricula_empregado = ?, email_empregado = ?, id_setor = ? WHERE id_empregado = ?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, empregado.getNomeEmpregado());  
        pstm.setString(2, empregado.getMatriculaEmpregado());  
        pstm.setString(3, empregado.getEmailEmpregado());  
        pstm.setInt(4, empregado.getSetor().getSetorId());  
        pstm.setInt(5, empregado.getIdEmpregado());  
        pstm.execute();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM empregado WHERE id_empregado = ?";
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  
        pstm.execute();
    }
}
