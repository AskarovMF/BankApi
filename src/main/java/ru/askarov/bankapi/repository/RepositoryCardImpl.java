package ru.askarov.bankapi.repository;

import org.springframework.stereotype.Repository;
import ru.askarov.bankapi.model.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositoryCardImpl implements RepositoryCard {

    @PersistenceContext
    private EntityManager entityManager;

    public RepositoryCardImpl() {
    }

    public RepositoryCardImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveCard(Card card) {
        entityManager.persist(card);
    }
}
