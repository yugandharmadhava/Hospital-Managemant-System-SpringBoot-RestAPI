package com.hms.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hms.model.Doctor;
import com.hms.service.DoctorService;
@RestController
@RequestMapping("/doctors")
public class DoctorController {
	private DoctorService service ;
	public DoctorController(DoctorService service) {
		super();
		this.service = service;
	}
	@PostMapping
	public ResponseEntity<?> saveDoctor(@RequestBody Doctor doctor) {
		Doctor obj = service.saveDoctor(doctor);
		if(obj != null) {
			return new ResponseEntity<>(obj,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Error in Adding in Doctor Details",HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping
	public ResponseEntity<?> getAllDoctors(){
		List<Doctor> docList = service.getAllDoctors();
		if(docList.size() != 0) {
			return new ResponseEntity<>(docList,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>("No Doctors to Display",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> searchDoctor(@PathVariable int id){
		Doctor doctor = service.searchDoctor(id);
		if(doctor.getId() != 0) {
			return new ResponseEntity<>(doctor , HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>("Invalid Doctor Id", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor){
		Doctor obj = service.searchDoctor(doctor.getId());
		if(obj.getId() != 0) {
			service.saveDoctor(doctor);
			return new ResponseEntity<>(doctor , HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>("Invalid Doctor Id", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable int id){
		Doctor doctor = service.searchDoctor(id);
		if(doctor.getId() != 0) {
			service.deleteDoctor(id);
			return new ResponseEntity<>("Doctor Deleted...", HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>("Invalid Doctor Id", HttpStatus.BAD_REQUEST);
		}
    }
}