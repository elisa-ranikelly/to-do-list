package com.example.tarefas.excecoes;

public class RecursoNaoEncontrado extends RuntimeException{

    public RecursoNaoEncontrado(String msg){
        super(msg);
    }
}
