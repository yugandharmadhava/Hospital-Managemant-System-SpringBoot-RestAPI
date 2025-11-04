package com.hms.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hms.model.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer>{

}
