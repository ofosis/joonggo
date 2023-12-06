package com.kb.joonggo.Freeboard;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FreeboardReq {
    private int fr_idx;

    @NotEmpty
    private String fr_title;

    @NotEmpty
    private String fr_content;

    @Nullable
    private String mbr_nick;

    private int mbr_idx;

    private int view_count;  // 조회수 필드 추가

    @Nullable
    private LocalDateTime created_at; // 작성 시간 필드 추가

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}