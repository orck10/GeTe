package com.Ge.Te.appTeGe.appTeGe.modelo;

import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Produto")
public class Produto {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pro_Id")
	@JsonView({View.All.class, View.Alternative.class})
	private int id;
	
	@Column(name = "pro_Descricao", length = 30, nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "pro_Validade")
	@JsonView({View.All.class, View.Alternative.class})
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Calendar validade;
	
	@Column(name = "pro_PrecoVenda", nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private float precoVenda;
	
	@Column(name = "pro_PrecoCompra", nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private float precoCompra; 
	
	@Column(name = "pro_Quantidade", nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private int quantidade;
	
	@Column(name = "pro_Min", nullable = false)
	@JsonView({View.All.class, View.Alternative.class})
	private int minimo;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Relação_1", 
    	joinColumns = { @JoinColumn(name = "pro_Id") }, 
    	inverseJoinColumns = { @JoinColumn(name = "for_id") })
	@JsonView({View.Main.class, View.Alternative.class})
	@XmlElement(name = "Fornecedor")
	private List<Fornecedor> listFornecedor;
	
	private boolean temFornecedor(Fornecedor f){
		for(Fornecedor i: listFornecedor){
			if(i.equals(f)){
				return true;
			}
		}
		return false;
	}
	
	public void addFornecedor(Fornecedor f){
		if(!this.temFornecedor(f)){
			this.listFornecedor.add(f);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getValidade() {
		return validade;
	}
	public void setValidade(Calendar validade) {
		this.validade = validade;
	}
	public float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}
	public float getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(float precoCompra) {
		this.precoCompra = precoCompra;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public List<Fornecedor> getListFornecedor() {
		return listFornecedor;
	}
	public void setListFornecedor(List<Fornecedor> listFornecedor) {
		this.listFornecedor = listFornecedor;
	}
	
	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}
	
	public boolean estaABaixo(){
		if(this.minimo > this.quantidade){
			return true;
		}
		return false;
	}
}
