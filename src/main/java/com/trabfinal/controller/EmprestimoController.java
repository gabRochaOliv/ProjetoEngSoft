package com.trabfinal.controller;

import com.trabfinal.model.Livro;
import com.trabfinal.model.Aluno;
import com.trabfinal.service.EmprestimoService;
import com.trabfinal.service.LivroService;
import com.trabfinal.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private AlunoService alunoService;

    // Página de empréstimos
    @GetMapping
    public String listarEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        model.addAttribute("alunos", alunoService.listarTodos());
        model.addAttribute("livrosDisponiveis", livroService.listarDisponiveis());
        return "emprestimos";
    }

    // Realizar empréstimo
    @PostMapping
    public String realizarEmprestimo(@RequestParam Long livroId, @RequestParam Long alunoId, Model model) {
        Livro livro = livroService.buscarPorId(livroId);
        Aluno aluno = alunoService.buscarPorId(alunoId);

        String erro = emprestimoService.realizarEmprestimo(livro, aluno);

        if (erro != null) {
            model.addAttribute("erro", erro);
            return listarEmprestimos(model); // Retorna à página com erro
        }

        return "redirect:/emprestimos";
    }

    // Devolver livro
    @PostMapping("/devolver/{id}")
    public String devolverEmprestimo(@PathVariable Long id) {
        emprestimoService.devolverEmprestimo(id);
        return "redirect:/emprestimos";
    }
}
