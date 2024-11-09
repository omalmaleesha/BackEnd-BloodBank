package edu.icet.crm.controller;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.dto.FeedBacks;
import edu.icet.crm.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/FeedBack")
public class FeedBackController {
    private final FeedBackService feedBackService ;

    @PostMapping("/add")
    public ResponseEntity<String> addFeedBack(@RequestBody FeedBacks feedBacks){
        System.out.println(feedBacks);
        if(feedBacks == null){
            return ResponseEntity.badRequest().body("Invalid FeedBack data");
        }
        return feedBackService
                .addFeedBack(feedBacks)?ResponseEntity
                .status(HttpStatus.OK)
                .body("FeedBack Added"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to add FeedBack");
    }

    @GetMapping("/all")
    public List<FeedBacks> getAll(){
        return feedBackService.getAll();
    }
}
