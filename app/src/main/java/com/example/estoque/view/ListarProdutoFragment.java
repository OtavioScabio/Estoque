package com.example.estoque.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.estoque.R;
import com.example.estoque.controller.ProdutoDatabaseHelper;

import java.util.List;


public class ListarProdutoFragment extends Fragment {

    private ProdutoDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listarproduto, container, false);

        dbHelper = new ProdutoDatabaseHelper(getContext());
        ListView listView = view.findViewById(R.id.listView2);

        List<String> produtos = dbHelper.getAllProdutos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, produtos);
        listView.setAdapter(adapter);

        return view;
    }
}