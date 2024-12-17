package com.trabfinal.dao;

import com.trabfinal.model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoDAOImpl implements AlunoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Aluno> listarTodos() {
        return entityManager.createQuery("FROM Aluno", Aluno.class).getResultList();
    }

    @Override
    public Aluno buscarPorId(Long id) {
        return entityManager.find(Aluno.class, id);
    }

    @Override
    @Transactional
    public void salvar(Aluno aluno) {
        if (aluno.getId() == null) {
            entityManager.persist(aluno);
        } else {
            entityManager.merge(aluno);
        }
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        if (aluno != null) {
            entityManager.remove(aluno);
        }
    }

    
}
