package edu.icet.crm.service.impl;

import edu.icet.crm.dto.BloodRecords;
import edu.icet.crm.entity.BloodRecordsEntity;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.repository.BloodRecordsRepository;
import edu.icet.crm.repository.DonarRepository;
import edu.icet.crm.service.BloodRecordsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRecordsServiceImpl implements BloodRecordsService {
    private final BloodRecordsRepository recordsRepository;
    private final DonarRepository donarRepository;


    @Override
    public List<BloodRecords> getListByDonarId(String id) {
        DonarEntity byDonorID = donarRepository.findByDonorID(id);
        List<BloodRecordsEntity> bloodRecordsEntities = recordsRepository.findByDonarEntity(byDonorID);
        List<BloodRecords> bloodRecords =  new ArrayList<>();
        System.out.println(bloodRecordsEntities);
        bloodRecordsEntities.forEach(obj->{
            bloodRecords.add(new ModelMapper().map(obj,BloodRecords.class));
        });
        return bloodRecords;
    }
}
