package edu.icet.crm.service.impl;

import edu.icet.crm.dto.BloodInventory;
import edu.icet.crm.entity.BloodInventoryEntity;
import edu.icet.crm.repository.BloodInventoryRepository;
import edu.icet.crm.service.BloodInventoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodInventoryServiceImpl implements BloodInventoryService {
    private final BloodInventoryRepository bloodInventoryRepository;

    @Override
    public List<BloodInventory> findAll() {
        List<BloodInventoryEntity> all = bloodInventoryRepository.findAll();
        List<BloodInventory> bloodInventories = new ArrayList<>();
        all.forEach(obj-> {
            bloodInventories.add(new ModelMapper().map(obj,BloodInventory.class));
        });
        return bloodInventories;
    }
}
