package Authentication;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DBConnection;
import Patterns.Mappers.ClientMapper;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/NewServlet")
public class NewServlet extends HttpServlet


{
	ArrayList<String> clients;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        clients = new ArrayList<>();
        clients.add("usain");
        clients.add("bolt");
        clients.add("is");
        clients.add("the");
        clients.add("greatest");
        
        // TODO Auto-generated constructor stub
    }
    
    public String toString() {
    	ClientMapper.getClientFromId(111);
    	return testClientMapper();
    }
    
    public String testClientMapper() {
    	ClientMapper.getAllClients();
    	return "usainbolt";
    }
    
    public String testDatabaseConnection() {
    	String result = "j";
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * FROM client");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("loop");
				System.out.println(rs.getInt(1));
				int id = rs.getInt(1);
				String titleArg = rs.getString(2);
				System.out.println(titleArg);
				//String authorArg = rs.getString(3);
				//int priceArg = rs.getInt(4);
				//int qtyArg = rs.getInt(5);
				//result.add(new BookGateway(id, titleArg, authorArg, priceArg, qtyArg));
				result = result + titleArg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
