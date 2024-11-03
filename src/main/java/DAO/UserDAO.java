package DAO;

import Model.User;
import Model.ReturnData;
//import com.google.gson.Gson;
import config.ConnectionFactory;

import java.sql.*;
import java.util.Optional;
import java.util.*;

public class UserDAO implements Dao<User> {
//    Gson gson = new Gson();

    @Override
    public Optional<User> get(String id) {
        User user = new User();
        String query = "SELECT * FROM t_fin_user WHERE cod_user = " + "'" + id + "'";

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

//            System.out.println(gson.toJson(user));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        String query = "SELECT * FROM t_fin_user";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){

            while (result.next()){
                User user = new User();
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

                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
//        System.out.println(gson.toJson(users));
        return users;
    }

    @Override
    public List<User> getAll(String id) {
        return List.of();
    }
    

    @Override
    public ReturnData save(User user) {
    	ReturnData returnData = new ReturnData();
    	
    	if(user.getNascimento() != null){
            String query = "INSERT INTO t_fin_user (cod_email, txt_password, nom_user, num_cpf, dat_nascimento, txt_naturalidade, txt_uf, nom_filiacao1, nom_filiacao2, ind_sexo, ind_estado_civil, ind_raca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection conexao = ConnectionFactory.getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)){

                statement.setString(1, user.getEmail());
                statement.setString(2, user.getSenha());
                statement.setString(3, user.getNome());
                statement.setString(4, user.getCpf());
                statement.setDate(5, user.getNascimento());
                statement.setString(6, user.getNaturalidade());
                statement.setString(7, user.getUfNascimento());
                statement.setString(8, user.getFiliacao1());
                statement.setString(9, user.getFiliacao2());
                statement.setInt(10, user.getSexo());
                statement.setInt(11, user.getEstadoCivil());
                statement.setInt(12, user.getRaca());
                statement.executeUpdate();
                
                System.out.println("Usuário cadastrado com sucesso!");
                returnData.setIsSaved(true);
                returnData.setMessage("Usuário cadastrado com sucesso!");
                
            } catch (SQLException e){
            	returnData.setIsSaved(false);
                returnData.setMessage(e.getMessage());
                e.printStackTrace();
            }
        } else{
            String query = "INSERT INTO t_fin_user (cod_email, txt_password, nom_user, num_cpf) VALUES (?, ?, ?, ?)";
            try(Connection conexao = ConnectionFactory.getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)){

                statement.setString(1, user.getEmail());
                statement.setString(2, user.getSenha());
                statement.setString(3, user.getNome());
                statement.setString(4, user.getCpf());
                statement.executeUpdate();

                System.out.println("Usuário cadastrado com sucesso!");
                returnData.setIsSaved(true);
                returnData.setMessage("Usuário cadastrado com sucesso!");
                

            } catch (SQLException e){
            	returnData.setIsSaved(false);
                returnData.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
		return returnData;

    }

    @Override
    public ReturnData delete(String id) {
    	ReturnData returnData = new ReturnData();
    	
        String query = "DELETE FROM t_fin_user WHERE cod_user = " + "'" + id + "'";
        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){
            result.next();
            System.out.println(id + " deletado com sucesso");
            returnData.setIsSaved(true);
            returnData.setMessage(id + " deletado com sucesso");
        }catch (SQLException e){
        	returnData.setIsSaved(false);
            returnData.setMessage(e.getMessage());
            e.printStackTrace();
        }
		return returnData;
    }
}
