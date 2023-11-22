package com.kb.joonggo.Tradeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeboardService {

    @Autowired
    TradeboardRepository tradeboardRepository;

}
