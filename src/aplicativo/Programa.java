package aplicativo;

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
		
		//A IMPLEMENTAÇÃO FICA PROTEGIDA, O PROGRAMA SÓ LIDA COM  A INTERFACE E COM AS CLASSES AUXILIARES.*/
		
		VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();
		
		System.out.println("=== TESTE NÚMERO 1: Achar Vendedor pelo ID ===");
		
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
		
	}

}
