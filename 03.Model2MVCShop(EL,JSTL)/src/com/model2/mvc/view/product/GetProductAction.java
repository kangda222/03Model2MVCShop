package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String prodNo = request.getParameter("prodNo");
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("product", product);
		
		String menu = request.getParameter("menu");
				
		String history = null;
		Cookie[] cookies = request.getCookies();
		
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
				}
			}
			
		}
				
		if(menu.equals("search")) {
			history += ","+prodNo;
			Cookie cookie = new Cookie("history",history);
			response.addCookie(cookie);		
			
		}
			
		return "forward:/product/readProduct.jsp";
	}

}
