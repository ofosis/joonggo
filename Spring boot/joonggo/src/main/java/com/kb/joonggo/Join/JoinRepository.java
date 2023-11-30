package com.kb.joonggo.Join;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface JoinRepository {

    public void insert(Join join);

    public Join check_id(String id);
}
