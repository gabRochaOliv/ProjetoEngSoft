package com.trabfinal.service;

import com.trabfinal.model.Aluno;
import com.trabfinal.model.Emprestimo;
import com.trabfinal.model.Livro;
import com.trabfinal.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo realizarEmprestimo(Livro livro, Aluno aluno) {
        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro indisponível para empréstimo.");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setAluno(aluno);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDevolvido(false);

        livro.setDisponivel(false); // Marca o livro como indisponível
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolverEmprestimo(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com o ID: " + emprestimoId));

        if (emprestimo.isDevolvido()) {
            throw new RuntimeException("O empréstimo já foi devolvido.");
        }

        emprestimo.setDevolvido(true);
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.getLivro().setDisponivel(true); // Marca o livro como disponível

        return emprestimoRepository.save(emprestimo);
    }
}
