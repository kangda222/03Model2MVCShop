package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;


public class LogoutAction extends Action {

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null) {
			session.invalidate();
			return "redirect:/user/loginView.jsp";
		}else {
			session.invalidate();
			
			return "redirect:/index.jsp";
		}
	}
}