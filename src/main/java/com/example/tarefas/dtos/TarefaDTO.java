package com.example.tarefas.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaDTO {

    @NotBlank(message = "O título é obrigatório.")
    String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    String descricao;

    LocalDate dataVencimento;

}
