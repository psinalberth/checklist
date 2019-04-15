package com.github.psinalberth.checklist.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.psinalberth.checklist.model.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, Long> {
	
	public Optional<Tarefa> findByDescricao(String descricao);
}
