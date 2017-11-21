package com.model2.mvc.view.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddCartAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Cart cart= new Cart();
		ProductService productService = new ProductServiceImpl();
		Product  product = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		
		cart.setUserId(user.getUserId());
		cart.setProdNo(product.getProdNo());
		cart.setProdName(product.getProdName());
		cart.setPrice(product.getPrice());
		cart.setRegDate(product.getRegDate());
		
		System.out.println("cart : "+cart);
		
		CartService service = new CartServiceImpl();
		service.addCart(cart);
		
		return null;
	}

}
