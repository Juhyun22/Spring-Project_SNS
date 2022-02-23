<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="timeline-posting-part bg-muted form-group">
			<div class="my-4">
				<textarea class="form-control" id="content" name="content" placeholder="내용을 입력해주세요." rows="5"></textarea>
				
				<div class="d-flex justify-content-between mt-1">
					<label for="file"><i class="fas fa-file fa-1g"></i> 이미지 추가</label>
					<input type="file" id="file" name="file" accept=".jpg,.png,.jpeg,.gif">
					
					<button type="button" class="btn btnCss btn-info" id="uploadBtn" name="uploadBtn">업로드</button>
				</div>
			</div>
		</div>
		<div class="timeline-posts-part">
			<div class="post-part">
				<div class="userId-part w-100 d-flex justify-content-between px-2">
					<div class="text-info font-weight-bold">bora</div>
					<label for="deletePost" class="delete-icon"><i id="deletePost" class="fa fa-ellipsis-h deletePost"></i></label>
				</div>
				<div class="post-image-part">
					<div class="mt-1">
						<div class="put-image">
							<div class="border text-center">No Imges</div>
						</div>
					</div>
				</div>
				<div class="like-part">
					<div class="mt-1 mb-0">
						<label for="like"><i id="like" class="fa fa-heart like"></i>  좋아요</label>
						<span class="">22개</span>
					</div>
				</div>
				<div class="post-comment-part">
					<span class="userId font-weight-bold">borabora</span>
					<span class="comment">testing</span>							
				</div>
			</div>
			<div class="comment-part my-2 px-2">
				<div class="comment-icon-part px-2">
					<div class="text-info font-weight-bold">댓글</div>
				</div>
				<div class="my-1">
					<span class="font-weight-bold">아이디1</span>
					<span>댓글testtt</span>
				</div>
				<div class="d-flex">
					<input type="text" id="commentText" name="commentText" class="form-control" placeholder="댓글 내용을 입력해주세요.">
					<button type="button" class="btn btn-info btnCss ml-3" id="commentBtn" name="commentBtn">게시</button>
				</div>
			</div>
		</div>
	</div>
</div>