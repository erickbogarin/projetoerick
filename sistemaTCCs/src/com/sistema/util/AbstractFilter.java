package com.sistema.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AbstractFilter {

	public AbstractFilter() {
		super();
	}

	protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("form-login.xhtml");
		rd.forward(request, response);
	}
	
	protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/pages/public/acessoNegado.xhtml");
		rd.forward(request, response);
	}
	
	protected void primeiroAcesso(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/pages/protected/primeiroAcesso.xhtml");
		rd.forward(request, response);
	}
}