package com.GreenEnergy.backupRestoreService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GreenEnergy.backupRestoreService.model.Backup;
import com.GreenEnergy.backupRestoreService.service.BackupService;

@RestController
@RequestMapping("/backups")
public class BackupController {
@Autowired
private BackupService backupService;

@PostMapping
public ResponseEntity<?> crearBackup() {
    try {
        Backup backup = backupService.createBackup();
        return ResponseEntity.status(HttpStatus.CREATED).body(backup);
    
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
}
