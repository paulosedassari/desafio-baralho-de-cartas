package br.com.cartas.repository;

import br.com.cartas.model.CardDeckComJogadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDeckComJogadorRepository extends JpaRepository<CardDeckComJogadorEntity, Long> {

    @Query(value = "SELECT * FROM CARD_DECK_JOGADOR WHERE NOME_JOGADOR = :nome", nativeQuery = true)
    List<CardDeckComJogadorEntity> findByName(String nome);
}
