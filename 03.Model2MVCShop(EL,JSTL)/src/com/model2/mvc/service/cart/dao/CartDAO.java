package com.model2.mvc.service.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;

public class CartDAO {

	public CartDAO() {
	}
	
	public void addCart(Cart cart) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO cart VALUES (?,?,?,?,SYSDATE)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, cart.getUserId());
		pStmt.setInt(2, cart.getProdNo());
		pStmt.setString(3, cart.getProdName());
		pStmt.setInt(4, cart.getPrice());
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();

	}
	
	public List<Cart> getCartList(Cart cart) throws Exception {
		List<Cart> list = new ArrayList<Cart>();
		
		Connection con = DBUtil.getConnection();
		
		return list;
	}	
	
}
