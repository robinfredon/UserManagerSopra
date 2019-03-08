package rfred.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class formAction
 */
@WebServlet("/formRegister")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL="/WEB-INF/register.jsp";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PWD = "pwd";
	public static final String FIELD_CPWD = "cpwd";
	public static final String FIELD_NAME = "name";
	
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
	
	private String validatePwd(String pwd, String cpwd) {
		String ret = "";
		if ( pwd != null && pwd.trim().length() >= 3 ) 
		{
			if ( cpwd != null && cpwd.trim().length() >= 3 ) 
			{
				if ( !pwd.equals(cpwd) ) 
				{
					ret = "Les deux mots de passe rentrés sont différents";
				}
			} else {
				ret = "Le mot de passe doit faire plus de 3 caractères";
			}
		} else {
			ret = "Le mot de passe doit faire plus de 3 caractères";
		}
		return ret;
	}
	
    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		// Reset validation feedback attributes
		 String msgErrorEmail="";
		 String msgErrorPwd="";
		 String msgErrorCpwd="";
		 String msgErrorName="";
		 String msgValidation="";
		 String email="";
		 String pwd="";
		 String cpwd="";
		 String name="";
		 request.setAttribute("msgErrorEmail", msgErrorEmail);
		 request.setAttribute("msgErrorPwd", msgErrorPwd);
		 request.setAttribute("msgErrorCpwd", msgErrorCpwd);
		 request.setAttribute("msgErrorName", msgErrorName);
		 request.setAttribute("msgValidation", msgValidation);
		 request.setAttribute(FIELD_EMAIL, email);
		 request.setAttribute(FIELD_PWD, pwd);
		 request.setAttribute(FIELD_CPWD, cpwd);
		 request.setAttribute(FIELD_NAME, name);
		 request.setAttribute("errorStatus", true);
		
		 // Display page
		 this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();		
		
		// Read parameters values
		Map<String, String> form = new HashMap<String, String>();
		form.put(FIELD_EMAIL, request.getParameter(FIELD_EMAIL));
		form.put(FIELD_PWD, request.getParameter(FIELD_PWD));
		form.put(FIELD_CPWD, request.getParameter(FIELD_CPWD));
		form.put(FIELD_NAME, request.getParameter(FIELD_NAME));		

		 // Validate parameters
		 Map<String, String> errors = new HashMap<String, String>();
		 
		 // Email address parameter
		 String errMsg = validateEmail(form.get(FIELD_EMAIL)) ;
		 if(errMsg!=null){
		  errors.put(FIELD_EMAIL, errMsg);
		 }		 
		 // Password parameters...
		 errMsg = validatePwd(form.get(FIELD_PWD),form.get(FIELD_CPWD));
		 if(errMsg!=null){
			  errors.put(FIELD_PWD, errMsg);
		 }	
		 
		 // Name parameter...
		 String msgErrorName = "";
		
		 // Form validation
		 String msgValidation="";
		 boolean errorStatus;
		 if(errors.get(FIELD_EMAIL) == "" && msgErrorName == "" && errors.get(FIELD_PWD) == ""){
			 msgValidation = "Inscription réussie";
			 errorStatus = false;
		 }
		 else {
			 msgValidation = "Inscription impossible";
			 errorStatus = true;
		 }
		 
		  // Set validation feedback attributes
		  request.setAttribute("msgValidation", msgValidation);
		  request.setAttribute("form", form);
		  request.setAttribute("errors", errors);
		  request.setAttribute("errorStatus", errorStatus);
		 
		  if(!errorStatus) {
			  User newUser=null;
			  newUser=new User(form.get(FIELD_NAME),form.get(FIELD_EMAIL),form.get(FIELD_PWD));
			  request.setAttribute("newUser", newUser);
			  
			  Map<String, User> users = (HashMap<String, User>) session.getAttribute("users" );
			  if(users==null){
				 users = new HashMap<String, User>();
			  }
			  users.put( newUser.getEmail(), newUser );
			  session.setAttribute( "users", users );
		  }
		  
		  // Display page again !
		  this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include(request, response);
	}
	
}
