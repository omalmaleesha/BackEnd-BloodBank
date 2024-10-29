package edu.icet.crm.controller;

import edu.icet.crm.dto.Donar;
import edu.icet.crm.service.DonarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/Donar")
public class DonarController {
    private final DonarService donarService;

    @PostMapping("/add")
    public ResponseEntity<String> addDonar(@RequestBody Donar donar){
        if(donar == null){
            return ResponseEntity.badRequest().body("Invalid donor data");
        }
        return donarService
                .addDonar(donar)?ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Donor Added"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to add donor");
    }


    @GetMapping("/all")
    public List<Donar> getAllDonar(){
        return donarService.getAllDonar();
    }


}
