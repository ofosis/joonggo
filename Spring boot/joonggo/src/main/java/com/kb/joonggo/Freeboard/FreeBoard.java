package com.kb.joonggo.Freeboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeBoard {

    private int fr_idx;
    private String fr_title;
    private String fr_content;
    private int fr_time;
    private int mbr_idx;
}


