document.addEventListener('DOMContentLoaded', function() {
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

    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const userId = document.getElementById('userId').value;
        const password = document.getElementById('password').value;
        login(userId, password);
    });

    signupButton.addEventListener('click', function() {
        window.location.href = '/signup';
    });
}

function setupSignupPage() {
    const signupForm = document.getElementById('signupForm');
    const checkIdButton = document.getElementById('checkIdButton');

    signupForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const userId = document.getElementById('userId').value;
        const password = document.getElementById('password').value;
        const email = document.getElementById('email').value;
        register(userId, password, email);
    });

    checkIdButton.addEventListener('click', function() {
        const userId = document.getElementById('userId').value;
        checkIdAvailability(userId);
    });
}

function setupWelcomePage() {
    const logoutButton = document.getElementById('logoutButton');

    logoutButton.addEventListener('click', function() {
        logout();
    });
}

function login(userId, password) {
    fetch('/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userId, password }),
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

function register(userId, password, email) {
    fetch('/api/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userId, password, email }),
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
    fetch(`/api/user/check-id?userId=${userId}`)
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

function logout() {
    fetch('/api/user/logout', { method: 'POST' })
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