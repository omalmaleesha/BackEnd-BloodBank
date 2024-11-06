package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.dto.Hospital;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.entity.HospitalEntity;
import edu.icet.crm.repository.HospitalRepository;
import edu.icet.crm.service.HospitalService;
import edu.icet.crm.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private int getNextSequenceNumber() {
        return (int)hospitalRepository.count() + 1;
    }

    @Override
    public boolean addHospital(Hospital hospital) {
        hospital.setPassword(Encrypt.encryptPassword(hospital.getPassword()));
        hospital.setHospitalID(HospitalEntity.generateHospitalID(getNextSequenceNumber()));
        hospitalRepository.save(new ModelMapper().map(hospital, HospitalEntity.class));
        HospitalEntity byEmail = hospitalRepository.findByEmail(hospital.getEmail());
        return byEmail.getHospitalID().equals(hospital.getHospitalID());
    }

    @Override
    public List<Hospital> getAllHospitals() {
        List<HospitalEntity> all = hospitalRepository.findAll();
        List<Hospital> hospital = new ArrayList<>();
        all.forEach(obj->{
            obj.setPassword("*****");
            hospital.add(new ModelMapper().map(obj, Hospital.class));
        });
        return hospital;
    }

    @Override
    public Hospital findHospitalById(String hospitalId) {
        return new ModelMapper().map(hospitalRepository.findById(hospitalId),Hospital.class);
    }

    @Override
    public boolean deleteHospitalById(String hospitalId) {
        hospitalRepository.deleteById(hospitalId);
        List<HospitalEntity> all = hospitalRepository.findAll();
        for (HospitalEntity hospitalEntity : all) {
            if(hospitalEntity.getHospitalID().equals(hospitalId)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateHospital(Hospital hospital) {
        if (hospitalRepository.existsById(hospital.getHospitalID())){
            HospitalEntity hospitalEntity = new ModelMapper().map(hospital, HospitalEntity.class);
            hospitalRepository.save(hospitalEntity);
            return true;
        } else {
            return false;
        }
    }
}
