package com.kb.joonggo.Join;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {
    private String name;
    private String contact;
    private String email;
    private String id;
    private String pass;
    private String nick;
    private String addr;
}
