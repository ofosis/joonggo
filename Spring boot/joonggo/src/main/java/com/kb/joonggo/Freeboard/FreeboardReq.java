package com.kb.joonggo.Freeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeboardReq {
    @Nullable
    private int fr_idx;

    @NotEmpty
    private String fr_title;
    @NotEmpty
    private String fr_content;
    @NotEmpty
    private int fr_time;

    private int mbr_idx;
}
