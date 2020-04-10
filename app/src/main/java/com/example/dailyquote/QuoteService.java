package com.example.dailyquote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteService {
    //The specific endpoint of the Chuck Norris API has been called below to ensure that only developer jokes/quotes are retrieved.
    @GET("jokes/random?category=dev")
    Call<Quote> getQuote();

}
