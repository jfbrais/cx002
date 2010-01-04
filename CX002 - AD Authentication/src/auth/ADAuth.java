package auth;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * @author jfbrais
 *
 */
public class ADAuth 
{
	private String domain;
	private String ldapHost;
	private String searchBase;

	public ADAuth()
	{
		this.domain = "transax.net";
		this.ldapHost = "ldap://192.168.1.1:389";
		this.searchBase = "dc=transax,dc=net";
	}

	public ADAuth(String domain, String host, String dn)
	{
		this.domain = domain;
		this.ldapHost = host;
		this.searchBase = dn;
	}

	public Map authenticate(String user, String pass) throws NamingException
	{
		String returnedAtts[] = { "sn", "givenName", "mail" };
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + user	+ "))";

		// Create the search controls
		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);

		// Specify the search scope
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, user + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, pass);

		LdapContext ctxGC = null;

		try
		{
			ctxGC = new InitialLdapContext(env, null);

			// Search objects in GC using filters
			NamingEnumeration answer = ctxGC.search(searchBase, searchFilter, searchCtls);

			while (answer.hasMoreElements())
			{
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();
				Map amap = null;

				if (attrs != null)
				{
					amap = new HashMap();

					NamingEnumeration ne = attrs.getAll();
					while (ne.hasMore()) 
					{
						Attribute attr = (Attribute) ne.next();
						amap.put(attr.getID(), attr.get());
					}
					ne.close();
				}
				return amap;
			}
		} 
		catch (NamingException e) 
		{
			String tempString;
			StringTokenizer tokenizerTemp = new StringTokenizer(e.toString());
			while (tokenizerTemp.hasMoreElements()) 
			{
		         tempString = tokenizerTemp.nextToken();
		         if (tempString.equalsIgnoreCase("AcceptSecurityContext")) 
		         {
		                 while (tokenizerTemp.hasMoreElements()) 
		                 {
	                          tempString = tokenizerTemp.nextToken();
	                          if (tempString.startsWith("773"))
	                        	  System.out.println("Password expired.");
	                        	  //setIsPasswordExpired(true);
	                          if (tempString.startsWith("52e"))
	                        	  System.out.println("Wrong credentials.");
	                        	  //setIsPasswordWrong(true);
	                          if (tempString.startsWith("533"))
	                        	  System.out.println("Account disabled.");			                                   
	                        	  //setIsAccountDisabled(true);
		                 }
		         }
			}
		}
		
		return null;
	  }
}