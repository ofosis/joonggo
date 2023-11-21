package com.kb.joonggo.Write;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Builder
public class WriteReq {

//    private int idx;
    @NotNull
    private String title;

    @Nullable
    private String content;

//    private Date date;

//    private int price;

//    private String category;

//    private int count;

//    private String state;

//    private int mbr_idx;

//    @Nullable
//    private int img_idx;

}
