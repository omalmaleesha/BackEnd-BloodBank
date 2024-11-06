package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Appointment;
import edu.icet.crm.dto.BloodRequest;
import edu.icet.crm.entity.AppointmentEntity;
import edu.icet.crm.entity.BloodRequestEntity;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.entity.HospitalEntity;
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

    private int getNextSequenceNumber() {
        return (int)bloodRequestRepository.count() + 1;
    }
}
