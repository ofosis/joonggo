package com.kb.joonggo.Buyer;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuyerRepository {
    List<BuyerDTO> list();

    public List<BuyerDTO> list(int pageNum);

    public int countRow();

    public BuyerDTO selectRow(int idx);
}
