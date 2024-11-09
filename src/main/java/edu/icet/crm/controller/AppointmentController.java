package edu.icet.crm.controller;

import edu.icet.crm.dto.Appointment;
import edu.icet.crm.dto.Donar;
import edu.icet.crm.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;


    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addAppointment(@RequestBody Appointment appointment){
        if (appointment == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Appointment data"));
        }
        System.out.println(appointment);
        boolean isAdded = appointmentService.addAppointment(appointment);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Appointment Added"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to add Appointment"));
        }
    }



    @GetMapping("/all")
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/pendings")
    public List<Appointment> getPendingList(){
        return appointmentService.getPendingList();
    }

    @GetMapping("/canceled")
    public List<Appointment> getcanceledList(){
        return appointmentService.getcanceledList();
    }

    @GetMapping("/approved")
    public List<Appointment> getApprovedList(){
        return appointmentService.getApprovedList();
    }


    @GetMapping("/findById/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable String appointmentId){
        return appointmentService.findAppointmentById(appointmentId);
    }

    @GetMapping("/findByDonarId/{donarID}")
    public List<Appointment> getDonarAppointmentsById(@PathVariable String donarID){
        return appointmentService.findDonarAppointmentsById(donarID);

    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable String appointmentId) {
        try {
            boolean isDeleted = appointmentService.deleteAppointmentById(appointmentId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Appointment Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment Not Found");
            }
        } catch (Exception e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Appointment");
        }
    }


    @PatchMapping("/update")
    public ResponseEntity<String> updateAppointment(@RequestBody Appointment appointment){
        return appointmentService.updateAppointment(appointment)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Appointment Updated"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update Appointment");
    }

    @PatchMapping("/update/{appointmentId}/{status}")
    public ResponseEntity<String> updateAppointmentStatus(
            @PathVariable String appointmentId,
            @PathVariable String status){
        return appointmentService.updateAppointmentStatus(appointmentId,status)?ResponseEntity
                .status(HttpStatus.OK)
                .body("Appointment Updated"):ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update Appointment");
    }
}
