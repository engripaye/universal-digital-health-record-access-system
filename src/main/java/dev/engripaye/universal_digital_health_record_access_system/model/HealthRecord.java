package dev.engripaye.universal_digital_health_record_access_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("records")
public class HealthRecord {

    @Id
    private String id;
    private String patientId;
    private String title;
    private String content;
    private String createdAt;
    private Set<String> sharedWithUserIds;

    public HealthRecord(String id, String patientId, String title, String content, String createdAt, Set<String> sharedWithUserIds) {
        this.id = id;
        this.patientId = patientId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.sharedWithUserIds = sharedWithUserIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Set<String> getSharedWithUserIds() {
        return sharedWithUserIds;
    }

    public void setSharedWithUserIds(Set<String> sharedWithUserIds) {
        this.sharedWithUserIds = sharedWithUserIds;
    }
}
