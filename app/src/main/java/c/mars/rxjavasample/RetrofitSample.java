package c.mars.rxjavasample;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 2/27/15.
 */
public class RetrofitSample {
    public interface GithubApi {
        @GET("/users/{user}")
        Observable<User> user(@Path("user") String user);
    }

    public static class User {
        public String avatar_url;
        public String location;
        public String name;
    }

    public static void run(String name) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
        GithubApi githubApi = restAdapter.create(GithubApi.class);
        Observable<User> user = githubApi.user("c-mars");
        user.subscribe( new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Timber.d("completed");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("error: "+e.getMessage());
            }

            @Override
            public void onNext(User user) {
                Timber.d("user: "+user.name+", country="+user.location+", avatar="+user.avatar_url);
            }
        });

        Timber.d("retrofit run");

    }
}
