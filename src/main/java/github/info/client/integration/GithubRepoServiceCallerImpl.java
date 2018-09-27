package github.info.client.integration;

import github.info.client.model.GithubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class GithubRepoServiceCallerImpl implements GithubRepoServiceCaller{
    @Autowired
    GithubServiceGenerator githubServiceGenerator;

    public GithubRepoServiceCallerImpl() {
    }

    public GithubRepo getRepoBasicInfoSync(String owner, String repositoryName){
        GithubRepoService service = githubServiceGenerator.createService(GithubRepoService.class); 
        GithubRepo githubRepo = null;
        try {
            Call<GithubRepo> callSync = service.getRepoInfo(owner, repositoryName);
            Response<GithubRepo> response = callSync.execute();
            githubRepo = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return githubRepo;
    }

}
