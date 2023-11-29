package com.kb.joonggo.Tradeboard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeboardRepository {

    List<TradeboardDTO> BuyAlllist();
    public List<TradeboardDTO> Buylist(int pageNum);
    public int countBuyRow();
    public TradeboardDTO selectBuyRow(int idx);

    public List<TradeboardDTO> SellAlllist();
    public List<TradeboardDTO> Selllist(int pageNum);
    public int countSellRow();
    public TradeboardDTO selectSellRow(int idx);


    public void insert(TradeboardDTO tradeboardDTO);

}
