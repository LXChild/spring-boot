package me.chong.springboot2_0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public RouterFunction<ServerResponse> findAll() {
        Collection<User> users = userRepository.findAll();
        return route(GET("/users"),
                serverRequest -> {
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return route(GET("/users/{id}").and(accept(APPLICATION_JSON)), userHandler::getUser);
    }

    @Component
    public class UserHandler {
        public Mono<ServerResponse> getUser(ServerRequest request) {
            User user = userRepository.findOne(Integer.valueOf(request.pathVariable("id")));
            return ServerResponse.ok().body(Flux.fromIterable(Collections.singletonList(user)), User.class);
        }
    }
}
