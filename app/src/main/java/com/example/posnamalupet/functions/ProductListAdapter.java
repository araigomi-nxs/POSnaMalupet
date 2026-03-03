package com.example.posnamalupet.functions;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.posnamalupet.R;
import com.example.posnamalupet.activities.BuyerCheckoutListActivity;
import com.example.posnamalupet.activities.ProductListActivity;
import com.example.posnamalupet.database.DatabaseHelper;
import com.example.posnamalupet.model.Product;

import java.util.List;

public class ProductListAdapter  extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;
    private int mode;

    public ProductListAdapter(@NonNull Context context,List<Product> productList, int mode) {
        super(context, 0, productList);
        this.context = context;
        this.productList =  productList;
        this.mode = mode;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View  convertView , @Nullable ViewGroup parent)
    {
        View productview = convertView;

        if(productview == null)
        {
            productview = LayoutInflater.from(context).inflate(R.layout.product_item_card, parent, false);
        }
        Product currentProduct = productList.get(position);

        TextView  productnameTV= productview.findViewById(R.id.tvProduct);
        TextView  pricetv = productview.findViewById(R.id.tvPrice);
        TextView quantitytv= productview.findViewById(R.id.tvQuantity);
        ImageView imageView = productview.findViewById(R.id.imageView2);
        TextView productIdtv= productview.findViewById(R.id.tvProductId);
        Button addToCart = productview.findViewById(R.id.btnAddToCart);

        productnameTV.setText(currentProduct.getName());
        productIdtv.setText(String.valueOf(currentProduct.getId()));
        quantitytv.setText(String.valueOf(currentProduct.getQuantity()));
        pricetv.setText(String.valueOf(currentProduct.getPrice()));
        addToCart.setVisibility(View.GONE);
        if( mode == 0) // buyer
        {
            addToCart.setVisibility(View.VISIBLE);

        }
        Button addtoCartButton = productview.findViewById(R.id.btnAddToCart);


        addtoCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product selectedproduct = productList.get(position);
                DatabaseHelper db = new DatabaseHelper(context);
                if( selectedproduct.getQuantity() >  0)
                {

                    Log.d("productlist", "product name: " + selectedproduct.getName());
                    int reduceProductQuantity = selectedproduct.getQuantity() - 1;
                    selectedproduct.setQuantity(reduceProductQuantity);

                    Product.setCheckoutList(new Product(selectedproduct.getId(),selectedproduct.getName(),selectedproduct.getImage(),selectedproduct.getPrice(),1));
                    //db.editProduct(selectedproduct);

                    Intent intent = new Intent(context, BuyerCheckoutListActivity.class);
                    context.startActivity(intent);

                }

            }
        });

        //set
        return productview;
    }
}
