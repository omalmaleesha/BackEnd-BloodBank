package edu.icet.crm.controller;

import edu.icet.crm.dto.Appointment;
import edu.icet.crm.dto.BloodRequest;
import edu.icet.crm.service.BloodRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/BloodRequest")
public class BloodRequestController {
    private final BloodRequestService bloodRequestService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addRequest(@RequestBody BloodRequest bloodRequest){
        if (bloodRequest == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid BloodRequest data"));
        }
        System.out.println(bloodRequest);
        boolean isAdded = bloodRequestService.addBloodRequest(bloodRequest);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "BloodRequest Added"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to add BloodRequest"));
        }
    }

    @GetMapping("/pending")
    public List<BloodRequest> getPendingList(){
        return bloodRequestService.getPendingList();
    }

    @GetMapping("/completed")
    public List<BloodRequest> getApprovedList(){
        return bloodRequestService.getCompletedList();
    }

    @PatchMapping("/update/{requestID}/{status}")
    public ResponseEntity<String> updateRequestStatus(
            @PathVariable String requestID,
            @PathVariable String status){
        return bloodRequestService.updateRequestStatus(requestID,status)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Request Updated"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update Request");
    }
}
