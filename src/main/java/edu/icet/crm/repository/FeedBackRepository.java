package edu.icet.crm.repository;

import edu.icet.crm.entity.FeedBacksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBacksEntity,String> {
    FeedBacksEntity findByFeedbackID(String feedbackID);
}
