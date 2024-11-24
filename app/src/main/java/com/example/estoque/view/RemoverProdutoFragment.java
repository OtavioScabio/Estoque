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
import com.example.estoque.controller.IRemove;
//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043
public class RemoverProdutoFragment extends Fragment implements IRemove {

    private ProdutoDatabaseHelper dbHelper;
    private EditText etID;
    private Button btnRemover;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_removerproduto, container, false);

        dbHelper = new ProdutoDatabaseHelper(getContext());
        etID = view.findViewById(R.id.etIremov);
        btnRemover = view.findViewById(R.id.btnRemover);

        btnRemover.setOnClickListener(v -> Remove());
        return view;
    }

    @Override
    public void Remove() {
        int id = Integer.parseInt(etID.getText().toString());

        if (dbHelper.deleteProduto(id)) {
            Toast.makeText(getContext(), "Produto removido!", Toast.LENGTH_SHORT).show();
            etID.setText("");
        } else {
            Toast.makeText(getContext(), "Erro ao remover produto.", Toast.LENGTH_SHORT).show();
            }
        }
    }






