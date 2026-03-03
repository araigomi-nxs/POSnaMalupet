package com.example.posnamalupet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posnamalupet.R;
import com.example.posnamalupet.database.DatabaseHelper;
import com.example.posnamalupet.model.Product;

public class EditProductPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_product_popup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        DatabaseHelper db = new DatabaseHelper(this);
        EditText editProductName = findViewById(R.id.etProductName);
        EditText editPrice = findViewById(R.id.etPrice);
        EditText editQuantity = findViewById(R.id.etQuantity);
        ImageView editImage = findViewById(R.id.ivImage);
        Button editButton = findViewById(R.id.btnUpdate);
        Button deleteButton = findViewById(R.id.btnDelete);
        int productId = (int) getIntent().getIntExtra("productId",0);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(
                        productId,
                        editProductName.getText().toString().trim(),
                        editImage.getId(),
                        Double.parseDouble(editPrice.getText().toString()),
                        Integer.parseInt(editQuantity.getText().toString())
                );
                db.editProduct(product);
                Intent intent = new Intent(EditProductPopupActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteProduct(productId);
                Intent intent = new Intent(EditProductPopupActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });
        db.close();
    }
}