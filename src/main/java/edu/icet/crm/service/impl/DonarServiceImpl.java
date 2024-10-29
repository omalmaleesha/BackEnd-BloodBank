package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.dto.LoginRequest;
import edu.icet.crm.entity.DonarEntity;
import edu.icet.crm.repository.DonarRepository;
import edu.icet.crm.service.DonarService;
import edu.icet.crm.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DonarServiceImpl implements DonarService {
    private final DonarRepository donarRepository;

    @Override
    public boolean addDonar(Donar donar) {
        donar.setPassword(Encrypt.encryptPassword(donar.getPassword()));
        donarRepository.save(new ModelMapper().map(donar, DonarEntity.class));
        DonarEntity byEmail = donarRepository.findByEmail(donar.getEmail());
        return byEmail.getDonorID().equals(donar.getDonorID());
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

}
