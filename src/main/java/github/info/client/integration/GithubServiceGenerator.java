package github.info.client.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import github.info.client.config.bean.GithubStartPathProperty;
import github.info.client.model.GithubRepo;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
@Configurable
public class GithubServiceGenerator {
    private Retrofit retrofit;

    @Autowired
    public GithubServiceGenerator(GithubStartPathProperty githubStartPathProperty) {
        String BASE_URL = githubStartPathProperty.getGithubStartPath();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(buildGithubRepoConverter())
                .client(httpClient.build());
        retrofit = builder.build();
    }

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
