package br.com.ifsp.vcRiquinho.usuario.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie ck = new Cookie("name", "");
		ck.setMaxAge(0);
		response.addCookie(ck);
		
		HttpSession session = request.getSession();
		session.removeAttribute("logado");
		session.removeAttribute("conta");

		session.removeAttribute("simulacoes");

		session.invalidate();
		
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
