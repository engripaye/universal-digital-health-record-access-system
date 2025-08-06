package dev.engripaye.universal_digital_health_record_access_system.controller;

import dev.engripaye.universal_digital_health_record_access_system.model.HealthRecord;
import dev.engripaye.universal_digital_health_record_access_system.model.Role;
import dev.engripaye.universal_digital_health_record_access_system.model.User;
import dev.engripaye.universal_digital_health_record_access_system.service.HealthRecordService;
import dev.engripaye.universal_digital_health_record_access_system.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/records")
public class HealthRecordController {


    private final HealthRecordService recordService;
    private final UserService userService;

    public HealthRecordController(HealthRecordService svc, UserService userService) {
        this.recordService = svc;
        this.userService = userService;
    }

    // create a record (patient creates their record)
    @PostMapping
    public HealthRecord create(@AuthenticationPrincipal OidcUser oidcUser, @RequestBody HealthRecord payload) {
        User user = userService.upsertFromOidc(oidcUser);
        // ensure owner is this patient
        payload.setPatientId(user.getId());
        return recordService.create(user.getId(), payload.getTitle(), payload.getContent());
    }

    // list records for current user (patient) or for a doctor show allowed records:
    @GetMapping
    public List<HealthRecord> listMyRecords(@AuthenticationPrincipal OidcUser oidcUser) {
        User user = userService.upsertFromOidc(oidcUser);
        boolean isDoctor = oidcUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals(Role.ROLE_DOCTOR.name()));
        if (isDoctor) {
            // doctors in this simple demo must be granted access per-record; here we return nothing by default
            // In a slightly extended design, doctors could search patients or accept shares.
            return List.of();
        } else {
            return recordService.findByPatient(user.getId());
        }
    }

    // get single record if allowed
    @GetMapping("/{id}")
    public HealthRecord get(@AuthenticationPrincipal OidcUser oidcUser, @PathVariable String id) {
        User user = userService.upsertFromOidc(oidcUser);
        Optional<HealthRecord> maybe = recordService.findById(id);
        var r = maybe.orElseThrow(() -> new RuntimeException("Not found"));

        boolean isOwner = r.getPatientId().equals(user.getId());
        boolean isSharedWithDoctor = r.getSharedWithUserIds() != null && r.getSharedWithUserIds().contains(user.getId());
        boolean isAdmin = oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals(Role.ROLE_ADMIN.name()));

        if (isOwner || isSharedWithDoctor || isAdmin) return r;
        throw new RuntimeException("Access denied");
    }

    // share record with a doctor (patient only)
    @PostMapping("/{id}/share/{doctorUserId}")
    public HealthRecord share(@AuthenticationPrincipal OidcUser oidcUser,
                              @PathVariable String id,
                              @PathVariable String doctorUserId) {
        User user = userService.upsertFromOidc(oidcUser);
        var r = recordService.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (!r.getPatientId().equals(user.getId())) throw new RuntimeException("Only patient can share");
        var set = r.getSharedWithUserIds();
        if (set == null) set = new java.util.HashSet<>();
        set.add(doctorUserId);
        r.setSharedWithUserIds(set);
        return recordService.save(r);
    }
}
