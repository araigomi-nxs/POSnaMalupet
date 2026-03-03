package com.example.posnamalupet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posnamalupet.R;
import com.example.posnamalupet.database.DatabaseHelper;
import com.example.posnamalupet.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//NOTE: REMOVE THE COMMENTS AND CALL THE db functions
//    void DebugSomething(){
//        DatabaseHelper db = new DatabaseHelper(this);
//            int id=0;
//            db.addProduct(new Product(id++,"nigga",0,1000, 50));
//            db.addProduct(new Product(id,"fucker",0,250, 10));
//        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        DebugSomething();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button BuyerButton = findViewById(R.id.btnBuyer);
        Button SellerButton = findViewById(R.id.btnSeller);

        BuyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, BuyerCheckoutListActivity.class);
               intent.putExtra("REFACT",0);
                startActivity(intent);
            }
        });
        SellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, ProductListActivity.class);
               intent.putExtra("REFACT",1);
                startActivity(intent);
            }
        });




    }
}