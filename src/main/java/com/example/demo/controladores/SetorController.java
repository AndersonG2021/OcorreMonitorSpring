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

import com.example.demo.entidades.Setor;
import com.example.demo.repositorios.SetorRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/setor")
public class SetorController {

    @PostMapping("/create")
    public ResponseEntity<String> createSetor(@RequestBody Setor setor) {
        try {
            SetorRepository.current.create(setor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Novo setor adicionado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao tentar criar o setor: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> getSetor(@PathVariable Integer id) {
        try {
            Setor setor = SetorRepository.current.read(id);
            if (setor != null) {
                return ResponseEntity.ok(setor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Setor>> getAllSetores() {
        try {
            List<Setor> setores = SetorRepository.current.readAll();
            return ResponseEntity.ok(setores);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSetor(@PathVariable Integer id, @RequestBody Setor setor) {
        try {
            setor.setSetorId(id);
            SetorRepository.current.update(setor);
            return ResponseEntity.ok("Dados do setor atualizados com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar atualizar o setor: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSetor(@PathVariable Integer id) {
        try {
            SetorRepository.current.delete(id);
            return ResponseEntity.ok("Setor removido com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar remover o setor: " + e.getMessage());
        }
    }
}
