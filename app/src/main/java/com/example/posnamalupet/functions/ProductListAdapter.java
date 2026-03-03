package com.example.posnamalupet.functions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.posnamalupet.R;
import com.example.posnamalupet.model.Product;

import java.util.List;

public class ProductListAdapter  extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;

    public ProductListAdapter(@NonNull Context context,List<Product> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList =  productList;

    }
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View  convertView , @Nullable ViewGroup parent)
    {
        View productview = convertView;

        if(productview == null)
        {
            productview = LayoutInflater.from(context).inflate(R.layout.productItemCard, parent, false);
        }
        Product currentProduct = productList.get(position);

        //id
        //name
        //price
        //quantity
        //image


        //set

        return productview;
    }
}
