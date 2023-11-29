package com.kb.joonggo.Freeboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeboardJson {

    private int[] idx;
    private String msg;
}
