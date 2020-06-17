// 정규표현식
//var regHp    =      // 휴대폰 번호 검사식

// 최종 점검을 위한 상태변수 선언
var isHpOk = false;

$(document).ready(function(){
	
	var alreadyCheck = false;
	
	$('input[name=hp]').focusout(function(){
		
		if(alreadyCheck){
			alreadyCheck = false;
			return false;
		}
		
		var tag = $(this);
		var hp = tag.val();
		var json = {"hp": hp};
		
		/* 정규식 일치 테스트
		if(regNick.test(nick) == false){
			alert('별명은 영어 소문자, 한글, 숫자(조합)로 최소 2자 이상이어야 합니다.');
			tag.focus();
			alreadyCheck = true;
			return false;
		}
		*/
		
		// 모든 검증이 통과되고 통신 시작
		$.ajax({
			url: '/Jboard1/user/proc/checkHp.jsp',
			type: 'get',
			data: json,
			dataType: 'json',
			success: function(data){
				
				if(data.result == 1){
					$('.resultHp').css('color', 'red').text('이미 등록된 번호 입니다.');
					//tag.focus();
				}else{
					$('.resultHp').css('color', 'green').text('사용하실 수 있는 번호 입니다.');
					isHpOk = true;
				}
			}
		});
	});
});