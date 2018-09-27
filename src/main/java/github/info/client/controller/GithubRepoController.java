package github.info.client.controller;

import github.info.client.exception.RepositoryNotFoundException;
import github.info.client.integration.GithubRepoServiceCaller;
import github.info.client.model.GithubRepo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Controller fetching basic info about a given repository of a given github user")
public class GithubRepoController {
    @Autowired
    GithubRepoServiceCaller githubRepoServiceCaller;

    @GetMapping("/repos/{owner}/{repository-name}")
    public GithubRepo getSomeString(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) throws RepositoryNotFoundException {
        GithubRepo githubRepo = githubRepoServiceCaller.getRepoBasicInfoSync(owner, repositoryName);
        if(githubRepo == null){
            throw new RepositoryNotFoundException(owner, repositoryName);
        }
        return githubRepo;
    }
}
