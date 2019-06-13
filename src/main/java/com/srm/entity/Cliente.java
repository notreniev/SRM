package com.srm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SRM_API_CLIENTE")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "cliente_pkey", sequenceName = "SRM_API_CLIENTS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_pkey")
	@Column(name = "NRO_INT_CLIENTE")
	private Integer idCliente;
	
	@Column(name = "NOME_CLIENTE")
	private String nomeCliente;
	
	@Column(name = "LIMITE_CREDITO")
	private Float limiteCredito;
	
	@Column(name = "RISCO")
	private String risco;

	@Column(name = "TAXA")
	private Float taxa;

	public Cliente() {
	}

	public Cliente(int idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public Cliente(Integer idCliente, String nomeCliente, Float limiteCredito, String risco) {
		super();
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.limiteCredito = limiteCredito;
		this.risco = risco;
	}
	
	public Cliente(String nomeCliente, Float limiteCredito, String risco) {
		super();
		this.nomeCliente = nomeCliente;
		this.limiteCredito = limiteCredito;
		this.risco = risco;
	}
		
	public Cliente(String nomeCliente, Float limiteCredito, String risco, Float taxa) {
		super();
		this.nomeCliente = nomeCliente;
		this.limiteCredito = limiteCredito;
		this.risco = risco;
		this.taxa = taxa;
	}

	public Cliente(Integer idCliente, String nomeCliente, Float limiteCredito, String risco, Float taxa) {
		super();
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.limiteCredito = limiteCredito;
		this.risco = risco;
		this.taxa = taxa;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}

	
	public Float getTaxa() {
		return taxa;
	}

	public void setTaxa(Float taxa) {
		this.taxa = taxa;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", limiteCredito=" + limiteCredito
				+ ", risco=" + risco + ", taxa=" + taxa + "]";
	}

	
	
}
