package com.kb.joonggo.Buyer;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BuyerReq {
    private int buy_idx;
    private String buy_title;
    private String buy_content;
    private Date buy_date;
    private int buy_price;
    private String buy_category;
    private int buy_count;
    private String buy_state;
    private int mbr_idx;
}
