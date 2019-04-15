package com.github.psinalberth.checklist.job;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.psinalberth.checklist.model.ListaAtividade;
import com.github.psinalberth.checklist.model.Periodicidade;
import com.github.psinalberth.checklist.model.Tarefa;
import com.github.psinalberth.checklist.service.ListaAtividadeRepository;

@Component
@EnableScheduling
public class Agendador {
	
	final static String A_CADA_10SEG = "0/10 * * * * *";
	final static String A_CADA_15SEG = "0/15 * * * * *";
	final static String A_CADA_HORA = "0 0 0/1 * * *";
	final static String DIARIO = "0 0 0 0/1 * *";
	
	@Autowired
	private ListaAtividadeRepository atividades;
	
	@Scheduled(cron = A_CADA_10SEG)
	public void verificarCada10Segundos() {
		System.out.println("Executo a cada 10: " + LocalDateTime.now());
	}
	
	@Scheduled(cron = A_CADA_15SEG)
	public void concluirTarefasDiarias() {
		
		System.out.println("Rotina para concluir tarefas sendo executada");
		
		Iterable<ListaAtividade> lista = atividades.todasParaConcluir(Periodicidade.DIARIO);
		Iterator<ListaAtividade> it = lista.iterator();
		
		System.out.println("Checklists a serem checados: " + ((Collection<?>)lista).size());
		
		int i = 0;
		
		while (it.hasNext()) {
			
			boolean tarefasConcluidas = true;
			
			ListaAtividade atv = it.next();
			
			System.out.println(String.format("Checklist %d - Tarefas: %d", i + 1, atv.getTarefas().size()));
			
			for (Tarefa t : atv.getTarefas()) {
				
				if (!t.isConcluido()) {
					
					tarefasConcluidas = false;
					break;
				}
			}
			
			if (tarefasConcluidas) {
				
				atv.setConcluido(true);
				atv.setDataConclusao(new Date());
				
				atividades.save(atv);
				
				System.out.println(String.format("Checklist %d concluído", i + 1));
			}
			
			i += 1;
		}
		
		System.out.println("Whoa 15: " + LocalDateTime.now());
	}
}
