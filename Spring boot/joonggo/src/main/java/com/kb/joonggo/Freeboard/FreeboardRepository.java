package com.kb.joonggo.Freeboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeboardRepository {

    public void delete(List idxList);

    public List<Freeboard> list(int pageNum);
    public void insert(Freeboard freeboard);

    public int countRow();
    public Freeboard selectRow(int idx);

    public void update(Freeboard freeboard);
}
