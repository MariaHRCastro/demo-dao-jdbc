package model.dao.implementacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entidades.Departamento;
import model.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;

	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// CLASSE QUE IMPLEMENTA A INTERFACE VENDEDOR DAO

	@Override
	public void inserir(Vendedor vendedor) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary,DepartmentId) "
					+"values "
					+ "( ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getEmail());
			ps.setDate(3,new java.sql.Date(vendedor.getNascimento().getTime()));
			ps.setDouble(4, vendedor.getSalarioBase());
			ps.setInt(5, vendedor.getDepartamento().getId());
			
			int linhasAfetadas = ps.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					vendedor.setId(id);
				}
				DB.closeResultSet(rs);
			}
				else {
					throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
				}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
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
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				// SE TEM PARA RETORNAR, TEM QUE INSTANCIAR AS INFORMA합ES
				Departamento dep = intanciacaoDepartamento(rs);

				Vendedor ved = instanciacaoVendedor(rs, dep);

				return ved;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	// CRIAR FUN플O A PARTE PARA INSTANCIAR
	private Departamento intanciacaoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	private Vendedor instanciacaoVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor ved = new Vendedor();
		ved.setId(rs.getInt("Id"));
		ved.setNome(rs.getString("Name"));
		ved.setEmail(rs.getString("Email"));
		ved.setSalarioBase(rs.getDouble("BaseSalary"));
		ved.setNascimento(rs.getDate("BirthDate"));
		ved.setDepartamento(dep);
		return ved;
	}

	@Override
	public List<Vendedor> retornarTodos() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");

			rs = ps.executeQuery();
			
			List <Vendedor> vendedores = new ArrayList<>();
			
			//MAP PARA IMPEDIR QUE HAJA DEPARTAMENTOS REPETIDOS
			Map<Integer,Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				//TESTE PARA VER SE DEPARTAMENTO J� EXISTE
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = intanciacaoDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendedor ved = instanciacaoVendedor(rs, dep);
				
				vendedores.add(ved);

			}
			return vendedores;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> acharPorDepartamento(Departamento departamento) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");

			ps.setInt(1, departamento.getId());
			rs = ps.executeQuery();
			
			List <Vendedor> vendedores = new ArrayList<>();
			
			//MAP PARA IMPEDIR QUE HAJA DEPARTAMENTOS REPETIDOS
			Map<Integer,Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				//TESTE PARA VER SE DEPARTAMENTO J� EXISTE
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = intanciacaoDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendedor ved = instanciacaoVendedor(rs, dep);
				
				vendedores.add(ved);

			}
			return vendedores;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
