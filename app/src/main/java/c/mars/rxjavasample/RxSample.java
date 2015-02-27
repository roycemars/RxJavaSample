package c.mars.rxjavasample;

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Constantine Mars on 2/26/15.
 */
public class RxSample {
    private static final String TAG = RxSample.class.getSimpleName();

    public static void run() {
        Observable.just("z","y","x").subscribe(s -> Log.d(TAG, "next:"+s));
    }
}
