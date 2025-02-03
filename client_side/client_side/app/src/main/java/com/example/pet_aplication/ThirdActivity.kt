package com.example.pet_aplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.example.pet_aplication.models.Client
import com.example.pet_aplication.network.RetrofitInstance

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val tv_name = findViewById<TextView>(R.id.name_tv)
        val tv_email = findViewById<TextView>(R.id.email_tv)
        val tv_phone = findViewById<TextView>(R.id.phoneNumber_tv)
        val tv_address = findViewById<TextView>(R.id.address_tv)
        val tv_createdAt = findViewById<TextView>(R.id.createdAt_tv)
        val et_client_id = findViewById<EditText>(R.id.client_id_et)
        val bt_get = findViewById<Button>(R.id.get_bt)


        bt_get.setOnClickListener{
            val client_id = et_client_id.text.toString()
            if (client_id.isNotEmpty()){
                val id = client_id.toInt()

                getClientById(id){client ->
                    if(client != null){
                        tv_name.setText(client.fullName)
                        tv_email.setText(client.email)
                        tv_phone.setText(client.phoneNumber)
                        tv_address.setText(client.address)
                        tv_createdAt.setText(client.createdAt)

                        Toast.makeText(this, "Client downloaded",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Client don't founded",Toast.LENGTH_LONG).show()
                    }

                }
            }else{
                Toast.makeText(this,"Input ID",Toast.LENGTH_LONG).show()
            }

        }


    }


    private fun getClientById(clientId: Int, callback: (Client?) -> Unit) {
        val call = RetrofitInstance.api.getClientById(clientId)

        call.enqueue(object : Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                callback(null)
            }
        })
    }
}