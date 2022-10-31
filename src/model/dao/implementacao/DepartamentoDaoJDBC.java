package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {
	
	private Connection conn;
	
	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	//IMPLANTAÇÃO DE CADA MÉTODO DE ALTERAÇÃO DO BANCO 

	@Override
	public void inserir(Departamento departamento) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department (name) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, departamento.getNome());
			
			int linhasAfetadas = ps.executeUpdate();
			
			if (linhasAfetadas>0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					departamento.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("ERRO INESPERADO! Nenhuma linha foi afetada!");}
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}finally {
				DB.closeStatement(ps);
			}
			
		}
		
	@Override
	public void atualizar(Departamento departamento) {

		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			
			ps.setString(1, departamento.getNome());
			ps.setInt(2, departamento.getId());
			
			ps.executeUpdate();	

		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deletarPorId(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Departamento acharPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> retornarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
