package com.sorteasy.sorteasy.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sorteio")
@EntityListeners(AuditingEntityListener.class)
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150) 
    private String nome;

    @CreatedDate
    @Column
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Boolean finalizado = false;

    @ManyToOne
    @JoinColumn(name = "vencedor_id")
    private Participante vencedor;

    @OneToMany(mappedBy = "sorteio")
    private List<Participante> participantes;

}
