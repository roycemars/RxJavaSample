package c.mars.rxjavasample;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Constantine Mars on 2/26/15.
 */
public class RxSample {
    private static final String TAG = RxSample.class.getSimpleName();

    public static void runComplete() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("test");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "next: "+s);
            }
        };
    }

    public static void runSimpleWithLambdas() {
        Observable.just("z","y","x").subscribe(s -> Log.d(TAG, "next:"+s));
    }

    public static void runMapSample() {
        Observable.just("z","y","x")
                .map(s -> s.hashCode())
                .map(i -> "hash:"+i)
                .subscribe(s -> Log.d(TAG, "next:"+s));
    }
}
