package com.kb.joonggo.Buyer;

import org.springframework.stereotype.Repository;


@Repository
public interface BuyRepository {


    public BuyerReq findByIdx (int idx);
}
