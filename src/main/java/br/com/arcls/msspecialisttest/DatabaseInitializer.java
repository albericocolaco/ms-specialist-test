package br.com.arcls.msspecialisttest;

import br.com.arcls.msspecialisttest.domain.dto.CreateUserRequest;
import br.com.arcls.msspecialisttest.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<String> usernames = List.of("admin@teste.com");
    private final List<String> fullNames = List.of("User Admin Init");
    private final String password = "123456";

    private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        for (int i = 0; i < usernames.size(); ++i) {
            CreateUserRequest request = new CreateUserRequest();
            request.setUsername(usernames.get(i));
            request.setFullName(fullNames.get(i));
            request.setPassword(password);
            request.setRePassword(password);

            userService.upsert(request);
        }
    }
}