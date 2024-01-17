package mentcare.repositories;

import mentcare.models.Prescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    List<Prescription> findByPatientID(Long patientID);
    Prescription findById(long id);
}