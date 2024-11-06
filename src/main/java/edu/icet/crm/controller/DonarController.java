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
        System.out.println(donar);
        if(donar == null){
            return ResponseEntity.badRequest().body("Invalid donor data");
        }
        return donarService
                .addDonar(donar)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Donor Added"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to add donor");
    }


    @GetMapping("/all")
    public List<Donar> getAllDonar(){
        return donarService.getAllDonar();
    }

    @GetMapping("/findBy/{email}")
    public boolean getDonarByEmail(@PathVariable String email){
        if(donarService.getDonarByEmail(email)!=null){
            return true;
        }
        return false;
    }



    @GetMapping("/findById/{donarId}")
    public Donar getDonarById(@PathVariable String donarId){
        return donarService.findDonarById(donarId);
    }


    @DeleteMapping("/delete/{donarId}")
    public ResponseEntity<String> deleteDonar(@PathVariable String donarId){
        return donarService.deleteDonarById(donarId)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Donor Deleted"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to delete donor");
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateDonar(@RequestBody Donar donar){
        return donarService.updateDonar(donar)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Donor Updated"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update donor");
    }

}
