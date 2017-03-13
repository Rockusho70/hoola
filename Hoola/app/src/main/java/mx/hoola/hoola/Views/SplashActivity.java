package mx.hoola.hoola.Views;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Guardar sesión del usuario
        session = new SessionManager(this);

        handler.sendEmptyMessageDelayed(1000,1000);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            session.checkLogin();
            finish();
        }
    };
}
