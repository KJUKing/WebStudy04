import {aw} from "../../../NiceAdmin/assets/vendor/chart.js/chunks/helpers.segment";

document.addEventListener("DOMContentLoaded", () => {
    const myModal = new bootstrap.Modal("#exampleModal");
    const detailTbl = document.getElementById("detailTbl");
    const baseUrl = '../../../prod';
    let table = new DataTable('#myTable', {
        ajax: {
            url: baseUrl,
            dataSrc: ''
        },
        select: true,
        //
        // <th>상품명</th>
        // <th>상품분류</th>
        // <th>상품제조사</th>
        // <th>구매가</th>
        // <th>판매가</th>
        // <th>입고일</th>
        columns: [
            {data: 'prodId', visible: false},
            {data: 'prodName'},
            {data: 'lprod.lprodNm'},
            {data: 'buyer.buyerName'},
            {data: 'prodCost'},
            {data: 'prodPrice'},
            {data: 'prodInsdate'}
        ]
    });

    table.on('select', async function (e, dt, type, indexes) {
        console.log(e);
        console.log(dt);
        console.log(type);
        console.log(indexes);
        if (type === 'row') {
            let data = table.row(indexes[0]).data();
            console.log(data);
            let prod = await fetch(`${baseUrl}/${data.prodId}`)
                .then(resp => resp.json());
            detailTbl.innerHTML =
    `
            <tr>
        <th>상품명</th>
                <td>${prod.prodName}</td>
            </tr>
            <tr>
                <th>상품분류</th>
                <td>${prod.lprod.lprodNm}</td>
            </tr>
            <tr>
                <th>제조사</th>
                <td>
                   ${prod.buyer.buyerName}
                </td>
            </tr>
            <tr>
                <th>구매가</th>
                <td>${prod.prodCost}</td>
            </tr>
            <tr>
                <th>판매가</th>
                <td>${prod.prodPrice}</td>
            </tr>
            <tr>
                <th>세일가</th>
                <td>${prod.prodSale}</td>
            </tr>
            <tr>
                <th>요약정보</th>
                <td>${prod.prodOutline}</td>
            </tr>
            <tr>
                <th>상세정보</th>
                <td>${prod.prodDetail}</td>
            </tr>
            <tr>
                <th>총재고</th>
                <td>${prod.prodTotalstock}</td>
            </tr>
            <tr>
                <th>입고일</th>
                <td>${prod.prodInsdate}</td>
            </tr>
            <tr>
                <th>적정재고</th>
                <td>${prod.prodProperstock}</td>
            </tr>
            <tr>
                <th>크기</th>
                <td>${prod.prodSize}</td>
            </tr>
            <tr>
                <th>색상</th>
                <td>${prod.prodColor}</td>
            </tr>
            <tr>
                <th>배송방법</th>
                <td>${prod.prodDelivery}</td>
            </tr>
            <tr>
                <th>단위</th>
                <td>${prod.prodUnit}</td>
            </tr>
            <tr>
                <th>입고량</th>
                <td>${prod.prodQtyin}</td>
            </tr>
            <tr>
                <th>판매량</th>
                <td>${prod.prodQtysale}</td>
            </tr>
            <tr>
                <th>마일리지</th>
                <td>${prod.prodMileage}</td>
        </tr>
            
    `;
            myModal.show();
        }
    });
});