package cmisAlfresco1.cmisAlfresco1.connexion;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.PropertyId;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisUnauthorizedException;

public class ConnexionAlfresco 
{
	private Logger log = Logger.getLogger(ConnexionAlfresco.class.getName());
	
	private String urlAtompub = "http://localhost:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom";
	
	private Map<String, Session>  listeDesConnexions = new ConcurrentHashMap<String, Session>();
	
	/**
	 * cette methode permet de faire le connexion à Alfresco
	 * @param nomConnexion
	 * @param nomUtilisateur
	 * @param motDePasse
	 * @return
	 */
	
	public Session ConnexionAlfresco(String nomConnexion, String nomUtilisateur, String motDePasse)
	{
		Session listeDesSessions = listeDesConnexions.get(nomConnexion);
		
		if(listeDesSessions == null)
		{
			log.info(nomConnexion +", " + "vous n'avez aucune connexionn dejà crée" + "\n" + "\t\t creation d'une nouvelle connexion");
			
			SessionFactory creationSession = SessionFactoryImpl.newInstance();
			
			Map<String , String> parametreSession = new HashMap<String, String>();
			
			parametreSession.put(SessionParameter.ATOMPUB_URL, urlAtompub );
			parametreSession.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			parametreSession.put(SessionParameter.COMPRESSION, "true");
			parametreSession.put(SessionParameter.USER, nomUtilisateur);
			parametreSession.put(SessionParameter.PASSWORD, motDePasse);
			
			List<Repository> listeDesRepertoires = creationSession.getRepositories(parametreSession);
			
			Repository repertoireAlfresco = null;
			
			if(listeDesRepertoires != null && listeDesRepertoires.size() > 0)
			{
				log.info("il y'a " + listeDesRepertoires.size() + "repertoire(s)");
				
				for(int i =0; i < listeDesRepertoires.size(); i++)
				{
					repertoireAlfresco = listeDesRepertoires.get(i);
					
					log.info("le nom du repertoire " + repertoireAlfresco.getId() + " est " + repertoireAlfresco.getName() + "\t\t la version du cmis supporté est" + repertoireAlfresco.getCmisVersionSupported());
					
				}
			}
			else
			{
				log.info("il y'a aucun repertoire dans alfresco");
			}
			
			
			listeDesSessions = repertoireAlfresco.createSession();
			
			listeDesConnexions.put(nomConnexion, listeDesSessions);
			
			
		}
		else
		{
			log.info("vous avez deja une connexion crée");
		}
		
		return listeDesSessions;
	}
	
	/**
	 * cette methode permet d'afficher les sous-repertoires de la racine principale et faire une pagination
	 * @param session
	 */
	
	public void listeSousRepertoire(Session session)
	{
		Folder racineRepertoire = session.getRootFolder();
		ItemIterable<CmisObject> listeSousRepertoire = racineRepertoire.getChildren();
		
		for(CmisObject sousRepertoire: listeSousRepertoire)
		{
			if(sousRepertoire instanceof Document)
			{
				Document metaDatadocument = (Document) sousRepertoire;
				ContentStream contenuDocument = metaDatadocument.getContentStream();
				
				log.info("le nom du document est " + metaDatadocument.getName()+ "\n" +
						"\t - la taille du document est: " + contenuDocument.getLength() + "\n" +
						"\t - le mimeType est: " + contenuDocument.getMimeType() + "\n" +
						"\t - la type de document est: " + metaDatadocument.getType().getDisplayName()
						+ "\n"
						);
			}
			else
			{
				log.info("le nom du sous repertoire " + sousRepertoire.getName() + "\n" +
						"\t - le type du sous repertoire est: " + sousRepertoire.getType().getDisplayName() + "\n");
				
			}
		}
	}
	
	/**
	 * cette methode permet d'afficher les sous-repertoires de la racine principale et faire une pagination
	 * @param session
	 */
	
