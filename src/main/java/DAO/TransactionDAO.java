package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.sql.*;

import Model.ReturnData;
import Model.Transaction;
import config.ConnectionFactory;


public class TransactionDAO implements Dao<Transaction> {

	@Override
	public Optional<Transaction> get(String id) {
		Transaction transaction = new Transaction();
		String query = "SELECT * FROM t_fin_transactions WHERE cod_transaction = " + "'" + id + "'";
				
		try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){
			
			if(!result.next()){
                return Optional.of(transaction);
            }
			
			transaction.setId(result.getString("cod_transaction"));
			transaction.setDescricao(result.getString("nom_transacao"));
			transaction.setValor(result.getDouble("val_transacao"));
			transaction.setTipo(result.getInt("ind_transacao"));
			transaction.setData(result.getTimestamp("dat_transacao").toString());
			transaction.setIdUsuario(result.getString("cod_user"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.of(transaction);
	}

	@Override
	public List<Transaction> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAll(String id) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		String query = "SELECT * FROM t_fin_transactions WHERE cod_user = " + "'" + id + "'";
		
		try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query);
            ResultSet result = statement.executeQuery()){
			
			while (result.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(result.getString("cod_transaction"));
				transaction.setDescricao(result.getString("nom_transacao"));
				transaction.setValor(result.getDouble("val_transacao"));
				transaction.setTipo(result.getInt("ind_transacao"));
				transaction.setData(result.getTimestamp("dat_transacao").toString());
				transaction.setIdUsuario(result.getString("cod_user"));
				
				transactions.add(transaction);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//      System.out.println(gson.toJson(transactions));
		return transactions;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ReturnData save(Transaction transaction) {
		ReturnData returnData = new ReturnData();
		
		String query = "INSERT INTO t_fin_transactions (nom_transacao, val_transacao, ind_transacao, dat_transacao, cod_user) VALUES (?, ?, ?, ?, ?)";

		try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(query)){
			
//			Timestamp timestamp = null;
//			try {
//			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh24:mi:ss.SSS");
//			    Date parsedDate = dateFormat.parse(transaction.getData());
//			    timestamp = new java.sql.Timestamp(parsedDate.getTime());
//			} catch(Exception e) { //this generic but you can control another types of exception
//			    // look the origin of excption 
//				System.out.print(e.getMessage());
//				e.printStackTrace();
//			}
			
			Timestamp date = new Timestamp(System.currentTimeMillis());;

            statement.setString(1, transaction.getDescricao());
            statement.setDouble(2, transaction.getValor());
            statement.setInt(3, transaction.getTipo());
//			statement.setDate(4, transaction.getData());
			statement.setTimestamp(4, date);
            statement.setString(5, transaction.getIdUsuario());

            statement.executeUpdate();
            
            System.out.println("Transacao cadastrada com sucesso!");
            returnData.setIsSaved(true);
            returnData.setMessage("Transacao cadastrada com sucesso!");
            
        } catch (SQLException e){
        	returnData.setIsSaved(false);
            returnData.setMessage(e.getMessage());
            e.printStackTrace();
        }
		
		return returnData;
	}

	@Override
	public ReturnData delete(String id) {
		ReturnData returnData = new ReturnData();
    	
        String query = "DELETE FROM t_fin_transactions WHERE cod_transaction = " + "'" + id + "'";
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
