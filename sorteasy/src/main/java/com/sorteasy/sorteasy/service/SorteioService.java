package com.sorteasy.sorteasy.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sorteasy.sorteasy.dto.ParticipanteDto;
import com.sorteasy.sorteasy.dto.SorteioDto;
import com.sorteasy.sorteasy.entity.Participante;
import com.sorteasy.sorteasy.entity.Sorteio;
import com.sorteasy.sorteasy.repository.SorteioRepository;

@Service
public class SorteioService {
    @Autowired
    private SorteioRepository sorteioRepository;

    @Autowired ParticipanteService participanteService;

    //Listar apenas sorteios que não foram finalizados
    public List<SorteioDto> findAllAtivos(){
        List<Sorteio> sorteios = sorteioRepository.findAllAtivos();
        List<SorteioDto> sorteioDtos = new ArrayList<>();
        for (Sorteio sorteio : sorteios) {
            sorteioDtos.add(toDTO(sorteio));
    }
        return sorteioDtos;
    }

    //metodo para realizar o sorteio e definir o vencedor aleatoriamente
    public ParticipanteDto realizarSorteio(Long id) {
        Sorteio sorteio = sorteioRepository.findById(id).orElseThrow();
        
        List<Participante> participantes = sorteio.getParticipantes();

        if (participantes.size() < 2) {
            throw new IllegalStateException("O sorteio deve ter mais de 2 inscritos.");
        }

        int sorteado = (int) (Math.random() * participantes.size());
        Participante vencedor = participantes.get(sorteado);

        sorteio.setVencedor(vencedor);
        sorteio.setFinalizado(true);
        sorteioRepository.save(sorteio);
        return participanteService.toDTO(vencedor);
    }

    //metodo para criar um novo sorteio
     public SorteioDto save(SorteioDto sorteioDto) {
        Sorteio sorteio = toEntity(sorteioDto);
        sorteio = sorteioRepository.save(sorteio);
        return toDTO(sorteio);
    }
    
    //metodo para deletar um sorteio
    public void deleteById(Long id) {
        sorteioRepository.deleteById(id);
    }

    //metodo para listar todos os sorteios
    public List<SorteioDto> findAll() {
        List<Sorteio> sorteios = sorteioRepository.findAll();
        List<SorteioDto> sorteioDtos = new ArrayList<>();
        for (Sorteio sorteio : sorteios) {
            sorteioDtos.add(toDTO(sorteio));
        }
        return sorteioDtos;
    }

    public SorteioDto toDTO(Sorteio sorteio){
        SorteioDto dto = new SorteioDto();
        BeanUtils.copyProperties(sorteio, dto);
        return dto;
        
    }
    public Sorteio toEntity(SorteioDto dto){
        Sorteio sorteio = new Sorteio();
        BeanUtils.copyProperties(dto, sorteio);
        return sorteio;
    }
}
