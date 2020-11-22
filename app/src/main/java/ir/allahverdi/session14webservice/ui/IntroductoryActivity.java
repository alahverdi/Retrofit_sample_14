package ir.allahverdi.session14webservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import ir.allahverdi.session14webservice.MainActivity;
import ir.allahverdi.session14webservice.R;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView imageView;
    LottieAnimationView lottie_shoppingCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroductoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2800);
    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        lottie_shoppingCard = findViewById(R.id.lottie_shoppingCard);
    }
}