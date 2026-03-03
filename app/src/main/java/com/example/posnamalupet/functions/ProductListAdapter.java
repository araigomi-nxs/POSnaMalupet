package com.example.posnamalupet.functions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.posnamalupet.R;
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
        if( mode == 1) // inventory_list
        {
            /// initialize addToCart button

        }

        //set
        return productview;
    }
}
