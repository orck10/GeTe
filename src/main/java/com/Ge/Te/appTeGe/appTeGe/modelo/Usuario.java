package com.Ge.Te.appTeGe.appTeGe.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Usuario")
public class Usuario {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "usu_Id")
	@JsonView({View.All.class, View.Alternative.class})
	private int id;
	
	@Column(name = "usu_Nome", length = 30, nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private String nome;
	
	@Column(name = "usu_Senha", length = 30, nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Relação_2", 
    	joinColumns = { @JoinColumn(name = "usu_Id") }, 
    	inverseJoinColumns = { @JoinColumn(name = "perf_Id") })
	@JsonView({View.Main.class, View.Alternative.class})
	@XmlElement(name = "Perfil")
	private List<Perfil> perfis;
	
	private boolean temPerfil(Perfil p){
		for(Perfil i: this.perfis){
			if(i.getId() == p.getId()){
				return true;
			}
		}
		return false;
	}
	public void addPerfil(Perfil p){
		if(!this.temPerfil(p)){
			this.perfis.add(p);
		}
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
