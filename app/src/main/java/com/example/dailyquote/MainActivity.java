package com.example.dailyquote;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private Quote mQuote;
    Button quoteButton;
    TextView tvQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning xml objects
        quoteButton = findViewById(R.id.quoteButton);
        tvQuote = findViewById(R.id.tvQuote);

        //QuoteButton onClick Actions
        quoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: SUCCESS");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.chucknorris.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d(TAG, "onBuild: SUCCESS");

                QuoteService service = retrofit.create(QuoteService.class);
                Call<Quote> quoteCall = service.getQuote();

                quoteCall.enqueue(new Callback<Quote>() {
                    @Override
                    public void onResponse(Call<Quote> call, Response<Quote> response) {
                        if(response.isSuccessful()) {
                            Log.d(TAG, "onResponse: SUCCESS");

                            Quote quotes = response.body();
                            tvQuote.setText(quotes.getValue());

                        }else{
                            Log.d(TAG, "onResponse: ERROR IS" + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Quote> call, Throwable t) {
                        Log.d(TAG, "onFailure: ON FAILURE IS:" + t.getLocalizedMessage());
                    }
                });


            }
        });


    }
}
