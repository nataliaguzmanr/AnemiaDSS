package diagnosis;

import jdbc.*;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ui.MedicalStaffMenu.initializeAnemia;

public class AppTesting {

    Anemia anemic_syndrome_male;

    @Test
    public void testApplication() {

        JDBCManager jdbcManager = new JDBCManager();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        JDBCUserManager userManager = new JDBCUserManager(jdbcManager);
        final JDBCPatientManager jdbcPatientManager = new JDBCPatientManager(jdbcManager);
        final JDBCMedicalStaffManager jdbcMedicalStaffManager = new JDBCMedicalStaffManager(jdbcManager);
        final JDBCSymptomManager jdbcSymptomManager = new JDBCSymptomManager(jdbcManager);
        final JDBCReportManager jdbcReportManager = new JDBCReportManager(jdbcManager);
        final JDBCClinicalHistoryManager jdbcClinicalHistoryManager =
                new JDBCClinicalHistoryManager(jdbcManager);


        final Logger LOG = LoggerFactory.getLogger(AppTesting.class);

        PatientUnit patientUnit;
        RuleUnitInstance<PatientUnit> instance;

//---------------------------------------------------------------------------------------------

        Anemia anemicSyndromeMALE = initializeAnemia(AnemiaType.ANEMIC_SYNDROME_MALE);
        Anemia anemicSyndromeFEMALE = initializeAnemia(AnemiaType.ANEMIC_SYNDROME_FEMALE);

        Anemia posthemorrhagicMALE = initializeAnemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA_MALE);
        Anemia posthemorrhagicFEMALE = initializeAnemia(AnemiaType.POSTHEMORRHAGIC_ANEMIA_FEMALE);

        Anemia hemolyticMALE = initializeAnemia(AnemiaType.HEMOLYTIC_ANEMIA_MALE);
        Anemia hemolyticFEMALE = initializeAnemia(AnemiaType.HEMOLYTIC_ANEMIA_FEMALE);

        Anemia inheritedSpherocytMALE = initializeAnemia(AnemiaType.INHERITED_SPHEROCYTOSIS_MALE);
        Anemia inheritedSpherocytFEMALE = initializeAnemia(AnemiaType.INHERITED_SPHEROCYTOSIS_FEMALE);

        Anemia thalassemiaMALE = initializeAnemia(AnemiaType.THALASSEMIA_MALE);
        Anemia thalassemiaFEMALE = initializeAnemia(AnemiaType.THALASSEMIA_FEMALE);

        Anemia ironDeficiencyMALE = initializeAnemia(AnemiaType.IRON_DEFICIENCY_ANEMIA_MALE);
        Anemia ironDeficiencyFEMALE = initializeAnemia(AnemiaType.IRON_DEFICIENCY_ANEMIA_FEMALE);

        Anemia megaloblasticMALE = initializeAnemia(AnemiaType.MEGALOBLASTIC_ANEMIA_MALE);
        Anemia megaloblasticFEMALE = initializeAnemia(AnemiaType.MEGALOBLASTIC_ANEMIA_FEMALE);

        Anemia aplasticMALE = initializeAnemia(AnemiaType.APLASTIC_ANEMIA_MALE);
        Anemia aplasticFEMALE = initializeAnemia(AnemiaType.APLASTIC_ANEMIA_FEMALE);

        Anemia chronicDiseaseMALE = initializeAnemia(AnemiaType.CHRONIC_DISEASE_ANEMIA_MALE);
        Anemia chronicDiseaseFEMALE = initializeAnemia(AnemiaType.CHRONIC_DISEASE_ANEMIA_FEMALE);

        Anemia polycythemiaMALE = initializeAnemia(AnemiaType.POLYCYTHEMIA_MALE);
        Anemia polycythemiaFEMALE = initializeAnemia(AnemiaType.POLYCYTHEMIA_FEMALE);

//---------------------------------------------------------------------------------------------

