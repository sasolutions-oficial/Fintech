package DAO;
import Model.Address;
import Model.User;
//import com.google.gson.Gson;
import config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AddressDAO implements Dao<Address>{
//    Gson gson = new Gson();

    @Override
    public Optional<Address> get(String id) {
        Address address = new Address();
        String query = "SELECT t_fin_address.ind_endereco, t_fin_address.num_cep, t_fin_address.nom_logradouro, t_fin_address.num_residencial, t_fin_address.txt_complemento, t_fin_address.nom_bairro, t_fin_address.nom_cidade, t_fin_address.txt_uf, t_fin_address.cod_user FROM t_fin_user INNER JOIN t_fin_address ON t_fin_user.cod_user = t_fin_address.cod_user WHERE t_fin_user.cod_user = " + "'" + id + "'";
        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){
            if(!result.next()){
                return Optional.of(address);
            }

            address.setId(result.getString("ind_endereco"));
            address.setCep(result.getString("num_cep"));
            address.setLogradouro(result.getString("nom_logradouro"));
            address.setNumero(result.getString("num_residencial"));
            address.setComplemento(result.getString("txt_complemento"));
            address.setBairro(result.getString("nom_bairro"));
            address.setCidade(result.getString("nom_cidade"));
            address.setUf(result.getString("txt_uf"));
            address.setIdUsuario(result.getString("cod_user"));

//            System.out.println(gson.toJson(address));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.of(address);
    }

    @Override
    public List<Address> getAll() {
        return List.of();
    }

    @Override
    public List<Address> getAll(String id) {
        return List.of();
    }

    @Override
    public void save(Address address) {
        if(!Objects.equals(address.getComplemento(), "")){
            String query = "INSERT INTO t_fin_address (num_cep, nom_logradouro, num_residencial, txt_complemento, nom_bairro, nom_cidade, txt_uf, cod_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection conexao = ConnectionFactory.getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)){

                statement.setString(1, address.getCep());
                statement.setString(2, address.getLogradouro());
                statement.setString(3, address.getNumero());
                statement.setString(4, address.getComplemento());
                statement.setString(5, address.getBairro());
                statement.setString(6, address.getCidade());
                statement.setString(7, address.getUf());
                statement.setString(8, address.getIdUsuario());
                statement.executeUpdate();

                System.out.println("Endereço cadastrado com sucesso");

            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            String query = "INSERT INTO t_fin_address (ind_endereco, num_cep, nom_logradouro, num_residencial, nom_bairro, nom_cidade, txt_uf, cod_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(Connection conexao = ConnectionFactory.getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)){

                statement.setString(1, address.getId());
                statement.setString(2, address.getCep());
                statement.setString(3, address.getLogradouro());
                statement.setString(4, address.getNumero());
                statement.setString(6, address.getBairro());
                statement.setString(7, address.getCidade());
                statement.setString(8, address.getUf());
                statement.setString(9, address.getIdUsuario());

                System.out.println("Endereço cadastrado com sucesso");

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM t_fin_address WHERE cod_user = " + "'" + id + "'";
        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){
            result.next();
            System.out.println(id + " deletado com sucesso");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
