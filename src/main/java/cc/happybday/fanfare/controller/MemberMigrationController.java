package cc.happybday.fanfare.controller;

import cc.happybday.fanfare.service.MemberMigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migration")
@RequiredArgsConstructor
public class MemberMigrationController {

    private final MemberMigrationService memberMigrationService;

    @PostMapping("/uuid")
    public ResponseEntity<String> migrateUUIDs() {
        memberMigrationService.migrateMembersWithUUID();
        return ResponseEntity.ok("UUID migration completed successfully.");
    }
}

