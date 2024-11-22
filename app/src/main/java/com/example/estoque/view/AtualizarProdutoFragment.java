package com.example.estoque.view;

import android.annotation.SuppressLint;
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

import com.example.estoque.R;
import com.example.estoque.controller.ProdutoDatabaseHelper;
import com.example.estoque.controller.IAtualiza;


public class AtualizarProdutoFragment extends Fragment implements IAtualiza {

    private ProdutoDatabaseHelper dbHelper;
    private EditText etID;
    private EditText etNome;
    private EditText etQuantidade;
    private EditText etPreco;
    private Button btnAtualizar;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atualizarproduto, container, false);

        dbHelper = new ProdutoDatabaseHelper(getContext());
        etID = view.findViewById(R.id.etIDatt);
        etNome = view.findViewById(R.id.etNome);
        etQuantidade = view.findViewById(R.id.etQuantidade);
        etPreco = view.findViewById(R.id.etPreco);
        btnAtualizar = view.findViewById(R.id.btnAtualizar);

        btnAtualizar.setOnClickListener(v -> Atualiza());

        return view;
        }

        @Override
        public void Atualiza() {
            int id = Integer.parseInt(etID.getText().toString());
            String nome = etNome.getText().toString();
            int quantidade = Integer.parseInt(etQuantidade.getText().toString());
            double preco = Double.parseDouble(etPreco.getText().toString());

            if (dbHelper.updateProduto(id, nome, quantidade, preco)) {
                Toast.makeText(getContext(), "Produto atualizado!", Toast.LENGTH_SHORT).show();
                etID.setText("");
                etNome.setText("");
                etQuantidade.setText("");
                etPreco.setText("");
            } else {
                Toast.makeText(getContext(), "Erro ao atualizar produto.", Toast.LENGTH_SHORT).show();
            }
         }

        }


