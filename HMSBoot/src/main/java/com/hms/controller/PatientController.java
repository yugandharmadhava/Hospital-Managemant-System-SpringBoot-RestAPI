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
import com.hms.model.Patient;
import com.hms.service.PatientService;
@RestController
@RequestMapping("/patients")
public class PatientController {
	private PatientService service;
	public PatientController(PatientService service) {
		super();
		this.service = service;
	}
	@PostMapping
	public ResponseEntity<?> savePatient(@RequestBody Patient patient) {
		Patient obj = service.savePatient(patient);
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error in Adding Patient Details", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllPatients() {
		List<Patient> patList = service.getAllPatients();
		if (patList.size() != 0) {
			return new ResponseEntity<>(patList, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>("No Patients to Display", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> searchPatient(@PathVariable int id) {
		Patient patient = service.searchPatient(id);
		if (patient.getId() != 0) {
			return new ResponseEntity<>(patient, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>("Invalid Patient Id", HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping
	public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
		Patient obj = service.searchPatient(patient.getId());
		if (obj.getId() != 0) {
			service.savePatient(patient);
			return new ResponseEntity<>(patient, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>("Invalid Patient Id", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable int id) {
		Patient patient = service.searchPatient(id);
		if (patient.getId() != 0) {
			service.deletePatient(id);
			return new ResponseEntity<>("Patient Deleted Successfully...", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Patient Id", HttpStatus.NOT_FOUND);
		}
	}
}