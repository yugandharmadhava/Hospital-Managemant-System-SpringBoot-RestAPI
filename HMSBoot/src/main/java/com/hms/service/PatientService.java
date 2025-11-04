package com.hms.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.model.Patient;
import com.hms.repo.PatientRepo;
@Service
public class PatientService {
	private PatientRepo repo;
	public PatientService(PatientRepo repo) {
		super();
		this.repo = repo;
	}
	public Patient savePatient(Patient patient) {
		return repo.save(patient);
	}
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}
	public Patient searchPatient(int id) {
		return repo.findById(id).orElse(new Patient());
	}
	public void deletePatient(int id) {
		repo.deleteById(id);
		
	}
	
	
}
