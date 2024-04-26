package com.example.javaprojekti.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.javaprojekti.Domain.Foods;
import com.example.javaprojekti.Helper.ManagmentCart;
import com.example.javaprojekti.R;
import com.example.javaprojekti.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Foods object;
    private int num=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart= new ManagmentCart(this);
        binding.backBtn.setOnClickListener(v ->finish()); {
            Glide.with(DetailActivity.this)
                    .load(object.getImagePath())
                    .placeholder(R.drawable.margherita) // Placeholder image while loading
                    .centerCrop()
                    .into(binding.pic);

            binding.priceTxt.setText("$"+object.getPrice());
            binding.titleTxt.setText(object.getTitle());
            binding.descriptionTxt.setText(object.getDescription());
            binding.rateTxt.setText(object.getStar()+"Rating");
            binding.ratingBar.setRating((float) object.getStar());
            binding.totalTxt.setText((num*object.getPrice()+"$"));
            binding.plusBtn.setOnClickListener(v12 -> {
                num=num+1;
                binding.numTxt.setText(num+"");
                binding.totalTxt.setText(("$"+num +object.getPrice()));
            });
            binding.minusBtn.setOnClickListener(v1 -> {
                if(num>1){
                    num=num-1;
                    binding.numTxt.setText(num+"");
                    binding.totalTxt.setText("$"+(num+object.getPrice()));
                }
            });
            binding.addBtn.setOnClickListener(v13 -> {
                object.setNumberInCart(num);
                managmentCart.insertFood(object);

            });
        };
    }

    private void getIntentExtra() {
        object= (Foods) getIntent().getSerializableExtra("object");
    }
}