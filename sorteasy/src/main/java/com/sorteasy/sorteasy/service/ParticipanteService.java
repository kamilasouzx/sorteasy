package com.sorteasy.sorteasy.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sorteasy.sorteasy.dto.ParticipanteDto;
import com.sorteasy.sorteasy.entity.Participante;
import com.sorteasy.sorteasy.entity.Sorteio;
import com.sorteasy.sorteasy.repository.ParticipanteRepository;
import com.sorteasy.sorteasy.repository.SorteioRepository;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private SorteioRepository sorteioRepository;

    //TAREFA 3:
    //metodo para deletar um participante pelo ID 
    public void deleteById(Long id) {
        sorteioRepository.deleteById(id);
    }

    //metodo para exibir historico de vencedores
    public List<ParticipanteDto> findAllVencedores() {
        List<Participante> vencedores = participanteRepository.findAllVencedores();
        List<ParticipanteDto> vencedoresDto = new ArrayList<>();
    
        for (Participante participante : vencedores) {
            vencedoresDto.add(toDTO(participante));
        }
    
        return vencedoresDto;
    }
    
    //metodo para exibir todos os participantes de um sorteio específico
    public List<ParticipanteDto> findAllBySorteioId(Long sorteioId) {
        Sorteio sorteio = sorteioRepository.findById(sorteioId).orElseThrow();
        List<Participante> participantes = sorteio.getParticipantes();
        List<ParticipanteDto> participanteDtos = new ArrayList<>();
        for (Participante participante : participantes) {
            participanteDtos.add(toDTO(participante));
        }
        return participanteDtos;
    }

    //metodo para criar um novo participante
    public ParticipanteDto save(ParticipanteDto participanteDto) {
        Participante participante = toEntity(participanteDto);
        participante = participanteRepository.save(participante);
        return toDTO(participante);
    }

    //metodo para listar todos os participantes
    public List<ParticipanteDto> findAll() {
        List<Participante> participantes = participanteRepository.findAll();
        List<ParticipanteDto> participanteDtos = new ArrayList<>();
        for (Participante participante : participantes) {
            participanteDtos.add(toDTO(participante));
        }
        return participanteDtos;
    }

    //inscrever um participante em um sorteio
    public ParticipanteDto inscreverParticipante(ParticipanteDto dto) {
        Participante participante = toEntity(dto);
        participante = participanteRepository.save(participante);
        return toDTO(participante);
    }

    public ParticipanteDto toDTO(Participante participante){
        ParticipanteDto dto = new ParticipanteDto();
        BeanUtils.copyProperties(participante, dto);
        return dto;
        
    }
    public Participante toEntity(ParticipanteDto dto){
        Participante participante = new Participante();
        BeanUtils.copyProperties(dto, participante);
        return participante;
    }
}
