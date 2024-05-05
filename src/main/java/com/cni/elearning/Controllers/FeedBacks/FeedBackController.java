package com.cni.elearning.Controllers.FeedBacks;

import com.cni.elearning.Models.FeedBacks.FeedBack;
import com.cni.elearning.Services.FeedBacks.IFeedBackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedbacks")
public class FeedBackController {
    private final IFeedBackService feedBackService;

    public FeedBackController(IFeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @GetMapping("/")
    public List<FeedBack> getAllFeedBacks(){
        return feedBackService.getAllFeedBacks();
    }

    @GetMapping("/{id}")
    public FeedBack getFeedBackById(@PathVariable int id){

        return feedBackService.getFeedBackById(id);
    }

    @PostMapping("/")
    public FeedBack saveFeedBack(@RequestBody FeedBack feedBack){
        return feedBackService.saveFeedBack(feedBack);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedBack(@PathVariable int id){
        feedBackService.deleteFeedBack(id);
    }


    @PutMapping("/{id}")
    public FeedBack updateFeedBack(@PathVariable int id, @RequestBody FeedBack feedBack){
        return feedBackService.updateFeedBack(feedBack,id);
    }
}
