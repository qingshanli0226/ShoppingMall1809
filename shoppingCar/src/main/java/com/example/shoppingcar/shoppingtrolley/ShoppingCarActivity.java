package com.example.shoppingcar.shoppingtrolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.shoppingcar.R;

@Route(path = "/shoppingCar/ShoppingCarActivity")
public class ShoppingCarActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frag_ll,new ShoppingViewTrolleyFragment());
        fragmentTransaction.commit();

    }
}