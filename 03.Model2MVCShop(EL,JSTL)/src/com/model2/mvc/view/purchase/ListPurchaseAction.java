package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println("sessionId : "+session.getId());
		if(session.getAttribute("user")==null) {
			return "forward:/logout.do";
		}else {
			Search search = new Search();
			
			int currentPage=1;
			
			if(request.getParameter("currentPage") != null){
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
			}
			System.out.println("currentPage : "+currentPage);
			search.setCurrentPage(currentPage);
							
			// web.xml  meta-data 로 부터 상수 추출 
			int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
			int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
			search.setPageSize(pageSize);
			
			
			User user = (User)session.getAttribute("user");
			System.out.println("userID:"+user.getUserId());
			PurchaseService service = new PurchaseServiceImpl();
			Map<String,Object> map=service.getPurchaseList(search, user.getUserId());
			
			Page resultPage	= new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			System.out.println("ListPurchaseAction ::"+resultPage);
			
			request.setAttribute("list", map.get("list"));
			request.setAttribute("resultPage", resultPage);
											
			return "forward:/purchase/listPurchase.jsp";
		}
	}

}
