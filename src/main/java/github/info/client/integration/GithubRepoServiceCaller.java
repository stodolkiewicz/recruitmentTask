package github.info.client.integration;


import github.info.client.model.GithubRepo;

public interface GithubRepoServiceCaller {
    GithubRepo getRepoBasicInfoSync(String owner, String repositoryName);
}
