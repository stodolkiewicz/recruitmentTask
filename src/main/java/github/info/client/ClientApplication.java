package github.info.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import github.info.client.integration.GithubRepoDeserializer;
import github.info.client.integration.GithubRepoService;
import github.info.client.model.GithubRepo;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.github.com/")
				.addConverterFactory(buildGsonConverter())
				.client(httpClient.build())
				.build();


		GithubRepoService service = retrofit.create(GithubRepoService.class);
		Call<GithubRepo> callSync = service.getRepoInfo("stodolkiewicz", "cryptofire");

		try {
			Response<GithubRepo> response = callSync.execute();
			GithubRepo githubRepo = response.body();
			System.out.println("githubREpo is:  " + githubRepo);
		} catch (Exception ex) {
			System.out.println("Oh no!");
		}
	}

	private static GsonConverterFactory buildGsonConverter(){
		GsonBuilder gsonBuilder = new GsonBuilder();

		// Adding custom deserializers
		gsonBuilder.registerTypeAdapter(GithubRepo.class, new GithubRepoDeserializer());
		Gson myGson = gsonBuilder.create();

		return GsonConverterFactory.create(myGson);
	}
}
