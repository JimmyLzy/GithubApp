import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class Connector {

    private final String username;
    private final String password;
    private String favLanguage = "";

    public Connector(String username, String password) {

        this.username = username;
        this.password = password;
    }


    public void connect() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter GitHub Username: ");
//        if(scanner.hasNext()) {
//            username = scanner.next();
//        }
//        System.out.println("Please enter password: ");
//        if(scanner.hasNext()) {
//            password = scanner.next();
//        }
        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);
        RepositoryService service = new RepositoryService();
        computeFavLanguage(service);
    }

    private void computeFavLanguage(RepositoryService service) {
        HashMap<String, LanguageCounter> hashMap = new HashMap<>();

        try {
            for (Repository repo : service.getRepositories(username)) {
                String language = repo.getLanguage();
                System.out.println(repo.getName() + " Language: " + language);
                if(hashMap.containsKey(language)) {
                    LanguageCounter counter = hashMap.get(language);
                    counter.increaseCount();
                } else {
                    LanguageCounter counter = new LanguageCounter(language, 1);
                    hashMap.put(language, counter);
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
                favLanguage = language;
                biggestCount = counter.getCount();
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println(favLanguage);
    }

    public String getFavLanguage() {
        return favLanguage;
    }
}
