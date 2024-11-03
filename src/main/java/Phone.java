

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Model.ReturnData;
import DAO.PhoneDAO;

/**
 * Servlet implementation class Phone
 */
@WebServlet("/phone/*")
public class Phone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Phone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] pathInfo = request.getPathInfo().split("/");
		String id = pathInfo[1];
		
		PhoneDAO phone = new PhoneDAO();
		
		List<Model.Phone> phones = phone.getAll(id);

		Gson gson = new Gson();
		String json = gson.toJson(phones);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		Model.Phone phoneData = null;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		Gson gson = new GsonBuilder().create();
		JsonObject json = gson.fromJson(buffer.toString(), JsonObject.class);
		
		String num_ddd = json.get("num_ddd").toString().replaceAll("\"", "");
		String num_telefone = json.get("num_telefone").toString().replaceAll("\"", "");
		String num_ramal = json.get("num_ramal") != null ? json.get("num_ramal").toString().replaceAll("\"", "") : "";
		int ind_tipo = json.get("ind_tipo").getAsInt();
		String cod_user = json.get("cod_user").toString().replaceAll("\"", "");
	
		if(num_ramal != null) {
			phoneData = new Model.Phone("", num_ddd, num_telefone, num_ramal, ind_tipo, cod_user);
		}else {
			phoneData = new Model.Phone("", num_ddd, num_telefone, ind_tipo, cod_user);
		}
		

		PhoneDAO phone = new PhoneDAO();
		ReturnData returnData = phone.save(phoneData);
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
		
		PhoneDAO phone = new PhoneDAO();
		
		ReturnData returnData = phone.delete(id);

		Gson gson = new Gson();
		String json = gson.toJson(returnData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(returnData.getIsSaved() ? 200 : 400);
        response.getWriter().write(json);
	}

}
