package com.kb.joonggo.Image;

import com.kb.joonggo.Tradeboard.TradeboardRepository;
import com.kb.joonggo.Tradeboard.TradeboardReq;
import com.kb.joonggo.Tradeboard.TradeboardService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("Trade")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    TradeboardRepository tradeboardRepository;


}
