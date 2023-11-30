package com.kb.joonggo.Join;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Join {
    private int mbr_idx;
    private String mbr_nick;
    private String mbr_id;
    private String mbr_pwd;
    private String mbr_name;
    private String mbr_contact;
    private String mbr_email;
    private String mbr_addr;
    private Double mbr_credit;
}
