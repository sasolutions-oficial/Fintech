

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.TransactionDAO;

/**
 * Servlet implementation class Transactions
 */
@WebServlet("/transactions")
public class Transactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transactions() {
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
		
		List<Model.Transaction> transactions = transaction.getAll(id);

		Gson gson = new Gson();
		String json = gson.toJson(transactions);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
	}

}
