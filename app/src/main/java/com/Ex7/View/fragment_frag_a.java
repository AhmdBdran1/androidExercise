package com.Ex7.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Ex7.Controler.CountryAdapter;
import com.Ex7.R;



public class fragment_frag_a extends Fragment {

    RecyclerView rvContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_a, container,false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        rvContacts =  view.findViewById(R.id.rvContacts);
        CountryAdapter adapter = new CountryAdapter(this.getContext());
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter.setOnLongClickLisenter(new CountryAdapter.onLongClickLisenter() {
            @Override
            public void onLongClickLisenter(int position) {
                Toast.makeText(getActivity(), "delete successfully", Toast.LENGTH_SHORT).show();
                //delete data
                adapter.removeItem(position);
            }
        });
    }










}