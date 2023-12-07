package com.kb.joonggo.Freeboard;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface FreeboardRepository {


    static void update(FreeBoard freeboard) {

    }

    void delete(List<Integer> idxList);

    List<FreeBoard> list(int pageNum);

    void insert(FreeBoard freeBoard);

    int countRow();

    FreeboardReq selectRow(int fr_idx);

    FreeboardReq findByIdx(int fr_idx);



    void save(FreeboardReq freeboard);


    @Query("SELECT f FROM FreeBoard f WHERE f.fr_title LIKE %:query% OR f.fr_content LIKE %:query%")
    List<FreeBoard> searchByTitleOrContent(@Param("query") String query);

    @Modifying
    @Query("UPDATE FreeBoard f SET f.view_count = f.view_count + 1 WHERE f.fr_idx = :frIdx")
    void updateViewCount(@Param("frIdx") int frIdx);

    @Query("SELECT f FROM FreeBoard f WHERE f.fr_idx = :postId")
    FreeBoard getPostById(@Param("postId") Long postId);

    @Modifying
    @Query("DELETE FROM FreeBoard f WHERE f.fr_idx = :postId")
    void deletePost(@Param("postId") Long postId);

}