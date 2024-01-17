package mentcare;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findByLastName(String lastName);

    Patient findById(long id);
}