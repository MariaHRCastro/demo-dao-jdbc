package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao{
	
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	//CLASSE QUE IMPLEMENTA A INTERFACE VENDEDOR DAO
	
	@Override
	public void inserir(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor acharPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"WHERE seller.Id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				//SE TEM PARA RETORNAR, TEM QUE INSTANCIAR AS INFORMAÇÕES
				Departamento dep = new Departamento();	
				dep.setId(rs.getInt("DepartmentId"));
				dep.setNome(rs.getString("DepName"));
				
				Vendedor ved = new Vendedor();
				ved.setId(rs.getInt("Id"));
				ved.setNome(rs.getString("Name"));
				ved.setEmail(rs.getString("Email"));
				ved.setSalarioBase(rs.getDouble("BaseSalary"));
				ved.setNascimento(rs.getDate("BirthDate"));
				ved.setDepartamento(dep);
				return ved;
			}
			return null;
			
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> retornarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
