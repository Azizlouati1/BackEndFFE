package com.cni.elearning.Services.FeedBacks;

import com.cni.elearning.Models.FeedBacks.FeedBack;

import java.util.List;

public interface IFeedBackService {
    List<FeedBack> getAllFeedBacks();

    FeedBack saveFeedBack(FeedBack feedBack);

    FeedBack getFeedBackById(int id);

    void deleteFeedBack(int id);

    FeedBack updateFeedBack(FeedBack feedBack, int id);
}
