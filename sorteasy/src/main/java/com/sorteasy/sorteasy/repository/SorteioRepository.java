package com.sorteasy.sorteasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sorteasy.sorteasy.entity.Sorteio;

public interface SorteioRepository extends JpaRepository<Sorteio, Long>{
    
    //Listar apenas sorteios que não foram finalizados
    @Query("SELECT s FROM Sorteio s WHERE s.finalizado = false")
    List<Sorteio> findAllAtivos();
}
