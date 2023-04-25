package com.example.bai5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai5.MainActivity;
import com.example.bai5.R;
import com.example.bai5.adapter.CatAdapter;
import com.example.bai5.adapter.SpinnerAdapter;
import com.example.bai5.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener {
    private CatAdapter catAdapter;
    private Spinner spinner;
    private EditText eName, ePrice, eDes;
    private Button btAdd, btSua;
    private RecyclerView recyclerView;
    private int pcurr;
    private int[] imgs = {R.drawable.images, R.drawable.images, R.drawable.images, R.drawable.images, R.drawable.images, R.drawable.images};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        catAdapter = new CatAdapter((MainActivity) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(catAdapter);
        catAdapter.setCatItemListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(ePrice.getText().toString());
                    Cat cat = new Cat(img, eName.getText().toString(), price, eDes.getText().toString());
                    catAdapter.add(cat);
                } catch (NumberFormatException e) {

                }
            }
        });
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(ePrice.getText().toString());
                    Cat cat = new Cat(img, eName.getText().toString(), price, eDes.getText().toString());
                    catAdapter.update(cat, pcurr);
                    btSua.setVisibility(View.INVISIBLE);
                    btAdd.setVisibility(View.VISIBLE);
                } catch (NumberFormatException e) {

                }
            }
        });
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), imgs);
        spinner.setAdapter(adapter);
        eName = view.findViewById(R.id.eName);
        ePrice = view.findViewById(R.id.eGia);
        eDes = view.findViewById(R.id.eDes);
        btAdd = view.findViewById(R.id.btAdd);
        btSua = view.findViewById(R.id.btSua);
        recyclerView = view.findViewById(R.id.reView);
        btSua.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setVisibility(View.INVISIBLE);
        btSua.setVisibility(View.VISIBLE);
        pcurr = position;
        Cat cat = catAdapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
        eName.setText(cat.getName());
        ePrice.setText(cat.getPrice() + "");
        eDes.setText(cat.getInfor());

    }

}
