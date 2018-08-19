package tests;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

public class testldap {

    private LdapContext ldapCtx;
    private HashMap <String,String> result = new HashMap<>(); 
    private Hashtable<String,String> env = new Hashtable<>();
    private SearchControls searchCtls = new SearchControls();
    //search base
    private String searchBase = "cn=OracleContext,DC=world";
    //attributes to return
    private String[] returnedAttrs ={ "cn","orclNetDescString" };
    //search filter
    private String searchfilter = "(&(objectClass=orclNetService)(cn=*))";                
        
    public void test() throws Exception {
        
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");
        //initialize context
        ldapCtx = new javax.naming.ldap.InitialLdapContext(env,null);                
        searchCtls.setReturningAttributes(returnedAttrs);
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        
        NamingEnumeration<SearchResult> answer = ldapCtx.search(searchBase, searchfilter, searchCtls);
        
        while (answer.hasMoreElements()) {
            SearchResult sr = (SearchResult) answer.next();
            Attributes attrs = sr.getAttributes();
            if (attrs != null) {
                result.put(attrs.get(returnedAttrs[0]).toString(),attrs.get(returnedAttrs[1]).toString());
            }
        }
        ldapCtx.close();
        Set<String> keys = result.keySet();
        for (String k : keys){
            System.out.println(k+":"+result.get(k));
        }
    }
}
