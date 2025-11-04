package com.hms.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hms.model.Doctor;
import com.hms.repo.DoctorRepo;
@Service
public class DoctorService {
	private DoctorRepo repo;
	public DoctorService(DoctorRepo repo) {
		super();
		this.repo = repo;
	}
	public Doctor saveDoctor(Doctor doctor) {
		return repo.save(doctor);
	}
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}
	public Doctor searchDoctor(int id) {
		return repo.findById(id).orElse(new Doctor());
	}
	public void deleteDoctor(int id) {
		repo.deleteById(id);
	}

}
