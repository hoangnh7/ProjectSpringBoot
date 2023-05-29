$(function () {
  resetModal();
  SIGNUP_DATA = DB.getAccountData();
  CURRENT_SIGNED_ACCOUNT = DB.getSignedAccount();
  if (DB.getSignedStatus() == true) {
    signedValidate(DB.getSignedStatus());
  } else {
    signedValidate();
  }

  $(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
      $('.back-to-top').fadeIn();
    } else {
      $('.back-to-top').fadeOut();
    }
  });
  $('.back-to-top').click(function () {
    $("html, body").animate({ scrollTop: 0 }, 600);
    return false;
  });
})

// Login/Sign up validate

$(document).on('click', function (e) {
  let target = e.target;

  // Validate sign in
  if (target.closest('.sign-in-btn')) {
    $('.invalid-feedback').css('display', 'none');
    let emailFormat = new RegExp(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);

    let isValid = true;
    let signInEmail = $('.sign-in-email');
    let signInEmailValue = signInEmail.val();
    let signInEmailInvalid = $('.sign-in-email').next();
    let signInPassword = $('.sign-in-password');
    let signInPasswordValue = signInPassword.val();
    let signInPasswordInvalid = $('.sign-in-password').next();


    if (signInEmailValue == "") {
      signInEmailInvalid.css('display', 'block');
      signInEmailInvalid.html('Vui lòng nhập email');
      isValid = false;
    } else if (!emailFormat.test(signInEmailValue)) {
      signInEmailInvalid.css('display', 'block');
      signInEmailInvalid.html('Email không hợp lệ');
      isValid = false;
    }
    if (signInPasswordValue == "") {
      signInPasswordInvalid.css('display', 'block');
      signInPasswordInvalid.html('Vui lòng nhập password')
      isValid = false;
    }

    // Get data from localStorage

    if (isValid == true) {

     req = {
             email: signInEmailValue,
             password: signInPasswordValue
          }
          var myJSON = JSON.stringify(req);
          $.ajax({
             url: '/api/login',
             type: 'POST',
             data: myJSON,
             contentType: "application/json; charset=utf-8",
             success: function(data) {
                 alert("Đăng nhập thành công");
                 signedValidate(true, data.email);
                 $('.modal').modal('hide');
             },
             error: function(data) {
//                 toastr.warning(data.responseJSON.message);
             },
          });

    }
  }

  // Validate sign up
  if (target.closest('.sign-up-btn')) {
    let isValid = true;
    $('.invalid-feedback').css('display', 'none');
    let phoneFormat = new RegExp(/((09|03|07|08|05)+([0-9]{8})\b)/g);
    let emailFormat = new RegExp(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);

    let fullName = $('.sign-up-full-name');
    let fullNameValue = fullName.val();
    let fullNameInvalid = fullName.next();
    let phone = $('.sign-up-phone');
    let phoneValue = phone.val();
    let phoneInvalid = phone.next();
    let email = $('.sign-up-email');
    let emailValue = email.val();
    let emailInvalid = email.next();
    let password = $('.sign-up-password');
    let passwordValue = password.val();
    let passwordInvalid = password.next();
    let confirmPassword = $('.sign-up-confirm-password');
    let confirmPasswordValue = confirmPassword.val();
    let confirmPasswordInvalid = confirmPassword.next();

    // Validate name
    if (fullNameValue == "") {
      fullNameInvalid.css('display', 'block');
      fullNameInvalid.html('Vui lòng nhập họ tên');
      isValid = false;
    }

    // Validate phone
    if (phoneValue == "") {
      phoneInvalid.css('display', 'block');
      phoneInvalid.html('Vui lòng nhập số điện thoại');
      isValid = false;
    } else if (!phoneFormat.test(phoneValue)) {
      phoneInvalid.css('display', 'block');
      phoneInvalid.html('Số điện thoại không hợp lệ');
      isValid = false;
    }

    // Validate email
    let checkExistedEmail = true;

    if (DB.getAccountData()['current-email'] !== undefined) {
      // SIGNUP_DATA = DB.getAccountData();
      let currentExistedEmail = []
      let currentEmail = SIGNUP_DATA['current-email'];

      for (let i = 0; i < currentEmail.length; i++) {
        currentExistedEmail.push(currentEmail[i]);
        if (currentExistedEmail.includes(emailValue)) {
          checkExistedEmail = false;
          break;
        }
      }

      if (!checkExistedEmail) {
        emailInvalid.css('display', 'block');
        emailInvalid.html('Email đã tồn tại, vui lòng chọn email khác');
        isValid = false;
      }
    }

    if (emailValue == "") {
      emailInvalid.css('display', 'block');
      emailInvalid.html('Vui lòng nhập email');
      isValid = false;
    } else if (!emailFormat.test(emailValue)) {
      emailInvalid.css('display', 'block');
      emailInvalid.html('Email không hợp lệ');
      isValid = false;
    }

    // Validate password
    if (passwordValue == "") {
      passwordInvalid.css('display', 'block');
      passwordInvalid.html('Vui lòng nhập password');
      isValid = false;
    }

    // Validate confirm password
    if (confirmPasswordValue == "") {
      confirmPasswordInvalid.css('display', 'block');
      confirmPasswordInvalid.html('Vui lòng xác nhận lại password');
      isValid = false;
    } else if (confirmPasswordValue !== passwordValue) {
      confirmPasswordInvalid.css('display', 'block');
      confirmPasswordInvalid.html('Mật khẩu và xác nhận mật khẩu không giống nhau');
      isValid = false;
    }

    // Save to localStorage

    if (isValid == true) {
        req = {
                 name: fullNameValue,
                 email: emailValue,
                 password: passwordValue,
                 phone: phoneValue
             }
             var myJSON = JSON.stringify(req);
             $.ajax({
                 url: '/api/register',
                 type: 'POST',
                 data: myJSON,
                 contentType: "application/json; charset=utf-8",
                 success: function(data) {
//                     toastr.success("Đăng ký thành công");
                      alert("đăng ký thành công");
                     signedValidate(true, data.fullName);
                     $('.modal').modal('hide');
                 },
                 error: function(data) {
//                     toastr.warning(data.responseJSON.message);
                 },
             });
      $('.modal').modal('hide');
    }
  }

  if (target.closest('.product-link')) {
    localStorage.setItem('sessionsProduct', $(this).attr('id'));

    if (target !== $('.product-link')) {
      let productId = $(target).parents('.product-link').attr('id');
      localStorage.setItem('sessionsProduct', productId);
    }
  }
})

