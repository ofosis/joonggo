package com.kb.joonggo.Write;

import com.kb.joonggo.Seller.SellerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface WriteRepository {


    public List<WriteDTO> list();

    public void insert(WriteDTO writeDTO);

}
