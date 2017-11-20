package com.model2.mvc.service.cart.impl;

import java.util.List;

import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.dao.CartDAO;
import com.model2.mvc.service.domain.Cart;

public class CartServiceImpl implements CartService {
	
	private CartDAO cartDAO;

	public CartServiceImpl() {
		// TODO Auto-generated constructor stub
		cartDAO = new CartDAO();
	}

	@Override
	public void addCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		cartDAO.addCart(cart);
	}

	@Override
	public List<Cart> getCartList(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return cartDAO.getCartList(cart);
	}

}
