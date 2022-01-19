/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.service.reportentities.ObjectifOperationnelListVE;
import com.mfptps.appdgessddi.service.reportentities.ProgrammationListVE;
import com.mfptps.appdgessddi.service.reportentities.StructuresByMinistereVE;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Component
public class QueryManagerRepository {

    @PersistenceContext
    private EntityManager em;

    public List<StructuresByMinistereVE> structuresList() {
        Query q = em.createNativeQuery("select * from structuresByMinistere", StructuresByMinistereVE.class);
        List<StructuresByMinistereVE> result = q.getResultList();
        return result;
    }

    public List<ProgrammationListVE> programmationList() {
        Query q = em.createNativeQuery("select * from programmationList", ProgrammationListVE.class);
        List<ProgrammationListVE> result = q.getResultList();
        return result;
    }

    public List<ObjectifOperationnelListVE> objectifOperationnelList() {
        Query q = em.createNativeQuery("select * from objectifOperationnelList", ObjectifOperationnelListVE.class);
        List<ObjectifOperationnelListVE> result = q.getResultList();
        return result;
    }
}
