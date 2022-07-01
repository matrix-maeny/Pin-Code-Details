package com.matrix_maeny.pincodedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.matrix_maeny.pincodedetails.databinding.ActivityDetailsBinding;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle(PinCodeAdapter.pinCode);
        PinCodeModel model = PinCodeAdapter.dataModel;


        if (model != null) {
            binding.vNameTv.setText(model.getVillageName());

            if (model.getDescription().length() > 0) {
                binding.descriptionTv.setText(model.getDescription());

            } else {
                binding.descLayout.setVisibility(View.GONE);
            }

            binding.deliveryStatusTv.setText(model.getDeliveryStatus());
            binding.talukTv.setText(model.getTaluk());
            binding.circleTv.setText(model.getCircle());
            binding.districtTv.setText(model.getDistrict());
            binding.divisionTv.setText(model.getDivision());
            binding.regionTv.setText(model.getRegion());
            binding.stateTv.setText(model.getState());
            binding.countryTv.setText(model.getCountry());
        }
    }
}