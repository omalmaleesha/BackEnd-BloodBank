package edu.icet.crm.service;


import edu.icet.crm.dto.BloodRecords;

import java.util.List;

public interface BloodRecordsService {
    List<BloodRecords> getListByDonarId(String id);
}
