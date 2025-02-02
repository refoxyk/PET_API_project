package com.example.pet_aplication

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


        // Устанавливаем разметку, чтобы можно было найти элементы UI
        setContentView(R.layout.activity_second)

        // Получаем ссылки на элементы после вызова setContentView
        val ed_name = findViewById<EditText>(R.id.name_ed)
        val ed_surname = findViewById<EditText>(R.id.surname_ed)
        val ed_email = findViewById<EditText>(R.id.email_ed)
        val ed_phone = findViewById<EditText>(R.id.phone_ed)
        val ed_address = findViewById<EditText>(R.id.address_ed)
        val bt_submit = findViewById<Button>(R.id.submit_bt)
        val bt_get = findViewById<Button>(R.id.get_bt)

        // Создаем объект для данных


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

        bt_get.setOnClickListener{
            val id = 1
            getClientById(id)
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


    private fun getClientById(clientId: Int) {
        val call = RetrofitInstance.api.getClientById(clientId)

        call.enqueue(object : Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                if (response.isSuccessful) {
                    val client = response.body() // Получаем объект клиента из ответа

                    if (client != null) {
                      
                        val fullName = client.fullName
                        val email = client.email
                        val phoneNumber = client.phoneNumber
                        val address = client.address
                        val createdAt = client.createdAt
                        val clientId = client.klientID

                        var  client = Client(
                             klientID  =clientId ,
                             fullName = fullName,
                             email = email ,
                             address = address ,
                             phoneNumber = phoneNumber,
                             createdAt = createdAt
                        )



                    
                        Log.d("API_RESPONSE", "Full Name: $fullName")
                        Log.d("API_RESPONSE", "Email: $email")
                        Log.d("API_RESPONSE", "Phone: $phoneNumber")
                        Log.d("API_RESPONSE", "Address: $address")
                        Log.d("API_RESPONSE", "Created At: $createdAt")

                      
                        Toast.makeText(this@SecondActivity, "Client Name: $fullName", Toast.LENGTH_LONG).show()

                    } else {
                        Log.e("API_ERROR", "Client not found")
                        Toast.makeText(this@SecondActivity, "Client not found", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.e("API_ERROR", "Error: ${response.code()}")
                    Toast.makeText(this@SecondActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Log.e("API_FAILURE", "Failure: ${t.message}")
                Toast.makeText(this@SecondActivity, "Failed to connect to server!", Toast.LENGTH_LONG).show()
            }
        })
    }

}
