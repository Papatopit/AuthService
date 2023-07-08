package org.authService.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authService.configuration.WebClientConfiguration;
import org.authService.dto.UserDto;
import org.authService.dto.LoginAndPassRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ProxyService {

    @Autowired
    private WebClientConfiguration roleConfiguration;



    public Mono<JsonNode> changeUserRole(Long id, UserDto userDto) {
        return roleConfiguration.webClient()
                .put()
                .uri("/rolechange/" + id)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    public Flux<JsonNode> getAllUsers() {
        return roleConfiguration.webClient()
                .get()
                .uri("/all")
                .retrieve()
                .bodyToFlux(JsonNode.class);
    }

    public JsonNode getUserDetails(Long id) {
        return roleConfiguration.webClient()
                .get()
                .uri("/user/" + id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

    public Mono<JsonNode> createUser(UserDto userDto) {
        return roleConfiguration.webClient()
                .post()
                .uri("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    public Mono<JsonNode> updateUser(Long id, UserDto userDto) {
        return roleConfiguration.webClient()
                .put()
                .uri("/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    public JsonNode deleteUserById(Long id) {
        return roleConfiguration.webClient()
                .delete()
                .uri("/delete/" + id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

    public UserDto getUserByLoginAndPass(LoginAndPassRequest loginAndPassRequest) {
        return roleConfiguration.webClient()
                .post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(loginAndPassRequest)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
    public UserDto getUserByLogin(String login) {
        return roleConfiguration.webClient()
                .post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(login)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

}
