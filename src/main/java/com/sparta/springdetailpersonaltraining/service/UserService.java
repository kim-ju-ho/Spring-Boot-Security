package com.sparta.springdetailpersonaltraining.service;

import com.sparta.springdetailpersonaltraining.models.SignupRequestDto;
import com.sparta.springdetailpersonaltraining.models.User;
import com.sparta.springdetailpersonaltraining.models.UserRoleEnum;
import com.sparta.springdetailpersonaltraining.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // 회원가입 처리
    public void registerUser(SignupRequestDto requestDto) throws IllegalAccessException {
        //회원 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            throw new IllegalAccessException("중복된 사용자 입니다.");
        }
        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }


        User user = new User(username, password, role);
        userRepository.save(user);
    }
}
