package me.chong.springboot2_0;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User save(String name) {
        User user = User.builder().name(name).build();
        if (userRepository.save(user)) {
            log.info("保存用户[{}]成功", user);
        }
        return user;
    }
}
