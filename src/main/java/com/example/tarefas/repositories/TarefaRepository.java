package com.example.tarefas.repositories;

import com.example.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    <Optional>Tarefa findByTitulo(String titulo);
}
