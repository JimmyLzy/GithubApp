import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by JimmyLiu on 29/01/2017.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        System.out.println("Please enter GitHub Username: ");
        if(scanner.hasNext()) {
            username = scanner.next();
        }
        System.out.println("Please enter password: ");
        if(scanner.hasNext()) {
            password = scanner.next();
        }
        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);
        RepositoryService service = new RepositoryService();
        try {
            for (Repository repo : service.getRepositories(username)) {
                System.out.println(repo.getName() + " Language: " + repo.getLanguage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
