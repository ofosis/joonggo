package com.kb.joonggo.Image;

import com.kb.joonggo.Tradeboard.TradeboardReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

//    public void saveImage(TradeboardReq tradeboardReq, byte[] img_blob) {
//        try {
//
//            if (img_blob!= null) {
//                ImageDTO image = ImageDTO.builder()
//                        .img_name(tradeboardReq.getImg_name())
//                        .img_blob(img_blob)
//                        .tb_idx(1) // 임시값
//                        .build();
//                imageRepository.save(image);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
