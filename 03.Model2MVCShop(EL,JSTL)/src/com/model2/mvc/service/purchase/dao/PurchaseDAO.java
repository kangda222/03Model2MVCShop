package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertPurchase(Purchase purchase) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction VALUES (seq_TRANSACTION_TRAN_NO.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE,?)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		pStmt.setString(2, purchase.getBuyer().getUserId());
		pStmt.setString(3, purchase.getPaymentOption());
		pStmt.setString(4, purchase.getReceiverName());
		pStmt.setString(5, purchase.getReceiverPhone());
		pStmt.setString(6, purchase.getDivyAddr());
		pStmt.setString(7, purchase.getDivyRequest());
		pStmt.setString(8, purchase.getTranCode());
		pStmt.setString(9, purchase.getDivyDate());
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		
	}
	
	public Purchase findPurchase(int tranNo) throws Exception{
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction WHERE tran_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, tranNo);
		
		ResultSet rs = pStmt.executeQuery();
		
		Purchase purchase = null;
		while (rs.next()) {
			purchase = new Purchase();
			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setPurchaseProd(new ProductServiceImpl().getProduct(rs.getInt("prod_no"))); //PROD_NO
			purchase.setBuyer(new UserServiceImpl().getUser(rs.getString("buyer_id"))); //아이디BUYER_ID
			purchase.setPaymentOption(rs.getString("payment_option"));
			purchase.setReceiverName(rs.getString("receiver_name")); //이름RECEIVER_NAME RECEIVER_NAME
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setDivyAddr(rs.getString("demailaddr"));
			purchase.setDivyRequest(rs.getString("dlvy_request"));
			purchase.setDivyDate(rs.getString("dlvy_date"));
			//ORDER_DATA TRAN_STATUS_CODE
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setTranCode(rs.getString("tran_status_code"));
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return purchase;
	}
	
	public Purchase findPurchase2(int prodNo) throws Exception{
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction WHERE prod_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, prodNo);
		
		ResultSet rs = pStmt.executeQuery();
		
		Purchase purchase = null;
		while (rs.next()) {
			purchase = new Purchase();
			purchase.setTranNo(rs.getInt("tran_no"));
			purchase.setPurchaseProd(new ProductServiceImpl().getProduct(rs.getInt("prod_no"))); //PROD_NO
			purchase.setBuyer(new UserServiceImpl().getUser(rs.getString("buyer_id"))); //아이디BUYER_ID
			purchase.setPaymentOption(rs.getString("payment_option"));
			purchase.setReceiverName(rs.getString("receiver_name")); //이름RECEIVER_NAME RECEIVER_NAME
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setDivyAddr(rs.getString("demailaddr"));
			purchase.setDivyRequest(rs.getString("dlvy_request"));
			purchase.setDivyDate(rs.getString("dlvy_date"));
			//ORDER_DATA TRAN_STATUS_CODE
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setTranCode(rs.getString("tran_status_code"));
			
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return purchase;
	}
	
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction WHERE buyer_id='"+buyerId+"' ORDER BY tran_no";
		
		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);
		
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println(search);
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()){
				Purchase purchase = new Purchase();
				purchase.setTranNo(rs.getInt("tran_no"));
				purchase.setPurchaseProd(new ProductServiceImpl().getProduct(rs.getInt("prod_no"))); //PROD_NO
				purchase.setBuyer(new UserServiceImpl().getUser(rs.getString("buyer_id"))); //아이디BUYER_ID
				purchase.setPaymentOption(rs.getString("payment_option"));
				purchase.setReceiverName(rs.getString("receiver_name")); //이름RECEIVER_NAME RECEIVER_NAME
				purchase.setReceiverPhone(rs.getString("receiver_phone"));
				purchase.setDivyAddr(rs.getString("demailaddr"));
				purchase.setDivyRequest(rs.getString("dlvy_request"));
				purchase.setDivyDate(rs.getString("dlvy_date"));
				//ORDER_DATA TRAN_STATUS_CODE
				purchase.setOrderDate(rs.getDate("order_data"));
				purchase.setTranCode(rs.getString("tran_status_code"));
				
				list.add(purchase);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}
	
	public Map<String,Object> getSaleList(Search search) throws Exception{
		Connection con = DBUtil.getConnection();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		con.close();
		
		return map;
	}
	
	public void updatePurchase(Purchase purchase) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET payment_option=?,receiver_name=?,receiver_phone=?,demailaddr=?,dlvy_request=?,dlvy_date=? WHERE tran_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, purchase.getPaymentOption());
		pStmt.setString(2, purchase.getReceiverName());
		pStmt.setString(3, purchase.getReceiverPhone());
		pStmt.setString(4, purchase.getDivyAddr());
		pStmt.setString(5, purchase.getDivyRequest());
		pStmt.setDate(6, Date.valueOf(purchase.getDivyDate()));
		pStmt.setInt(7, purchase.getTranNo());
				
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, purchase.getTranCode());
		pStmt.setInt(2, purchase.getTranNo());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}
	
	public void refundPurchase(Purchase purchase) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "DELETE FROM transaction WHERE tran_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, purchase.getTranNo());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}
	
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}	
	
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("PurchaseDAO :: make SQL :: "+ sql);	
		
		return sql;
	}	

}
