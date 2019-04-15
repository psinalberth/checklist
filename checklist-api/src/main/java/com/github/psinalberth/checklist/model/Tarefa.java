package com.github.psinalberth.checklist.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarefa")
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa implements Serializable {
	
	private static final long serialVersionUID = 6038698252064426443L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarefa")
	@Getter @Setter
	private Long id;

	@Column(unique = true)
	@Getter @Setter
	private String descricao;
	
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
	
	@ManyToOne
	@JoinColumn(name = "id_lista_atividade")
	@Getter @Setter
	@JsonIgnore
	private ListaAtividade listaAtividade;
	
	@PrePersist
	public void prePersist() {
		this.setDataCriacao(new Date());
	}
}
