package com.kb.joonggo.Freeboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeboardRepository {
    public void delete(List idxList);

    //    public List<FreeBoard> list(int pageNum);
    public List<FreeBoard> list(int pageNum);

    public void insert(FreeBoard freeBoard);

    public int countRow();

    public FreeboardReq selectRow(int fr_idx);

    public void update(FreeBoard freeBoard);
}
