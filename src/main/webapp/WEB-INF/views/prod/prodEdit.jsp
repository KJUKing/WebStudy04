<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="post" enctype="multipart/form-data" modelAttribute="lastCreated">
    <table class="table table-bordered">
    <tr>
    <th>상품코드</th>
    <td>
        <form:input path="prodId" cssClass="form-control"/>
        <form:errors path="prodId" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>상품명</th>
    <td>
    <form:input path="prodName" cssClass="form-control"/>
    <form:errors path="prodName" cssClass="text-danger"/>
    </td>
    </tr>
        <tr>
            <th>상품분류</th>
            <td>
                <select name="prodLgu" class="form-select">
                    <option value>분류선택</option>
                    <c:forEach items="${lprodList }" var="lprod">
                        <option label="${lprod.lprodNm }" value="${lprod.lprodGu }"
                            ${lprod.lprodGu eq prod.prodLgu ? "selected" : "" }
                        />
                    </c:forEach>
                </select>
                <span class="text-danger">${errors.prodLgu }</span>
            </td>
        </tr>
        <tr>
            <th>제조사</th>
            <td>
                <select name="prodBuyer" class="form-select">
                    <option value>제조사선택</option>
                    <c:forEach items="${buyerList }" var="buyer">
                        <option label="${buyer.buyerName }" value="${buyer.buyerId }"
                            ${buyer.buyerId eq prod.prodBuyer ? "selected" : "" }
                                class="${buyer.buyerLgu }"
                        />
                    </c:forEach>
                </select>
                <span class="text-danger">${errors.prodBuyer }</span>
            </td>
        </tr>
    <th>구매가</th>
    <td>
    <form:input path="prodCost" cssClass="form-control"/>
    <form:errors path="prodCost" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>판매가</th>
    <td>
    <form:input path="prodPrice" cssClass="form-control"/>
    <form:errors path="prodPrice" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>세일가</th>
    <td>
    <form:input path="prodSale" cssClass="form-control"/>
    <form:errors path="prodSale" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>요약정보</th>
    <td>
    <form:input path="prodOutline" cssClass="form-control"/>
    <form:errors path="prodOutline" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>상세정보</th>
    <td>
    <form:input path="prodDetail" cssClass="form-control"/>
    <form:errors path="prodDetail" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>이미지</th>
    <td>
        <spring:eval expression="@dirInfo.prodImages" var="prodImgaes"/>
        <form:input path="prodImg"/>
    <input type="file" name="prodImage" class="form-control" accept="image/*"/>
    <form:errors path="prodImage" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>총재고</th>
    <td>
    <form:input path="prodTotalstock" cssClass="form-control"/>
    <form:errors path="prodTotalstock" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>입고일</th>
    <td>
    <form:input type="date" path="prodInsdate" cssClass="form-control"/>
    <form:errors path="prodInsdate" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>적정재고</th>
    <td>
    <form:input path="prodProperstock" cssClass="form-control"/>
    <form:errors path="prodProperstock" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>크기</th>
    <td>
    <form:input path="prodSize" cssClass="form-control"/>
    <form:errors path="prodSize" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>색상</th>
    <td>
    <form:input path="prodColor" cssClass="form-control"/>
    <form:errors path="prodColor" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>배송방법</th>
    <td>
    <form:input path="prodDelivery" cssClass="form-control"/>
    <form:errors path="prodDelivery" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>단위</th>
    <td>
    <form:input path="prodUnit" cssClass="form-control"/>
    <form:errors path="prodUnit" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>입고량</th>
    <td>
    <form:input path="prodQtyin" cssClass="form-control"/>
    <form:errors path="prodQtyin" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>판매량</th>
    <td>
    <form:input path="prodQtysale" cssClass="form-control"/>
    <form:errors path="prodQtysale" cssClass="text-danger"/>
    </td>
    </tr>
    <tr>
    <th>마일리지</th>
    <td>
    <form:input path="prodMileage" cssClass="form-control"/>
    <form:errors path="prodMileage" cssClass="text-danger"/>
    </td>
    </tr>
    </tr>
        <tr>
            <td colspan="2">
                <button type="submit" class="btn btn-primary">전송</button>
                <button type="reset" class="btn btn-warning">취소</button>
            </td>
        </tr>
    </table>

</form:form>
    <script src="<c:url value='/resources/js/app/prod/prodEdit.js'/>"></script>