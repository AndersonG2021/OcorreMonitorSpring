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

import com.example.demo.entidades.Falha;
import com.example.demo.repositorios.FalhaRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/falhas")
public class FalhaController {

    @PostMapping("/create")
    public ResponseEntity<String> createFalha(@RequestBody Falha falha) {
        try {
            FalhaRepository.current.create(falha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Nova falha registrada com êxito!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível registrar a falha: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Falha>> getAllFalhas() {
        try {
            List<Falha> falhas = FalhaRepository.current.readAll();
            return ResponseEntity.ok(falhas);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Falha> getFalha(@PathVariable Integer id) {
        try {
            Falha falha = FalhaRepository.current.read(id);
            return falha != null ? ResponseEntity.ok(falha) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFalha(@PathVariable Integer id, @RequestBody Falha falha) {
        try {
            falha.setIdFalha(id);
            FalhaRepository.current.update(falha);
            return ResponseEntity.ok("Falha atualizada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a falha: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFalha(@PathVariable Integer id) {
        try {
            FalhaRepository.current.delete(id);
            return ResponseEntity.ok("Falha removida com êxito!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a falha: " + e.getMessage());
        }
    }
}
