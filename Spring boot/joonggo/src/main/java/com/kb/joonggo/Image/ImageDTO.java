package com.kb.joonggo.Image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
public class ImageDTO {
    private int img_idx;

    //    private MultipartFile img_name;
    private String img_name;

    private String img_path; // 파일경로

    private int tb_idx;

}
