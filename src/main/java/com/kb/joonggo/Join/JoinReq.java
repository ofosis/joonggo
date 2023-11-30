package com.kb.joonggo.Join;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinReq {

    private int mbr_idx;

    @NotEmpty
    @Size(min = 2, max = 8, message = "닉네임 입력이 잘못되었습니다.")
    private String mbr_nick;

    @NotEmpty
    @Size(min = 4, max = 16,message = "아이디 입력이 잘못되었습니다.")
    private String mbr_id;

    @NotEmpty
    @Size(min = 6, max = 20,message = "비밀번호 입력이 잘못되었습니다.")
    private String mbr_pwd;

    @NotEmpty
    private String mbr_name;

    @NotEmpty
    @Size(min = 13, max = 13,message = "전화번호 입력이 잘못되었습니다.")
    private String mbr_contact;

    @NotEmpty
    @Email
    @Size(min = 4, max = 30,message = "이메일 입력이 잘못되었습니다.")
    private String mbr_email;

    @NotEmpty
    private String mbr_addr;


    private Double mbr_credit;
}
