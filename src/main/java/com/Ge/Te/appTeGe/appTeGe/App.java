package com.Ge.Te.appTeGe.appTeGe;

import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;
import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;
import com.Ge.Te.appTeGe.appTeGe.modelo.Produto;
import com.Ge.Te.appTeGe.appTeGe.modelo.Usuario;
import com.Ge.Te.appTeGe.appTeGe.repositorio.FornecedorRepositorio;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoFornecedor;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoPerfil;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoProduto;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoUsuario;


public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
        ServicoUsuario servicoUsuario = (ServicoUsuario) context.getBean("servicoUsuario");
        ServicoPerfil servicoPerfil = (ServicoPerfil) context.getBean("servicoPerfil");
        ServicoFornecedor servicoFor = (ServicoFornecedor) context.getBean("servicoFornecedor");
        ServicoProduto servicoProd = (ServicoProduto) context.getBean("servicoProduto");
        
        servicoProd.addFronecedor(1, 1);
    }
    
}
