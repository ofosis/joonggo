package com.kb.joonggo.Image;

import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface ImageRepository {

    public void save(ImageDTO imageDTO);

    public void modify(String img_name, String img_path, int img_idx);

    public void tb_idx_update(
            int tb_idx,
            int img_idx,
            String img_name,
            String img_path
    );

    public int search_img_idx(String img_name, String img_path, int tb_idx);
}
