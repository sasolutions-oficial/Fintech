

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

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

import DAO.AuthDAO;
import Model.User;


/**
 * Servlet implementation class Auth
 */
@WebServlet("/auth/login")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		Optional<User> userData = null;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD").create();
		JsonObject json = gson.fromJson(buffer.toString(), JsonObject.class);
		
		String usuario = json.get("usuario").toString().replaceAll("\"", "");
		String senha = json.get("senha").toString().replaceAll("\"", "");
		
		AuthDAO auth = new AuthDAO();
		userData = auth.login(usuario, senha);
		String json1 = gson.toJson(userData);
		
		String idOfUser = userData.get().getId() != null ? userData.get().getId() : "";
		JsonElement emptyUserParsed = new JsonParser().parse("{\"isLogged\": false,\"value\": {}}");
		JsonElement fullUserParsed = new JsonParser().parse("{\"isLogged\": true,\"user\": " + json1 + "}");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(idOfUser.isBlank() ? 400 : 200);
        response.getWriter().write(idOfUser.isBlank() ? gson.toJson(emptyUserParsed) : gson.toJson(fullUserParsed));
	}

}
