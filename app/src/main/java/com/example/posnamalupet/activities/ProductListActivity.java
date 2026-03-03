package com.example.posnamalupet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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


        ListView listView = findViewById(R.id.listViewProduct);


        Button addProductButton = findViewById(R.id.btnAddProduct);
        DatabaseHelper databaseHelper =new DatabaseHelper(ProductListActivity.this);

        ProductListAdapter productListAdapter = new ProductListAdapter(ProductListActivity.this,databaseHelper.getAllProducts() , 0);
        listView.setAdapter(productListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ProductListActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
                Log.d("LV", ""+position);
                Intent intent = new Intent(ProductListActivity.this, EditProductPopupActivity.class);
               intent.putExtra("PID",position+1);
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


    }
}