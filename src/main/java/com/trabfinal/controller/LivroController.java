package com.trabfinal.controller;

import com.trabfinal.model.Livro;
import com.trabfinal.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Endpoint para exibir a página de gerenciamento de livros
    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.listarTodos();
        model.addAttribute("livros", livros);
        return "livros";
    }

    // Endpoint para salvar um livro
    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroService.salvar(livro);
        return "redirect:/livros";
    }

    // Endpoint para excluir um livro
    @PostMapping("/delete")
    public String deletarLivro(@RequestParam Long id) {
        livroService.deletar(id);
        return "redirect:/livros";
    }
}
