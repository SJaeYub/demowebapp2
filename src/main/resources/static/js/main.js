document.addEventListener('DOMContentLoaded', function () {
    const currentPath = window.location.pathname;

    if (currentPath === '/') {
        setupLoginPage();
    } else if (currentPath === '/signup') {
        setupSignupPage();
    } else if (currentPath === '/welcome') {
        setupWelcomePage();
    }

    checkSession();
});

function setupLoginPage() {
    const loginForm = document.getElementById('loginForm');
    const signupButton = document.getElementById('signupButton');

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const userId = document.getElementById('userId').value;
        const password = document.getElementById('password').value;
        login(userId, password);
    });

    signupButton.addEventListener('click', function () {
        window.location.href = '/signup';
    });
}

function setupSignupPage() {
    const signupForm = document.getElementById('signupForm');
    const checkIdButton = document.getElementById('checkIdButton');
    const checkCodeButton = document.getElementById('checkCodeButton');

    signupForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const userId = document.getElementById('userId').value;
        const password = document.getElementById('password').value;
        const email = document.getElementById('email').value;
        const name = document.getElementById('name').value;
        const englishName = document.getElementById('engName').value;
        const registrationCode = document.getElementById('registerCode').value;

        console.log(registrationCode);

        register(userId, password, email, name, englishName, registrationCode);
    });

    checkIdButton.addEventListener('click', function () {
        const userId = document.getElementById('userId').value;
        checkIdAvailability(userId);
    });

    checkCodeButton.addEventListener('click', function () {
        const registerCode = document.getElementById('registerCode').value;
        checkCodeAvailability(registerCode);
    })
}

function setupWelcomePage() {
    const logoutButton = document.getElementById('logoutButton');
    const callThreadTest = document.getElementById('callThreadTest');
    const callOrderTest = document.getElementById('callOrderTest');
    const adminButton = document.getElementById('adminButton');

    logoutButton.addEventListener('click', function () {
        logout();
    });

    callThreadTest.addEventListener('click', function () {
        fetchAllUsers();
    });

    callOrderTest.addEventListener('click', function () {
        testCallOrder();
    });

    // admin 권한 체크 및 버튼 표시/숨김 처리
    fetch('/api/member/check-admin')
        .then(response => response.json())
        .then(isAdmin => {
            if (isAdmin) {
                adminButton.style.display = 'block';
                adminButton.addEventListener('click', () => {
                    window.location.href = '/admin';
                });
            } else {
                adminButton.style.display = 'none';
            }
        })
        .catch(error => console.error('Error:', error));
}

function login(userId, password) {
    fetch('/api/member/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({userId, password}),
    })
        .then(response => response.text())
        .then(result => {
            if (result === 'Login successful') {
                window.location.href = '/welcome';
            } else {
                alert('로그인 실패: ' + result);
            }
        })
        .catch(error => console.error('Error:', error));
}

function register(userId, password, email, name, englishName, registrationCode) {
    console.log(userId + " " + password + " " + email + " " + name + " " + engName + " " + registrationCode)
    fetch('/api/member/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({userId, password, email, name, englishName, registrationCode}),
    })
        .then(response => response.text())
        .then(result => {
            if (result === 'User registered successfully') {
                alert('회원가입 성공');
                window.location.href = '/';
            } else {
                alert('회원가입 실패: ' + result);
            }
        })
        .catch(error => console.error('Error:', error));
}

function checkIdAvailability(userId) {
    fetch(`/api/member/check-id?userId=${userId}`)
        .then(response => response.json())
        .then(available => {
            if (available) {
                alert('사용 가능한 ID입니다.');
            } else {
                alert('이미 사용 중인 ID입니다.');
            }
        })
        .catch(error => console.error('Error:', error));
}

function checkCodeAvailability(registerCode) {
    fetch(`/api/member/check-code?registerCode=${registerCode}`)
        .then(response => response.json())
        .then(available => {
            if (available) {
                alert('유효한 등록 CODE입니다.');
            } else {
                alert('잘못된 등록 CODE입니다.');
            }
        })
        .catch(error => console.error('Error:', error));
}

function logout() {
    fetch('/api/member/logout', {method: 'POST'})
        .then(response => response.text())
        .then(result => {
            alert(result);
            window.location.href = '/';
        })
        .catch(error => console.error('Error:', error));
}

function checkSession() {
    fetch('/api/session/check')
        .then(response => response.json())
        .then(isValid => {
            if (!isValid && window.location.pathname !== '/' && window.location.pathname !== '/signup') {
                window.location.href = '/';
            } else if (isValid && (window.location.pathname === '/' || window.location.pathname === '/signup')) {
                window.location.href = '/welcome';
            }
        })
        .catch(error => console.error('Error:', error));
}

function fetchAllUsers() {
    fetch('/api/threads/testuserlist')
        .then(response => response.json())
        .then(users => {
            displayUsers(users);
        })
        .catch(error => console.error('Error:', error));
}

function displayUsers(users) {
    const tableBody = document.querySelector('#userTable tbody');
    const tableContainer = document.getElementById('userTableContainer');
    tableBody.innerHTML = ''; // 기존 테이블 내용 초기화

    users.forEach(user => {
        const row = tableBody.insertRow();
        row.insertCell(0).textContent = user.memberId;
        row.insertCell(1).textContent = user.userId;
        row.insertCell(2).textContent = user.name;
        row.insertCell(3).textContent = user.englishName;
        row.insertCell(4).textContent = user.email;
        row.insertCell(5).textContent = user.joinDate ? new Date(user.joinDate).toLocaleString() : 'N/A';
        row.insertCell(6).textContent = user.status;
        row.insertCell(7).textContent = user.subscriptionStatus;
    });

    tableContainer.style.display = 'block'; // 테이블 표시
}

function testCallOrder() {
    fetch('/api/threads/testdbthreads')
        .then(response => response.json())
        .then(result => {
            alert(result)
        })
        .catch(error => console.error('Error:', error));
}

