package com.kb.joonggo.Find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Find {
    private int mbr_idx;
    private String mbr_nick;
    private String mbr_id;
    private String mbr_pwd;
    private String mbr_name;
    private String mbr_contact;
    private String mbr_email;
    private String mbr_addr;
    private int mbr_credit;
}
