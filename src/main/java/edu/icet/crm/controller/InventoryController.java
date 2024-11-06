package edu.icet.crm.controller;


import edu.icet.crm.dto.BloodInventory;
import edu.icet.crm.dto.Donar;
import edu.icet.crm.repository.BloodInventoryRepository;
import edu.icet.crm.service.BloodInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/Inventory")
public class InventoryController {
    private final BloodInventoryService bloodInventoryService;

    @GetMapping("/all")
    public List<BloodInventory> getAll(){
        return bloodInventoryService.findAll();
    }

}
