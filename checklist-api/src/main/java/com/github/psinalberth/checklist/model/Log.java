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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
	
	private static final long serialVersionUID = 7540208734694258047L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_log")
	@Getter @Setter
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_lista_atividade")
	@Getter @Setter
	private ListaAtividade listaAtividade;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_conclusao")
	@Getter @Setter
	private Date dataConclusao;
}
