package edu.icet.crm.service.impl;

import edu.icet.crm.dto.FeedBacks;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.entity.FeedBacksEntity;
import edu.icet.crm.repository.FeedBackRepository;
import edu.icet.crm.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {
    private final FeedBackRepository feedBackRepository;

    @Override
    public boolean addFeedBack(FeedBacks feedBacks) {
        feedBacks.setFeedbackID(FeedBacksEntity.generateFeedBackID(getNextSequenceNumber()));
        feedBackRepository.save(new ModelMapper().map(feedBacks,FeedBacksEntity.class));
        FeedBacksEntity feedBacksEntity = feedBackRepository.findByFeedbackID(feedBacks.getFeedbackID());
        return feedBacksEntity != null;
    }

    @Override
    public List<FeedBacks> getAll() {
        List<FeedBacksEntity> all = feedBackRepository.findAll();
        List<FeedBacks> feedBacks  = new ArrayList<>();
        all.forEach(obj->{
            feedBacks.add(new ModelMapper().map(obj,FeedBacks.class));
        });
        return feedBacks;
    }

    private int getNextSequenceNumber() {
        return (int)feedBackRepository.count() + 1;
    }
}
