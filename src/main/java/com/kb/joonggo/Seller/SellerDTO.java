package com.kb.joonggo.Seller;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SellerDTO {
    private int sel_idx;
    private String sel_title;
    private String sel_content;
    private Date sel_date;
    private int sel_price;
    private String sel_category;
    private int sel_count;
    private String sel_state;
    private int mbr_idx;
}