package com.example.tarefas.model;

import com.example.tarefas.enuns.StatusTarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TAREFAS")
public class Tarefa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status = StatusTarefa.PENDENTE;
}
