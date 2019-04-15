package com.github.psinalberth.checklist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lista_atividade")
@NoArgsConstructor
@AllArgsConstructor
public class ListaAtividade implements Serializable {
	
	private static final long serialVersionUID = 6094567064637570670L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lista_atividade")
	@Getter @Setter
	private Long id;
	
	@Column(unique = true)
	@Getter @Setter
	private String nome;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	@Getter @Setter
	private Periodicidade periodicidade;
	
	@Column
	@Getter @Setter
	private boolean concluido = false;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	@Getter @Setter
	private Date dataCriacao = new Date();
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_conclusao")
	@Getter @Setter
	private Date dataConclusao = new Date();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "listaAtividade", orphanRemoval = true)
	@Getter @Setter
	private List<@Valid Tarefa> tarefas = new ArrayList<Tarefa>();
	
	@PrePersist
	public void prePersist() {
		this.setDataCriacao(new Date());
	}
}
