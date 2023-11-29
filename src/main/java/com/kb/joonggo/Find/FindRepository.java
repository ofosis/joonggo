package com.kb.joonggo.Find;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FindRepository {

    public void insert(Find find);

    public Find find_id(String mbr_contact, String mbr_email);

    public Find find_pwd(String mbr_id, String mbr_email);
}
