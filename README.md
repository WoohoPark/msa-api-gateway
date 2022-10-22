<h1>API Gateway</h1>
API Gateway는 다수의 서버를 하나로 묶어, 외부 애플리케이션 혹은 클라이언트에 대한 하나의 진입점이라고 생각하면 된다.<br>
외부 어플리케이션 및 외부 클라이언트들은 마이크로 서비스에 직접 엑세스 하는 것이 제한되어 있기 때문에 그 사이의 중개자 역할을 한다.<br>

<h2>API Gateway가 필요한 이유</h2>
마이크로서비스 기반 애플리케이션에서는 일반적으로 서비스 마다 하나의 서버에 배포된다.<br>
이 때, 서비스를 요창하는 주체는 각각의 서비스의 호스트 및 포트를 기억해야 하고, 보안 인증을 거쳐야 한다.<br>
위에서 설명했듯, 이 일련의 과정들을 API Gateway로 해결이 가능하다.<br>

<h2>Zuul vs Spinrg Cloud Gateway</h2>
GateWay를 구축 하기 위해서는 Zuul과 Spring Cloud Gateway 둘 중에 하나를 선택해야 된다.<br>
특히 zuul 2와 sping cloud gateway 비교글은 많은데 spring boot 2와 spring cloud 2가 릴리즈 된 후에는 spring gateway의 성능이 더 뛰어나다고 평이 많다.<br>

![post-images_tlatldms_dc281650-3d89-11ea-9b93-ef55f725a755_msa-api-gateway-60-1024](https://user-images.githubusercontent.com/24665763/197346289-c36c4270-3b41-4e12-b5de-20c99999e217.jpg)
* Spring Cloud Gateway 구조



# 출처
https://techblog.woowahan.com/2523/
https://dev-racoon.tistory.com/41