	public void listeSousRepertoireAvecPagination(Session session)
	{
		Folder racineRepertoire = session.getRootFolder();
		
		int maximiumSousRepertoireParPage = 5;
		OperationContext operationContext = new OperationContextImpl();
		operationContext.setMaxItemsPerPage(maximiumSousRepertoireParPage);
		
		ItemIterable<CmisObject> listeSousRepertoire = racineRepertoire.getChildren(operationContext);
		
		long nombreDePage = Math.abs(listeSousRepertoire.getTotalNumItems() / maximiumSousRepertoireParPage);
		
		int numeroDePage = 1;
		boolean pageTermine = false;
		int compteur = 0;
		
		while(!pageTermine)
		{
			log.info(" \n \t /////////////    Debut page " + numeroDePage +"/" + nombreDePage + "  //////////////////////////////// \t");
			
			ItemIterable<CmisObject> pageActuelle = listeSousRepertoire.skipTo(compteur).getPage();
			
			for(CmisObject repertoire: pageActuelle)
			{
				log.info(repertoire.getName() + " son type est: " + repertoire.getType().getDisplayName());
				compteur++;
			}
			log.info("\t /////////////    Fin page  "  + numeroDePage   +"/"  + nombreDePage + "  //////////////////////////////// \t \n ");
		
			
			numeroDePage++;
			
			if(!pageActuelle.getHasMoreItems())
			{
				pageTermine = true;
			}
		}
	}	
	
	/**
	 * cette methode permet de creer un dossier dans le repertoire -default-
	 * 
	 */
	
	// methode privée permettant de specifier et creer le chemin du dossier dans un repertoifre specifique
	
	private CmisObject VerifieSiDossierExiste(Session session, Folder racineRepertoire, String nomDossier )
	{
		CmisObject cheminOuCreeObjet = null;
		
		try 
		{
			String cheminRepertoire = racineRepertoire.getPath();
			
			if(!cheminRepertoire.endsWith("/"))
			{
				cheminRepertoire += "/";
			}
			
			cheminRepertoire += nomDossier;
			
			// recherche d'objet appartenant au repertoire specifié si il y'a pas d'objet ou le chemin n'existe pas.. il renvoie null
			
			cheminOuCreeObjet = session.getObjectByPath(cheminRepertoire);
		} 
		catch (CmisObjectNotFoundException e)
		{
			// TODO: handle exception
			log.warning("Aucun objet n'a été trouvé");
		}
		
		return cheminOuCreeObjet;
	}
	
	// methode privée permettant de creer l'heure
	
	private String AffichageDate(Date date)
	{
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:z").format(date);
	}
	
	// methode permettant de verifier si on peut creer un objet la dedans
	
	
	// methode qui crea le dossier au repertoire choisi
	
	public Folder CreationDossier(Session session)
	{
		
		String nomDossier = "CmisTest1";
		Folder repertoireOuCreeDossier = session.getRootFolder();
		
		if(repertoireOuCreeDossier.getAllowableActions().getAllowableActions().contains(Action.CAN_CREATE_FOLDER) == false)
		{
			throw new CmisUnauthorizedException("impossible de creer un document dans le repertoire " + repertoireOuCreeDossier.getPath());
		}

		
		Folder nouveauDossier = (Folder) VerifieSiDossierExiste(session, repertoireOuCreeDossier, nomDossier);
		
		if(nouveauDossier == null)
		{
			Map<String, String> metaDataDossier = new HashMap<String, String>();
			
			metaDataDossier.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
			metaDataDossier.put(PropertyIds.NAME, nomDossier);
			
			nouveauDossier = repertoireOuCreeDossier.createFolder(metaDataDossier);
			
			log.info(nouveauDossier.getName() + " a été crée par " + nouveauDossier.getCreatedBy() + " le " + AffichageDate(nouveauDossier.getCreationDate().getTime()) );
		}
		else
		{
			log.severe("Dossier existant: " + nomDossier + " au chemin " + nouveauDossier.getPath());
		}
		
		return nouveauDossier;
	}
	
	
	public String CreationSousDossier(Session session, String DossierOuSeraPlace, String nomObjetACree)
	{
		Folder folder = (Folder) session.getObjectByPath(DossierOuSeraPlace);
		
		Folder nouveauSousDossier = (Folder) VerifieSiDossierExiste(session, folder, nomObjetACree);
		
		if(nouveauSousDossier == null)
		{
			Map<String, String> parametreSousDossier = new HashMap<String, String>();
			
			parametreSousDossier.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
			parametreSousDossier.put(PropertyIds.NAME, nomObjetACree);
			
			nouveauSousDossier = folder.createFolder(parametreSousDossier);
			
			log.info("le dossier " + nomObjetACree + " dans le repertoire " + nouveauSousDossier.getId());
		}
		else
		{
			log.info("le dossier exite deja: " + nouveauSousDossier.getPath());
		}
		
		
		return nouveauSousDossier.getPath();
	}
	
	
	
