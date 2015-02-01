/**
 * 
 */
package br.com.startup.fucapi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.startup.fucapi.model.Congresso;
import br.com.startup.fucapi.model.Organizador;
import br.com.startup.fucapi.util.ConexaoManager;

/**
 * @author JhimmyLiborio
 * @version 1
 * @since 28/01/2015
 *
 */
public class CongressoDAO {
	
	private ConexaoManager conexaoManager;
	public CongressoDAO() {
		//OBTEM UMA UNICA INSTANCIA DE UMA CLASSE DO CONEXAOMANAGER (Padro Singleton)
		conexaoManager = ConexaoManager.getIntancia();
	}

	
	//SALVAR
	public void  salvarCongresso(Congresso congresso){
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO congresso ");
		sql.append("(nome, descricao, categoria, data_inicio, hora_inicio, data_fim, hora_fim, local, valor, Cadastro_id) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? ,? )");
		
		
		try{
			Connection connection = conexaoManager.getConexaoManager();
			PreparedStatement pstm = connection.prepareStatement(sql.toString());
			pstm.setString(1, congresso.getNome());
			pstm.setString(2, congresso.getDescricao());
			pstm.setString(3, congresso.getCategoria());
			pstm.setDate(4, new Date(congresso.getData_inicio().getTime()));
			pstm.setString(5, congresso.getHora_icnio());
			pstm.setDate(6, new Date(congresso.getData_fim().getTime()));
			pstm.setString(7, congresso.getHora_fim());
			pstm.setString(8, congresso.getLocal());
			pstm.setDouble(9, congresso.getValor());
			pstm.setLong(10, congresso.getCadastro().getId()); // Congresso relacionado a um ID de cadastro de organizador
			pstm.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//lISTAR CONGRESSO DE UM ORGANIZADOR
	public List<Congresso> listarCongresso(Organizador organizador){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, descricao, categoria, data_inicio, hora_inicio, data_fim, hora_fim, local, valor, Cadastro_id ");
		sql.append("FROM congresso WHERE Cadastro_id = ? ");
		
		List<Congresso> list = null;
		try{
			Connection connection = conexaoManager.getConexaoManager();
			PreparedStatement pstm = connection.prepareStatement(sql.toString());
			pstm.setLong(1, organizador.getCadastro().getId());
			ResultSet rs =  pstm.executeQuery();
			
			list = new ArrayList<Congresso>();
			while(rs.next()){
				
				Congresso congresso = new Congresso();
				congresso.setId(rs.getLong("id"));
				congresso.setNome(rs.getString("nome"));
				congresso.setDescricao(rs.getString("descricao"));
				congresso.setCategoria(rs.getString("categoria"));
				congresso.setData_inicio(rs.getDate("data_inicio"));
				congresso.setHora_icnio(rs.getString("hora_inicio"));
				congresso.setData_fim(rs.getDate("data_fim"));
				congresso.setHora_fim(rs.getString("hora_fim"));
				congresso.setLocal(rs.getString("local"));
				congresso.setValor(rs.getDouble("valor"));
				//congresso.setCadastro();
				list.add(congresso);
			}	
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
}
