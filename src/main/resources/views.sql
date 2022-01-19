-- VIEWS CREATION
--
-- structuresByMinistere
DROP VIEW IF EXISTS structuresByMinistere;
CREATE VIEW structuresByMinistere AS 
    SELECT s.id AS id, ministere_id AS ministereid, s.libelle AS libellestructure, s.sigle AS siglestructure, 
            s.niveau AS niveaustructure, s.statut AS statutstructure, s.telephone AS telephonestructure, 
            s.email_resp AS emailrespstructure, s.email_struct AS emailstructstructure, s.parent_id AS parentstructure
    FROM ministere_structure AS ms, structure AS s WHERE ms.statut IS TRUE GROUP BY ministere_id, s.id;

-- programmationList
DROP VIEW IF EXISTS programmationList CASCADE;
CREATE VIEW programmationList AS 
    SELECT DISTINCT p.id AS id, p.cible, p.code, p.cout_previsionnel AS coutprevisionnel, p.cout_reel AS coutreel,
        p.est_programme AS estprogramme, p.observations, p.taux, p.activite_id AS activiteid,
        p.objectif_id AS objectifid, p.projet_id AS projetid, p.source_financement_id AS sourcefinancementid,
        s.id AS structureid, e.id AS exerciceid
    FROM programmation p, structure s, exercice e 
    WHERE p.deleted = false AND p.structure_id = s.id AND p.exercice_id = e.id;
    -- GROUP BY p.objectif_id;

-- objectifOperationnelList
DROP VIEW IF EXISTS objectifOperationnelList CASCADE;
CREATE VIEW objectifOperationnelList AS 
    SELECT o.id AS id, o.code AS codeobjectifop, o.libelle AS libelleobjectifop, v.id AS programmationid, v.cible, v.code, 
        v.coutprevisionnel, v.coutreel,v.estprogramme, 
        v.observations, v.taux, v.activiteid, v.exerciceid, 
        --v.objectif_id AS objectifid,
        v.projetid, v.sourcefinancementid, v.structureid
    FROM objectif o, programmationlist v 
    WHERE o.deleted IS FALSE AND o.action_id IS NOT NULL AND o.id = v.objectifid;

-- VIEW GLOBALE
DROP VIEW IF EXISTS dgessreportview CASCADE;
CREATE VIEW dgessreportview AS 
    SELECT p.id AS id, p.code AS codeprogrammation, p.activite_id AS idactivite, a.libelle AS libelleactivite,
            p.cible AS cibleprogrammation, p.cout_previsionnel AS coutprevisionnel, p.cout_reel AS coutreel,
            p.taux AS tauxprogrammation, f.id AS idfinancement, f.libelle AS libellefinancement, op.id AS idobjectifope,
            op.code AS codeobjectifope, op.libelle AS libelleobjectifope, ac.id AS idaction, ac.code AS codeaction,
            ac.libelle AS libelleaction, ac.objectif_id AS idobjectifstra, os.code AS codeobjectifstra, os.libelle AS libelleobjectifstra,
            os.programme_id AS idprogramme, prog.code AS codeprogramme, prog.libelle AS libelleprogramme, 
            p.structure_id AS idstructure, ms.ministere_id AS idministere, p.exercice_id AS idexercice
    FROM programmation p, activites a, source_financement f, objectif op, objectif os, action ac, programme prog, ministere_structure ms
    WHERE p.activite_id = a.id -- jointure entre Programmation & Activites
    AND p.source_financement_id = f.id -- jointure entre Programmation & SourceFinancement
    AND p.objectif_id = op.id -- jointure entre Programmation & ObjectifOperationnel
    AND op.action_id = ac.id -- jointure entre ObjectifOperationnel & Action
    AND ac.objectif_id = os.id -- jointure entre Action & ObjectifStrategique
    AND os.programme_id = prog.id -- jointure entre ObjectifStrategique & Programme
    AND p.structure_id = ms.structure_id AND ms.statut IS TRUE; -- jointure entre Structure & Ministere