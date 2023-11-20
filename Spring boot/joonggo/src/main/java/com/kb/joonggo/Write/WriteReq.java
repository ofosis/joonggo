package com.kb.joonggo.Write;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class WriteReq {

    private int idx;

    private String title;

    private String content;

    private Date date;

    private int price;

    private String category;

    private int count;

    private String state;

    private int mbr_idx;

    private int img_idx;

}
