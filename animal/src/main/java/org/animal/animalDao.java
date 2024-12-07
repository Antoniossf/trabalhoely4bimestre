package org.animal;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class animalDao {
	
	public void salvar(animal p) {
		if (p.getidanimal()>0) {
			alterar(p);
		}else {
			inserir(p);
		}
	}
	
	public Retorno1 inserir(animal p) {
		//lista.add(p);
		//abre a conexao com o bd
		Conexao con = new Conexao();
		
		Retorno1Dao retornoDao = new Retorno1Dao();
		try {
			String sql = "INSERT INTO animal (nome,raca ,nomedono,telefonedono)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement prep = con.getConnection().prepareStatement(sql);
			prep.setString(1,  p.getnome());
			prep.setString(2,  p.getraca());
			prep.setString(3,  p.getnomedono());
			prep.setString(4,  p.gettelefonedono());
			prep.execute();
			
			String mensagem = "Inserido com sucesso!";
			Boolean resposta = true;
			
			Retorno1 retorno = retornoDao.RetornoJson(resposta, mensagem);
			
			//fecha a conexao com o banco de dados 
			con.desconecta();
			return retorno;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//fecha a conexao com o banco de dados 
			con.desconecta();
			String mensagem = "Falha ao inserir!";
			Boolean resposta = false;
			
			Retorno1 retorno = retornoDao.RetornoJson(resposta, mensagem);
			
			return retorno;
		}
	}
	public LinkedList<animal> listar(String pesquisa) {
		//return lista;
		LinkedList<animal> lista = new LinkedList<animal>();
		Conexao con = new Conexao();
		try {
			String sql = "SELECT * FROM animal "
					+ "WHERE nome like ? "
					+ "ORDER BY nome";
			PreparedStatement sta = con.getConnection().prepareStatement(sql);
			sta.setString(1,  "%" + pesquisa + "%");
			
			ResultSet res = sta.executeQuery();
			while (res.next()) {
				animal p = new animal();
				p.setidanimal(res.getInt("idanimal"));
				p.setnome(res.getString("nome"));
				p.setraca(res.getString("raca"));
				p.setnomedono(res.getString("nomedono"));
				p.settelefonedono(res.getString("telefonedono"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return lista;
	}
	
	public Retorno1 alterar(animal p) {
		Conexao con = new Conexao();
		
		Retorno1Dao retornoDao = new Retorno1Dao();
		
		try {
			String sql = "UPDATE animalSET"
					+" nome = ?, raca= ?," 
					+ "nomedono = ? , telefonedono= ? "
					+ "WHERE idanimal = ?";
			PreparedStatement prep = con.getConnection().prepareStatement(sql);
			prep.setString(1, p.getnome());
			prep.setString(2, p.getraca());
			prep.setString(3, p.getnomedono());
			prep.setString(4, p.gettelefonedono());
			prep.setInt(5, p.getidanimal());
			prep.execute();
			
			String mensagem = "Alterado com sucesso!";
			Boolean resposta = true;
			
			Retorno1 retorno = retornoDao.RetornoJson(resposta, mensagem);
			
			//fecha a conexao com o banco de dados 
			con.desconecta();
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
			String mensagem = "Falha ao alterar!";
			Boolean resposta = false;
			
			Retorno1 retorno = retornoDao.RetornoJson(resposta, mensagem);
			
			//fecha a conexao com o banco de dados 
			con.desconecta();
			
			return retorno;
		}
		
	}
	public Retorno1 excluir(animal p) {
		Conexao con = new Conexao();
		
		Retorno1Dao retornoDao = new Retorno1Dao();
		
		try {
			String sql = "DELETE FROM animal"
					+ " WHERE idanimal = ?";
			PreparedStatement prep = con.getConnection().prepareStatement(sql);
			prep.setInt(1, p.getidanimal());
			prep.execute();
			
			String mensagem = "Excluido com sucesso!";
			Boolean resposta = true;
			
			Retorno1 retorno = retornoDao.RetornoJson(resposta, mensagem);
			
			//fecha a conexao com o banco de dados 
			con.desconecta();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			
			String mensagem = "Falha ao excluir!";
			Boolean resposta = false;
			
			Retorno1 retorno = retornoDao.RetornoJson(resposta, mensagem);
			
			//fecha a conexao com o banco de dados 
			con.desconecta();
			
			return retorno;
		}
		
	}
	
	public animal consultar(int id) {
		animal p = new animal();
		Conexao con = new Conexao();
		try {
			String sql = "SELECT * FROM animal WHERE idanimal = "+ id;
			Statement sta = con.getConnection().createStatement();
			ResultSet res = sta.executeQuery(sql);
			if (res.next()) {
				p.setidanimal(res.getInt("idanimal"));
				p.setnome(res.getString("nome"));
				p.setraca(res.getString("raca"));
				p.setnomedono(res.getString("nomedono"));
				p.settelefonedono(res.getString("telefonedono"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return p;
	}
}