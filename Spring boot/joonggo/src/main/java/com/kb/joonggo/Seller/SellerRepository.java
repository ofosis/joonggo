package com.kb.joonggo.Seller;

import com.kb.joonggo.Buyer.BuyerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerRepository {
    List<SellerDTO> list();
    public List<SellerDTO> list(int pageNum);
    public int countRow();
    public SellerDTO selectRow(int idx);
}

