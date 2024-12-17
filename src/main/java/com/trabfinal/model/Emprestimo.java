package com.trabfinal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Aluno aluno;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private boolean devolvido = false; // Definindo padrão como 'false'

    private LocalDate dataPrazoMaximo;

    @PrePersist
    public void prePersist() {
        if (this.dataPrazoMaximo == null) {
            this.dataPrazoMaximo = this.dataEmprestimo != null
                    ? this.dataEmprestimo.plusDays(3)
                    : LocalDate.now().plusDays(3);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public LocalDate getDataPrazoMaximo() {
        return dataPrazoMaximo;
    }

    public void setDataPrazoMaximo(LocalDate dataPrazoMaximo) {
        this.dataPrazoMaximo = dataPrazoMaximo;
    }
}
