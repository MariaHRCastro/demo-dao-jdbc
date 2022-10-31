package model.dao;

import java.util.List;

import model.entidades.Departamento;

public interface DepartamentoDao {

	//INSERIR OBJTO DE NO BANCO
	void inserir(Departamento departamento);
	
	void atualizar(Departamento departamento);
	
	void deletarPorId (Integer id);
	
	//RETORNAR UMA BUSCA POR ID
	Departamento acharPorId(Integer id);
	
	List <Departamento> retornarTodos();
	
	
}
