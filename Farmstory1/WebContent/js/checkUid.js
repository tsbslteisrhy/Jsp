// 정규표현식
var regId    = /^[a-z]+[a-z0-9]{3,19}$/;     // 아이디 검사식

// 최종 점검을 위한 상태변수 선언
var isUidOk = false;

$(document).ready(function(){
	
	var alreadyCheck = false;
	
	$('input[name=uid]').focusout(function(){
		
		if(alreadyCheck){
			alreadyCheck = false;
			return false;
		}
		
		var tag = $(this);
		var uid = tag.val();
		var json = {"uid": uid};
		
		if(regId.test(uid) == false){
			alert('아이디는 영어 소문자, 숫자 조합으로 최소 4자 이상이어야 합니다.');
			alreadyCheck = true;
			return false;
		}
		
		// 모든 검증이 통과되고 통신 시작
		$.ajax({
			url: '/Farmstory1/user/proc/checkUid.jsp',
			type: 'get',
			data: json,
			dataType: 'json',
			success: function(data){
				
				if(data.result == 1){
					$('.resultId').css('color', 'red').text('이미 사용 중인 아이디 입니다.');
					tag.focus();
				}else{
					$('.resultId').css('color', 'green').text('사용하실 수 있는 아이디 입니다.');
					isUidOk = true;
				}
			}
		});
	});
});