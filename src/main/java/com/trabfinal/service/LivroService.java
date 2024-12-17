package com.trabfinal.service;

import com.trabfinal.model.Livro;
import com.trabfinal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public void salvar(Livro livro) {
        livroRepository.save(livro);
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }

    // Método para listar apenas livros disponíveis
    public List<Livro> listarDisponiveis() {
        return livroRepository.findAll().stream()
                .filter(livro -> livro.isAtivo() && livro.isDisponivel())
                .toList();
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }
}
