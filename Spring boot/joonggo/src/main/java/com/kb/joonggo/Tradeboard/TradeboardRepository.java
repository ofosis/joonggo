package com.kb.joonggo.Tradeboard;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TradeboardRepository {

    public List<TradeboardReq> BuyAlllist(int BuypageNum);
    public List<TradeboardDTO> Buylist(int pageNum);
    public int countBuyRow();
    public TradeboardDTO selectBuyRow(int idx);

    public List<TradeboardReq> SellAlllist(int SellpageNum);
    public List<TradeboardDTO> Selllist(int pageNum);
    public int countSellRow();
    public TradeboardDTO selectSellRow(int idx);

    public TradeboardReq selectRow2(int idx);

    public void saveImage(TradeboardReq tradeboardReq);

    List<TradeboardReq> showRecommend(@Param("tb_category") String tb_category, @Param("tb_idx") int tb_idx);

    List<TradeboardReq> selectallproduct(int mbr_idx);
    public void insert(TradeboardDTO tradeboardDTO);
    public void update(TradeboardDTO tradeboardDTO);

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