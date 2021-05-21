package com.pizerolabs.pincodelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pizerolabs.pincodelist.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ListView listView;
    ActivityMainBinding binding;
    final ArrayList<CategoryModel> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        button = findViewById(R.id.searchButton);
        editText = findViewById(R.id.editPinCode);

        // recyclerview
        fetchData();


        final CategoryAdapter adapter = new CategoryAdapter(this, categories);


        binding.categoryList.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryList.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityzipcode = editText.getText().toString();
                for (int i = 0; i < categories.size(); i++) {
                    if (cityzipcode == categories.get(i).getCityZip()) {
                        Toast.makeText(MainActivity.this, categories.get(i).getCityName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "PinCode entered is invalid", Toast.LENGTH_SHORT).show();
                    }
                }


            }

        });

    }

    public void fetchData() {
        // Instantiate the RequestQueue.
        // RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://edflow.cladev.com/api/users/zipcodes/" +
                "token-100f8bcd4626d373cade4e832633b5f7";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject object1 = response.get("data");
                        JSONArray array = object1.getJSONArray("list");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object2 = array.getJSONObject(i);
                            String cityName = object2.getString("id");
                            String cityzip = object2.getString("zipcode");
                            categories.add(new CategoryModel(cityName, cityzip));

                        }
                    }
                }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(MainActivity.this, "There was an error in fetching data from API", Toast.LENGTH_SHORT).show();
                    }

                });


        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
    }
}