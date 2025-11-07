package com.example.tarefas.controller;

import com.example.tarefas.dtos.TarefaDTO;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

    private TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefa(@PathVariable Long id){
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);

        TarefaDTO tarefaDTO = new TarefaDTO();
        BeanUtils.copyProperties(tarefa, tarefaDTO);
        return ResponseEntity.ok(tarefaDTO);
    }

    @PostMapping()
    public ResponseEntity<TarefaDTO> salvarTarefa(@RequestBody @Valid  TarefaDTO tarefaDTO){
        //converte o dto recebido em uma tarefa
        Tarefa tarefa = new  Tarefa();
        BeanUtils.copyProperties(tarefaDTO, tarefa);

        //salva a tarefa no service
        Tarefa novaTarefa = tarefaService.adicionarTarefa(tarefa);

        // Converter Entidade → DTO (para resposta)
        TarefaDTO taskDTO = new TarefaDTO();
        BeanUtils.copyProperties(novaTarefa, taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskDTO);
    }

    @GetMapping()
    public ResponseEntity<List<TarefaDTO>> listarTarefa(){
        List<Tarefa> tarefas = tarefaService.listarTarefas();

        List<TarefaDTO> tarefasDTO = tarefas.stream()
                .map(t -> {
                    TarefaDTO tarefaDTO = new TarefaDTO();
                    BeanUtils.copyProperties(t, tarefaDTO);
                    return tarefaDTO;
                })
                .toList();

        return ResponseEntity.ok(tarefasDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefaDTO tarefaDTO){

        Tarefa tarefa = new  Tarefa();
        BeanUtils.copyProperties(tarefaDTO, tarefa);

        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefa);

        TarefaDTO taskDTO = new TarefaDTO();
        BeanUtils.copyProperties(tarefaAtualizada, taskDTO);

        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id){
        tarefaService.deletarTarefa(id);
        TarefaDTO tarefaDTO = new TarefaDTO();

        return ResponseEntity.noContent().build();
    }
}
