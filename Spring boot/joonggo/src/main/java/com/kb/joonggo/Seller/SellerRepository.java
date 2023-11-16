package com.kb.joonggo.Seller;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SellerRepository {
//
//    public void delete(List idxList);
//    public List<Seller> list(int pageNum);

    public List<Seller> list();

    public void insert(Seller seller);

    public int countRow();

    public Seller selecrRow(int idx);

    public void update(Seller seller);

}
