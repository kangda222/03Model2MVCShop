package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String prodNo = request.getParameter("prodNo");
		System.out.println("prodNo"+prodNo);
		Product product = new Product();
		product.setProdNo(Integer.parseInt(prodNo));
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		System.out.println("ManuDate"+request.getParameter("manuDate"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);
		
		//String menu = request.getParameter("menu");
		String menu = "ok";
		System.out.println(menu);
				
		request.setAttribute("vo", product);
		
		return "forward:/getProduct.do?prodNo="+prodNo+"&menu="+menu;
	}

}
