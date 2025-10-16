package com.sorteasy.sorteasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sorteasy.sorteasy.entity.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long>{

    //Exibir histórico de vencedores
    @Query("SELECT p FROM Participante p INNER JOIN Sorteio s ON p.id = s.vencedor.id")
    List<Participante> findAllVencedores();

    //Exibir todos os participantes de um sorteio específico
    @Query("SELECT p FROM Participante p WHERE p.sorteio.id = :sorteioId")
    List<Participante> findAllBySorteioId(Long sorteioId);
}
