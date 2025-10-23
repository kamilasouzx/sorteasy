package com.sorteasy.sorteasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorteasy.sorteasy.dto.ParticipanteDto;
import com.sorteasy.sorteasy.dto.SorteioDto;
import com.sorteasy.sorteasy.service.SorteioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sorteio")
public class SorteioController {
    @Autowired
    SorteioService service;

    //Listar todos os sorteios
    @GetMapping
    public List<SorteioDto> findAll(){
        return service.findAll();
    }

    //Criar um novo sorteio
    @PostMapping
    public SorteioDto save(
            @RequestBody @Valid SorteioDto sorteioCreateDTO ){
        return service.save(sorteioCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id")Long id){
        service.deleteById(id);
    }

    //metodo para realizar o sorteio e definir o vencedor aleatoriamente
    @GetMapping("/{id}/realizar")
    public ParticipanteDto realizarSorteio(@PathVariable Long id) {
        return service.realizarSorteio(id);
    }

    //TAREFA 1:
    //Listar apenas sorteios que não foram finalizados
    @GetMapping("/ativos")
    public List<SorteioDto> findAllAtivos(){
        return service.findAllAtivos();
    }

    //TAREFA 2:
    //retornar todos os sorteios que já possuem um vencedor definido
    @GetMapping("/vencedores")
    public List <SorteioDto> findAllComVencedor(){
        return service.findAllComVencedor();
    }
}

