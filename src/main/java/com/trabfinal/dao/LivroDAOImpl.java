package com.trabfinal.dao;

import com.trabfinal.model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LivroDAOImpl implements LivroDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Livro> listarTodos() {
        return entityManager.createQuery("FROM Livro", Livro.class).getResultList();
    }

    @Override
    public Livro buscarPorId(Long id) {
        return entityManager.find(Livro.class, id);
    }

    @Override
    @Transactional
    public void salvar(Livro livro) {
        if (livro.getId() == null) {
            entityManager.persist(livro);
        } else {
            entityManager.merge(livro);
        }
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Livro livro = buscarPorId(id);
        if (livro != null) {
            entityManager.remove(livro);
        }
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        return entityManager.createQuery(
                "SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo)", Livro.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
    }
}
