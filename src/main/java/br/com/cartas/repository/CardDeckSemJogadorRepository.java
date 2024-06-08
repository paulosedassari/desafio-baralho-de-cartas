package br.com.cartas.repository;

import br.com.cartas.model.CardDeckSemJogadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDeckSemJogadorRepository extends JpaRepository<CardDeckSemJogadorEntity, Long> {
}
