package edu.icet.crm.service;

import edu.icet.crm.dto.Hospital;

import java.util.List;

public interface HospitalService {
    boolean addHospital(Hospital hospital);

    List<Hospital> getAllHospitals();

    Hospital findHospitalById(String hospitalId);

    boolean deleteHospitalById(String hospitalId);

    boolean updateHospital(Hospital hospital);
}
