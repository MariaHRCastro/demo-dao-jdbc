package aplicativo;

import java.util.Date;
import java.util.List;

import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {

		/*Departamento dpt = new Departamento(1, "Books");
		
		Vendedor vendedor = new Vendedor(21, "Maria", "mariahribeiro@gmail.com", new Date(), 3000.00, dpt);
		
		System.out.println(dpt);
		System.out.println(vendedor);
		
		//A IMPLEMENTA��O FICA PROTEGIDA, O PROGRAMA S� LIDA COM  A INTERFACE E COM AS CLASSES AUXILIARES.*/
		
		VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();
		
		System.out.println("=== TESTE N�MERO 1: Achar Vendedor pelo ID ===");
		
		Vendedor vendedor = vendedorDao.acharPorId(3);
		
		System.out.println(vendedor);
		
		System.out.println();
		
		System.out.println("===TESTE ACHAR POR DEPARTAMENTO ===");
		Departamento departamento = new Departamento(2,null);
		List<Vendedor> lista = vendedorDao.acharPorDepartamento(departamento);
		
		for (Vendedor v: lista) {
			System.out.println(v);
		}
		
		System.out.println();
		System.out.println("===TESTE ACHAR TODOS ===");
		lista = vendedorDao.retornarTodos();
		
		for (Vendedor v: lista) {
			System.out.println(v);
		}
		
		/*System.out.println();
		System.out.println("===== TESTE DE INSER��O ====");
		Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.00, departamento);
		vendedorDao.inserir(novoVendedor);
		System.out.println("Vendedor Inserido! Id = "+novoVendedor.getId());*/
		
		System.out.println();
		System.out.println("===TESTE 5 - UPDATE ===");
		vendedor = vendedorDao.acharPorId(1);
		vendedor.setNome("Martha");
		
		vendedorDao.atualizar(vendedor);
		System.out.println();
		System.out.println("ATUALIZA��O COMPLETA");
		System.out.println(vendedor);
		
		
		
	}

}
