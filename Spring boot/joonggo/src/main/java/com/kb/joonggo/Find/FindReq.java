package com.kb.joonggo.Find;

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
public class FindReq {

    private int mbr_idx;

    @NotEmpty
    @Size(min = 4, max = 16,message = "아이디 입력이 잘못되었습니다.")
    private String mbr_id;

    @NotEmpty
    @Size(min = 13, max = 13,message = "전화번호 입력이 잘못되었습니다.")
    private String mbr_contact;

    @NotEmpty
    @Email
    @Size(min = 4, max = 16,message = "제목을 2글자 이상 입력하세요.")
    private String mbr_email;

}
