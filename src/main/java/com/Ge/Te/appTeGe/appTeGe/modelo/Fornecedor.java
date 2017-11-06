package com.Ge.Te.appTeGe.appTeGe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Fornecedor")
public class Fornecedor {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "for_Id")
	@JsonView({View.All.class, View.Alternative.class})
	private int id;
	
	@Column(name = "for_cnpj", length = 30, nullable = false)
	@JsonView({View.Main.class, View.Alternative.class})
	private String cnpj;
	
	
	@Column(name = "for_Nome", length = 30, nullable = false)
	@JsonView({View.Main.class, View.Alternative.class})
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
