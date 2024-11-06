package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.repository.DonarRepository;
import edu.icet.crm.service.DonarService;
import edu.icet.crm.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DonarServiceImpl implements DonarService {
    private final DonarRepository donarRepository;

    @Override
    public boolean addDonar(Donar donar) {
        donar.setPassword(Encrypt.encryptPassword(donar.getPassword()));
        donar.setDonorID(DonarEntity.generateDonorID(getNextSequenceNumber()));
        donarRepository.save(new ModelMapper().map(donar, DonarEntity.class));
        DonarEntity byEmail = donarRepository.findByEmail(donar.getEmail());
        return byEmail.getDonorID().equals(donar.getDonorID());
    }

    private int getNextSequenceNumber() {
        return (int)donarRepository.count() + 1;
    }

    @Override
    public List<Donar> getAllDonar() {
        List<DonarEntity> all = donarRepository.findAll();
        List<Donar> donars = new ArrayList<>();
        all.forEach(obj->{
            obj.setPassword("*****");
            donars.add(new ModelMapper().map(obj, Donar.class));
        });
        return donars;
    }

    @Override
    public boolean deleteDonarById(String donarId) {
        donarRepository.deleteById(donarId);
        List<DonarEntity> all = donarRepository.findAll();
        for (DonarEntity donarEntity : all) {
            if(donarEntity.getDonorID().equals(donarId)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Donar findDonarById(String donarId) {
        return new ModelMapper().map(donarRepository.findById(donarId),Donar.class);
    }

    @Override
    public boolean updateDonar(Donar donar) {
        if (donarRepository.existsById(donar.getDonorID())) {
            DonarEntity existingDonarEntity = donarRepository.findById(donar.getDonorID()).orElse(null);

            if (existingDonarEntity != null) {
                existingDonarEntity.setName(donar.getName() != null ? donar.getName() : existingDonarEntity.getName());
                existingDonarEntity.setAddress(donar.getAddress() != null ? donar.getAddress() : existingDonarEntity.getAddress());
                existingDonarEntity.setCity(donar.getCity() != null ? donar.getCity() : existingDonarEntity.getCity());
                existingDonarEntity.setEmail(donar.getEmail() != null ? donar.getEmail() : existingDonarEntity.getEmail());
                existingDonarEntity.setContactNumber(donar.getContactNumber() != null ? donar.getContactNumber() : existingDonarEntity.getContactNumber());
                existingDonarEntity.setBloodGroup(donar.getBloodGroup() != null ? donar.getBloodGroup() : existingDonarEntity.getBloodGroup());
                existingDonarEntity.setDob(donar.getDob() != null ? donar.getDob() : existingDonarEntity.getDob());
                existingDonarEntity.setGender(donar.getGender() != null ? donar.getGender() : existingDonarEntity.getGender());
                existingDonarEntity.setAge(donar.getAge() != null ? donar.getAge() : existingDonarEntity.getAge());

                if (donar.getPassword() != null && !donar.getPassword().isEmpty()) {
                    existingDonarEntity.setPassword(donar.getPassword());
                }

                donarRepository.save(existingDonarEntity);
                return true;
            }
        }
        return false;
    }

    @Override
    public Donar getDonarByEmail(String email) {
        return new ModelMapper().map(donarRepository.findByEmail(email),Donar.class);
    }


}
