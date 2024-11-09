package edu.icet.crm.controller;

import edu.icet.crm.dto.BloodRecords;
import edu.icet.crm.dto.BloodRequest;
import edu.icet.crm.service.BloodRecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/BloodRecords")
public class BloodRecordsController {
    private final BloodRecordsService bloodRecordsService;

    @GetMapping("/donarId/{id}")
    public List<BloodRecords> getListByDonarId(@PathVariable String id){
        return bloodRecordsService.getListByDonarId(id);
    }

}
