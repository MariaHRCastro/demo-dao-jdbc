package aplicativo;

import java.util.Date;

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
		
		Vendedor vendedor = vendedorDao.acharPorId(3);
		
		System.out.println(vendedor);
		
	}

}
