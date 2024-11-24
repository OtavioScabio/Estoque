package com.example.estoque.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.estoque.R;
import com.example.estoque.controller.FornecedorDatabaseHelper;
import com.example.estoque.model.FornecedorInternacional;
import com.example.estoque.model.FornecedorNacional;

import java.util.ArrayList;
import java.util.List;

//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043
public class ListarFornecedorFragment extends Fragment {

    private FornecedorDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_fornecedor, container, false);

        dbHelper = new FornecedorDatabaseHelper(getContext());
        ListView listViewInternacionais = view.findViewById(R.id.lvFornecedoresInter);
        ListView listViewNacionais = view.findViewById(R.id.lvFornecedoresNac);

        List<String> fornecedoresInternacionais = dbHelper.getAllFornecedoresInternacionais();
        ArrayAdapter<String> adapterI = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fornecedoresInternacionais);
        listViewInternacionais.setAdapter(adapterI);

        List<String> fornecedoresNac = dbHelper.getAllFornecedoresNacionais();
        ArrayAdapter<String> adapterN = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fornecedoresNac);
        listViewNacionais.setAdapter(adapterN);

        return view;
    }

}
