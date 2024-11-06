package edu.icet.crm.service.impl;

import edu.icet.crm.dto.ApprovedRequest;
import edu.icet.crm.entity.*;
import edu.icet.crm.repository.*;
import edu.icet.crm.service.ApproveRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovedRequestImpl implements ApproveRequestService {
    private final ApproveRequestRepository approveRequestRepository;
    private final DonarRepository donarRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodInventoryRepository repository;
    private final BloodRecordsRepository recordsRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean addApproveRequest(ApprovedRequest bloodRequest) {
        bloodRequest.setApprovedRequestId(ApprovedRequestEntity.generateApprovedRequestID(getNextSequenceNumber()));
        String recordId = null;

        // Fetch related entities
        DonarEntity donorEntity = donarRepository.findByDonorID(bloodRequest.getDonarID());
        HospitalEntity byHospitalID = hospitalRepository.findByHospitalID(bloodRequest.getHospitalID());
        AppointmentEntity byAppointmentID = appointmentRepository.findByAppointmentID(bloodRequest.getAppointmentID());
        System.out.println(byAppointmentID);

        // Validate fetched entities
        if (donorEntity == null || byHospitalID == null || byAppointmentID == null) {
            System.out.println("One or more related entities not found.");
            return false;
        }

        // Map request to ApprovedRequestEntity
        ApprovedRequestEntity map = new ModelMapper().map(bloodRequest, ApprovedRequestEntity.class);
        map.setHospitalEntity(byHospitalID);
        map.setDonarEntity(donorEntity);
        map.setAppointmentEntity(byAppointmentID);

        // Handle different statuses
        if ("COMPLETED".equals(bloodRequest.getStatus())) {
            approveRequestRepository.save(map);

            // Update the blood inventory
            BloodInventoryEntity bloodInventoryEntity = repository.findByBloodType(donorEntity.getBloodGroup());
            if (bloodInventoryEntity != null) {
                double newAmount = bloodInventoryEntity.getAmount() + bloodRequest.getAmount();
                bloodInventoryEntity.setAmount(newAmount);
                repository.save(bloodInventoryEntity);
                updateStatus(bloodRequest.getAppointmentID());
            } else {
                System.out.println("Blood inventory for donor's blood type not found.");
                return false;
            }

            // Create a new blood record
            BloodRecordsEntity bloodRecordsEntity = new BloodRecordsEntity();
            bloodRecordsEntity.setRecordID(BloodRecordsEntity.generateBloodRecordID(getNextSequenceOfRecordNumber()));
            bloodRecordsEntity.setHospitalEntity(byHospitalID);
            bloodRecordsEntity.setDonarEntity(donorEntity);
            bloodRecordsEntity.setDonationDate(bloodRequest.getDate());
            bloodRecordsEntity.setAmount(bloodRequest.getAmount());
            recordsRepository.save(bloodRecordsEntity);
            recordId = bloodRecordsEntity.getRecordID();

            // Verify the record and update the appointment status


        } else if ("CANCELED".equals(bloodRequest.getStatus())) {
            approveRequestRepository.save(map);
            byAppointmentID.setStatus("CANCELED");
            appointmentRepository.save(byAppointmentID);
        }

        // Verify the approved request was saved successfully
        BloodRecordsEntity savedRequest = recordsRepository.findByRecordID(recordId);
        return savedRequest.getRecordID().equals(recordId);
    }


    private void updateStatus(String id){
        AppointmentEntity byAppointmentID = appointmentRepository.findByAppointmentID(id);
        System.out.println(byAppointmentID);
        byAppointmentID.setAppointmentID(byAppointmentID.getAppointmentID());
        byAppointmentID.setStatus("COMPLETED");
        appointmentRepository.save(byAppointmentID);
    }

    private int getNextSequenceNumber() {
        return (int)approveRequestRepository.count() + 1;
    }
    private int getNextSequenceOfRecordNumber() {
        return (int)recordsRepository.count() + 1;
    }

    @Override
    public List<ApprovedRequest> getCompletedList() {
        List<ApprovedRequestEntity> approvedRequestEntities = approveRequestRepository.findByStatus("COMPLETED");
        List<ApprovedRequest> approvedRequests = new ArrayList<>();
        approvedRequestEntities.forEach(obj->{
            approvedRequests.add(new ModelMapper().map(obj,ApprovedRequest.class));
        });
        return approvedRequests;
    }


}
