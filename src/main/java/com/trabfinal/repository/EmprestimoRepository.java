package com.trabfinal.repository;

import com.trabfinal.model.Aluno;
import com.trabfinal.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByAlunoAndDevolvidoFalse(Aluno aluno);
}
