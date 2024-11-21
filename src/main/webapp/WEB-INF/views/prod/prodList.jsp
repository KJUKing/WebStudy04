<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>일련번호</th>
        <th>상품명</th>
        <th>상품분류</th>
        <th>상품제조사</th>
        <th>구매가</th>
        <th>판매가</th>
        <th>입고일</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty prodList }">
        <c:forEach items="${prodList }" var="prod">

            <tr>
                <td>${prod.rnum }</td>
                <td>
                    <c:if test="${prod eq lastCreated }">
                        <c:set var="idExpr" value="id='lastCreated'" />
                        <c:remove var="lastCreated" scope="session"/>
                    </c:if>
                    <c:url value="/prod/prodDetail.do" var="detailUrl">
                        <c:param name="what" value="${prod.prodId }"/>
                    </c:url>
                    <a ${idExpr } data-href="${detailUrl }" data-bs-toggle="offcanvas" href="#offcanvasExample" role="button" aria-controls="offcanvasExample">${prod.prodName }</a>
                </td>
                <td>${prod.lprod.lprodNm }</td>
                <td>${prod.buyer.buyerName }</td>
                <td>${prod.prodCost }</td>
                <td>${prod.prodPrice }</td>
                <td>${prod.prodInsdate }</td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty prodList }">
        <tr>
            <td colspan="7"> 상품 없음. </td>
        </tr>
    </c:if>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="7">
            <div class="paging-area">
                ${pagingHTML }
            </div>
            <div class="search-area">
                <form:select path="condition.prodLgu">
                    <form:option value="" label="전체"/>
                    <form:options items="${lprodList}" itemValue="lprodGu" itemLabel="lprodNm"/>
                </form:select>
                <form:select path="condition.prodBuyer">
                    <form:option value="" label="전체"/>
<%--                    <form:options items="${buyerList}" itemValue="buyerId" itemLabel="buyerName"/>--%>
                        <c:forEach items="${buyerList }" var="buyer">
                            <form:option label="${buyer.buyerName }" value="${buyer.buyerId }"
                                    class="${buyer.buyerLgu }"
                            />
                        </c:forEach>
                </form:select>
                <form:input path="condition.prodName" />
                <button type="button" class="search-btn">검색</button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
<form id="searchForm">
    <form:input path="condition.prodLgu" placeholder="prodLgu"/>
    <form:input path="condition.prodBuyer" placeholder="prodBuyer"/>
    <form:input path="condition.prodName" placeholder="prodName"/>
    <input type="text" name="page" placeholder="page"/>
</form>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        asdfsad
    </div>
</div>

<script src="${pageContext.request.contextPath }/resources/js/app/prod/prodList.js"></script>










