package github.info.client.controller;

import github.info.client.integration.GithubRepoServiceCaller;
import github.info.client.integration.GithubRepoServiceCallerImpl;
import github.info.client.model.GithubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubRepoController {
    @Autowired
    GithubRepoServiceCaller githubRepoServiceCaller;

    @GetMapping("/repos/{owner}/{repository-name}")
    public GithubRepo getSomeString(@PathVariable String owner, @PathVariable("repository-name") String repositoryName){
        GithubRepo githubRepo = githubRepoServiceCaller.getRepoBasicInfoSync(owner, repositoryName);

        return githubRepo;
    }
}
