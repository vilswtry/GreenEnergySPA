package com.GreenEnergy.backupRestoreService.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.GreenEnergy.backupRestoreService.model.Backup;
import com.GreenEnergy.backupRestoreService.repository.BackupRepository;

@Service
public class BackupService {

    @Autowired
    private BackupRepository backupRepository;

    @Value("${backup.dir}")
    private String backupDir;

    @Value("${mysql.path}")
    private String mysqlDumpPath;

    @Value("${mysql.restore.path}")
    private String mysqlRestorePath;

    @Value("${mysql.user}")
    private String mysqlUser;

    @Value("${mysql.db}")
    private String mysqlDb;

    public Backup createBackup() {
        try {

            new File(backupDir).mkdirs();

            String fechaCreacion = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = "backup_" + fechaCreacion + ".sql";
            File backupFile = new File(backupDir, filename);

            ProcessBuilder pb = new ProcessBuilder(
                    mysqlDumpPath, "-u", mysqlUser, mysqlDb);
            pb.redirectOutput(backupFile);

            Process process = pb.start();

            int resultadoProceso = process.waitFor();
            if (resultadoProceso != 0) {
                throw new RuntimeException("Error al crear el respaldo, código de salida: " + resultadoProceso);
            }

            Backup backup = new Backup(null, filename, new Date());
            return backupRepository.save(backup);

        } catch (Exception e) {
            throw new RuntimeException("Error durante la creación del respaldo", e);
        }
    }
}
