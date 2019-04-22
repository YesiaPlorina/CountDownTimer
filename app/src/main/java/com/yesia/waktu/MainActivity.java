package com.yesia.waktu;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_timer)
    TextView tvTimer;
    @BindView(R.id.btn_start)
    Button btnStart;

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnStart = (Button) this.findViewById(R.id.btn_start);
        tvTimer = (TextView) this.findViewById(R.id.tv_timer);
        countDownTimer = new MyCountDownTimer(startTime, interval);
    }



    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            btnStart.setText("Stop");
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            btnStart.setText("restart");
        }
    }


    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            tvTimer.setText("Time's Up!");
            countDownTimer.cancel();
            timerHasStarted = false;
            btnStart.setText("restart");
        }

        @Override
        public void onTick(long millisUntilFinish) {
            tvTimer.setText("" + millisUntilFinish / 1000);
        }
    }

}
