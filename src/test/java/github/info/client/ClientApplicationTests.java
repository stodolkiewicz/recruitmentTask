package github.info.client;

import github.info.client.controller.GithubRepoController;
import github.info.client.exception.RepositoryNotFoundException;
import github.info.client.model.GithubRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.*;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {
	private static final String OWNER = "stodolkiewicz";
	private static final String CORRECT_REPOSITORY_NAME = "cryptofire";
	private static final String CORRECT_FULL_NAME = "stodolkiewicz/cryptofire";
	private static final String CORRECT_DESCRIPTION = "null";
	private static final String CORRECT_CLONE_URL = "https://github.com/stodolkiewicz/cryptofire.git";
	private static final int CORRECT_STARS = 0;

	private static final String INCORRECT_REPOSITORY_NAME = "IDoNotExist";

	@Autowired
	GithubRepoController repoController;

	@Test
	public void getGithubRepoInfo_GivenExistingOwnerAndRepository_ShouldReturnCorrectData() throws RepositoryNotFoundException {
		//when
		GithubRepo githubRepoInfo = repoController.getGithubRepoInfo(OWNER, CORRECT_REPOSITORY_NAME);

		//then
		assertTrue(githubRepoInfo.getFullName().equals(CORRECT_FULL_NAME) );
		assertTrue(githubRepoInfo.getDescription().equals(CORRECT_DESCRIPTION) );
		assertTrue(githubRepoInfo.getCloneUrl().equals(CORRECT_CLONE_URL) );
		assertEquals(githubRepoInfo.getStars(), CORRECT_STARS );
	}

	@Test(expected = RepositoryNotFoundException.class)
	public void getGithubRepoInfo_GivenExistingOwnerAndNotExistingRepository_ShouldThrowRepositoryNotFoundException() throws RepositoryNotFoundException {
		//when
		GithubRepo githubRepoInfo = repoController.getGithubRepoInfo(OWNER, INCORRECT_REPOSITORY_NAME);
	}

}
