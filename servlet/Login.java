 	package rfred.com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL="/WEB-INF/login.jsp";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PWD = "pwd";
       
	private String validateEmail( String email ) {
		String ret = "";
		if ( email != null && email.trim().length() != 0 ) {
			 if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			 	return "Veuillez saisir une adresse mail valide";
			 }
		} else {
			return  "L'adresse mail est obligatoire";
		}
		return ret;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Reset validation feedback attributes
		String msgErrorEmail="";
		String email="";
		String pwd="";
		request.setAttribute("msgErrorEmail", msgErrorEmail);
		request.setAttribute(FIELD_EMAIL, email);
		request.setAttribute(FIELD_PWD, pwd);
		request.setAttribute("errorStatus", true);
		
		// Display page
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();	
		
		// Read parameters values
		Map<String, String> form = new HashMap<String, String>();
		form.put(FIELD_EMAIL, request.getParameter(FIELD_EMAIL));
		form.put(FIELD_PWD, request.getParameter(FIELD_PWD));
		
		// Validate parameters
		Map<String, String> errors = new HashMap<String, String>();
		Map<String, User> users = (HashMap<String, User>) session.getAttribute("users" );
		
		
		// Email address parameter
		String errMsg = validateEmail(form.get(FIELD_EMAIL)) ;
		if(errMsg!=null){
			errors.put(FIELD_EMAIL, errMsg);
		}

		// Form validation
		String msgValidation="Connexion échouée";
		boolean errorStatus = true;
		
		if( users != null && !users.isEmpty() ) {	
			if(errors.get(FIELD_EMAIL) == "" && users.containsKey(form.get(FIELD_EMAIL)) ){
				User newUser = users.get(form.get(FIELD_EMAIL));
				msgValidation = "Connexion réussie";
				errorStatus = false;
				request.setAttribute("newUser", newUser);
			}			
		}
		
		// Set validation feedback attributes
		request.setAttribute("msgValidation", msgValidation);
		request.setAttribute("errorStatus", errorStatus);		
		request.setAttribute("form", form);
		request.setAttribute("errors", errors);
		session.setAttribute( "users", users );
		
		// Display page again !
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include(request, response);
	}

}
