package com.example.estoque.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.estoque.controller.FornecedorDatabaseHelper;
import com.example.estoque.R;
import com.example.estoque.controller.IRemove;
//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043
public class RemoverFornecedorFragment extends Fragment implements IRemove {

    private FornecedorDatabaseHelper dbHelper;
    private EditText edtFornecedorId;
    private Button btnExcluir;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remover_fornecedor, container, false);

        dbHelper = new FornecedorDatabaseHelper(getContext());
        edtFornecedorId = view.findViewById(R.id.edtFornecedorId);
        btnExcluir = view.findViewById(R.id.btnExcluir);

        btnExcluir.setOnClickListener(v -> Remove());

            return view;
        }

    @Override
    public void Remove() {
        String idStr = edtFornecedorId.getText().toString();
        if (idStr.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, insira o ID!", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);

        boolean isDeletedNacional = dbHelper.deleteFornecedorNacional(id);
        boolean isDeletedInternacional = dbHelper.deleteFornecedorInternacional(id);

        if (isDeletedNacional || isDeletedInternacional) {
            Toast.makeText(getContext(), "Fornecedor excluído com sucesso!", Toast.LENGTH_SHORT).show();
            edtFornecedorId.setText("");
        } else {
            Toast.makeText(getContext(), "Fornecedor não encontrado!", Toast.LENGTH_SHORT).show();
        }

    }
}


