package github.info.client.integration;

import com.google.gson.*;
import github.info.client.model.GithubRepo;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class GithubRepoDeserializer implements JsonDeserializer<GithubRepo> {

    public GithubRepo deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String fullName = jsonObject.get("full_name").getAsString();
        String description = jsonObject.get("description").toString();
        String cloneUrl = jsonObject.get("clone_url").getAsString();
        int stars = jsonObject.get("stargazers_count").getAsInt();

        String createdAtString = jsonObject.get("created_at").getAsString();
        Instant instant = Instant.parse(createdAtString);
        LocalDateTime createdAt = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.systemDefault().getId()));

        GithubRepo githubRepo = new GithubRepo();
        githubRepo.setFullName(fullName);
        githubRepo.setDescription(description);
        githubRepo.setCloneUrl(cloneUrl);
        githubRepo.setStars(stars);
        githubRepo.setCreatedAt(createdAt);

        return githubRepo;
    }
}

