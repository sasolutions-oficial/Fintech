package DAO;

import Model.User;
import config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthDAO {
	public Optional<User> login(String usuario, String senha){
		User user = new User();
		
		String query = "SELECT * FROM t_fin_user WHERE cod_email = " + "'" + usuario + "' AND txt_password = " + "'" + senha + "'";
		try(Connection conexao = ConnectionFactory.getConnection();
	            PreparedStatement statement = conexao.prepareStatement(query);
	            ResultSet result = statement.executeQuery()){
				
				if(!result.next()){
	                return Optional.of(user);
	            }
				
				user.setId(result.getString("cod_user"));
	            user.setEmail(result.getString("cod_email"));
	            user.setSenha(result.getString("txt_password"));
	            user.setNome(result.getString("nom_user"));
	            user.setCpf(result.getString("num_cpf"));
	            user.setNascimento(result.getDate("dat_nascimento"));

	            user.setNaturalidade(result.getString("txt_naturalidade") != null ? result.getString("txt_naturalidade") : "");
	            user.setUfNascimento(result.getString("txt_uf") != null ? result.getString("txt_uf") : "");
	            user.setFiliacao1(result.getString("nom_filiacao1") != null ? result.getString("nom_filiacao1") : "");
	            user.setFiliacao2(result.getString("nom_filiacao2") != null ? result.getString("nom_filiacao2") : "");

	            user.setSexo(result.getInt("ind_sexo"));
	            user.setEstadoCivil(result.getInt("ind_estado_civil"));
	            user.setRaca(result.getInt("ind_raca"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.of(user);
	}

}
