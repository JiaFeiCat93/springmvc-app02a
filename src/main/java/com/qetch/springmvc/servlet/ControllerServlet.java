package com.qetch.springmvc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qetch.springmvc.domain.Product;
import com.qetch.springmvc.form.ProductForm;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		if ("product_input.action".equals(action)) {
			// 
		} else if ("product_save.action".equals(action)) {
			ProductForm productForm = new ProductForm();
			productForm.setName(req.getParameter("name"));
			productForm.setDescription(req.getParameter("description"));
			productForm.setPrice(req.getParameter("price"));
			
			Product product = new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			product.setPrice(Float.parseFloat(productForm.getPrice()));
			
			// code to save product
			
			req.setAttribute("product", product);
		}
		
		String dispatchUrl = null;
		if ("product_input".equals(action)) {
			dispatchUrl = "/WEB-INF/jsp/ProductForm.jsp";
		} else if ("product_save".equals(action)) {
			dispatchUrl = "/WEB-INF/jsp/ProductDetail.jsp";
		}
		
		if (dispatchUrl != null) {
			RequestDispatcher rd = req.getRequestDispatcher(dispatchUrl);
			rd.forward(req, resp);
		}
	}
}
