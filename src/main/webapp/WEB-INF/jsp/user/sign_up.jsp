<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex mt-4">
	<div class="col-1"></div>
	<div class="col-10">
		<div class="display-4">회원가입</div>
		
		<form id="signUpForm" method="post" action="/user/sign_up">
		<div class="sign-up-box">
			<div class="mt-3 ml-3">
				<div class="form-group pt-3">
					<label for="loginId">ID</label>
					<div class="d-flex">
						<input type="text" class="form-control col-5" id="loginId" name="loginId" 
						placeholder="ID를 입력해주세요.">
						<button type="button" id="isDuplicatedIdBtn" class="btn btn-info ml-4">중복확인</button>
					</div>
					<div>
						<small id="checkIdLength" class="text-danger d-none">ID를 4자 이상 입력해주세요.</small>
						<small id="checkIdDuplicate" class="text-danger d-none">중복된 ID입니다.</small>
						<small id="checkIdOk" class="text-info d-none">사용가능한 ID입니다.</small>
					</div>
				</div>
				<div class="form-group">
					<label for="password">password</label>
					<input type="password" class="form-control col-6" id="password" name="password" placeholder="비밀번호를 입력해주세요.">
					<small id="pwdCheckProvided" class="text-danger">최소 8자 하나 이상의 문자와 숫자</small>
				</div>
				<div class="form-group">
					<label for="checkPwd">confirm password</label>
					<input type="password" class="form-control col-6" id="checkPwd" name="checkPwd" placeholder="비밀번호를 다시 입력해주세요.">
				</div>
				<div class="form-group">
					<label for="name">이름</label>
					<input type="text" class="form-control col-6" id="name" name="name" placeholder="이름을 입력해주세요.">
				</div>
				<div class="form-group">
					<label for="email">이메일</label>
					<input type="text" class="form-control col-6" id="email" name="email" placeholder="이메일을 입력해주세요.">
				</div>
				<div class="form-group">
					<label for="profileImg">프로필 사진을 선택해주세요.</label>
					<input type="file" class="form-control col-7" id="profileImg" name="profileImg" accept="image/png, imape/jpeg">
				</div>
			</div>
			<div class="d-flex justify-content-end mr-4 pb-4">
				<button type="submit" id="signUpBtn" class="btn btn-info">가입하기</button>
			</div>
		</div>
		</form>
	</div>
	<div class="col-1"></div>
</div>

<script>
	$(document).ready(function() {
		// 아이디 중복 확인 
		$('#isDuplicatedIdBtn').on('click', function(e) {
			let loginId = $('#loginId').val().trim();
			
			// 상황 문구 안보이게 모두 초기화 
			$('#checkIdLength').addClass('d-none');
			$('#checkIdDuplicate').addClass('d-none');
			$('#checkIdOk').addClass('d-none');
			
			if (loginId.length < 4) {
				$('#checkIdLength').removeClass('d-none');
			}
			
			// AJAX - 중복확인 
			$.ajax ({
				url: "/user/is_duplicated_id"
				, data: {"loginId":loginId}
				, success: function(data) {
					if (data.result == true) {
						// 중복 
						$('#checkIdDuplicate').removeClass('d-none');
					} else if (data.result == false) {
						// 사용 가능
						$('#checkIdOk').removeClass('d-none');
					} else {
						alert("아이디 중복 확인을 할 수 없습니다. 관리자에게 문의해 주세요.");
					}
				}
				, error: function(e) {
						alert("아이디 중복 확인에 실패하였습니다. 관리자에게 문의하여 주세요.");
				}
			});
		});
		
		// 회원가입 
		$('#signUpForm').on('submit', function(e) {
			e.preventDefault(); // 서브밋 기능 중단 
			
			// validation 
			let loginId = $('#loginId').val().trim();
			if (loginId == '') {
				alert("아이디를 입력해주세요.");
				return false;
			}
			
			let password = $('#password').val();
			let checkPwd = $('#checkPwd').val();
			let reg = new RegExp("^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$");
			if (password == '' || checkPwd == '') {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			/* console.log(password);
			if (reg.test(password) == false) {
				alert("비밀번호를 다시 설정해주세요.");
				return false;
			} */
			
			if (password != checkPwd) {
				alert("비밀번호가 일치하지 않습니다.");
				// 텍스트 값 초가화 
				$('#password').val('');
				$('#checkPwd').val('');
				return false;
			}
			
			let name = $('#name').val().trim();
			if (name == '') {
				alert("이름을 입력해주세요.");
				return false;
			}
			
			let email = $('#email').val().trim();
			if (email == '') {
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			// id 중복확인 유무 확인 
			if ($('#checkIdOk').hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요.");
				return false;
			}
			
			let url = $(this).attr('action');  // form에 있는 action주소 가져오기 
			let params = $(this).serialize();  // 폼태그에 들어있는 값을 한번에 보낼수 있게 구성한다. (name 속성)
			
			$.post(url, params)
			.done(function(data) {
				if (data.result == 'success') {
					alert("회원가입 완료! 로그인을 해주세요!");
					location.href = "/user/sign_in_view";
				} else {
					alert("회원 가입에 실패했습니다. 다시 시도해주세요.");
				}
			});
		});
	});

</script>








