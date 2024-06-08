package br.com.cartas.service;

import br.com.cartas.dto.search.CartaBaralhoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DesafioCartasCalculoService {

    Map<String, Integer> calcularPontuacaoDasCartas(ArrayList<List<CartaBaralhoDto>> hands);
}
