package edu.icet.crm.service;

import edu.icet.crm.dto.BloodInventory;
import edu.icet.crm.dto.Donar;

import java.util.List;

public interface BloodInventoryService {

    List<BloodInventory> findAll();

}
