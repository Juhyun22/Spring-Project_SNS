<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="w-50">
	
		<%-- post-part 로그인이 된 경우에만 보이게! --%>
		<c:if test="${not empty userId}">
		<div class="timeline-posting-part bg-muted form-group">
			<div class="my-4">
				<textarea class="form-control" id="postContent" name="postContent" placeholder="내용을 입력해주세요."></textarea>
				
				<div class="d-flex justify-content-between mt-1">
					<label for="file"><i class="fas fa-file fa-1g"></i><span id="fileName"></span></label>
					<input type="file" id="file" name="file" accept=".jpg,.png,.jpeg,.gif">
					
					<button type="button" class="btn btnCss btn-info" id="uploadBtn" name="uploadBtn">업로드</button>
				</div>
			</div>
		</div>
		</c:if>
		
		<%-- timelinepart --%>
		<div class="timeline-posts-part mt-2">
		<c:forEach items="${postList}" var="post">
			<div class="post-part">
				<div class="userId-part w-100 d-flex justify-content-between px-2">
					<div class="text-info font-weight-bold">${post.id} borabora</div>
					<label for="deletePost" class="delete-icon"><i id="deletePost" class="fa fa-ellipsis-h deletePost"></i></label>
				</div>
				<div class="post-image-part">
					<div class="mt-1">
						<div class="put-image">
							<div class="border text-center">No Imges</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		
				<%-- 좋아요 part  --%>
				<div class="like-part">
					<div class="mt-1 mb-0">
						<label for="like"><i id="like" class="fa fa-heart like"></i>  좋아요</label>
						<span class="">22개</span>
					</div>
				</div>
				<div class="post-content-part">
					<span class="userId font-weight-bold">borabora</span>
					<span class="comment">testing</span>							
				</div>
			</div>
			
			<%-- 댓글 part --%>
			<div class="comment-part my-2 px-2">
				<div class="comment-icon-part px-2">
					<div class="text-info font-weight-bold">댓글</div>
				</div>
				<div class="my-1">
					<span class="font-weight-bold">아이디1</span>
					<span>댓글testtt</span>
				</div>
				<%-- 댓글 작성 part --%>
				<div class="d-flex">
					<input type="text" id="commentText" name="commentText" class="form-control" placeholder="댓글 내용을 입력해주세요.">
					<button type="button" class="btn btn-info btnCss ml-3" id="commentBtn" name="commentBtn">게시</button>
				</div>
			</div>
		</div>
	</div>


<script>
	$(document).ready(function() {
		// 파일 업로드 이미지 클릭 
		/* $('#file').on('click', function(e) {
			e.preventDefault(); // <a>기본 기능 중단 - 위로 올라가는 현상 
			$('#file').click(); // input file을 클릭한것과 같은 효과 
		}); */
		
		// 사용자가 파일을 업로드 했을 때, 유효성 확인 및 업로드 된 파일 이름 노출 
		$('#file').on('change', function(e) {
			let fileName = e.target.files[0].name;  // ex) image.png
			let extension =  fileName.split('.');
			
			// 확장자 유효성 확인
			if (extension.length < 2 || 
					(extension[extension.length - 1]) != 'gif' &&
						(extension[extension.length - 1]) != 'jpeg' &&
							(extension[extension.length - 1]) != 'png' &&
								(extension[extension.length - 1]) != 'jpg' ) {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$(this).val();  // 비워주어야 한다. 
				$('#fileName').text('');
				return;
			}
			
			if (fileName.length > 30) {
				fileName = " ~" + fileName.slice(-30);
			}
								
			$('#fileName').text(fileName);				
			
		});
		
		// 게시물 업로드 - 파일 업로드 
		$('#uploadBtn').on('click', function(e) {
			let postContent = $('#postContent').val();
			if (postContent < 1) {
				alert("내용을 입력해주세요.");
				return;
			}
			
			// 파일이 업로드 되는 경우 확장자 체크 
			let file = $('#file').val(); // 파일의 경로만 가져온다.
			
			// form 태그를 javascript에서 만든다. 
			// post, comment 두 부분으로 form 태그 id만 다르게 해서 만들어줘도 될듯..!
			let formData = new formData();
			formData.append("content", postContent);
			formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]은 업로드 된 첫번쨰 파일을 의미 
			
			// ajax 통신으로 form에 있는 데이터를 전송한다. 
			$.ajax({
				type: "post"
				, url: "/post/create"
				, data: formData
				, enctype: "multipart/form-data"  // 파일 업로드를 위한 필수 설정 
				, processData: false  // 파일 업로드를 위한 필수 설정 
				, contentType: false  // 파일 업로드를 위한 필수 설정 
				, success: function(data) {
					if (data.result == 'success') {
						
					} else {
						alert("게시물 업로드에 실패하였습니다. 다시 시도해 주세요.");
					}
				}
				, error : function(e) {
					alert("게시물 업로드에 실패하였습니다. 관리자에게 문의해주세요.");
				}
			});
		});
		
		// 댓글 게시 -> 로그인 되었을때 만 
		
		// 댓글 삭제 -> 로그인 되었을 때, 로그인 된 본인만 볼 수 있게 
	});
	
</script>
