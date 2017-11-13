<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%--
	String menu=request.getParameter("menu");
	User user = (User)session.getAttribute("user");
--%>
    
<%--
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null �� ""(nullString)���� ����
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
--%>  
    
<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetUserList(currentPage,condition) {
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("condition").value = condition;
   	document.detailForm.submit();		
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${param.menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					<%--if(menu.equals("manage")){ 
							��ǰ ����
							}else{ ��ǰ �����ȸ<%} --%>
					${param.menu=='manage' ? "��ǰ ����" : "��ǰ �����ȸ"}
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%--
		if(search.getSearchCondition() != null) {
	--%>
	
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${!empty search.searchCondition && search.searchCondition==0 ? "selected" : ""}>��ǰ��ȣ</option>
				<option value="1" ${!empty search.searchCondition && search.searchCondition==1 ? "selected" : ""}>��ǰ��</option>
				<option value="2" ${!empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>��ǰ����</option>		
			</select>
			<input type="text" name="searchKeyword"  value="${! empty search.searchKeyword ? search.searchKeyword : ""}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
	<%-- 
		}else{
	--%>
	
	<%--
		}
	--%>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetUserList('1')">�˻�</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="8" >��ü ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage} ������</td>
		<td colspan="3" align = "right">
		<input type="hidden" id="condition" name="condition" value=""/>
		<a href="javascript:fncGetUserList('${resultPage.currentPage }','high')">���ݳ�����</a>  <a href="javascript:fncGetUserList('${resultPage.currentPage }','low')">���ݳ�����</a>
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">${user.role=='user' ? "������" : "�����"}</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%--
		for(int i=0; i<list.size(); i++) {
			Product product = list.get(i);
	--%>
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
			<td></td>
					<td align="left">
					<%--if(user.getRole().equals("user")){
						if(product.getProTranCode()!=null){--%>
					<c:if test="${user.role=='user'}">
						<c:if test="${!empty product.proTranCode }">
							${product.prodName}
						</c:if>
						<%--}else{--%>
						<c:if test="${empty product.proTranCode }">
							<a href="/getProduct.do?prodNo=${product.prodNo}&menu=${param.menu}">${product.prodName}</a>
						</c:if>
						<%--}				
					}else {
						if(menu.equals("manage")){--%>
					</c:if>
					<c:if test="${user.role!='user'}">
						<c:if test="${param.menu=='manage'}">
							<c:if test="${empty product.proTranCode }">
							<a href="/updateProductView.do?prodNo=${product.prodNo}&menu=${param.menu}">${product.prodName}</a>
							</c:if>
							<c:if test="${!empty product.proTranCode }">
							${product.prodName}
							</c:if>
						<%--}else{--%>
						</c:if>
						<c:if test="${param.menu!='manage'}">
							<c:if test="${empty product.proTranCode }">
							<a href="/getProduct.do?prodNo=${product.prodNo}&menu=${param.menu}">${product.prodName}</a>
							</c:if>
							<c:if test="${!empty product.proTranCode }">
							${product.prodName}
							</c:if>
						<%--}
					}--%>
						</c:if>
					</c:if></td>
			
			<td></td>
			<td align="left">${product.price}</td>
			<td></td>
			<td align="left">${user.role=='user' ? product.prodDetail : product.regDate}</td>
			<td></td>
			<td align="left">
				<%--if(user.getRole().equals("user")){ --%>
				<c:if test="${user.role=='user'}">
				<%--=(product.getProTranCode()==null ? "�Ǹ���":"������") --%>
				${empty product.proTranCode ? "�Ǹ���" : "������"}
				</c:if>
				<c:if test="${user.role!='user'}">
				<%--}else{
						if(product.getProTranCode()==null){�Ǹ���
					  }else if(product.getProTranCode().trim().equals("1")){
							if(menu.equals("manage")){���ſϷ� <a href="/updateTranCodeByProd.do?prodNo=<%=product.getProdNo() %>&tranCode=2">����ϱ�</a>
						  }else{%>���ſϷ�}						
						}else if(product.getProTranCode().trim().equals("2")){�����
					  }else {��ۿϷ�}
				   }--%>
				   <c:if test="${empty product.proTranCode}">�Ǹ���</c:if>
				   <c:if test="${product.proTranCode.trim()==1}">
				   	<c:if test="${param.menu=='manage' }">���ſϷ� <a href="/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=2">����ϱ�</a></c:if>
				   	<c:if test="${param.menu!='manage' }">���ſϷ�</c:if>
				   </c:if>
				   <c:if test="${product.proTranCode.trim()==2}">�����</c:if>
				   <c:if test="${product.proTranCode.trim()==3}">��ۿϷ�</c:if>	
				  </c:if>
			</td>	
		</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<%-- } --%>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<%-- if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ 
					�� ����
			 }else{ 
					<a href="javascript:fncGetProductList('<%=resultPage.getBeginUnitPage()-1')">�� ����</a>
			 } 

				for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	
					<a href="javascript:fncGetProductList('i ');">i </a>
			 	}  
	
			 if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ 
					���� ��
			 }else{ 
					<a href="javascript:fncGetProductList('resultPage.getEndUnitPage()+1')">���� ��</a>
			} --%>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
		
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>
