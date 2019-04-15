package com.github.psinalberth.checklist.controller;

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

import com.github.psinalberth.checklist.model.Tarefa;
import com.github.psinalberth.checklist.service.TarefaRepository;

@RestController
@RequestMapping(path = "/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaRepository repository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Tarefa> todas() {
		return repository.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tarefa> salvar(@RequestBody Tarefa tarefa) {
		
		if (tarefa == null)
			return ResponseEntity.badRequest().build();
					
		Long id = tarefa.getId();
		
		if (id == null) 
			return new ResponseEntity<Tarefa>(repository.save(tarefa), HttpStatus.CREATED);
			
		return new ResponseEntity<Tarefa>(repository.save(tarefa), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tarefa> porId(@PathVariable Long id) {
		
		Optional<Tarefa> tarefa = repository.findById(id);
		
		if (tarefa.isPresent()) {
			return new ResponseEntity<Tarefa>(tarefa.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Tarefa>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/{id}/concluir")
	public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
		
		Optional<Tarefa> tarefa = repository.findById(id);
		
		if (tarefa.isPresent()) {
			
			tarefa.get().setDataConclusao(new Date());
			tarefa.get().setConcluido(true);
			
			repository.save(tarefa.get());
			
			return new ResponseEntity<Tarefa>(tarefa.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Tarefa>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/{id}/reabrir")
	public ResponseEntity<Tarefa> reabrir(@PathVariable Long id) {
		
		Optional<Tarefa> tarefa = repository.findById(id);
		
		if (tarefa.isPresent()) {
			
			tarefa.get().setDataConclusao(null);
			tarefa.get().setConcluido(false);
			
			repository.save(tarefa.get());
			
			return new ResponseEntity<Tarefa>(tarefa.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Tarefa>(HttpStatus.NOT_FOUND);
	}
}