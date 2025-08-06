package dev.engripaye.universal_digital_health_record_access_system.service;

import dev.engripaye.universal_digital_health_record_access_system.model.HealthRecord;
import dev.engripaye.universal_digital_health_record_access_system.repository.HealthRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class HealthRecordService {

    private final HealthRepository repo;

    public HealthRecordService(HealthRepository repo) {
        this.repo = repo; }

    public HealthRecord create(String patientId, String title, String content) {
        HealthRecord r = new HealthRecord();
        r.setPatientId(patientId);
        r.setTitle(title);
        r.setContent(content);
        r.setCreatedAt(String.valueOf(Instant.now()));
        return repo.save(r);
    }

    public List<HealthRecord> findByPatient(String patientId) {
        return repo.findByPatientId(patientId);
    }

    public Optional<HealthRecord> findById(String id) {
        return repo.findById(id);
    }

    public HealthRecord save(HealthRecord r) { return repo.save(r); }

    public void deleteById(String id) { repo.deleteById(id); }
}
