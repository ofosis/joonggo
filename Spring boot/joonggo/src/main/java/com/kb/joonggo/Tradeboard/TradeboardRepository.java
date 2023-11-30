package com.kb.joonggo.Tradeboard;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeboardRepository {

    public List<TradeboardReq> BuyAlllist(int BuypageNum);
    public List<TradeboardDTO> Buylist(int pageNum);
    public int countBuyRow();
    public TradeboardDTO selectBuyRow(int idx);

    public List<TradeboardReq> SellAlllist();
    public List<TradeboardDTO> Selllist(int pageNum);
    public int countSellRow();
    public TradeboardDTO selectSellRow(int idx);

    public List<TradeboardReq> Recoselllist(String category);
    public TradeboardReq selectRow2(int idx);

    public void saveImage(TradeboardReq tradeboardReq);


    public void insert(TradeboardDTO tradeboardDTO);

    public int search_tb_idx(String tb_title,String tb_content,int tb_price,String tb_category,int tb_count,String tb_state);

    public void img_idx_update(
            int img_idx,
            int tb_idx,
            String tb_title,
            String tb_content,
            int tb_price,
            String tb_category,
            int tb_count,
            String tb_state
    );
}