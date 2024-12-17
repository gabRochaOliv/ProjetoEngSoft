package com.trabfinal.controller;

import com.trabfinal.model.Aluno;
import com.trabfinal.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alunos") // Define um prefixo de rota
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // Exibir página de cadastro e listar alunos
    @GetMapping
    public String listarAlunos(Model model) {
        List<Aluno> alunos = alunoService.listarTodos();
        model.addAttribute("alunos", alunos);
        return "alunos"; // Renderiza alunos.html
    }

    // Processar cadastro de alunos
    @PostMapping
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        alunoService.salvar(aluno);
        return "redirect:/alunos";
    }

    // Buscar alunos por nome
    @GetMapping("/buscar")
    @ResponseBody // Retorna JSON diretamente
    public List<Aluno> buscarPorNome(@RequestParam String nome) {
        return alunoService.buscarPorNome(nome);
    }
}
