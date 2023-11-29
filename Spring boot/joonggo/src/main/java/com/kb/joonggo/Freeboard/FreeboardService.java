package com.kb.joonggo.Freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FreeboardService {

    private final FreeboardRepository freeboardRepository;

    @Autowired
    public FreeboardService(FreeboardRepository freeboardRepository) {
        this.freeboardRepository = freeboardRepository;
    }

    public FreeboardReq getFreeboardByIdx(int fr_idx) {
        FreeboardReq freeboard = freeboardRepository.findByIdx(fr_idx);
        if (freeboard != null) {
            increaseViewCount(fr_idx);
        }
        return freeboard;
    }

    @Transactional
    public void increaseViewCount(int fr_idx) {
        freeboardRepository.updateViewCount(fr_idx);
    }
}
