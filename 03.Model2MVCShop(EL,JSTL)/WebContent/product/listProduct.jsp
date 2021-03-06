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
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
--%>  
    
<html>
<head>
<title>상품 목록조회</title>

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
							상품 관리
							}else{ 상품 목록조회<%} --%>
					${param.menu=='manage' ? "상품 관리" : "상품 목록조회"}
					
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
				<option value="0" ${!empty search.searchCondition && search.searchCondition==0 ? "selected" : ""}>상품번호</option>
				<option value="1" ${!empty search.searchCondition && search.searchCondition==1 ? "selected" : ""}>상품명</option>
				<option value="2" ${!empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>상품가격</option>		
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
						<a href="javascript:fncGetUserList('1')">검색</a>
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
		<td colspan="8" >전체 ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage} 페이지</td>
		<td colspan="3" align = "right">
		<input type="hidden" id="condition" name="condition" value=""/>
		<a href="javascript:fncGetUserList('${resultPage.currentPage }','high')">가격높은순</a>  <a href="javascript:fncGetUserList('${resultPage.currentPage }','low')">가격낮은순</a>
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">${user.role=='user' ? "상세정보" : "등록일"}</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
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
				<%--=(product.getProTranCode()==null ? "판매중":"재고없음") --%>
				${empty product.proTranCode ? "판매중" : "재고없음"}
				</c:if>
				<c:if test="${user.role!='user'}">
				<%--}else{
						if(product.getProTranCode()==null){판매중
					  }else if(product.getProTranCode().trim().equals("1")){
							if(menu.equals("manage")){구매완료 <a href="/updateTranCodeByProd.do?prodNo=<%=product.getProdNo() %>&tranCode=2">배송하기</a>
						  }else{%>구매완료}						
						}else if(product.getProTranCode().trim().equals("2")){배송중
					  }else {배송완료}
				   }--%>
				   <c:if test="${empty product.proTranCode}">판매중</c:if>
				   <c:if test="${product.proTranCode.trim()==1}">
				   	<c:if test="${param.menu=='manage' }">구매완료 <a href="/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=2">배송하기</a></c:if>
				   	<c:if test="${param.menu!='manage' }">구매완료</c:if>
				   </c:if>
				   <c:if test="${product.proTranCode.trim()==2}">배송중</c:if>
				   <c:if test="${product.proTranCode.trim()==3}">배송완료</c:if>	
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
					◀ 이전
			 }else{ 
					<a href="javascript:fncGetProductList('<%=resultPage.getBeginUnitPage()-1')">◀ 이전</a>
			 } 

				for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	
					<a href="javascript:fncGetProductList('i ');">i </a>
			 	}  
	
			 if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ 
					이후 ▶
			 }else{ 
					<a href="javascript:fncGetProductList('resultPage.getEndUnitPage()+1')">이후 ▶</a>
			} --%>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
