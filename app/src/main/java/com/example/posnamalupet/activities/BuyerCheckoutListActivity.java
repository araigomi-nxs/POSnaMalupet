package com.example.posnamalupet.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posnamalupet.R;
import com.example.posnamalupet.database.DatabaseHelper;
import com.example.posnamalupet.functions.ProductListAdapter;
import com.example.posnamalupet.model.Product;

import java.util.List;

public class BuyerCheckoutListActivity extends AppCompatActivity {
    ListView listViewCheckOutList;
    TextView totalitemstv, totalpricetv;
    Button buttonAddProduct;
    Button buttonCheckout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.buyer_checkout_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewCheckOutList = findViewById(R.id.lvCheckOutList);
        buttonAddProduct = findViewById(R.id.btnAddproduct);
        buttonCheckout = findViewById(R.id.btnCheckout);
        totalitemstv = findViewById(R.id.totalView);
        totalpricetv = findViewById(R.id.totalView2);


        DatabaseHelper db= new DatabaseHelper(this);
        List<Product> checkOutProduct = Product.getAllCheckout();
        //intialize templist



        listViewCheckOutList.setAdapter(new ProductListAdapter(this,checkOutProduct,1));

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerCheckoutListActivity.this, ProductListActivity.class);
                intent.putExtra("REFACT", 0);
                startActivity(intent);
            }
        });
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BuyerCheckoutListActivity.this, "SUCCESSFULLY CHECKOUT", Toast.LENGTH_SHORT).show();

                for ( Product product : Product.getTemporaryList())
                {
                    Toast.makeText(BuyerCheckoutListActivity.this, "inside CHECKOUT", Toast.LENGTH_SHORT).show();
                    Log.d("productlist", "product name: " + product.getName()+ product.getQuantity());
                    db.editProduct(product);
                }
                Product.getAllCheckout().clear();

;

            }
        });
    }
}