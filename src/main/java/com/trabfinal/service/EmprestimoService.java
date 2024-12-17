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

    public String realizarEmprestimo(Livro livro, Aluno aluno) {
        // Verifica se o aluno tem débitos
        if (possuiDebitos(aluno)) {
            return "O aluno possui débitos";
        }

        // Verifica se o aluno já atingiu o limite máximo de 3 livros
        if (atingiuLimiteEmprestimos(aluno)) {
            return "O aluno já emprestou o máximo de livros";
        }

        // Realiza o empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setAluno(aluno);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrazoMaximo(LocalDate.now().plusDays(3)); // Garante o prazo máximo
        emprestimo.setDevolvido(false);

        livro.setDisponivel(false);
        emprestimoRepository.save(emprestimo);

        return null; // Nenhum erro
    }

    private boolean possuiDebitos(Aluno aluno) {
        List<Emprestimo> emprestimosPendentes = emprestimoRepository.findByAlunoAndDevolvidoFalse(aluno);
        return emprestimosPendentes.stream()
                .filter(e -> e.getDataPrazoMaximo() != null) // Evita null pointer
                .anyMatch(e -> e.getDataPrazoMaximo().isBefore(LocalDate.now())); // Verifica prazo vencido
    }

    private boolean atingiuLimiteEmprestimos(Aluno aluno) {
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByAlunoAndDevolvidoFalse(aluno);
        return emprestimosAtivos.size() >= 3;
    }

    public void devolverEmprestimo(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));
        emprestimo.setDevolvido(true);
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.getLivro().setDisponivel(true);
        emprestimoRepository.save(emprestimo);
    }
}
