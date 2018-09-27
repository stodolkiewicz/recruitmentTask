package github.info.client.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import github.info.client.model.GithubRepo;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class GithubServiceGenerator {

    public GithubServiceGenerator() {}

    private static final String BASE_URL = "https://api.github.com/";

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(buildGithubRepoConverter())
            .client(httpClient.build());

    private Retrofit retrofit = builder.build();

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private GsonConverterFactory buildGithubRepoConverter(){
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(GithubRepo.class, new GithubRepoDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }
}
