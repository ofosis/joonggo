package com.kb.joonggo.Tradeboard;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
@Data
@Builder
public class TradeboardDTO {
    private int tb_idx;
    private String tb_title;
    private String tb_content;
    private Date tb_date;
    private int tb_price;
    private String tb_category;
    private int tb_count;
    private String tb_state;
    private int mbr_idx;
    private int img_idx;
}