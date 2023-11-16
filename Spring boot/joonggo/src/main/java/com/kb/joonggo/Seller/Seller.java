package com.kb.joonggo.Seller;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Builder
public class Seller {

    private int sel_idx;
    private String sel_title;
    private String sel_content;
    private Timestamp sel_date;
    private int sel_price;
    private String sel_category;
    private int sel_count;
    private String sel_state;
    private int mbr_idx;
    private int img_idx;

}
