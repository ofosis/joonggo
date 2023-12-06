package com.kb.joonggo.Freeboard;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoard {

    private int fr_idx;
    private String fr_title;
    private String fr_content;
    private int mbr_idx;

    @NotNull
    private LocalDateTime created_at; // 작성 시간 필드 추가

    private int view_count; // 조회수 필드 추가


}