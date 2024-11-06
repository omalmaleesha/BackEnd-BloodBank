package edu.icet.crm.service;

import edu.icet.crm.dto.ApprovedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApproveRequestService {

    boolean addApproveRequest(ApprovedRequest bloodRequest);

    List<ApprovedRequest> getCompletedList();

}
