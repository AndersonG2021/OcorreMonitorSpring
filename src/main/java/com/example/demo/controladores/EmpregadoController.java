package com.example.demo.controladores;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Empregado;
import com.example.demo.repositorios.EmpregadoRepository;

@CrossOrigin(origins="*")
@RequestMapping("/empregado")
@RestController
public class EmpregadoController {
    
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Empregado empregado) {
        try {
            EmpregadoRepository.current.create(empregado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empregado criado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o empregado: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empregado> read(@PathVariable Integer id) {
        try {
            Empregado empregado = EmpregadoRepository.current.read(id);
            if (empregado != null) {
                return ResponseEntity.ok(empregado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Empregado>> readAll() {
        try {
            List<Empregado> empregados = EmpregadoRepository.current.readAll();
            return ResponseEntity.ok(empregados);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Empregado empregado) {
        try {
            empregado.setIdEmpregado(id);
            EmpregadoRepository.current.update(empregado);
            return ResponseEntity.ok("Empregado atualizado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o empregado: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            EmpregadoRepository.current.delete(id);
            return ResponseEntity.ok("Empregado deletado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o empregado: " + e.getMessage());
        }
    }
}