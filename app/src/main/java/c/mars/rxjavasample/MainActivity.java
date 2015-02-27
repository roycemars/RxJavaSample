package c.mars.rxjavasample;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.text)
    TextView textView;

    @InjectView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setup tools
        ButterKnife.inject(this);
        Timber.plant(new Timber.DebugTree());

//        run pure rxjava sample(s) - results in log
        RxSample.runMapSample();

        Activity activity = this;
//        run retrofit sample - get user info from github api
        RetrofitSample.run("c-mars", new RetrofitSample.ResultViewer() {
            @Override
            public void display(String text, String imagerUrl) {
//                display downloaded info in ui - text in textview, image - in imageview after downloading with picasso
                textView.setText(text);

                Picasso.with(activity).load(imagerUrl)
                        .placeholder(R.drawable.empty)
                        .error(R.drawable.agt_action_fail1)
                        .into(imageView);
                Timber.d("picasso done");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
