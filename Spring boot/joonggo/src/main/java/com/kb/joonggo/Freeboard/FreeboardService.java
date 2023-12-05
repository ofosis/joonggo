package com.kb.joonggo.Freeboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreeboardService {

    private final FreeboardRepository freeboardRepository;

    @Autowired
    public FreeboardService(FreeboardRepository freeboardRepository) {
        this.freeboardRepository = freeboardRepository;
    }

    public FreeboardReq getFreeboardByIdx(int fr_idx) {
        return freeboardRepository.findByIdx(fr_idx);
    }

    @Transactional
    public void increaseViewCount(int fr_idx) {
        freeboardRepository.updateViewCount(fr_idx);
    }

    public List<FreeBoard> searchByTitleOrContent(String query) {
        return freeboardRepository.searchByTitleOrContent(query);
    }
}
