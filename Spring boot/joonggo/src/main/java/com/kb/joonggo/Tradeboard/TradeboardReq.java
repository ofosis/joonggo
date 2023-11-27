package com.kb.joonggo.Tradeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeboardReq {
    @Nullable
    private int tb_idx;
    @NotEmpty
    private String tb_title;
    @NotEmpty
    private String tb_content;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date tb_date;
    @NotNull
    private int tb_price;
    @NotEmpty
    private String tb_category;
    @Nullable
    private int tb_count;
    @NotEmpty
    private String tb_state;
    @NotNull
    private int mbr_idx;
    @Nullable
    private int img_idx;
    @NotEmpty
    private String mbr_nick;
}
