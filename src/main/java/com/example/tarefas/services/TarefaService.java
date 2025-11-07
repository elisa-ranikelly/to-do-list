package com.example.tarefas.services;

import com.example.tarefas.excecoes.RecursoNaoEncontrado;
import com.example.tarefas.excecoes.TarefaAdicionada;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa buscarTarefaPorId(Long id){
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Tarefa com id " + id + " não encontrada!"));
    }

    public Tarefa adicionarTarefa(Tarefa tarefa){
        Optional<Tarefa> optional = Optional.ofNullable(tarefaRepository.findByTitulo(tarefa.getTitulo()));
        if(optional.isPresent()){
            throw  new TarefaAdicionada("Já existe uma tarefa com esse titulo!");
        }
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas(){
        return tarefaRepository.findAll();
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada){
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Tarefa com id " + id + " não encontrada!"));
        tarefa.setDataVencimento(tarefaAtualizada.getDataVencimento());
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setStatus(tarefaAtualizada.getStatus());

        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id){
        if(!tarefaRepository.existsById(id)){
            throw new RecursoNaoEncontrado("Tarefa com id " + id + " não encontrada!");
        }
        tarefaRepository.deleteById(id);
    }
}
