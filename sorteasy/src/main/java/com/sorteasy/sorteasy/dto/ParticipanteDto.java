package com.sorteasy.sorteasy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sorteasy.sorteasy.entity.Sorteio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome deve ser preenchido")//n pode ser vazio, só p string
    @Size(max = 150, message =  "O nome deve ter no máximo 150 caracteres")
    private String nome;
    
    @NotBlank(message = "O email deve ser preenchido")
    @Size(max = 150, message =  "O email deve ter no máximo 150 caracteres")
    @Email(message = "Email invalido")
    private String email;
    
    @NotNull(message = "O ID do sorteio deve ser preenchido")
    private Sorteio sorteio;
}
