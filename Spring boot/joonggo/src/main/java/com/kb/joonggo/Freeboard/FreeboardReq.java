package com.kb.joonggo.Freeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FreeboardReq {
    @Nullable
    private int fr_idx;

    @NotEmpty
    private String fr_title;

    @NotEmpty
    private String fr_content;

    @Nullable
    private Date fr_time;

    @Nullable
    private String originalfilename;

    @NotEmpty
    private String mbr_nick;

    @Nullable
    private int mbr_idx;
}
