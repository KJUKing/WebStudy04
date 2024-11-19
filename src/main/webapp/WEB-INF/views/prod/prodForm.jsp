<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" enctype="multipart/form-data">
    <table class="table table-bordered">
        <tr>
            <th>상품명</th>
            <td>
                <input type="text" name="prodName" class="form-control"
                       required value="${prod.prodName }" />
                <span class="text-danger">${errors.prodName }</span>
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
        <tr>
            <th>구매가</th>
            <td>
                <input type="number" name="prodCost" class="form-control"
                       required value="${prod.prodCost }" />
                <span class="text-danger">${errors.prodCost }</span>
            </td>
        </tr>
        <tr>
            <th>판매가</th>
            <td>
                <input type="number" name="prodPrice" class="form-control"
                       required value="${prod.prodPrice }" />
                <span class="text-danger">${errors.prodPrice }</span>
            </td>
        </tr>
        <tr>
            <th>세일가</th>
            <td>
                <input type="number" name="prodSale" class="form-control"
                       required value="${prod.prodSale }" />
                <span class="text-danger">${errors.prodSale }</span>
            </td>
        </tr>
        <tr>
            <th>요약정보</th>
            <td>
                <input type="text" name="prodOutline" class="form-control"
                       required value="${prod.prodOutline }" />
                <span class="text-danger">${errors.prodOutline }</span>
            </td>
        </tr>
        <tr>
            <th>상세정보</th>
            <td>
                <textarea name="prodDetail">${prod.prodDetail }</textarea>
                <span class="text-danger">${errors.prodDetail }</span>
            </td>
        </tr>
        <tr>
            <th>이미지</th>
            <td>
                <input type="text" name="prodImage" class="form-control" required accept="image/*"/>
                <span class="text-danger">${errors.prodImg }</span>
            </td>
        </tr>
        <tr>
            <th>총재고</th>
            <td>
                <input type="number" name="prodTotalstock"
                       class="form-control" required value="${prod.prodTotalstock }" />
                <span
                        class="text-danger">${errors.prodTotalstock }</span>
            </td>
        </tr>
        <tr>
            <th>입고일</th>
            <td>
                <input type="date" name="prodInsdate" class="form-control"
                       value="${prod.prodInsdate }" />
                <span class="text-danger">${errors.prodInsdate }</span>
            </td>
        </tr>
        <tr>
            <th>적정재고</th>
            <td>
                <input type="number" name="prodProperstock"
                       class="form-control" required value="${prod.prodProperstock }" />
                <span
                        class="text-danger">${errors.prodProperstock }</span>
            </td>
        </tr>
        <tr>
            <th>크기</th>
            <td>
                <input type="text" name="prodSize" class="form-control"
                       value="${prod.prodSize }" />
                <span class="text-danger">${errors.prodSize }</span>
            </td>
        </tr>
        <tr>
            <th>색상</th>
            <td>
                <input type="text" name="prodColor" class="form-control"
                       value="${prod.prodColor }" />
                <span class="text-danger">${errors.prodColor }</span>
            </td>
        </tr>
        <tr>
            <th>배송방법</th>
            <td>
                <input type="text" name="prodDelivery" class="form-control"
                       value="${prod.prodDelivery }" />
                <span class="text-danger">${errors.prodDelivery }</span>
            </td>
        </tr>
        <tr>
            <th>단위</th>
            <td>
                <input type="text" name="prodUnit" class="form-control"
                       value="${prod.prodUnit }" />
                <span class="text-danger">${errors.prodUnit }</span>
            </td>
        </tr>
        <tr>
            <th>입고량</th>
            <td>
                <input type="number" name="prodQtyin" class="form-control"
                       value="${prod.prodQtyin }" />
                <span class="text-danger">${errors.prodQtyin }</span>
            </td>
        </tr>
        <tr>
            <th>판매량</th>
            <td>
                <input type="number" name="prodQtysale" class="form-control"
                       value="${prod.prodQtysale }" />
                <span class="text-danger">${errors.prodQtysale }</span>
            </td>
        </tr>
        <tr>
            <th>마일리지</th>
            <td>
                <input type="number" name="prodMileage" class="form-control"
                       value="${prod.prodMileage }" />
                <span class="text-danger">${errors.prodMileage }</span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit" class="btn btn-primary">전송</button>
                <button type="reset" class="btn btn-warning">취소</button>
            </td>
        </tr>
    </table>
</form>