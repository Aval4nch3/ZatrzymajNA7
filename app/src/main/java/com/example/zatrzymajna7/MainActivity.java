package com.example.zatrzymajna7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView tvCounter, tvMessage, tvHits;
    private Button btnStart, btnStop;

    private Handler handler = new Handler();

    private int number = 0;
    private int hits = 0;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            number = (number + 1) % 10;
            tvCounter.setText(String.valueOf(number));

            handler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        tvMessage = findViewById(R.id.tvMessage);
        tvHits = findViewById(R.id.tvHits);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(v -> {
            number = 0;
            tvCounter.setText("0");
            tvMessage.setText("");

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

            handler.postDelayed(runnable, 500);
        });

        btnStop.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);

            if (number == 7) {
                hits++;
                tvMessage.setText("Trafienie!");
                tvHits.setText("Trafienia: " + hits);
            } else {
                tvMessage.setText("Pudło! Zatrzymano na " + number);
            }

            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        });
    }
}