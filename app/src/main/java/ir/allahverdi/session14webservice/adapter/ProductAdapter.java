package ir.allahverdi.session14webservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.allahverdi.session14webservice.R;
import ir.allahverdi.session14webservice.entity.Product;

public class ProductAdapter extends BaseAdapter {


    private Context context;
    private List<Product> list;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_items_layout, null);
        }

        Product product = this.list.get(position);

        TextView tv_id_tittle = view.findViewById(R.id.tv_id_tittle);
        TextView tv_toman = view.findViewById(R.id.tv_toman);

        ImageView imageView = view.findViewById(R.id.iv_item);
        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_price = view.findViewById(R.id.tv_price);

        tv_id.setText(String.valueOf(product.getId()));
        tv_name.setText(product.getTittle());
        tv_price.setText(String.valueOf(product.getPrice()));
        // use picasso library:
        Picasso.get().load(product.getImageId()).into(imageView);

        return view;
    }
}
