package com.example.estoque.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estoque.R;
import com.example.estoque.controller.ProdutoDatabaseHelper;
import com.example.estoque.controller.ICadastra;


//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043

public class CadastrarProdutoFragment extends Fragment implements ICadastra {

    private View view;

    private EditText etNome;
    private EditText etQuantidade;
    private EditText etPreco;
    private Button btnCadastrar;
    private TextView tvRespCadastrar;
    private ProdutoDatabaseHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cadastrarproduto, container, false);
        etNome = view.findViewById(R.id.etNome);
        etQuantidade = view.findViewById(R.id.etQuantidade);
        etPreco = view.findViewById(R.id.etPreco);
        btnCadastrar = view.findViewById(R.id.btnCadastrar);
        tvRespCadastrar = view.findViewById(R.id.tvRespCadastrarItem);
        dbHelper = new ProdutoDatabaseHelper(getContext());

        btnCadastrar.setOnClickListener(op -> Cadastra());

        return view;
    }

    @Override
    public void Cadastra() {
        String nome = etNome.getText().toString();
        String quantidadeStr = etQuantidade.getText().toString();
        String precoStr = etPreco.getText().toString();

        if (nome.isEmpty() || quantidadeStr.isEmpty() || precoStr.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantidade = Integer.parseInt(quantidadeStr);
        double preco = Double.parseDouble(precoStr);


        boolean isInserted = dbHelper.addProduto(nome, quantidade, preco);

        if (isInserted) {
            Toast.makeText(getContext(), "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show();
            etNome.setText("");
            etQuantidade.setText("");
            etPreco.setText("");
        } else {
            Toast.makeText(getContext(), "Erro ao adicionar o produto!", Toast.LENGTH_SHORT).show();
        }


    }
}