package com.example.demo.filter;

import com.example.demo.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
//@RequiredArgsConstructor
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
	private final JwtTokenProvider jwtTokenProvider;

	Environment env;


//	@Autowired
	public AuthorizationHeaderFilter(JwtTokenProvider jwtTokenProvider, Environment env){
		super(Config.class);
		this.jwtTokenProvider=jwtTokenProvider;
		this.env=env;
	}

	public static class Config {
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();

			HttpHeaders headers = request.getHeaders();
			if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
				return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
			}

			String authorizationHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);

			String token = authorizationHeader.replace("Bearer", "");

			jwtTokenProvider.validateJwtToken(token);

			String subject = jwtTokenProvider.getUserId(token);

//			if (subject.equals("feign")) return chain.filter(exchange);

//			if (false == jwtTokenProvider.getRoles(token).contains("Customer")) {
//				return onError(exchange, "권한 없음", HttpStatus.UNAUTHORIZED);
//			}
			log.info("jwtTokenProvider.getRoles(token) : "+jwtTokenProvider.getRoles(token));

			ServerHttpRequest newRequest = request.mutate()
					.header("user-id", subject)
					.build();

			return chain.filter(exchange.mutate().request(newRequest).build());
		};
	}

	private Mono<Void> onError(ServerWebExchange exchange, String errorMsg, HttpStatus httpStatus) {
		log.error(errorMsg);

		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);

		return response.setComplete();
	}
}
