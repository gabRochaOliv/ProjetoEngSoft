package com.trabfinal.controller;

import com.trabfinal.model.Aluno;
import com.trabfinal.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // Endpoint para listar todos os alunos
    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    // Endpoint para salvar um aluno
    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    // Endpoint para deletar um aluno por ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }
}
