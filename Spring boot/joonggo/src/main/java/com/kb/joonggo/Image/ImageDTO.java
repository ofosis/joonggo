package com.kb.joonggo.Image;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ImageDTO {
    private int img_idx;

    //    private MultipartFile img_name;
    private String img_name;

    private String img_path; // 파일경로

    private int tb_idx;

}
