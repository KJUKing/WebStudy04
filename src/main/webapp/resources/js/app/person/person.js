// person.js
// 전체적으로 스크립트 구간은 gpt에게 많이물어봤음
// 반드시 리뷰다시한번 해야함
// 현재 페이지의 경로에서 컨텍스트 패스 추출


document.addEventListener('DOMContentLoaded', function () {
    loadPersonList();

    // 새로운 Person 추가 폼 처리
    const addPersonForm = document.getElementById('addPersonForm');
    addPersonForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const personData = {
            id: document.getElementById('id').value.trim(),
            name: document.getElementById('name').value.trim(),
            gender: document.getElementById('gender').value.trim(),
            age: document.getElementById('age').value.trim(),
            address: document.getElementById('address').value.trim()
        };
        addPerson(personData);
    });
});
var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));

// Person 목록 로드
function loadPersonList() {
    //전부조회하는거임
    fetch(contextPath + '/person')
        .then(response => response.json())
        .then(data => {
            populatePersonTable(data);
        })
        .catch(error => console.error('Error:', error));
}

// 테이블에 Person 데이터 추가
function populatePersonTable(personList) {
    const tableBody = document.querySelector('#personTable tbody');
    tableBody.innerHTML = ''; // 기존 데이터 초기화

    personList.forEach(person => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.gender}</td>
            <td>${person.age}</td>
            <td>${person.address}</td>
            <td>
                <button onclick="editPerson('${person.id}')">수정</button>
                <button onclick="deletePerson('${person.id}')">삭제</button>
            </td>
        `;

        tableBody.appendChild(row);
    });
}

// 새로운 Person 추가
function addPerson(personData) {
    fetch(contextPath + '/person', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(personData)
    })
        .then(resp => {
            if (resp.ok) {
                // 성공적으로 추가되면 목록을 다시 로드
                loadPersonList();
                // 폼 초기화
                document.getElementById('addPersonForm').reset();
            } else {
                resp.json().then(data => {
                    alert('오류 발생: ' + JSON.stringify(data));
                });
            }
        })
        .catch(error => console.error('Error:', error));
}

// Person 삭제
function deletePerson(id) {
    fetch(contextPath + `/person/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                // 성공적으로 삭제되면 목록을 다시 로드
                loadPersonList();
            } else {
                alert('삭제 실패');
            }
        })
        .catch(error => console.error('Error:', error));
}

// Person 수정 (추가 구현 필요)
function editPerson(id) {
    // 수정 폼을 띄우거나, 인라인 편집 기능을 구현할 수 있습니다.
    // 여기서는 간단히 alert로 표시합니다.
    alert('수정 기능은 추가 구현이 필요합니다.');
}
