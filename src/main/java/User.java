

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.SQLException;
import java.util.Optional;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import DAO.UserDAO;
import Model.*;
//import config.ConnectionFactory;

import java.util.Date;


/**
 * Servlet implementation class User
 */
@WebServlet("/user/*")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		String id = pathInfo[1];
		
		UserDAO user = new UserDAO();
		
		Optional<Model.User> userData = user.get(id);

		Gson gson = new Gson();
		String json = gson.toJson(userData);
		
		String idOfUser = userData.get().getId() != null ? userData.get().getId() : "";
		JsonElement emptyUserParsed = new JsonParser().parse("{\"value\": {}}");
		String emptyUser = gson.toJson(emptyUserParsed);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(idOfUser.isBlank() ? 400 : 200);
        response.getWriter().write(idOfUser.isBlank() ? emptyUser : json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		Model.User userData = null;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD").create();
		JsonObject json = gson.fromJson(buffer.toString(), JsonObject.class);
		
		String email = json.get("email").toString().replaceAll("\"", "");
		String senha = json.get("senha").toString().replaceAll("\"", "");
		String nome = json.get("nome").toString().replaceAll("\"", "");
		String cpf = json.get("cpf").toString().replaceAll("\"", "");
		String nascimento = json.get("nascimento").toString().replaceAll("\"", "");
		String naturalidade = json.get("naturalidade").toString().replaceAll("\"", "");
		String ufNascimento = json.get("ufNascimento").toString().replaceAll("\"", "");
		String filiacao1 = json.get("filiacao1").toString().replaceAll("\"", "");
		String filiacao2 = json.get("filiacao2").toString().replaceAll("\"", "");
		int sexo = json.get("sexo").getAsInt();
		int estadoCivil = json.get("estadoCivil").getAsInt();
		int raca = json.get("raca").getAsInt();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		
		if(nascimento != null) {
			try {
				date = formatter.parse(nascimento);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());			
			userData = new Model.User("", email, senha, nome, cpf, sqlDate, naturalidade, ufNascimento, filiacao1, filiacao2, sexo, estadoCivil, raca);
		}else {
			userData = new Model.User("", email, senha, nome, cpf);
		}
		

		UserDAO user = new UserDAO();
		ReturnData returnData = user.save(userData);
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
		
		UserDAO user = new UserDAO();
		
		ReturnData returnData = user.delete(id);

		Gson gson = new Gson();
		String json = gson.toJson(returnData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(returnData.getIsSaved() ? 200 : 400);
        response.getWriter().write(json);
		
	}

}
