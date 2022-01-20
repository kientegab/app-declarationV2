/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.service.reportentities.ActionRE;
import com.mfptps.appdgessddi.service.reportentities.ActiviteRE;
import com.mfptps.appdgessddi.service.reportentities.JasperTest;
import com.mfptps.appdgessddi.service.reportentities.ObjectifOperationnelRE;
import com.mfptps.appdgessddi.service.reportentities.ObjectifStrategiqueRE;
import com.mfptps.appdgessddi.service.reportentities.ProgrammeDataRE;
import com.mfptps.appdgessddi.service.reportentities.ProgrammeRE;
import com.mfptps.appdgessddi.service.reportentities.StrucTest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@Slf4j
@Service
@Transactional
public class ServiceTest {
    @PersistenceContext
    private EntityManager em;

    public ServiceTest(EntityManager em) {
        this.em = em;
    }
    
    public void print(){
         Query l = em.createNativeQuery("select * from structurebymin ");
        log.info("__________________ l " + l.toString());
        List<StrucTest> structures = l.getResultList();
        for (StrucTest structure : structures) {
            
             System.out.println("--- structure" + structure.getLibelle());
        }
        
        List<Object[]> list = l.getResultList();
        log.info("__________________ taille " + list.size());
        for (Object[] o : list) {
            System.out.println("---" + Arrays.toString(o));
        }

        ProgrammeDataRE programmeDataRE = new ProgrammeDataRE();
        List<ProgrammeDataRE> programmeDataREs = new ArrayList<>();

        ProgrammeRE programmeRE = new ProgrammeRE();
        List<ProgrammeRE> programmeREs = new ArrayList<>();

        ObjectifStrategiqueRE objectifStrategiqueRE = new ObjectifStrategiqueRE();
        List<ObjectifStrategiqueRE> objectifStrategiqueREs = new ArrayList<>();

        ActionRE actionRE = new ActionRE();
        List<ActionRE> actionREs = new ArrayList<>();

        ObjectifOperationnelRE objectifOperationnelRE = new ObjectifOperationnelRE();
        List<ObjectifOperationnelRE> objectifOperationnelREs = new ArrayList<>();

        ActiviteRE activiteRE = new ActiviteRE();
        List<ActiviteRE> activiteREs = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            activiteREs.add(new ActiviteRE("" + (i + 1), "libelle" + i, "indicateur" + i, "" + i, (double) i, "fin" + i, "ST-GVAP" + i));
        }

        List<ActiviteRE> activiteREsBis = new ArrayList<>();

        for (int i = 10; i < 23; i++) {
            activiteREsBis.add(new ActiviteRE("" + (i + 1), "libelle" + i, "indicateur" + i, "" + i, (double) i, "fin" + i, "ST-GVAP" + i));
        }

        programmeREs.add(new ProgrammeRE("1.1", "Programme1",
                "ST-GVAP",
                Arrays.asList(
                        new ObjectifStrategiqueRE("1.1.1",
                                "OS1", "-",
                                "-", Arrays.asList(
                                        new ActionRE("1.1.1.1", "Action1",
                                                "-", Arrays.asList(
                                                        new ObjectifOperationnelRE("1.1.1.1.1", "OP1",
                                                                "-", activiteREs))))))));

        programmeREs.add(new ProgrammeRE("1.2", "Programme2",
                "ST-GVAP",
                Arrays.asList(
                        new ObjectifStrategiqueRE("1.2.1",
                                "OS2", "-",
                                "-", Arrays.asList(
                                        new ActionRE("1.2.1.1", "Action2",
                                                "-", Arrays.asList(
                                                        new ObjectifOperationnelRE("1.2.1.1.1", "OP2",
                                                                "-", activiteREsBis))))))));
        programmeDataREs.add(new ProgrammeDataRE("MFTPS", "Cabinet", "ST-GVAP",
                "20000000", "PROGRAMME D'ACTIVITES", null, programmeREs));

        try {

            InputStream reportStream = JasperTest.class.getResourceAsStream("/conteneur_principal.jasper");

            Map<String, Object> parameters = new HashMap<>();

            JRDataSource datasource = new JRBeanCollectionDataSource(programmeDataREs);
            JasperReport japerReport = (JasperReport) JRLoader.loadObject(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(japerReport, parameters, datasource);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Canisius\\Pictures\\test.pdf");//exportReportToPdfStream(jasperPrint, outStream);
        } catch (Exception e) {
            log.error("________", e);
        }
    }
    }
