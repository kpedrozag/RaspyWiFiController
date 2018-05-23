package com.example.ceisutb12.raspywi_ficontroller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun show_toast(st: String)
    {
        //val st = "Conexión fallida.\nRevisa tu conexión a la\nred de la Raspberry."
        Toast.makeText(this, st, Toast.LENGTH_LONG).show()
    }

    fun check_connection(v: View)
    {
        //val address_api = ""
        val base_url = "https://jsonplaceholder.typicode.com/posts/1"  //address_api + "/api/v1/login"
        base_url.httpGet().responseJson{ request, response, result ->
            when(result)
            {
                is Result.Failure ->
                {
                    val s = "Conexión fallida.\nRevisa tu conexión a la\nred de la Raspberry."
                    show_toast(s)
                }
                is Result.Success ->
                {
                    val token = result.get().obj().getString("id")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("token", token)
                    startActivity(intent)
                }
            }
        }
    }
}
