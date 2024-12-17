package com.trabfinal.dao;

import com.trabfinal.model.Livro;

import java.util.List;

public interface LivroDAO {
    List<Livro> listarTodos();
    Livro buscarPorId(Long id);
    void salvar(Livro livro);
    void deletar(Long id);
    List<Livro> buscarPorTitulo(String titulo);
}
