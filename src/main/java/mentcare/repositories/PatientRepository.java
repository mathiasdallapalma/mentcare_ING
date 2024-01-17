package mentcare.repositories;

import java.util.List;

import mentcare.models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {


    Patient findById(long id);
}