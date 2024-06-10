package br.com.cartas.service.impl;

import br.com.cartas.dto.RodadasComJogadorDto;
import br.com.cartas.dto.RodadasDto;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import br.com.cartas.repository.CardDeckComJogadorRepository;
import br.com.cartas.repository.CardDeckSemJogadorRepository;
import br.com.cartas.service.RodadasService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RodadasServiceImpl implements RodadasService {

    private final CardDeckSemJogadorRepository cardDeckSemJogadorRepository;
    private final CardDeckComJogadorRepository cardDeckComJogadorRepository;
    private final ModelMapper modelMapper;

    public RodadasServiceImpl(CardDeckSemJogadorRepository cardDeckSemJogadorRepository, CardDeckComJogadorRepository cardDeckComJogadorRepository, ModelMapper modelMapper) {
        this.cardDeckSemJogadorRepository = cardDeckSemJogadorRepository;
        this.cardDeckComJogadorRepository = cardDeckComJogadorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RodadasComJogadorDto> obterOsRegistrosSemJogador() {
        List<CardDeckSemJogadorEntity> todosOsRegistrosSemJogador = cardDeckSemJogadorRepository.findAll();
        java.lang.reflect.Type targetListType = new TypeToken<List<RodadasDto>>() {
        }.getType();
        return modelMapper.map(todosOsRegistrosSemJogador, targetListType);
    }

    @Override
    public List<RodadasComJogadorDto> obterOsRegistrosComJogador(String nome) {
        List<CardDeckComJogadorEntity> todosOsRegistrosComJogador;

        if (nome == null) {
            todosOsRegistrosComJogador = cardDeckComJogadorRepository.findAll();
        } else {
            todosOsRegistrosComJogador = cardDeckComJogadorRepository.findByName(nome);
        }

        java.lang.reflect.Type targetListType = new TypeToken<List<RodadasComJogadorDto>>() {
        }.getType();

        return modelMapper.map(todosOsRegistrosComJogador, targetListType);
    }
}
