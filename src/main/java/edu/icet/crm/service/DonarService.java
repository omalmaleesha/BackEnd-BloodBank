package edu.icet.crm.service;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.dto.LoginRequest;

import java.util.List;

public interface DonarService {
    boolean addDonar(Donar donar);

    List<Donar> getAllDonar();

}
