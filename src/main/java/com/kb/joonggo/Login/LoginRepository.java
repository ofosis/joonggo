package com.kb.joonggo.Login;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRepository {
    public void insert(Login login);

    public Login login(String mbr_id, String mbr_pwd);
}
