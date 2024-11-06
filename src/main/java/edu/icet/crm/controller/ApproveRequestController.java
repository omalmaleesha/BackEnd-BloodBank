package edu.icet.crm.controller;

import edu.icet.crm.dto.ApprovedRequest;
import edu.icet.crm.dto.BloodRequest;
import edu.icet.crm.service.ApproveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/ApproveRequest")
public class ApproveRequestController {
    private final ApproveRequestService approveRequestService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addApproveRequest(@RequestBody ApprovedRequest bloodRequest){
        if (bloodRequest == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid BloodRequest data"));
        }
        System.out.println(bloodRequest);
        boolean isAdded = approveRequestService.addApproveRequest(bloodRequest);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "BloodRequest Added"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to add BloodRequest"));
        }
    }

    @GetMapping("/completed")
    public List<ApprovedRequest> getCompletedList(){
        List<ApprovedRequest> completedList = approveRequestService.getCompletedList();
        System.out.println(completedList);
        return completedList;
    }
}
