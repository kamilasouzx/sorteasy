package com.sorteasy.sorteasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sorteasy.sorteasy.entity.Sorteio;

public interface SorteioRepository extends JpaRepository<Sorteio, Long>{
    
    //TAREFA 1:
    //Listar apenas sorteios que não foram finalizados
    @Query("SELECT s FROM Sorteio s WHERE s.finalizado = false")
    List<Sorteio> findAllAtivos();

    //TAREFA 2:
    //retornar todos os sorteios que já possuem um vencedor definido
    @Query("SELECT s FROM Sorteio s WHERE s.vencedor IS NOT NULL")
    List<Sorteio> findAllComVencedor();
}
