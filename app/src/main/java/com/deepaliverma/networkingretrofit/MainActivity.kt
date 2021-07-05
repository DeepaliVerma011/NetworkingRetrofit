package com.deepaliverma.networkingretrofit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    val adapter=UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        adapter.onItemClick={
val intent = Intent(this,UserActivity::class.java)
            intent.putExtra("ID",it)
            startActivity(intent)
        }

        userRv.apply{
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main){
             var response= withContext(Dispatchers.IO) {Client.api.getUsers()}
            if(response.isSuccessful){
                response.body()?.let {
                    adapter.swapData(it)
                }
            }
        }



    }

}