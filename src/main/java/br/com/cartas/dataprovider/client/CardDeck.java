package br.com.cartas.dataprovider.client;

import br.com.cartas.dto.create.DadosBaralhoDto;
import br.com.cartas.dto.search.CartasDoBaralhoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cardDeck", url = "${deck.of.cards.api}")
public interface CardDeck {

    @PostMapping("/api/deck/new/shuffle/?deck_count={deckCount}")
    DadosBaralhoDto criarBaralho(@PathVariable("deckCount") int deckCount);

    @GetMapping("/api/deck/{deck_id}/draw/?count={count}")
    CartasDoBaralhoDto retirarCartasDoBaralho(@PathVariable("deck_id") String deckId, @PathVariable("count") int count);
}
