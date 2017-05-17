package utils;

import data.BookRepository;
import data.BookRepositoryRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Initializer {
    public static BookRepositoryRemote lookupRemoteStatefullLibrary() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        final Context context = new InitialContext(jndiProperties);


        final String appName = "";
        final String moduleName = "ejb-remote-server";
        final String distinctName = "";
        final String beanName = BookRepository.class.getSimpleName();

        final String viewClassName = BookRepositoryRemote.class.getName();
        System.out.println("Looking EJB via JNDI ");
        System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

        return (BookRepositoryRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }
}
