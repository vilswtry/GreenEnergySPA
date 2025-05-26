package com.GreenEnergy.coordinacionRecursos.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.GreenEnergy.coordinacionRecursos.model.Material;
import com.GreenEnergy.coordinacionRecursos.repository.MaterialRepository;

@Configuration
public class LoadMateriales {

    @Bean
    CommandLineRunner initDatabase(MaterialRepository materialRepository) {
        return args -> {
            if (materialRepository.count() == 0) {
                List<Material> materiales = new ArrayList<>();

                Material panelSolar = new Material();
                panelSolar.setCodigoMaterial("PS");
                panelSolar.setNombreMaterial("Panel Solar");
                panelSolar.setStock(110);
                panelSolar.setUnidadMedida("unidad");

                Material inversor = new Material();
                inversor.setCodigoMaterial("INV");
                inversor.setNombreMaterial("Inversor");
                inversor.setStock(20);
                inversor.setUnidadMedida("unidad");

                Material cableSolar = new Material();
                cableSolar.setCodigoMaterial("CBDC");
                cableSolar.setNombreMaterial("Cable Solar");
                cableSolar.setStock(450);
                cableSolar.setUnidadMedida("metro");

                Material rielAluminio = new Material();
                rielAluminio.setCodigoMaterial("RIA");
                rielAluminio.setNombreMaterial("Riel de aluminio");
                rielAluminio.setStock(80);
                rielAluminio.setUnidadMedida("unidad");

                Material abrazaderas = new Material();
                abrazaderas.setCodigoMaterial("ABR");
                abrazaderas.setNombreMaterial("Abrazadera");
                abrazaderas.setStock(180);
                abrazaderas.setUnidadMedida("unidad");

                Material ganchosTecho = new Material();
                ganchosTecho.setCodigoMaterial("GCH");
                ganchosTecho.setNombreMaterial("Gancho para techo");
                ganchosTecho.setStock(120);
                ganchosTecho.setUnidadMedida("unidad");

                Material tornillos = new Material();
                tornillos.setCodigoMaterial("TRN");
                tornillos.setNombreMaterial("Tornillo");
                tornillos.setStock(450);
                tornillos.setUnidadMedida("unidad");

                Material pernosTarugos = new Material();
                pernosTarugos.setCodigoMaterial("PTS");
                pernosTarugos.setNombreMaterial("Perno con tarugo");
                pernosTarugos.setStock(450);
                pernosTarugos.setUnidadMedida("unidad");

                Material kitProteccion = new Material();
                kitProteccion.setCodigoMaterial("KPE");
                kitProteccion.setNombreMaterial("Kit de Proteccion Electrica");
                kitProteccion.setStock(20);
                kitProteccion.setUnidadMedida("unidad");

                Material cableAC = new Material();
                cableAC.setCodigoMaterial("CBAC");
                cableAC.setNombreMaterial("Cable AC");
                cableAC.setStock(300);
                cableAC.setUnidadMedida("metro");

                Material tableroAC = new Material();
                tableroAC.setCodigoMaterial("TAC");
                tableroAC.setNombreMaterial("Tablero AC Eléctrico");
                tableroAC.setStock(20);
                tableroAC.setUnidadMedida("unidad");

                Material cableTierra = new Material();
                cableTierra.setCodigoMaterial("CBT");
                cableTierra.setNombreMaterial("Cable de puesta a tierra");
                cableTierra.setStock(90);
                cableTierra.setUnidadMedida("metro");

                Material varillaTierra = new Material();
                varillaTierra.setCodigoMaterial("VCT");
                varillaTierra.setNombreMaterial("Varilla de cobre para tierra");
                varillaTierra.setStock(20);
                varillaTierra.setUnidadMedida("unidad");

                Material cajaEstanca = new Material();
                cajaEstanca.setCodigoMaterial("CAE");
                cajaEstanca.setNombreMaterial("Caja Estanca");
                cajaEstanca.setStock(30);
                cajaEstanca.setUnidadMedida("unidad");

                Material conectores = new Material();
                conectores.setCodigoMaterial("MC4");
                conectores.setNombreMaterial("Conector MC4");
                conectores.setStock(120);
                conectores.setUnidadMedida("unidad");

                Material canaletas = new Material();
                canaletas.setCodigoMaterial("CNT");
                canaletas.setNombreMaterial("Canaleta");
                canaletas.setStock(150);
                canaletas.setUnidadMedida("metro");

                Material etqSeguridad = new Material();
                etqSeguridad.setCodigoMaterial("ETQ");
                etqSeguridad.setNombreMaterial("Etiqueta de Seguridad");
                etqSeguridad.setStock(20);
                etqSeguridad.setUnidadMedida("unidad");

                Material kitSeguridad = new Material();
                kitSeguridad.setCodigoMaterial("EPP");
                kitSeguridad.setNombreMaterial("Kit de seguridad personal (EPP)");
                kitSeguridad.setStock(20);
                kitSeguridad.setUnidadMedida("unidad");

                Material multimetro = new Material();
                multimetro.setCodigoMaterial("MULT");
                multimetro.setNombreMaterial("Multimetros");
                multimetro.setStock(20);
                multimetro.setUnidadMedida("unidad");

                Material cepillo = new Material();
                cepillo.setCodigoMaterial("CEP");
                cepillo.setNombreMaterial("Cepillo no metálico");
                cepillo.setStock(50);
                cepillo.setUnidadMedida("unidad");

                Material paño = new Material();
                paño.setCodigoMaterial("PAN");
                paño.setNombreMaterial("Paño");
                paño.setStock(50);
                paño.setUnidadMedida("unidad");

                Material aguaDes = new Material();
                aguaDes.setCodigoMaterial("AGP");
                aguaDes.setNombreMaterial("Agua Destilada");
                aguaDes.setStock(100);
                aguaDes.setUnidadMedida("litro");

                Material tester = new Material();
                tester.setCodigoMaterial("TST");
                tester.setNombreMaterial("Tester Solar");
                tester.setStock(20);
                tester.setUnidadMedida("unidad");

                materiales.add(panelSolar);
                materiales.add(inversor);
                materiales.add(cableSolar);
                materiales.add(rielAluminio);
                materiales.add(abrazaderas);
                materiales.add(ganchosTecho);
                materiales.add(tornillos);
                materiales.add(pernosTarugos);
                materiales.add(kitProteccion);
                materiales.add(cableAC);
                materiales.add(tableroAC);
                materiales.add(cableTierra);
                materiales.add(varillaTierra);
                materiales.add(cajaEstanca);
                materiales.add(conectores);
                materiales.add(canaletas);
                materiales.add(etqSeguridad);
                materiales.add(kitSeguridad);
                materiales.add(multimetro);
                materiales.add(cepillo);
                materiales.add(paño);
                materiales.add(aguaDes);
                materiales.add(tester);

                materialRepository.saveAll(materiales);
                System.out.println("Materiales iniciales cargados");

            } else {
                System.out.println("Materiales ya existentes. No se realizó la carga");
            }
        };
    }
}