package br.com.cartas.repository;

import br.com.cartas.model.CardDeckComJogadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDeckComJogadorRepository extends JpaRepository<CardDeckComJogadorEntity, Long> {
}
