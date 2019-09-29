package com.test.dataformat.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Pessoa {
	
	private int id;
	private String nome;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataNasc;
	
	public Pessoa() {
		
	}

	public Pessoa(int id, String nome, LocalDate dataNasc) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNasc = dataNasc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataNasc=" + dataNasc + "]";
	}
	
}
