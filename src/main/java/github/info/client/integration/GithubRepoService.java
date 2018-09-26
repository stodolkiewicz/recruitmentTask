package github.info.client.integration;

import github.info.client.model.GithubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubRepoService {

    @GET("/repos/{owner}/{repository-name}")
    Call<GithubRepo> getRepoInfo(@Path("owner") String owner, @Path("repository-name") String repositoryName);

}
