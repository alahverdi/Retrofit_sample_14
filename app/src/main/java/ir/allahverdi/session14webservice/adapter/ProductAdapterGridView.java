package ir.allahverdi.session14webservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.allahverdi.session14webservice.FaNum;
import ir.allahverdi.session14webservice.R;
import ir.allahverdi.session14webservice.entity.Product;

public class ProductAdapterGridView extends BaseAdapter implements Filterable {

    private Context context;
    private ArrayList<Product> list;
    private ArrayList<Product> temp;

    public ProductAdapterGridView(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        this.temp = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gridview_items_layout, null);
        }

        Product product = this.list.get(position);

        TextView tv_id_tittle = view.findViewById(R.id.tv_id_tittle);
        TextView tv_toman = view.findViewById(R.id.tv_toman);

        ImageView imageView = view.findViewById(R.id.iv_item);
        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_price = view.findViewById(R.id.tv_price);

        tv_id.setText(FaNum.convert(String.valueOf(product.getId())));
        tv_name.setText(product.getTittle());
        tv_price.setText(FaNum.convert(String.valueOf(product.getPrice())));
        // use picasso library:
        Picasso.get().load(product.getImageId()).into(imageView);

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                ArrayList<Product> filterList = new ArrayList<>();
                for (Product item : temp) {
                    if (item.getTittle().contains(constraint)) {
                        filterList.add(item);
                    }
                }

                filterResults.count = filterList.size();
                filterResults.values = filterList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
