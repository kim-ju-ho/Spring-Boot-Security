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
    public String registerUser(SignupRequestDto requestDto, String passwordConfirm) throws IllegalAccessException {
        //회원 중복 확인
        String username = requestDto.getUsername();
        if(!validUser(requestDto,passwordConfirm).equals("true")){
            return validUser(requestDto,passwordConfirm);
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
        return "true";
    }

    // 회원가입 validation 체크
    public String validUser(SignupRequestDto requestDto, String passwordConfirm) throws IllegalAccessException {
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        String validChkStr = "";
        if(found.isPresent()){
            validChkStr = "중복된 사용자 입니다.";
            return validChkStr;
        }else if(!(username.matches("^[가-힣a-zA-Z0-9]*$"))){
            validChkStr ="최소3자이상인 숫자,영어,한글로 이루어져야만 합니다.";
            return validChkStr;
        }else if(requestDto.getPassword().contains(username)){
            validChkStr = "비밀번호에는 username이 포함 될 수 없습니다";
            return validChkStr;
        } else if(requestDto.getPassword().length() <3){
            validChkStr = "비밀번호는 3자이상이어야합니다.";
            return validChkStr;
        }else if(!(requestDto.getPassword().equals(passwordConfirm))){
            validChkStr ="비밀번호 확인이 비밀번호랑 다릅니다.";
            return validChkStr;
        }else{
            return "true";
        }



    }
}
