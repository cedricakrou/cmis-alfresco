package cmisAlfresco1.cmisAlfresco1.connexion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class ConnexionRepertoire
{
	private Logger log = Logger.getLogger(ConnexionAlfresco.class.getName());
	
	private String urlAtompub = "http://localhost:8180/alfresco/api/-default-/public/cmis/versions/1.1/atom";
	
	private Map<String, Session> listeConnexions = new HashMap<String, Session>();
	
	public Session ConnexionRepertoireSpecifique(String nomConnexion, String nomUtilisateur, String mdp)
	{
		Session listeSession = listeConnexions.get(nomConnexion);
		
		if (listeSession == null) 
		{
			SessionFactory creationSession = SessionFactoryImpl.newInstance();
			
			Map<String , String> parametreSession = new HashMap<String, String>();
			
			parametreSession.put(SessionParameter.ATOMPUB_URL, urlAtompub );
			parametreSession.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			parametreSession.put(SessionParameter.COMPRESSION, "true");
			parametreSession.put(SessionParameter.USER, nomUtilisateur);
			parametreSession.put(SessionParameter.PASSWORD, mdp);
			
			List<Repository> listeRepertoire = creationSession.getRepositories(parametreSession);
			
			parametreSession.put(SessionParameter.REPOSITORY_ID, "-default-");
			
			

		} 
		else
		{
			log.info("connexion existante");
		}
		
		return null;
	}

}
