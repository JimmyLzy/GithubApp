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


    public void connect() {
        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);
        ifConnected = true;
        RepositoryService service = new RepositoryService();
        computeFavLanguage(service);
    }

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
