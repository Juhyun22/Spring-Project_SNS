<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex mt-4">
	<div class="col-2"></div>
	<div class="col-8">
		<div class="display-4">로그인</div>
		
		<form id="signInForm" method="post" action="/user/sign_in">
			<div class="login-part">
				<div class="mt-4 pt-3 pb-3 w-100">
					<div class="input-group d-flex justify-content-center">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control col-6" id="loginId" name="loginId">
					</div>
					<div class="input-group mt-1 d-flex justify-content-center">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
						</div>
						<input type="password" class="form-control col-6" id="password" name="password">
					</div>
					<div class="d-flex mt-2 justify-content-center">
						<a href="/user/sign_up_view" class="btn btn-info col-3 mr-2 btnCss">회원가입</a>
						<button type="submit" class="form-control col-3 btn-info">로그인</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="col-2"></div>
</div>

<script>
	$(document).ready(function() {
		$('#signInForm').on('submit', function(e) {
			e.preventDefault(); // submit 기능 중단 -> validation check 등등
			
			// validation check
			let loginId = $('#loginId').val().trim();
			if (loginId == '') {
				alert("아이디를 입력해주세요.");
				return false;
			}
			
			let password = $('#password').val();
			if (password.length < 1) {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			// ajax 호출
			let url = $(this).attr('action');
			let params = $(this).serialize(); // form 태그에 있는 name 값들을 쿼리스트링으로 구성
			
			$.post(url, params)
			.done(function(data) { // 성공하면,
				if (data.result == 'success') {
					location.href = "/post/post_list_view";
				} else {
					alert(data.errorMessage);
				}
			});
		});
	});
</script>



