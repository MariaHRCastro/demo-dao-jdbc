package model.dao;

import java.util.List;

import model.entidades.Vendedor;

public interface VendedorDao {

	//INSERIR OBJTO DE NO BANCO
		void inserir(Vendedor vendedor);
		
		void atualizar(Vendedor vendedor);
		
		void deletarPorId (Vendedor vendedor);
		
		//RETORNAR UMA BUSCA POR ID
		Vendedor acharPorId(Integer id);
		
		List <Vendedor> retornarTodos();
	
}
