package com.example.pet_aplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pet_aplication.models.Client
import com.example.pet_aplication.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


      
        setContentView(R.layout.activity_second)

      
        val ed_name = findViewById<EditText>(R.id.name_ed)
        val ed_surname = findViewById<EditText>(R.id.surname_ed)
        val ed_email = findViewById<EditText>(R.id.email_ed)
        val ed_phone = findViewById<EditText>(R.id.phone_ed)
        val ed_address = findViewById<EditText>(R.id.address_ed)
        val bt_submit = findViewById<Button>(R.id.submit_bt)
        val bt_next = findViewById<Button>(R.id.next_sec_bt)

        


        bt_submit.setOnClickListener {
            val name = ed_name.text.toString()
            val surname = ed_surname.text.toString()
            val fullName = "$name $surname"
            val email = ed_email.text.toString()
            val phone = ed_phone.text.toString()
            val address = ed_address.text.toString()

            val newClient = Client(

                fullName = fullName,
                email = email,
                phoneNumber = phone,
                address = address,



            )


            createClient(newClient)
        }

        bt_next.setOnClickListener{
            val intent = Intent(this, ThirdActivity::class.java)

            startActivity(intent)
        }


    }

    private fun createClient(client: Client) {
        val call = RetrofitInstance.api.createClient(client)


        call.enqueue(object : Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                if (response.isSuccessful) {
                    val createdClient = response.body()
                    Log.d("API_RESPONSE", "Created Client: $createdClient")
                    Toast.makeText(this@SecondActivity, "Client Created: $createdClient", Toast.LENGTH_LONG).show()

                } else {
                    Log.e("API_ERROR", "Error: ${response.code()}")
                    Toast.makeText(this@SecondActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Log.e("API_FAILURE", "Failure: ${t.message}")
                Toast.makeText(this@SecondActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()

            }
        })
    }




}
