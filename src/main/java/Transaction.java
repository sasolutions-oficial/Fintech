

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DAO.TransactionDAO;
import Model.ReturnData;

/**
 * Servlet implementation class Transactions
 */
@WebServlet("/transaction/*")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	TimeZone tz = TimeZone.getTimeZone("GMT-3");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
//	df.setTimeZone(tz);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		String id = pathInfo[1];
		
		TransactionDAO transaction = new TransactionDAO();
		
		Optional<Model.Transaction> transactionData = transaction.get(id);

		Gson gson = new Gson();
		String json = gson.toJson(transactionData);
		
		String idOfTransaction = transactionData.get().getId() != null ? transactionData.get().getId() : "";
		JsonElement emptyUserParsed = new JsonParser().parse("{\"value\": {}}");
		String emptyUser = gson.toJson(emptyUserParsed);
		
//		System.out.print(json);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(idOfTransaction.isBlank() ? 400 : 200);
        response.getWriter().write(idOfTransaction.isBlank() ? emptyUser : json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		Model.Transaction transactionData = null;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.S").create();
		JsonObject json = gson.fromJson(buffer.toString(), JsonObject.class);
		
		String descricao = json.get("descricao").toString().replaceAll("\"", "");
		double valor = json.get("valor").getAsDouble();
		int tipo = json.get("tipo").getAsInt();
		String data = json.get("data").toString().replaceAll("\"", "");
		String idUsuario = json.get("idUsuario").toString().replaceAll("\"", "");
		
//		System.out.print(data);

		transactionData = new Model.Transaction("", descricao, valor, tipo, data, idUsuario);
		
		TransactionDAO transaction = new TransactionDAO();
		ReturnData returnData = transaction.save(transactionData);
		String json1 = gson.toJson(returnData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(returnData.getIsSaved() ? 200 : 400);
		response.getWriter().write(json1);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		String id = pathInfo[1];
		
		TransactionDAO transaction = new TransactionDAO();
		
		ReturnData returnData = transaction.delete(id);

		Gson gson = new Gson();
		String json = gson.toJson(returnData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(returnData.getIsSaved() ? 200 : 400);
        response.getWriter().write(json);
	}

}
