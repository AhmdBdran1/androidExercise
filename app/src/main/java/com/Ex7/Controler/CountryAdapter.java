package com.Ex7.Controler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Ex7.R;
import com.Ex7.model.Country;
import com.Ex7.model.CountryXMLParser;

import java.util.ArrayList;
import java.util.List;


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class CountryAdapter extends
        RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    //interface
    public interface onLongClickLisenter
    {
        void onLongClickLisenter(int position);
    }
    	     //declare the interface
    private onLongClickLisenter onLongClickLisenter;

    public void setOnLongClickLisenter(CountryAdapter.onLongClickLisenter onLongClickLisenter) {
        this.onLongClickLisenter = onLongClickLisenter;
    }





    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View countryView = inflater.inflate(R.layout.item_country, parent, false);
        //long press delete

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(countryView);

        countryView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int layoutPosition = viewHolder.getLayoutPosition();
                if (onLongClickLisenter!=null)
                {
                    onLongClickLisenter.onLongClickLisenter(layoutPosition);
                }
                return true;
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         // Get the data model based on position
        Country country = mCountries.get(position);
        bindData(country,holder);
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public void removeItem(int position)
    {
         //Operation data source
        mCountries.remove(position);
        //Refresh
        notifyDataSetChanged();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView ImgeView;
        public TextView TextView1;
        public TextView TextView2;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            ImgeView = (ImageView) itemView.findViewById(R.id.imageView2);
            TextView1 = (TextView) itemView.findViewById(R.id.textView3);
            TextView2 = (TextView) itemView.findViewById(R.id.textView4);
        }
    }

    // Store a member variable for the contacts
    private ArrayList<Country> mCountries;
    private Context context;
    // Pass in the contact array into the constructor
    public CountryAdapter(Context context) {
        this.context=context;
        mCountries = CountryXMLParser.parseCountries(context);
    }


//

    public void bindData(Country country,ViewHolder holder){
        int id = context.getResources().getIdentifier(country.getFlag(), "drawable", context.getPackageName());
        ImageView ImgeView = holder.ImgeView;
        ImgeView.setImageResource(id);
        TextView textView = holder.TextView1;
        textView.setText(country.getName());
        TextView TextView2 = holder.TextView2;
        TextView2.setText(country.getShorty());
    }
}
