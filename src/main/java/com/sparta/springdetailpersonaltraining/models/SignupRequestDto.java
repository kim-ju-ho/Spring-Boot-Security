package com.sparta.springdetailpersonaltraining.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter

public class SignupRequestDto {


    private String username;
    private String password;
    private boolean admin = false;
    private String adminToken = "";


}
