package com.deepaliverma.networkingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val id = intent.getStringExtra("ID")

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client.api().getUsersbyId(id ?: "") }
            if (response.isSuccessful) {
                response.body()?.let {
                    textView.text = it.name
                    textView1.text = it.login
                    Picasso.get().load(it.avatarUrl).into(imageView);
                }
            }
        }

    }
}