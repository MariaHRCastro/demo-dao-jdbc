package aplicativo;

import java.util.Scanner;

import model.dao.DaoFabrica;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class Programa2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		DepartamentoDao departamentoDao = DaoFabrica.criarDepartamentoDao();
				
		/*
		System.out.println("===TESTE DE DEPARTAMENTO 1 - INSERÇÃO ===");
		
		System.out.print("Departamento a ser inserido: ");
		String nome = sc.nextLine();
		
		Departamento dep = new Departamento(null, nome);
		
		departamentoDao.inserir(dep);
		
		System.out.println("Departamento Inserido! Id = "+dep.getId());
		*/
		
		System.out.println("===TESTE 2 - TESTE DE ATUALIZAÇÃO ====");
		System.out.println();
		System.out.print("Insira o Id a ser deletado: ");
		Integer id = sc.nextInt();
		
		departamentoDao.deletarPorId(id);
		
		sc.close();
		
	}

}
