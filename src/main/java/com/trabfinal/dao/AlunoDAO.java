package com.trabfinal.dao;

import com.trabfinal.model.Aluno;

import java.util.List;

public interface AlunoDAO {
    List<Aluno> listarTodos();
    Aluno buscarPorId(Long id);
    void salvar(Aluno aluno);
    void deletar(Long id);
}
