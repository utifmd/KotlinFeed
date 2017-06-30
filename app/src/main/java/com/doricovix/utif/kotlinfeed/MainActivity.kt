package com.doricovix.utif.kotlinfeed

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import net.simplifiedcoding.firstkotlinapp.EndPoints
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    //edittext and spinner
    private var editTextDescName: EditText? = null
    private var spinnerImage: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting it from xml
        editTextDescName = findViewById(R.id.editTextDescName) as EditText
        spinnerImage = findViewById(R.id.spinnerImage) as Spinner

        //adding a click listener to button
        findViewById(R.id.buttonAddDesc).setOnClickListener { addDesc() }

        //in the second button click
        //opening the activity to display all the Desc
        findViewById(R.id.buttonViewDesc).setOnClickListener {
            val intent = Intent(applicationContext, ViewDataActivity::class.java)
            startActivity(intent)
        }
    }

    //adding a new record to database
    private fun addDesc() {
        //getting the record values
        val name = editTextDescName?.text.toString()
        val image = spinnerImage?.selectedItem.toString()

        //creating volley string request
        val stringRequest = object : StringRequest(Request.Method.POST, EndPoints.URL_ADD_DATA,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                    }
                }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("description", name)
                params.put("image", image)
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
}
