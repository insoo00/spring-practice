package hello.spring.security.service;

import hello.spring.security.dto.JoinDto;
import hello.spring.security.entity.UserEntity;
import hello.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDto joinDto) {

        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if (isUser) {
            return;
        }

        UserEntity user = new UserEntity();
        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setRole("ROLE_ADMIN");


        userRepository.save(user);
    }
}
