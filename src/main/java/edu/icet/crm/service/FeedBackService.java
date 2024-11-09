package edu.icet.crm.service;

import edu.icet.crm.dto.FeedBacks;

import java.util.List;

public interface FeedBackService {
    boolean addFeedBack(FeedBacks feedBacks);

    List<FeedBacks> getAll();


}
