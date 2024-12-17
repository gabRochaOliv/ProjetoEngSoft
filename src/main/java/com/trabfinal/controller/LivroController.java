package com.trabfinal.controller;

import com.trabfinal.model.Livro;
import com.trabfinal.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros") // Define um prefixo de rota
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Exibir página de cadastro e listar livros
    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.listarTodos();
        model.addAttribute("livros", livros);
        return "livros"; // Renderiza livros.html
    }

    // Processar cadastro de livros
    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroService.salvar(livro);
        return "redirect:/livros";
    }

    // Processar exclusão de livros
    @PostMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroService.deletar(id);
        return "redirect:/livros";
    }

    // Buscar livros por título
    @GetMapping("/buscar")
    @ResponseBody // Retorna JSON diretamente
    public List<Livro> buscarPorTitulo(@RequestParam String titulo) {
        return livroService.buscarPorTitulo(titulo);
    }
}