	/**
	 * methode permettant de creer un nouveau document
	 * @param session
	 * @param repertoireOuLeDocumentSeraCree
	 * @return
	 */
	
	public Document creationDocument(Session session, String DossierOuSeraCreeDocument)
	{
		Folder repertoireOuLeDocumentSeraCree = (Folder) session.getObjectByPath(DossierOuSeraCreeDocument);
		
		String nomDocument = "CmisTestDocument1";
		
		Document nouveauDocument = (Document) VerifieSiDossierExiste(session, repertoireOuLeDocumentSeraCree, nomDocument);
		
		if(nouveauDocument == null)
		{
			
			if(repertoireOuLeDocumentSeraCree.getAllowableActions().getAllowableActions().contains(Action.CAN_CREATE_DOCUMENT) == false)
			{
				throw new CmisUnauthorizedException("impossible de creer un document dans le repertoire " + repertoireOuLeDocumentSeraCree.getPath());
			}

			Map<String, String> metaDataDocument = new HashMap<String, String>();
			
			metaDataDocument.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
			metaDataDocument.put(PropertyIds.NAME, nomDocument);
			
		
			
			// creation de document
			
			String document = "document";
			String mimetype = "text/plain; charset=UTF-8";
			
			byte[] bytes = null;
			try 
			{
				bytes = document.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ByteArrayInputStream input = new ByteArrayInputStream(bytes);
			ContentStream contentStrean = session.getObjectFactory().createContentStream(nomDocument, bytes.length, mimetype, input);
			
			nouveauDocument = repertoireOuLeDocumentSeraCree.createDocument(metaDataDocument, contentStrean, VersioningState.MAJOR);
			
			log.info(nouveauDocument.getName() + " a été crée par " + nouveauDocument.getCreatedBy() + " le " + AffichageDate(nouveauDocument.getCreationDate().getTime()) );
			
		}
		else
		{
			log.severe("Document existant:" + nomDocument);
		}
		
		return nouveauDocument;
	}
	
	/**
	 *  ces methodes permettent de faire des recherches de document dans alfresco et ramene leur chemin absolu
	 */
	
	// cette methode affichant tous les repertoires d'un document
	
	private Folder ListeDesRepertoiresParentsDuDocument(Document document)
	{
		List<Folder> listeDesRepertoires = document.getParents();
		
		Folder premierRepertoire = null;
		
		if(listeDesRepertoires.size() > 0)
		{
			log.info("le document " + document + " a " + listeDesRepertoires.size() + " répertoire(s)" );
			
			if(listeDesRepertoires.size() > 1)
			{
			premierRepertoire = listeDesRepertoires.get(0);
			log.info("le premier repertoire est " + premierRepertoire);
			}
		}
		else
		{
			log.warning(document + " n'a pas de repertoire parent");
		}

		return premierRepertoire;
	}
	
	private String CheminAbsoluDuRepertoireParent(Document document)
	{
		Folder repertoireParent = ListeDesRepertoiresParentsDuDocument(document);
		
		String cheminRepertoire = null;
		
		if ( repertoireParent == null)
		{
			log.severe("il y'a pas de repertoire parent");
		}
		else
		{
			cheminRepertoire = repertoireParent.getPath();
		}
		
		return cheminRepertoire;
	}
	
	private String cheminAbsolutDuDocument(Document document)
	{
		String cheminAbsoluDocument = CheminAbsoluDuRepertoireParent(document);
		
		if(!cheminAbsoluDocument.endsWith("/"))
		{
			cheminAbsoluDocument += "/";
		}
		
		 cheminAbsoluDocument += document;
		 
		 return cheminAbsoluDocument;
	}
	
}
