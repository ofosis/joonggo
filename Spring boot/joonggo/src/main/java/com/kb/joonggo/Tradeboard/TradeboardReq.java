package com.kb.joonggo.Tradeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeboardReq {
    @Nullable
    private int idx;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotNull
    private Date date;
    @NotNull
    private int price;
    @NotEmpty
    private String category;
    @Nullable
    private int count;
    @NotEmpty
    private String state;
    @NotNull
    private int mbr_idx;
    @Nullable
    private int img_idx;
}