        try {

            //probamos el acceso a la base de datos
            MedicalStaff med = jdbcMedicalStaffManager.getMedicalStaff(1);
            //System.out.println(med);

            Integer pId = jdbcPatientManager.getPatientId("Angela Lopez");
            Patient p2 = jdbcPatientManager.getPatient(pId);
            //System.out.println(p2);

//---------------------------------------------------------------------------------------------

            //creamos toda la lista de sintomas

            Symptom hb2 = new Symptom(8.1F, "Hb");
            p2.addSymptom(hb2);
            Symptom epo2 = new Symptom(2.3F, "EPO");
            p2.addSymptom(epo2);
            Symptom pvm = new Symptom(null, "Hematocrit/PVM");
            p2.addSymptom(pvm);
            Symptom plaquets2 = new Symptom(7.2F, "Platelets");
            p2.addSymptom(plaquets2);
            Symptom anisoc = new Symptom(7.2F, "Anisocytosis coefficient");
            p2.addSymptom(anisoc);
            Symptom reticu2 = new Symptom(2.7F, "Reticulocytes");
            p2.addSymptom(reticu2);
            Symptom Leukoc2 = new Symptom(4.3F, "Leukocytes");
            p2.addSymptom(Leukoc2);


            Float normalBloodVol = p2.getWeight() * 0.075F;
            System.out.println(normalBloodVol);
            if (normalBloodVol > 6) {
                Symptom incBloodVol = new Symptom(1.0F, "Increased blood volume");
                p2.addSymptom(incBloodVol);

                Symptom decBloodVol = new Symptom(0F, "Decreased blood volume");
                p2.addSymptom(decBloodVol);

            } else if (normalBloodVol < 4) {

                Symptom incBloodVol = new Symptom(0F, "Increased blood volume");
                p2.addSymptom(incBloodVol);

                Symptom decBloodVol = new Symptom(1.0F, "Decreased blood volume");
                p2.addSymptom(decBloodVol);
            }else{
                Symptom incBloodVol = new Symptom(null, "Increased blood volume");
                p2.addSymptom(incBloodVol);

                Symptom decBloodVol = new Symptom(null, "Decreased blood volume");
                p2.addSymptom(decBloodVol);
            }

            Symptom rbc2 = new Symptom(3.9F, "RBC");
            p2.addSymptom(rbc2);
            Symptom vcm = new Symptom(null, "VCM");
            p2.addSymptom(vcm);
            Symptom mch2 = new Symptom(26F, "MCH");
            p2.addSymptom(mch2);
            Symptom mhch2 = new Symptom(378F, "MCHC");
            p2.addSymptom(mhch2);
            Symptom hbUrine = new Symptom(null, "Hb in urine");
            p2.addSymptom(hbUrine);
            Symptom feUrine = new Symptom(null, "Fe in urine");
            p2.addSymptom(feUrine);
            Symptom fe2 = new Symptom(140F, "Fe");
            p2.addSymptom(fe2);
            Symptom bili1 = new Symptom(3F, "Bilirubin");
            p2.addSymptom(bili1);
            Symptom ldh = new Symptom(null, "LDH");
            p2.addSymptom(ldh);
            Symptom hapto2 = new Symptom(46F, "Haptoglobin");
            p2.addSymptom(hapto2);
            Symptom ferr = new Symptom(null, "Ferritin");
            p2.addSymptom(ferr);
            Symptom b12_2 = new Symptom(140F, "B12");
            p2.addSymptom(b12_2);
            Symptom folAc = new Symptom(null, "Folic acid");
            p2.addSymptom(folAc);
            Symptom gluc = new Symptom(null, "Glucocorticoids");
            p2.addSymptom(gluc);
            Symptom skin1 = new Symptom(1.0F, "Skin paleness");
            p2.addSymptom(skin1);
            Symptom tach1 = new Symptom(1.0F, "Tachycardia");
            p2.addSymptom(tach1);
            Symptom tinn = new Symptom(null, "Tinnitus");
            p2.addSymptom(tinn);
            Symptom chestPain = new Symptom(null, "Chest pain");
            p2.addSymptom(chestPain);
            Symptom infar = new Symptom(null, "Infarction");
            p2.addSymptom(infar);
            Symptom dizz = new Symptom(null, "Dizziness");
            p2.addSymptom(dizz);
            Symptom blurredV = new Symptom(null, "Blurred vision");
            p2.addSymptom(blurredV);
            Symptom head = new Symptom(null, "Headache");
            p2.addSymptom(head);
            Symptom fat1 = new Symptom(1.0F, "Fatigue");
            p2.addSymptom(fat1);
            Symptom muscleP = new Symptom(null, "Muscle pain");
            p2.addSymptom(muscleP);
            Symptom dysp = new Symptom(null, "Dyspnea");
            p2.addSymptom(dysp);
            Symptom tachp = new Symptom(null, "Tachypnea");
            p2.addSymptom(tachp);
            Symptom coldHF = new Symptom(null, "Cold hands/feet");
            p2.addSymptom(coldHF);
            Symptom intHem = new Symptom(null, "Intern hemorrhage");
            p2.addSymptom(intHem);
            Symptom extHem = new Symptom(null, "Extern hemorrhage");
            p2.addSymptom(extHem);
            Symptom nau = new Symptom(null, "Nausea, poor apetite");
            p2.addSymptom(nau);
            Symptom jaun1 = new Symptom(1.0F, "Jaundice");
            p2.addSymptom(jaun1);
            Symptom sple = new Symptom(null, "Splenomegaly");
            p2.addSymptom(sple);
            Symptom cran = new Symptom(null, "Cranial ballooning");
            p2.addSymptom(cran);
            Symptom aplC = new Symptom(null, "Aplastic crisis");
            p2.addSymptom(aplC);
            Symptom bactInf = new Symptom(null, "Bacterial infections");
            p2.addSymptom(bactInf);
            Symptom ost = new Symptom(null, "Osteoporosis");
            p2.addSymptom(ost);
            Symptom boneDef = new Symptom(null, "Bone deformations");
            p2.addSymptom(boneDef);
            Symptom maxHyp = new Symptom(null, "Maxila bone hyperplasia");
            p2.addSymptom(maxHyp);
            Symptom britN = new Symptom(null, "Brittle nails");
            p2.addSymptom(britN);
            Symptom tong = new Symptom(null, "Tongue inflammation");
            p2.addSymptom(tong);
            Symptom diar = new Symptom(null, "Diarrhea");
            p2.addSymptom(diar);
            Symptom par = new Symptom(null, "Paresthesia");
            p2.addSymptom(par);
            Symptom cya = new Symptom(null, "Cyanosis");
            p2.addSymptom(cya);
            Symptom thr = new Symptom(null, "Thrombosis");
            p2.addSymptom(thr);
            Symptom vom = new Symptom(null, "Vomit");
            p2.addSymptom(vom);
            Symptom pet = new Symptom(null, "Petechiae");
            p2.addSymptom(pet);

//---------------------------------------------------------------------------------------------

            LOG.info("Creating RuleUnit");
            patientUnit = new PatientUnit();

            instance = RuleUnitProvider.get().createRuleUnitInstance(patientUnit);
            LOG.info("Insert data");
            patientUnit.getPatients().add(p2);

            LOG.info("Fire rules");
            instance.fire();


            Set<Patient> patientsWithHemolyticAnemia = patientUnit.getPatientsWithHemolyticAnemia();
            assertEquals(1, patientUnit.getPatientsWithHemolyticAnemia().size());
            assertTrue(patientsWithHemolyticAnemia.contains(p2));

            Set<Patient> patientsWithInheritedSpherocythosis = patientUnit.getPatientsWithInheritedSpherocythosis();
            assertEquals(1, patientUnit.getPatientsWithInheritedSpherocythosis().size());
            assertTrue(patientsWithInheritedSpherocythosis.contains(p2));

            Set<Patient> patientsWithIronDeficiencyAnemia = patientUnit.getPatientsWithIronDeficiencyAnemia();
            assertEquals(1, patientUnit.getPatientsWithIronDeficiencyAnemia().size());
            assertTrue(patientsWithIronDeficiencyAnemia.contains(p2));

            Set<Patient> patientsWithMegaloblasticAnemia = patientUnit.getPatientsWithMegaloblasticAnemia();
            assertEquals(1, patientUnit.getPatientsWithMegaloblasticAnemia().size());
            assertTrue(patientsWithMegaloblasticAnemia.contains(p2));

            Set<Patient> patientsWithAplasticAnemia = patientUnit.getPatientsWithAplasticAnemia();
            assertEquals(1, patientUnit.getPatientsWithAplasticAnemia().size());
            assertTrue(patientsWithAplasticAnemia.contains(p2));

            Set<Patient> patientsWithChronicDiseaseAnemia = patientUnit.getPatientsWithChronicDiseaseAnemia();
            assertEquals(1, patientUnit.getPatientsWithChronicDiseaseAnemia().size());
            assertTrue(patientsWithChronicDiseaseAnemia.contains(p2));

            instance.close();

//---------------------------------------------------------------------------------------------

            List<Anemia> anemiasList = p2.getAnemiasList();
            //System.out.println(anemiasList);
            List<Symptom> symptomsList = p2.getSymptomsList();
            //System.out.println(symptomsList);
            //hasta aqui funciona

            List<Float> scoresList = new LinkedList<>();

            for(Anemia a : anemiasList) {

                //tenemos que inicializar las conditions y weights
                AnemiaType type = a.getAnemiaType();
                switch (type) {
                    case ANEMIC_SYNDROME_MALE:
                        a.setWeights(anemicSyndromeMALE.getWeights());
                        a.setConditions(anemicSyndromeMALE.getConditions());
                        break;
                    case ANEMIC_SYNDROME_FEMALE:
                        a.setWeights(anemicSyndromeFEMALE.getWeights());
                        a.setConditions(anemicSyndromeFEMALE.getConditions());
                        break;
                    case POSTHEMORRHAGIC_ANEMIA_MALE:
                        a.setWeights(posthemorrhagicMALE.getWeights());
                        a.setConditions(posthemorrhagicMALE.getConditions());
                        break;
                    case POSTHEMORRHAGIC_ANEMIA_FEMALE:
                        a.setWeights(posthemorrhagicFEMALE.getWeights());
                        a.setConditions(posthemorrhagicFEMALE.getConditions());
                        break;
                    case HEMOLYTIC_ANEMIA_MALE:
                        a.setWeights(hemolyticMALE.getWeights());
                        a.setConditions(hemolyticMALE.getConditions());
                        break;
                    case HEMOLYTIC_ANEMIA_FEMALE:
                        a.setWeights(hemolyticFEMALE.getWeights());
                        a.setConditions(hemolyticFEMALE.getConditions());
                        break;
                    case INHERITED_SPHEROCYTOSIS_MALE:
                        a.setWeights(inheritedSpherocytMALE.getWeights());
                        a.setConditions(inheritedSpherocytMALE.getConditions());
                        break;
                    case INHERITED_SPHEROCYTOSIS_FEMALE:
                        a.setWeights(inheritedSpherocytFEMALE.getWeights());
                        a.setConditions(inheritedSpherocytFEMALE.getConditions());
                        break;
                    case THALASSEMIA_MALE:
                        a.setWeights(thalassemiaMALE.getWeights());
                        a.setConditions(thalassemiaMALE.getConditions());
                        break;
                    case THALASSEMIA_FEMALE:
                        a.setWeights(thalassemiaFEMALE.getWeights());
                        a.setConditions(thalassemiaFEMALE.getConditions());
                        break;
                    case IRON_DEFICIENCY_ANEMIA_MALE:
                        a.setWeights(ironDeficiencyMALE.getWeights());
                        a.setConditions(ironDeficiencyMALE.getConditions());
                        break;
                    case IRON_DEFICIENCY_ANEMIA_FEMALE:
                        a.setWeights(ironDeficiencyFEMALE.getWeights());
                        a.setConditions(ironDeficiencyFEMALE.getConditions());
                        break;
                    case MEGALOBLASTIC_ANEMIA_MALE:
                        a.setWeights(megaloblasticMALE.getWeights());
                        a.setConditions(megaloblasticMALE.getConditions());
                        break;
                    case MEGALOBLASTIC_ANEMIA_FEMALE:
                        a.setWeights(megaloblasticFEMALE.getWeights());
                        a.setConditions(megaloblasticFEMALE.getConditions());
                        break;
                    case APLASTIC_ANEMIA_MALE:
                        a.setWeights(aplasticMALE.getWeights());
                        a.setConditions(aplasticMALE.getConditions());
                        break;
                    case APLASTIC_ANEMIA_FEMALE:
                        a.setWeights(aplasticFEMALE.getWeights());
                        a.setConditions(aplasticFEMALE.getConditions());
                        break;
                    case CHRONIC_DISEASE_ANEMIA_MALE:
                        a.setWeights(chronicDiseaseMALE.getWeights());
                        a.setConditions(chronicDiseaseMALE.getConditions());
                        break;
                    case CHRONIC_DISEASE_ANEMIA_FEMALE:
                        a.setWeights(chronicDiseaseFEMALE.getWeights());
                        a.setConditions(chronicDiseaseFEMALE.getConditions());
                        break;
                    case POLYCYTHEMIA_MALE:
                        a.setWeights(polycythemiaMALE.getWeights());
                        a.setConditions(polycythemiaMALE.getConditions());
                        break;
                    case POLYCYTHEMIA_FEMALE:
                        a.setWeights(polycythemiaFEMALE.getWeights());
                        a.setConditions(polycythemiaFEMALE.getConditions());
                        break;
                }
            }

            //calculamos los scores para cada anemia
            for(Anemia a : anemiasList) {
                List<Condition> conditionList = a.getConditions();
                //System.out.println(conditionList);
                List<Boolean> booleanList = Utilities.equalsSymptomCondition(symptomsList, conditionList);
                //System.out.println(booleanList);
                //System.out.println(symptomsList);
                Float score = Utilities.getScore(a,booleanList);
                scoresList.add(score);
            }

            //System.out.println(scoresList);

            ClinicalHistory clinicalHistory = new ClinicalHistory(LocalDate.now());
            jdbcClinicalHistoryManager.addClinicalHistory(clinicalHistory, p2.getId());

            //get Id of the clinical history
            ClinicalHistory ch = jdbcClinicalHistoryManager.getClinicalHistory(p2.getId());

            clinicalHistory.setId(ch.getId());
            Utilities.getReport(med, clinicalHistory, p2, scoresList, anemiasList);


        } catch (SQLException e) {
            System.out.println("error");

        }
    }




}
