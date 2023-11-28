package com.kb.joonggo.Tradeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class TradeboardReq {

    @Nullable
    private int idx;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotNull
    private String date;
    @Nullable
    private int price;
    @NotEmpty
    private String category;
    @Nullable
    private int count;
    @NotEmpty
    private String state;
    @Nullable
    private int mbr_idx;

    @Nullable
    private int img_idx;
    @Nullable
//    private MultipartFile img_name;
    private String img_name;

    private String img_path;
}
