package br.com.cartas.configuration;

import br.com.cartas.dto.RodadasComJogadorDto;
import br.com.cartas.dto.RodadasDto;
import br.com.cartas.model.CardDeckComJogadorEntity;
import br.com.cartas.model.CardDeckSemJogadorEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(CardDeckSemJogadorEntity.class, RodadasDto.class)
                .addMappings(mapper -> {
                    mapper.map(CardDeckSemJogadorEntity::getId, RodadasDto::setId);
                    mapper.map(CardDeckSemJogadorEntity::getMaoVencedora, RodadasDto::setMaoVencedora);
                    mapper.map(CardDeckSemJogadorEntity::getDataDoJogo, RodadasDto::setDataJogo);
                });

        modelMapper.createTypeMap(CardDeckComJogadorEntity.class, RodadasComJogadorDto.class)
                .addMappings(mapper -> {
                    mapper.map(CardDeckComJogadorEntity::getId, RodadasComJogadorDto::setId);
                    mapper.map(CardDeckComJogadorEntity::getNomeJogador, RodadasComJogadorDto::setNomeJogador);
                    mapper.map(CardDeckComJogadorEntity::getMaoDaAposta, RodadasComJogadorDto::setMaoDaAposta);
                    mapper.map(CardDeckComJogadorEntity::getMaoVencedora, RodadasComJogadorDto::setMaoVencedora);
                    mapper.map(CardDeckComJogadorEntity::getDataDoJogo, RodadasComJogadorDto::setDataJogo);
                    mapper.map(CardDeckComJogadorEntity::getAcerto, RodadasComJogadorDto::setAcerto);
                });

        return modelMapper;
    }
}
