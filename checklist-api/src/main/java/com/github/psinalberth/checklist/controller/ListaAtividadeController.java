package com.github.psinalberth.checklist.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.psinalberth.checklist.model.ListaAtividade;
import com.github.psinalberth.checklist.model.Log;
import com.github.psinalberth.checklist.model.Periodicidade;
import com.github.psinalberth.checklist.model.Tarefa;
import com.github.psinalberth.checklist.service.ListaAtividadeRepository;
import com.github.psinalberth.checklist.service.LogRepository;

@RestController
@RequestMapping(path = "/atividades")
public class ListaAtividadeController {
	
	@Autowired
	private ListaAtividadeRepository repository;
	
	@Autowired
	private LogRepository logger;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<ListaAtividade> todas() {
		return repository.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListaAtividade> salvar(@RequestBody ListaAtividade lista) {
		
		if (lista == null)
			return ResponseEntity.badRequest().build();
					
		Long id = lista.getId();
		
		for (Tarefa tarefa : lista.getTarefas()) {
			tarefa.setListaAtividade(lista);
		}
		
		if (id == null) 
			return new ResponseEntity<ListaAtividade>(repository.save(lista), HttpStatus.CREATED);
			
		return new ResponseEntity<ListaAtividade>(repository.save(lista), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListaAtividade> porId(@PathVariable Long id) {
		
		Optional<ListaAtividade> lista = repository.findById(id);
		
		if (lista.isPresent()) {
			return new ResponseEntity<ListaAtividade>(lista.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<ListaAtividade>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/{id}/concluir")
	public ResponseEntity<ListaAtividade> concluir(@PathVariable Long id) {
		
		Optional<ListaAtividade> lista = repository.findById(id);
		
		if (lista.isPresent()) {
			
			lista.get().setDataConclusao(new Date());
			lista.get().setConcluido(true);
			
			for (Tarefa tarefa : lista.get().getTarefas()) {
				
				tarefa.setDataConclusao(lista.get().getDataConclusao());
				tarefa.setConcluido(true);
			}
			
			repository.save(lista.get());
			
			Log log = new Log();
			log.setListaAtividade(lista.get());
			log.setDataConclusao(new Date());
			
			logger.save(log);
			
			return new ResponseEntity<ListaAtividade>(lista.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<ListaAtividade>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/periodicidade/{periodo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<ListaAtividade> todasPorPeriodicidade(@PathVariable int periodo) {
		
		Periodicidade valorPeriodicidade = Periodicidade.getEnum(periodo);
		
		Iterable<ListaAtividade> ret = repository.findByPeriodicidade(valorPeriodicidade);
		
		return ret;
	}
	
	@GetMapping(path = "/encerradas/{periodo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public int getQuantidadeEncerradas(@PathVariable int periodo) {
		
		Periodicidade valorPeriodicidade = Periodicidade.getEnum(periodo);
		
		Iterable<ListaAtividade> ret = repository.findByPeriodicidade(valorPeriodicidade);
		
		return ((Collection<?>) ret).size();
	}
}