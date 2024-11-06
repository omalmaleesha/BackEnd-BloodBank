package edu.icet.crm.service;

import edu.icet.crm.dto.BloodRequest;

import java.util.List;

public interface BloodRequestService {
    boolean addBloodRequest(BloodRequest bloodRequest);

    List<BloodRequest> getPendingList();
}
