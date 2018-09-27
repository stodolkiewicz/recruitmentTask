package github.info.client.config;

import github.info.client.config.bean.GithubStartPathProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${github.start.path}")
    String githubStartPathValue;

    @Bean
    public GithubStartPathProperty getGithubStartPath(){
        GithubStartPathProperty githubStartPathProperty = new GithubStartPathProperty();
        githubStartPathProperty.setGithubStartPath(githubStartPathValue);

        return githubStartPathProperty;
    }

}
