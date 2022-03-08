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
					<label for="file">
						<i class="bi bi-file-earmark-arrow-down" id="fileDownloadIcon"></i>
						<span id="fileName"></span>
					</label>
					<input type="file" id="file" name="file" accept=".jpg,.png,.jpeg,.gif">
					
					<button type="button" class="btn btnCss btn-info" id="uploadBtn" name="uploadBtn">업로드</button>
				</div>
			</div>
		</div>
		</c:if>
		
		<%-- timelinepart --%>
		<c:forEach items="${contentList}" var="content">
		<div class="border my-3">
		<div class="timeline-posts-part">
			<div class="post-part">
				<div class="userId-part w-100 d-flex justify-content-between px-2">
					<div class="text-info font-weight-bold">${content.user.loginId}</div>
					
					<%-- 글 쓴 사용자와 로그인 사용자가 일치할 때만 삭제 가능 --%>
					<c:if test="${userLoginId eq content.user.loginId}">
					<a href="#" data-toggle="modal" data-target="#moreModal" class="more-btn text-dark" data-post-id="${content.post.id}">
						<i id="deletePost" class="bi bi-three-dots deletePost"></i>
					</a>
					</c:if>
				</div>
				<div class="post-image-part">
					<div class="mt-1">
						<div class="put-image">
							<div class="text-center border">
							<c:if test="${not empty content.post.imagePath}">
								<img src="${content.post.imagePath}" width="380px" alt="게시물이미지">
							</c:if>
							<c:if test="${empty content.post.imagePath}">
								<i class="bi bi-image" id="imageIcon"></i>
								<div>No Image</div>
							</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
				<%-- 좋아요 part  --%>
				<div class="like-part">
					<div class="mt-1 mb-0">
						<a href="#" id="likeBtn" class="likeBtn text-dark" data-post-id="${content.post.id}">
							<%-- 안 좋아요 --%>
							<c:if test="${content.filledLike eq false}">
								<i class="bi bi-heart"></i>
							</c:if>
							<%-- 좋아요 --%>
							<c:if test="${content.filledLike eq true}">
								<i class="bi bi-heart-fill"></i>
							</c:if>
						</a>
						<span class="likeCount-part">좋아요 ${content.likeCount}개</span>
					</div>
				</div>
				
				<%-- 게시글 내용 --%>
				<div class="post-content-part">
					<span class="userId font-weight-bold">${content.user.loginId}</span>
					<span class="comment">${content.post.content}</span>							
				</div>
			</div>
			
			<%-- 댓글 part --%>
			<div class="comment-part my-2 px-2">
				<div class="comment-icon-part px-2">
					<div class="text-info font-weight-bold">댓글</div>
				</div>
				
				<%-- 글에 대한 댓글 --%>
				<c:if test="${not empty content.commentList}">
				<c:forEach items="${content.commentList}" var="commentView">
				<div class="my-1">
					<span class="font-weight-bold">${commentView.user.loginId}</span>
					<span class="ml-2">${commentView.comment.content}</span>
					
					<%-- 댓글쓴이면 삭제 가능 --%>
					<c:if test="${commentView.user.loginId eq userLoginId}">
						<label for="deleteComment">
							<i id="deleteComment" class="deleteComment bi bi-x" data-comment-id="${commentView.comment.id}"></i>
						</label>
					</c:if>
				</div>
				</c:forEach>
				</c:if>
				
				<%-- 댓글 작성 part --%>
				<%-- 로그인이 된 상태에서만 쓸 수 있다. --%>
				<c:if test="${not empty userId}">
				<div class="d-flex mt-2">
					<input type="text" id="commentText${content.post.id}" name="commentText" class="form-control" placeholder="댓글 내용을 입력해주세요.">
					<button type="button" id="commentBtn" class="commentBtn btn btn-info btnCss ml-3" data-post-id="${content.post.id}" name="commentBtn">게시</button>
				</div>
				</c:if>
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="moreModal">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
 		<%-- 모달 창 안에 내용 넣기 --%>
 		<div>
 			<div class="my-3 text-center">
 				<a href="#" class="del-post d-block text-dark">삭제</a>
 			</div>
 			<div class="border-top text-center">
 				<%-- data-dismiss: 모달창 닫힘 --%>
 				<a href="#" class="cancle d-block text-dark" data-dismiss="modal">취소</a>
 			</div>
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
			let formData = new FormData();
			formData.append("content", postContent);
			formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]은 업로드 된 첫번쨰 파일을 의미 
			
			// ajax 통신으로 form에 있는 데이터를 전송한다. 
			$.ajax({
				type: "POST"
				, url: "/post/create"
				, data: formData
				, enctype: "multipart/form-data"  // 파일 업로드를 위한 필수 설정 
				, processData: false  // 파일 업로드를 위한 필수 설정 
				, contentType: false  // 파일 업로드를 위한 필수 설정 
				, success: function(data) {
					if (data.result == 'success') {
						alert("게시물 업로드 성공!");
						location.reload();
					} else {
						alert("게시물 업로드에 실패하였습니다. 다시 시도해 주세요.");
					}
				}
				, error : function(e) {
					alert("게시물 업로드에 실패하였습니다. 관리자에게 문의해주세요.");
				}
			});
		});
		
		// 카드에서 더보기(...) 클릭 시 모달에 삭제될 글 번호를 넣어준다.
		$('.more-btn').on('click', function(e) {
			e.preventDefault();
			
			let postId = $(this).data('post-id');
			
			$('#moreModal').data('post-id', postId);  // data-post-id처럼 값 넣어주기 
		});
		
		
		// 게시물 삭제 - 모달창 안에 있는 삭제하기 버튼 클릭 
		$('#moreModal .del-post').on('click', function(e) {
			e.preventDefault();
			
			let postId = $('#moreModal').data('post-id');  // postId 세팅 

			// 삭제 AJAX 
			$.ajax({
				type: "DELETE"
				, url: "/post/delete"
				, data: {"postId":postId}
				, success: function(data) {
					if (data.result == "success") {
						alert(data.successMessage);
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("게시물 삭제에 실패했습니다. 관리자에게 문의하여주세요.");
				}
			});
		});
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		// 좋아요 
		$('.likeBtn').on('click', function(e) {
			e.preventDefault();  // <a>기능 중단 
			
			let postId = $(this).data('post-id');
			
			// alert(postId);
			
			$.ajax({
				url: "/like/" + postId
				, success: function(data) {
					if (data.result == "success") {
						location.reload();
					} else {
						alert(result.errorMessage);
					}
				}
				, error : function(e) {
					alert("좋아요 등록에 실패하였습니다. 다시 시도해주세요.");
				}
			});
		});
		
		
		///////////////////////////////////////////////////////////////////////////////////////////
		
		// 댓글 쓰기 - 게시 버튼 클릭 
		$(".commentBtn").on('click', function(e) {
			let postId = $(this).data('post-id');  // data-post-id .. 무조건 -으로! : 규칙임!!
			
			let commentContent = $('#commentText' + postId).val().trim();
			if (commentContent == '') {
				alert("댓글을 입력해주세요.");
				return;
			}
			
			// ajax
			$.ajax({
				type: "POST"
				, url: "/comment/create"
				, data: {"postId":postId, "content":commentContent}
				, success: function(data) {
					if (data.result == "success") {
						alert("댓글 작성 성공!");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("댓글 작성에 실패하였습니다. 관리자에게 문의하여주세요.");
				}
			});
		});
  
		
		// 댓글 삭제 -> 로그인 되었을 때, 로그인 된 본인만 볼 수 있게 
		$('.deleteComment').on('click', function(e) {
			let commentId = $(this).data('comment-id');
			
			// AJAX
			$.ajax({
				type: "DELETE"
				, url: "/comment/delete"
				, data: {"commentId":commentId}
				, success: function(data) {
					if (data.result == "success") {
						alert("댓글 삭제 완료!");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("댓글 삭제에 실패하였습니다. 관리자에게 문의해주세요.");
				}
			});
		});
		
	});
	
</script>
