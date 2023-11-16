package com.kb.joonggo.Freeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeboardReq {

    @Nullable
    private int idx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String name;

    private String originalfilename;
}
