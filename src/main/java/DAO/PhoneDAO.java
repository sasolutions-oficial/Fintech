package DAO;

import Model.Phone;
import Model.ReturnData;
import Model.User;
//import com.google.gson.Gson;
import config.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneDAO implements Dao<Phone> {
//    Gson gson = new Gson();

    @Override
    public Optional<Phone> get(String id) {
        Phone phone = new Phone();
        String query = "SELECT * FROM t_fin_phone WHERE ind_telefone = " + "'" + id + "'";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){

            if(!result.next()){
                return Optional.of(phone);
            }

            phone.setId(result.getString("ind_telefone"));
            phone.setDdd(result.getString("num_ddd"));
            phone.setTelefone(result.getString("num_telefone"));
            phone.setRamal(result.getString("num_ramal"));
            phone.setTipoTelefone(result.getInt("ind_tipo"));
            phone.setIdUsuario(result.getString("cod_user"));

//            System.out.println(gson.toJson(phone));
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.of(phone);
    }

    @Override
    public List<Phone> getAll() {
        return List.of();
    }

    @Override
    public List<Phone> getAll(String id) {
        List<Phone> phones = new ArrayList<Phone>();
        String query = "SELECT t_fin_phone.ind_telefone, t_fin_phone.num_ddd, t_fin_phone.num_telefone, t_fin_phone.num_ramal, t_fin_phone.ind_tipo, t_fin_phone.cod_user FROM t_fin_user INNER JOIN t_fin_phone ON t_fin_user.cod_user = t_fin_phone.cod_user WHERE t_fin_user.cod_user = " + "'" + id + "'";;

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){

            while (result.next()){
                Phone phone = new Phone();
                phone.setId(result.getString("ind_telefone"));
                phone.setDdd(result.getString("num_ddd"));
                phone.setTelefone(result.getString("num_telefone"));
                phone.setRamal(result.getString("num_ramal") != null ? result.getString("num_ramal") : "");
                phone.setTipoTelefone(result.getInt("ind_tipo"));
                phone.setIdUsuario(result.getString("cod_user"));

                phones.add(phone);
            }
//            System.out.println(gson.toJson(phones));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public ReturnData save(Phone phone) {
    	ReturnData returnData = new ReturnData();

    	if(phone.getRamal() != null){
    		String query = "INSERT INTO t_fin_phone (num_ddd, num_telefone, num_ramal, ind_tipo, cod_user) values (?, ?, ?, ?, ?)";
    		try(Connection conexao = ConnectionFactory.getConnection();
	            PreparedStatement statement = conexao.prepareStatement(query)) {

	            statement.setString(1, phone.getDdd());
	            statement.setString(2, phone.getTelefone());
	            statement.setString(3, phone.getRamal());
	            statement.setInt(4, phone.getTipoTelefone());
	            statement.setString(5, phone.getIdUsuario());
	            statement.executeUpdate();

	            System.out.println("Telefone cadastrado com sucesso");
	            returnData.setIsSaved(true);
	            returnData.setMessage("Telefone cadastrado com sucesso!");
	            
	        } catch (SQLException e){
	        	returnData.setIsSaved(false);
	            returnData.setMessage(e.getMessage());
	            e.printStackTrace();
	        }
    	} else {
    		String query = "INSERT INTO t_fin_phone (num_ddd, num_telefone, ind_tipo, cod_user) values (?, ?, ?, ?)";
    		try(Connection conexao = ConnectionFactory.getConnection();
	            PreparedStatement statement = conexao.prepareStatement(query)) {

	            statement.setString(1, phone.getDdd());
	            statement.setString(2, phone.getTelefone());
	            statement.setInt(3, phone.getTipoTelefone());
	            statement.setString(4, phone.getIdUsuario());
	            statement.executeUpdate();

	            System.out.println("Telefone cadastrado com sucesso");
	            returnData.setIsSaved(true);
	            returnData.setMessage("Telefone cadastrado com sucesso!");
	            
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
    	
        String query = "DELETE FROM t_fin_phone WHERE ind_telefone = " + "'" + id + "'";
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