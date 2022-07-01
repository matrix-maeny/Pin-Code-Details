package com.matrix_maeny.pincodedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.matrix_maeny.pincodedetails.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private PinCodeAdapter adapter = null;
    private ArrayList<PinCodeModel> list;

    private String searchQuery = "";

    private RequestQueue requestQueue;
    private final Handler handler = new Handler();
    private ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();


    }

    View.OnClickListener goBtnListener = v -> {
        searchPin();
        binding.searchView.setQuery(searchQuery, false);
    };
    SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchPin();

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void initialize() {
        list = new ArrayList<>();
        adapter = new PinCodeAdapter(MainActivity.this, list);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.recyclerView.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        processDialog = new ProgressDialog(MainActivity.this);

        binding.goBtn.setOnClickListener(goBtnListener);
        binding.searchView.setOnQueryTextListener(queryTextListener);
    }

    private void searchPin() {
        if (checkSearchQuery()) {
            binding.searchView.clearFocus();
            new Thread() {
                public void run() {
                    getDataFromPinCode(searchQuery);
                }
            }.start();
        }


    }

    private void getDataFromPinCode(String pinCode) {
        requestQueue.getCache().clear();
        PinCodeAdapter.pinCode = pinCode;

        String url = "http://www.postalpincode.in/api/pincode/" + pinCode;
        dialogStart("Fetching details...", true);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("Status").equals("Error")) {
                        handler.post(() -> {
                            Toast.makeText(MainActivity.this, "Invalid Pin Code", Toast.LENGTH_SHORT).show();
                            processDialog.dismiss();
                        });
                        return;
                    }
                    JSONArray postOfficeArray = response.getJSONArray("PostOffice");
                    String villageName, description, branchType, deliveryStatus, taluk, circle, district, division, region, state, country;


                    for (int i = 0; i < postOfficeArray.length(); i++) {
                        JSONObject object = postOfficeArray.getJSONObject(i);
                        villageName = object.getString("Name");
                        description = object.getString("Description");
                        branchType = object.getString("BranchType");
                        deliveryStatus = object.getString("DeliveryStatus");
                        taluk = object.getString("Taluk");
                        circle = object.getString("Circle");
                        district = object.getString("District");
                        division = object.getString("Division");
                        region = object.getString("Region");
                        state = object.getString("State");
                        country = object.getString("Country");

                        list.add(new PinCodeModel(villageName, description, branchType, deliveryStatus, taluk, circle, district, division, region, state, country));
                        handler.post(() -> adapter.notifyDataSetChanged());
                    }

                    if (!list.isEmpty()) {
                        binding.emptyTv.setVisibility(View.GONE);
                    } else {
                        binding.emptyTv.setVisibility(View.VISIBLE);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(() -> {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Invalid Pin Code", Toast.LENGTH_SHORT).show();
                    });
                }
                dialogStart(null, false);


            }
        }, new Response.ErrorListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.post(() -> {
                    adapter.notifyDataSetChanged();
                    String msg = error.getMessage();
                    if (msg == null) {
                        msg = "Slow network";
                    } else if (msg.contains("UnknownHost")) {
                        msg = "No internet";
                    }
                    Toast.makeText(MainActivity.this, "Error: " + msg, Toast.LENGTH_SHORT).show();
                });
                dialogStart(null, false);
                error.printStackTrace();
            }

        });
        requestQueue.add(jsonObjectRequest);

    }

    private void dialogStart(String msg, boolean shouldEnable) {
        if (shouldEnable) {
            processDialog.setMessage(msg);
            handler.post(() -> {
                processDialog.show();

            });
        } else {
            handler.post(() -> {
                processDialog.dismiss();
                if (!list.isEmpty()) {
                    binding.emptyTv.setVisibility(View.GONE);
                } else {
                    binding.emptyTv.setVisibility(View.VISIBLE);

                }

            });
        }
    }


    private boolean checkSearchQuery() {
        try {
            searchQuery = binding.searchView.getQuery().toString().trim();
            if (!searchQuery.equals("")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Please enter Pin code", Toast.LENGTH_SHORT).show();
        return false;
    }

}