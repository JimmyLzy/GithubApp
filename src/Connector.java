import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class Connector {

    private final String username;
    private final String password;
    private List<String> favLanguages = new ArrayList<>();
    private boolean ifConnected = false;

    public Connector(String username, String password) {

        this.username = username;
        this.password = password;
    }


    /* Use the username to connect the github repositories and compute the favourite language(s).
     * This method also set the ifConnected to be true once connected to github.
     * However, this method does not handle the requestException when user enter not existing
     * username and password. In addition, the connection does not check the user password. */
    public void connect() {
        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);
        RepositoryService service = new RepositoryService();
        ifConnected = true;
        computeFavLanguage(service);
    }


    /* Loop through each repository and find the most often used language in each repository.
    * Then store the string language and a language counter in the hashMap.
    * Finally loop through the hashMap to obtain the language(s) with the biggest count. */
    private void computeFavLanguage(RepositoryService service) {
        HashMap<String, LanguageCounter> hashMap = new HashMap<>();

        try {
            for (Repository repo : service.getRepositories(username)) {
                String language = repo.getLanguage();
                System.out.println(repo.getName() + " Language: " + language);
                if(language != null) {
                    if (hashMap.containsKey(language)) {
                        LanguageCounter counter = hashMap.get(language);
                        counter.increaseCount();
                    } else {
                        LanguageCounter counter = new LanguageCounter(language, 1);
                        hashMap.put(language, counter);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int biggestCount = 0;
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            LanguageCounter counter = (LanguageCounter) pair.getValue();
            String language = (String) pair.getKey();
            if(counter.getCount() > biggestCount) {
                favLanguages.clear();
                favLanguages.add(language);
                biggestCount = counter.getCount();
            } else if(counter.getCount() == biggestCount){
                favLanguages.add(language);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        for(String s: favLanguages) {
            System.out.println(s);
        }
    }

    public List<String> getFavLanguages() {
        return favLanguages;
    }

    public boolean isConnected() {
        return ifConnected;
    }
}
