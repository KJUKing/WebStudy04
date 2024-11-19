<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h4>회원 목록 조회</h4>
<table class="table table-bordered">
    <thead class="table-dark">
    <tr>
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
</table>





