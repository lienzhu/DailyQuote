package com.example.dailyquote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteService {

    @GET("jokes/random?category=dev")
    Call<Quote> getQuote();

}
