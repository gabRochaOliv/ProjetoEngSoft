package com.trabfinal.controller;

import com.trabfinal.model.Emprestimo;
import com.trabfinal.model.Livro;
import com.trabfinal.model.Aluno;
import com.trabfinal.service.EmprestimoService;
import com.trabfinal.service.LivroService;
import com.trabfinal.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private AlunoService alunoService;

    // Endpoint para listar todos os empréstimos
    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimoService.listarTodos();
    }

    // Endpoint para realizar um empréstimo
    @PostMapping("/{livroId}/{alunoId}")
    public Emprestimo realizarEmprestimo(@PathVariable Long livroId, @PathVariable Long alunoId) {
        Livro livro = livroService.buscarPorId(livroId);
        Aluno aluno = alunoService.buscarPorId(alunoId);
        return emprestimoService.realizarEmprestimo(livro, aluno);
    }

    // Endpoint para devolver um empréstimo
    @PutMapping("/devolver/{emprestimoId}")
    public Emprestimo devolverEmprestimo(@PathVariable Long emprestimoId) {
        return emprestimoService.devolverEmprestimo(emprestimoId);
    }
}
