$(function() {
	// 아이디 중복 체크
	$('#id').focusout(function () {
		$('#idDiv2').empty();

		if ($('#id').val() == ''){
			$('#idDiv2').html('먼저 아이디 입력');
		}
		else{
			$.ajax({
				type: 'post',
				url: '/getExistId',
				data: {'id': $('#id').val()},
				dataType: 'text',
				success: function (data) {
					if (data.trim() == 'non_exist') $('#idDiv2').html('사용 가능한 아이디').css('color', 'blue');
					else $('#idDiv2').html('사용 불가능한 아이디').css('color', 'red');
				},
				error: function (e) {
					console.log(e);
				}
			});
		}
	});
});
	// 아이디 등록
$('#writeBtn').click(function(){
	let name = $('#name').val();
	let id = $('#id').val();
	let pwd = $('#pwd').val();

	$('#nameDiv').empty();
	$('#idDiv2').empty();
	$('#pwdDiv').empty();

	if(name == '') $('#nameDiv').html('이름을 입력하세요');
	else if(id == '') $('#idDiv2').html('아이디를 입력하세요');
	else if(pwd == '') $('#pwdDiv').html('비밀번호를 입력하세요');
	else {
		$.ajax({
			type : 'post',
			url : '/write',
			data : $('#userWriteForm').serialize(),
			success : function(){
				location.href = '/list';
			},
			error : function(e){
				console.log(e);
			}
		});
	}
});
$('#updateBtn').click(function(){
	let name = $('#name').val();
	let id = $('#id').val();
	let pwd = $('#pwd').val();

	$('#nameDiv').empty();
	$('#idDiv2').empty();
	$('#pwdDiv').empty();

	if(name == '') $('#nameDiv').html('이름을 입력하세요');
	else if(id == '') $('#idDiv2').html('아이디를 입력하세요');
	else if(pwd == '') $('#pwdDiv').html('비밀번호를 입력하세요');
	else {
		$.ajax({
			type : 'post',
			url : '/update',
			data : $('#updateForm').serialize(),
			success : function(){
				location.href = '/list';
			},
			error : function(e){
				console.log(e);
			}
		});
	}
});
$('#deleteBtn').click(function(){
	const pwdCheck = $('#pwd').val();

	const pwdInput = prompt("비밀번호를 입력하세요 : ");

	if (pwdCheck != pwdInput){
		alert("비밀번호가 일치하지 않습니다.")
	}else{
		$.ajax({
			type : 'get',
			url : '/delete',
			data : {'id': $('#id').val()},
			dataType: 'text',
			success : function (data) {
				alert("비밀번호가 일치합니다. 회원정보가 삭제됩니다.")
				location.href='/list?pg=1';
			},
			error : function(e){
				console.log(e);
			}
		})
	}
	/*$('#deleteBtn').click(function(){
		$('#pwdDiv').empty();

		if ($('#pwd').val()=='')
			$('#pwdDiv').html('먼저 비밀번호를 입력');
		else
			$.ajax({
				type : 'post',
				url : '/getExistPwd',
				data : {'id': $('#id').val()}, //서버로 보내는 데이터
				dataType: 'json', //서버로부터 받는 데이터 타입
				success : function (data) {},
				alert() {
				}
				error : function(e){
					console.log(e);
				}
			})
	});
	*/
})


