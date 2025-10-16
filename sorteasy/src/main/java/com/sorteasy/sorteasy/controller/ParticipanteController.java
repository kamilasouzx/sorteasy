package com.sorteasy.sorteasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorteasy.sorteasy.dto.ParticipanteDto;
import com.sorteasy.sorteasy.service.ParticipanteService;
@RestController
@RequestMapping("/api/v1/participante")
public class ParticipanteController {
    @Autowired
    ParticipanteService service;

    //Exibir todos os participantes de um sorteio específico
    @GetMapping("/sorteio/{id}") 
    public List<ParticipanteDto> findAllBySorteioId(@PathVariable("id")Long sorteioId){
        return service.findAllBySorteioId(sorteioId);
    }

    //Criar um novo participante
    @PostMapping
    public ParticipanteDto save(
            @RequestBody ParticipanteDto participanteCreateDTO ){
        return service.save(participanteCreateDTO);
    }
    
    //Exibir histórico de vencedores
    @GetMapping("/vencedores")
    public List<ParticipanteDto> findAllVencedores(){
        return service.findAllVencedores();
    }

}
