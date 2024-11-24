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
import com.example.estoque.controller.IAtualiza;

//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043
public class AtualizarFornecedorFragment extends Fragment implements IAtualiza {

    private FornecedorDatabaseHelper dbHelper;
    private EditText edtId;
    private EditText edtNome;
    private EditText edtCnpj;
    private EditText edtEstado;
    private EditText edtPais;
    private EditText edtNumeroRegistro;
    private Button btnAtualizar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atualizar_fornecedor, container, false);

        dbHelper = new FornecedorDatabaseHelper(getContext());

         edtId = view.findViewById(R.id.edtFornecedorId);
         edtNome = view.findViewById(R.id.edtNome);
         edtCnpj = view.findViewById(R.id.edtCnpj);
         edtEstado = view.findViewById(R.id.edtEstado);
         edtPais = view.findViewById(R.id.edtPais);
         edtNumeroRegistro = view.findViewById(R.id.edtNumeroRegistro);
         btnAtualizar = view.findViewById(R.id.btnAtualizar);

        btnAtualizar.setOnClickListener(v -> Atualiza());

        return view;
    }

    @Override
    public void Atualiza() {
        String idStr = edtId.getText().toString();
        if (idStr.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, insira o ID!", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);
        String nome = edtNome.getText().toString();
        String cnpj = edtCnpj.getText().toString();
        String estado = edtEstado.getText().toString();
        String pais = edtPais.getText().toString();
        String numeroRegistro = edtNumeroRegistro.getText().toString();


        boolean isUpdatedNacional = dbHelper.updateFornecedorNacional(id, nome, cnpj, estado);

        boolean isUpdatedInternacional = dbHelper.updateFornecedorInternacional(id, nome, pais, numeroRegistro);

        if (isUpdatedNacional || isUpdatedInternacional) {
            Toast.makeText(getContext(), "Fornecedor atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            edtId.setText("");
            edtNome.setText("");
            edtPais.setText("");
            edtNumeroRegistro.setText("");
            edtCnpj.setText("");
            edtEstado.setText("");
        } else {
            Toast.makeText(getContext(), "Fornecedor não encontrado!", Toast.LENGTH_SHORT).show();
        }
    }





}