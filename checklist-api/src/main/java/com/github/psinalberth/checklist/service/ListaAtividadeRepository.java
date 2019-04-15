package com.github.psinalberth.checklist.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.psinalberth.checklist.model.ListaAtividade;
import com.github.psinalberth.checklist.model.Periodicidade;

public interface ListaAtividadeRepository extends CrudRepository<ListaAtividade, Long> {
	
	public Iterable<ListaAtividade> findByPeriodicidade(Periodicidade periodicidade);
	
	@Query("select distinct a from ListaAtividade a join fetch a.tarefas where a.periodicidade = ?1")
	public Iterable<ListaAtividade> todasParaConcluir(Periodicidade periodicidade);
}
