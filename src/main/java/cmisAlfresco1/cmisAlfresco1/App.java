package cmisAlfresco1.cmisAlfresco1;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import cmisAlfresco1.cmisAlfresco1.connexion.ConnexionAlfresco;

/**
 * cette classe sert à créer le plan de classement de la CIE dans Alfresco
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
        ConnexionAlfresco connexionAlfresco = new ConnexionAlfresco();
        
        Session session = connexionAlfresco.ConnexionAlfresco("vvvvf", "admin", "admin");
        
        System.out.println("----------------------------Creation De dossier et de sous dossier pour le projet cie---------------------------");
        
        // --------------- CREATION DU REPERTOIRE PRNCIPAL: PROJET CIE ---------------------
        
        String racine = connexionAlfresco.CreationSousDossier(session, "/", "PROJET CIE");
        
  //-----------------RUBRIQUE ORGANISATION ET SOUS RUBRIQUE --------------------------------------
         
        String rubriqueOrganisation =  connexionAlfresco.CreationSousDossier(session, racine , "Organisation"); 
 
        // sous rubrique imprimés vierges

        String impVierge = connexionAlfresco.CreationSousDossier(session, rubriqueOrganisation, "Imprimés vierges");
               
//        connexionAlfresco.CreationSousDossier(session, impVierge , "DI");   
//        connexionAlfresco.CreationSousDossier(session, impVierge, "DA");
//        connexionAlfresco.CreationSousDossier(session, impVierge, "Bon de commande");
//        connexionAlfresco.CreationSousDossier(session, impVierge, "Bon de commande");
//        connexionAlfresco.CreationSousDossier(session, impVierge, "Carnet de congés ou permission");
//        connexionAlfresco.CreationSousDossier(session, impVierge, "Note de frais");
        
        // sous rubrique deplacement
        
        String dplcmt = connexionAlfresco.CreationSousDossier(session, rubriqueOrganisation, "Déplacment");
        
//        connexionAlfresco.CreationSousDossier(session, dplcmt, "Ordre de mission");
        
        // sous rubrique Maitrise documentaire
        
        String md = connexionAlfresco.CreationSousDossier(session, rubriqueOrganisation, "Maîtrise documentaire");
        
//        connexionAlfresco.CreationSousDossier(session, md, "Plan de classement");
//        connexionAlfresco.CreationSousDossier(session, md, "Etudes documentaires");
//        connexionAlfresco.CreationSousDossier(session, md, "Documentations sur l'archivage");

        // sous rubrique Organisation de Service
        
        String orgS = connexionAlfresco.CreationSousDossier(session, rubriqueOrganisation , "Organisation de Service");
        
//        connexionAlfresco.CreationSousDossier(session, orgS, "Organigrammes");
//        connexionAlfresco.CreationSousDossier(session, orgS, "Activités du service(rapports d'activités)");
//        connexionAlfresco.CreationSousDossier(session, orgS, "Planning de congés");
//        connexionAlfresco.CreationSousDossier(session, orgS, "Planning de réunions");
        
        // sous rubrique organisation sociéte
        
        String orgSoc = connexionAlfresco.CreationSousDossier(session, rubriqueOrganisation, "Organisation de Société");
        
//        connexionAlfresco.CreationSousDossier(session, orgSoc, "Organigramme GS2E");
//        connexionAlfresco.CreationSousDossier(session, orgSoc, "Organigramme Groupe");
//        connexionAlfresco.CreationSousDossier(session, orgSoc, "ERANOVE");
//        connexionAlfresco.CreationSousDossier(session, orgSoc, "Conseil d'Administration");
        
 // RUBRIQUE GESTION COMPTABILITE
        
        String gesCom = connexionAlfresco.CreationSousDossier(session, racine, "Gestion Comptabilité");
        
        // sous rubrique Frais de Structure
        
        connexionAlfresco.CreationSousDossier(session, gesCom, "Frais de Structure");
        
        // rubrique budget
       String budget = connexionAlfresco.CreationSousDossier(session, gesCom, "Budget");
       
//       connexionAlfresco.CreationSousDossier(session, budget , "Prévisions budgétaires");
//       connexionAlfresco.CreationSousDossier(session, budget, "Budget notifié");
       
       // rubrique controle bugetaire et suivi analytique
       
       String cbsa = connexionAlfresco.CreationSousDossier(session, gesCom, "Contrôle budgétaire suivi analytique");
       
//       connexionAlfresco.CreationSousDossier(session, cbsa, "Tableau de bord");
//       connexionAlfresco.CreationSousDossier(session, cbsa, "Détail d'écriture analytique");
       
       // rubrique copie commande & Factures
       
       String ccf = connexionAlfresco.CreationSousDossier(session, gesCom, "Copie commande & Factures");
       
//       connexionAlfresco.CreationSousDossier(session, ccf, "Engagements");
//       connexionAlfresco.CreationSousDossier(session, ccf, "Notes de débit interne");
//       connexionAlfresco.CreationSousDossier(session, ccf, "Double Facture Externe");
       
       // sous rubriques note de frais
       
       connexionAlfresco.CreationSousDossier(session, gesCom, "Note de frais");
       
// RUBRIQUE RESSOURCES HUMAINES
       
       String rh = connexionAlfresco.CreationSousDossier(session, racine, "Ressources Humaines");
       
       // sous rubrique confference salaire
       
       String confSalaire = connexionAlfresco.CreationSousDossier(session, rh, "Conférence Salaire");
//       connexionAlfresco.CreationSousDossier(session, confSalaire, "Etat des salaires");
       
       // sous rubrique Dossier individuel agent
       
      String dia = connexionAlfresco.CreationSousDossier(session, rh, "Dossier individuel agent");
      
//      connexionAlfresco.CreationSousDossier(session, dia , "Classement alphabetique des collaborateurs");
      
      // sous rubrique des absences congés
      
      String absConge = connexionAlfresco.CreationSousDossier(session, rh, "Absences congés");
      
//		  connexionAlfresco.CreationSousDossier(session, absConge, "Fiches indiduelles Congés");
//	      connexionAlfresco.CreationSousDossier(session, absConge, "Demandes d'autorisation d'absences & Congés");
      	
      // sous rubrique Personnel temporaire
      
      String personnelT = connexionAlfresco.CreationSousDossier(session, rh, "Personnel temporaire");
      
//      connexionAlfresco.CreationSousDossier(session, personnelT, "Stagiaire");
//      connexionAlfresco.CreationSousDossier(session, ccf, "Interimaire");
//      connexionAlfresco.CreationSousDossier(session, ccf, "CDD");
      
// Rubrique Formation
      
      String formation = connexionAlfresco.CreationSousDossier(session, racine, "Formation");
      
      // sous rubrique plan de formation 
      
      connexionAlfresco.CreationSousDossier(session, formation, "Formation des collaborateurs");
      
      //Intervention de formation
      
      connexionAlfresco.CreationSousDossier(session, formation, "Intervention de formation");
      
// Rubrique de Comités Notes de services compte Rendu
      
      String cnscr = connexionAlfresco.CreationSousDossier(session, racine, "Comités Notes de Services Comptes Rendus");
      
      //sous rubrique notes de services
      
      connexionAlfresco.CreationSousDossier(session, cnscr, "Notes de Services");
      
      // sous rubrique conseil d'administration
      
      connexionAlfresco.CreationSousDossier(session, cnscr, "Conseil d'Administration");
      
      // sous rubrique reunion
      
      connexionAlfresco.CreationSousDossier(session, cnscr, "Réunions");
      
      // sous rubrique Séminaires
      connexionAlfresco.CreationSousDossier(session, cnscr, "Séminaires");
      
      // sous rubrique comité
      connexionAlfresco.CreationSousDossier(session, cnscr, "Comités");
      
 // RUBRIQUE INFORMATIQUE
      
      String inf = connexionAlfresco.CreationSousDossier(session, racine, "Informatique");
      
      // sous rubrique Equipements de matériels
      
      connexionAlfresco.CreationSousDossier(session, inf, "Equipements de matériels");
      
      // sous rubrique Logiciels
      
      connexionAlfresco.CreationSousDossier(session, inf, "Logiciels");
      
      // sous rubrique Burautique
      
      connexionAlfresco.CreationSousDossier(session, inf, "Burautique");
  
 // RUBRIQUE COMMUNICATION
      
      String com = connexionAlfresco.CreationSousDossier(session, racine, "Communication");
      
      // sous rubrique Plan de communication
      
      connexionAlfresco.CreationSousDossier(session, com, "Plan de communication");
      
      // sous rubrique Charte graphique
      
      connexionAlfresco.CreationSousDossier(session, com, "Charte graphique");
      
      // sous rubrique Compilation d'intervention
      
      connexionAlfresco.CreationSousDossier(session, confSalaire, "Compilation d'intervention");
      
      // sous rubrique Support de communication
      
      connexionAlfresco.CreationSousDossier(session, com, "Support de communication");
      
      // sous rubrique Manifestations
      
      connexionAlfresco.CreationSousDossier(session, com, "Manifestations");
      
      // sous rubrique Communiqué de presse 
      
      connexionAlfresco.CreationSousDossier(session, com, "Communiqué de presse");
      
 // RUBRIQUE PUBLICATIONS INTERNES
      
      String pubInt = connexionAlfresco.CreationSousDossier(session, racine, "Publications Internes");
      
      // sous rubrique Editions
      
      connexionAlfresco.CreationSousDossier(session, pubInt, "Editions");
      
      // sous rubrique Editions des Directions & Services
      
      connexionAlfresco.CreationSousDossier(session, pubInt, "Editions des Directions & Services");
      
// RUBRIQUE Relations Groupe ERANOVE
      
      String rge = connexionAlfresco.CreationSousDossier(session, racine, "Relations Groupe ERANOVE");
      
      // sous rubrique Siège Groupe
      
      connexionAlfresco.CreationSousDossier(session, rge, "Siège Groupe");
      
      // sous rubrique Filiales du Groupe
      
      connexionAlfresco.CreationSousDossier(session, rge, "Filiales du Groupe");
      
      // sous rubrique GS2E - SIEGE
      
      connexionAlfresco.CreationSousDossier(session, rge, "GS2E - SIEGE");
      
      // sous rubrique GS2E - DR
      
      connexionAlfresco.CreationSousDossier(session, rge, "GS2E - DR");
      
// RUBRIQUE DES Relations Avec L'Autorité concédante
      
      String ralc = connexionAlfresco.CreationSousDossier(session, racine, "Relations Avec L'Autorité concédante");
      
      // sous rubrique Etat
      
      connexionAlfresco.CreationSousDossier(session, ralc, "Etat");
      
      // sous rubrique Organismes
      
      connexionAlfresco.CreationSousDossier(session, ralc, "Organismes");
      
// RUBRIQUE Suivi de Projets
      
      String suiviProjet = connexionAlfresco.CreationSousDossier(session, racine, "Suivi de Projets");
      
      // sous rubrique Projet 1
      
      connexionAlfresco.CreationSousDossier(session, suiviProjet, "Projet 1");
      
      // sous rubrique Projet 2
      
      connexionAlfresco.CreationSousDossier(session, suiviProjet, "Projet 2");
      
// RUBRIQUE Qualité
      
      String qualite = connexionAlfresco.CreationSousDossier(session, racine, "Qualité");
      
      // sous rubrique Démarche Qualité
      
      connexionAlfresco.CreationSousDossier(session, qualite, "Démarche Qualité");
      
      // sous rubrique Coordination
      
      connexionAlfresco.CreationSousDossier(session, qualite, "Coordination");
      
      // sous rubrique Données d'évaluation
      
      connexionAlfresco.CreationSousDossier(session, qualite, "Données d'évaluation");
      
      // sous rubrique Documents du Système Qualité
      
      connexionAlfresco.CreationSousDossier(session, qualite, "Système Qualité");
      
      // sous rubrique Suivi Qualité(hors rapport d'audit)
      
      connexionAlfresco.CreationSousDossier(session, qualite, "Suivi Qualité(hors rapport d'audit)");
      
      // sous rubrique Tableau de bord
      
      connexionAlfresco.CreationSousDossier(session, qualite, "Tableau de bord");
      
// RUBRIQUE Prévention & Sécurité
      
      String ps = connexionAlfresco.CreationSousDossier(session, racine, "Prévention & Sécurité");
      
      // sous rubrique Diagnostic Hygiène & Sécurité
      
      connexionAlfresco.CreationSousDossier(session, ps, "Diagnostic Hygiène & Sécurité");
      
      // sous rubrique Rapport d'Audit Sécurité
      
      connexionAlfresco.CreationSousDossier(session, ps, "Rapport d'Audit Sécurité");
      
      // sous rubrique Dossier Sinistre
      
      connexionAlfresco.CreationSousDossier(session, ps, "Dossier Sinistre");
      
      // sous rubrique Gestion de la prévention sécurité
      
      connexionAlfresco.CreationSousDossier(session, ps, "Gestion de la prévention sécurité");
      
// RUBRIQUE Relations Avec l'Extérieur      
      
      String rae = connexionAlfresco.CreationSousDossier(session, racine, "Relations Avec l'Extérieur");
      
      // sous rubrique Relations Organismes certificateurs
      
      connexionAlfresco.CreationSousDossier(session, rae, "Relations Organismes certificateurs");
      
      // sous rubrique Organismes professionnels
      
      connexionAlfresco.CreationSousDossier(session, rae, "Organismes professionnels");
      
      // sous rubrique Prestations de Services
      
      connexionAlfresco.CreationSousDossier(session, rae, "Prestations de Services");
      
      // sous rubrique Sociétés
      
      connexionAlfresco.CreationSousDossier(session, rae, "Sociétés");
      
// RUBRIQUE Finances
      
      String finance = connexionAlfresco.CreationSousDossier(session, racine, "Finances");
      
      
// RUBRIQUE Droit & Réglementation
      
      String dr = connexionAlfresco.CreationSousDossier(session, racine, "Droit & Réglementation");
      
      // sous rubrique Normes
      
      connexionAlfresco.CreationSousDossier(session, dr, "Normes");
      
      // sous rubrique Réglementation
      
      connexionAlfresco.CreationSousDossier(session, dr, "Réglementation");
      
// RUBRIQUE Journaux & Revues

      String jr = connexionAlfresco.CreationSousDossier(session, racine, "Journaux & Revues");
      
      // sous rubrique Liste des Abonnements
      
      connexionAlfresco.CreationSousDossier(session, jr, "Liste des Abonnements");
      
      // sous rubrique Revues
      
      connexionAlfresco.CreationSousDossier(session, jr, "Revues");
      
      // sous rubrique Revue de presse
      
      connexionAlfresco.CreationSousDossier(session, jr, "Revue de presse");
      
    }
}
