<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
callback 처리중입니다. 이 페이지에서는 callback을 처리하고 바로 main으로 redirect하기때문에 이 메시지가 보이면 안됩니다.

	<!-- (1) LoginWithNaverId Javscript SDK -->
	<script src="<c:url value='/assets/js/jquery.min.js'/>"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>


	<!-- (2) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>
	var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "bGcr_qQ18WcY_tp0AIjX",
				callbackUrl: "http://localhost:8080/runbike/user/login/naver",
				isPopup: true,
				callbackHandle: true
				/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
			}
		);

		/* (3) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		
		/* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
					var email = naverLogin.user.getEmail();
					var name = naverLogin.user.getName();
					
					$.ajax({
						type:'GET',
						url:'http://localhost:8080/runbike/user/register/idCheck?u_id='+email,
						success: function(data){
							console.log(data);
							if(data == 'Y'){									
								alert("사용 가능한 아이디 입니다.");
							} else if(data == 'N') {
								alert("이미 존재하거나 탈퇴한 아이디 입니다.");
							}
						},
						error: function(data){
							console.log("실패쓰");
						}
					
					});
					window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/runbike/record/startRide");
				} else {
					console.log("callback 처리에 실패하였습니다.");
				}
			});
		});
	</script>
</body>
</html>