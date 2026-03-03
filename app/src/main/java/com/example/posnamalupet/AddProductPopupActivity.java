package com.example.posnamalupet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddProductPopupActivity extends AppCompatActivity {

    Button buttonCancel;
    Button buttonAddProduct;
    ImageView imageViewProduct;
    EditText editTextProductName;
    EditText editTextPrice;
    EditText editTextQuantity;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_product_popup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonCancel = findViewById(R.id.btnCancel);
        buttonAddProduct = findViewById(R.id.btnUpdate);
        imageViewProduct = findViewById(R.id.ivImage);
        editTextProductName = findViewById(R.id.etProductName);
        editTextPrice = findViewById(R.id.etPrice);
        editTextQuantity = findViewById(R.id.etQuantity);

        buttonCancel.setOnClickListener(view -> {
            finish();
        });

    }
}