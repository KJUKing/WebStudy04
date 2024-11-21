<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h4>회원 목록 조회</h4>
<table class="table table-bordered">
    <thead class="table-dark">
    <tr>
        <th>일련번호</th>
        <th>이름</th>
        <th>휴대폰</th>
        <th>이메일</th>
        <th>거주지역</th>
        <th>마일리지</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty list }">
        <c:forEach items="${list }" var="member">
            <tr>
                <td>${member.rnum }</td>
                <td>
                    <c:url value="/member/memberDetail.do" var="detailUrl">
                        <c:param name="who" value="${member.memId }" />
                    </c:url>
                    <a href="${detailUrl }">${member.memName }</a>
                </td>
                <td>${member.memHp }</td>
                <td>${member.memMail }</td>
                <td>${member.memAdd1 }</td>
                <td>${member.memMileage }</td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty list }">
        <tr>
            <td colspan="5">회원 없음.</td>
        </tr>
    </c:if>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="5">
            <div class="paging-area">
            ${pagingHtml }
            </div>
            <div class="search-area">
                <form:select path="condition.searchType">
                    <form:option value="" label="전체"/>
                    <form:option value="name" label="이름"/>
                    <form:option value="address" label="지역"/>
                </form:select>
                <form:input path="condition.searchWord"/>
                <button type="button" class="search-btn">검색</button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
<form id="searchForm">
    <form:input path="condition.searchType" placeholder="searchType"/>
    <form:input path="condition.searchWord" placeholder="searchWord"/>
    <input type="text" name="page" placeholder="page"/>
</form>
<script src="${pageContext.request.contextPath}/resources/js/app/member/memberList.js">

</script>




