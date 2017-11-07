package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String tranNo = request.getParameter("tranNo");
		String tranCode = request.getParameter("tranCode");
		
		PurchaseService service = new PurchaseServiceImpl();
		Purchase purchase = service.getPurchase(Integer.parseInt(tranNo));
		purchase.setTranCode(tranCode);
		service.updateTranCode(purchase);
		
		return "forward:/listPurchase.do";
	}

}