function convertPrice(currency) {
  let convert = new Intl.NumberFormat('vn-VN', {
    minimumFractionDigits: 0
  }).format(currency);

  return convert;
}

let SIGNUP_DATA = {};
let CURRENT_SIGNED_ACCOUNT = {
};
let CURRENT_ACCOUNT_DETAILS;
let signed = false;
let account = false;
// Local storage

let DB = {
  getAccountData: function () {
    if (typeof (Storage) !== "undefined") {
      let data;
      try {
        data = JSON.parse(localStorage.getItem('sign-up')) || {};
      } catch (error) {
        data = {};
      }

      return data;
    } else {
      alert('Sorry! No Web Storage support...');
      return {};
    }
  },

  setAccountData: function (data) {
    localStorage.setItem('sign-up', JSON.stringify(data));
  },

  // Check signed or not
  getSignedStatus: function () {
    if (typeof (Storage) !== "undefined") {
      let data;
      try {
        data = JSON.parse(localStorage.getItem('signed')) || {};
      } catch (error) {
        data = {};
      }

      return data;
    } else {
      alert('Sorry! No Web Storage support...');
      return {};
    }
  },

  setSignedStatus: function (data) {
    localStorage.setItem('signed', data);
  },

  getSignedAccount: function () {
    if (typeof (Storage) !== "undefined") {
      let data;
      try {
        data = JSON.parse(localStorage.getItem('signed-account')) || {};
      } catch (error) {
        data = {};
      }

      return data;
    } else {
      alert('Sorry! No Web Storage support...');
      return {};
    }
  },

  setSignedAccount: function (data) {
    localStorage.setItem('signed-account', JSON.stringify(data));
  }
}

// Reset form after close modal

function resetModal() {
  $('.modal').on('hidden.bs.modal', function () {
    $(this).find('form').trigger('reset');
    $('.invalid-feedback').css('display', 'none');
  })
}

function signedValidate(status = false, fullname = '') {
  if (status == true) {
    isLogined = true;
    let signedLink = `
  <a id="account-setting" class="nav-link account-setting" href="/tai-khoan">Xin chào ${fullname}</a>`;

    $('.account-setting').replaceWith(signedLink);
  } else {
    isLogined = false;
    let notSignedLink = `
  <a class="nav-link account-setting" href="" data-toggle="modal" data-target="#signInSignUp">Tài khoản</a>
  `;
    $('.account-setting').replaceWith(notSignedLink);
  }
}
