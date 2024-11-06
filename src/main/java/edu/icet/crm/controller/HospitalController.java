package edu.icet.crm.controller;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.dto.Hospital;
import edu.icet.crm.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/Hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/add")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital){
        if(hospital == null){
            return ResponseEntity.badRequest().body("Invalid Hospital data");
        }
        return hospitalService
                .addHospital(hospital)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Hospital Added"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to add Hospital");
    }

    @GetMapping("/all")
    public List<Hospital> getAllHospitals(){
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/findById/{hospitalId}")
    public Hospital getHospitalsById(@PathVariable String hospitalId){
        return hospitalService.findHospitalById(hospitalId);
    }

    @DeleteMapping("/delete/{hospitalId}")
    public ResponseEntity<String> deleteHospital(@PathVariable String hospitalId){
        return hospitalService.deleteHospitalById(hospitalId)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Hospital Deleted"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to delete Hospital");
    }
    @PatchMapping("/update")
    public ResponseEntity<String> updateHospital(@RequestBody Hospital hospital){
        return hospitalService.updateHospital(hospital)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Hospital Updated"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update Hospital");
    }
}
