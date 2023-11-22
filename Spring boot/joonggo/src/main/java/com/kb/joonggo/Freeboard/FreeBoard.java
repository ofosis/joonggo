package com.kb.joonggo.Freeboard;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FreeBoard {

    private int fr_idx;
    private String fr_title;
    private String fr_content;
    private Date fr_time;
    private String originalfilename;
    private int mbr_idx;
   // private String mbr_nick;
}


