

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import DAO.AddressDAO;
import DAO.UserDAO;
import Model.ReturnData;

/**
 * Servlet implementation class Address
 */
@WebServlet("/address/*")
public class Address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Address() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		String id = pathInfo[1];
		
		AddressDAO address = new AddressDAO();
		
		Optional<Model.Address> addressData = address.get(id);

		Gson gson = new Gson();
		String json = gson.toJson(addressData);
		
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
		Model.Address addressData = null;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD").create();
		JsonObject json = gson.fromJson(buffer.toString(), JsonObject.class);
		
		String cep = json.get("cep").toString().replaceAll("\"", "");
		String logradouro = json.get("logradouro").toString().replaceAll("\"", "");
		String numero = json.get("numero").toString().replaceAll("\"", "");
		String complemento = json.get("complemento").toString().replaceAll("\"", "");
		String bairro = json.get("bairro").toString().replaceAll("\"", "");
		String cidade = json.get("cidade").toString().replaceAll("\"", "");
		String uf = json.get("uf").toString().replaceAll("\"", "");
		String idUsuario = json.get("idUsuario").toString().replaceAll("\"", "");
		
		if(complemento != null) {
			addressData = new Model.Address("", cep, logradouro, numero, complemento, bairro, cidade, uf, idUsuario);
		}else {
			addressData = new Model.Address("", cep, logradouro, numero, bairro, cidade, uf, idUsuario);
		}

		AddressDAO address = new AddressDAO();
		ReturnData returnData = address.save(addressData);
		String json1 = gson.toJson(returnData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
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
		
		AddressDAO address = new AddressDAO();
		
		ReturnData addressData = address.delete(id);

		Gson gson = new Gson();
		String json = gson.toJson(addressData);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
	}

}
