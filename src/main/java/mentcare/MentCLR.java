package mentcare;

import mentcare.models.Patient;
import mentcare.models.Visit;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MentCLR implements CommandLineRunner {


    @Autowired
    PatientRepository patientRepo;

    @Autowired
    VisitRepository visitRepo;

    @Override
    public void run(String... args) throws Exception {
        Patient p1 = new Patient("John", "Doe", 70, 170, 26, "14 03 1998",
                "maschio", "1234567890", "30elode@aley.org", "Via vittoria 2",
                "lattice","BBB");

        Patient p2 = new Patient("Jane", "Doe", 60, 160, 20, "20 04 2000",
                "femmina", "1234567890", "30elode@aley.org", "Via vittoria 2",
                "api","CCC");
        patientRepo.save(p1);
        patientRepo.save(p2);
        Visit v1 = new Visit("2/2/24", "10:30", "E-PT", "John Doe");
        Visit v2 = new Visit("2/2/24", "12:30", "E-PT", "Mario Rossi");
        Visit v3 = new Visit("4/3/24", "17:30", "G-P1", "Luigi verdi");
        visitRepo.save(v1);
        visitRepo.save(v2);
        visitRepo.save(v3);
    }
}
