package com.example.tarefas.excecoes;

public class TarefaAdicionada extends RuntimeException {

    public TarefaAdicionada(String msg) {
        super(msg);
    }
}
