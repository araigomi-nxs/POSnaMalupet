package com.example.posnamalupet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posnamalupet.activities.AddProductPopupActivity;
import com.example.posnamalupet.R;
import com.example.posnamalupet.database.DatabaseHelper;
import com.example.posnamalupet.functions.ProductListAdapter;
import com.example.posnamalupet.model.Product;

public class ProductListActivity extends AppCompatActivity {
    static int searchFilter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int referenceActivity = (int) getIntent().getIntExtra("REFACT",0);


        ListView listView = findViewById(R.id.listViewProduct);


        Button addProductButton = findViewById(R.id.btnAddProduct);
        Button backButton = findViewById(R.id.btnBack);
        Switch switchfilter = findViewById(R.id.switch1);
        switchfilter.setChecked(false);



        if( referenceActivity == 0)
        {
            addProductButton.setVisibility(View.GONE);
        }

        DatabaseHelper databaseHelper =new DatabaseHelper(ProductListActivity.this);

        ProductListAdapter productListAdapter = new ProductListAdapter(ProductListActivity.this,databaseHelper.getAllProducts(searchFilter) , referenceActivity);
        listView.setAdapter(productListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ProductListActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProductListActivity.this, EditProductPopupActivity.class);
                intent.putExtra("PID",position+1);


                DatabaseHelper db =new DatabaseHelper(ProductListActivity.this);
                Product selectedproduct = db.getProduct(position+1);

                startActivity(intent);

            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(ProductListActivity.this, AddProductPopupActivity.class );
               startActivity(intent);

            }
        });

        backButton.setOnClickListener( view -> {
            Intent intent;
            if(referenceActivity == 0)
            {
               intent = new Intent(ProductListActivity.this, BuyerCheckoutListActivity.class);

            }
            else {
                intent = new Intent(ProductListActivity.this, MainActivity.class);
            }
            startActivity(intent);
        });

        switchfilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    searchFilter = 1;



                }
                else
                {
                    searchFilter = 0;

                }
                Intent intent = new Intent( ProductListActivity.this, ProductListActivity.class);
                intent.putExtra("REFACT",1);
                startActivity(intent);
            }
        });



    }
}