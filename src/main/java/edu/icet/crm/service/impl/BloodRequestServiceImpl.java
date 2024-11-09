package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Appointment;
import edu.icet.crm.dto.BloodRequest;
import edu.icet.crm.entity.*;
import edu.icet.crm.repository.BloodInventoryRepository;
import edu.icet.crm.repository.BloodRequestRepository;
import edu.icet.crm.repository.HospitalRepository;
import edu.icet.crm.service.BloodRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestServiceImpl implements BloodRequestService {
    private final BloodRequestRepository bloodRequestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodInventoryRepository inventoryRepository;

    @Override
    public boolean addBloodRequest(BloodRequest bloodRequest) {
        bloodRequest.setRequestID(BloodRequestEntity.generateBloodRequestID(getNextSequenceNumber()));
        HospitalEntity hospitalEntity = hospitalRepository.findByHospitalID(bloodRequest.getHospitalID());
        BloodRequestEntity map = new ModelMapper().map(bloodRequest, BloodRequestEntity.class);
        map.setHospitalEntity(hospitalEntity);
        bloodRequestRepository.save(map);
        BloodRequestEntity byId = bloodRequestRepository.findByRequestID(bloodRequest.getRequestID());
        return byId.getRequestID().equals(bloodRequest.getRequestID());


    }

    @Override
    public List<BloodRequest> getPendingList() {
        List<BloodRequestEntity> appointmentEntities = bloodRequestRepository.findByStatus("PENDING");
        List<BloodRequest> bloodRequests = new ArrayList<>();
        appointmentEntities.forEach(obj->{
            bloodRequests.add(new ModelMapper().map(obj,BloodRequest.class));
        });
        return bloodRequests;
    }

    @Override
    public List<BloodRequest> getCompletedList() {
        List<BloodRequestEntity> appointmentEntities = bloodRequestRepository.findByStatus("COMPLETED");
        List<BloodRequest> bloodRequests = new ArrayList<>();
        appointmentEntities.forEach(obj->{
            bloodRequests.add(new ModelMapper().map(obj,BloodRequest.class));
        });
        return bloodRequests;
    }

    @Override
    public boolean updateRequestStatus(String requestID, String status) {
        BloodRequestEntity byRequestID = bloodRequestRepository.findByRequestID(requestID);

        if (byRequestID != null) {
            byRequestID.setStatus(status);
            bloodRequestRepository.save(byRequestID);

            if ("COMPLETED".equals(status)) {
                String bloodType = byRequestID.getBloodType();
                BloodInventoryEntity byBloodType = inventoryRepository.findByBloodType(bloodType);

                if (byBloodType != null) {
                    byBloodType.setAmount(byBloodType.getAmount() + byRequestID.getAmount());
                    inventoryRepository.save(byBloodType);
                }
            }

            return true;
        }

        return false;
    }



    private int getNextSequenceNumber() {
        return (int)bloodRequestRepository.count() + 1;
    }
}
