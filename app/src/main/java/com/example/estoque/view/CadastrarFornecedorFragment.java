package com.example.estoque.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.estoque.R;
import com.example.estoque.controller.FornecedorDatabaseHelper;
import com.example.estoque.controller.ICadastra;

//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043
public class CadastrarFornecedorFragment extends Fragment implements ICadastra {
    private Spinner spinnerTipoFornecedor;
    private EditText edtCnpj;
    private EditText edtNome;
    private EditText edtEstado;
    private EditText edtPais;
    private EditText edtNumeroRegistro;
    private Button btnSalvar;


    private FornecedorDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_fornecedor, container, false);

        dbHelper = new FornecedorDatabaseHelper(getContext());

         spinnerTipoFornecedor = view.findViewById(R.id.spinnerTipoFornecedor);
         edtNome = view.findViewById(R.id.etNomeForne);
         edtCnpj = view.findViewById(R.id.etCnpjForne);
         edtEstado = view.findViewById(R.id.etEstadoForne);
         edtPais = view.findViewById(R.id.etPaisForne);
         edtNumeroRegistro = view.findViewById(R.id.etNumeroRegistroForne);
         btnSalvar = view.findViewById(R.id.btnSalvarForne);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.tipos_fornecedor,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoFornecedor.setAdapter(adapter);




        spinnerTipoFornecedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) { // Nacional
                    edtCnpj.setVisibility(View.VISIBLE);
                    edtEstado.setVisibility(View.VISIBLE);
                    edtPais.setVisibility(View.GONE);
                    edtNumeroRegistro.setVisibility(View.GONE);
                } else if (position == 2) { // Internacional
                    edtCnpj.setVisibility(View.GONE);
                    edtEstado.setVisibility(View.GONE);
                    edtPais.setVisibility(View.VISIBLE);
                    edtNumeroRegistro.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Não fazer nada
            }
        });


        btnSalvar.setOnClickListener(v -> Cadastra());

        return view;
    }

    @Override
    public void Cadastra() {
        String nome = edtNome.getText().toString();

        if (spinnerTipoFornecedor.getSelectedItemPosition() == 1) { // Nacional
            String cnpj = edtCnpj.getText().toString();
            String estado = edtEstado.getText().toString();

            if (nome.isEmpty() || cnpj.isEmpty()  || estado.isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isInserted = dbHelper.addFornecedorNacional(nome, cnpj, estado);
            if (isInserted) {
                Toast.makeText(getContext(), "Fornecedor Nacional adicionado!", Toast.LENGTH_SHORT).show();
                edtNome.setText("");
                edtCnpj.setText("");
                edtEstado.setText("");
            } else {
                Toast.makeText(getContext(), "Erro ao adicionar fornecedor!", Toast.LENGTH_SHORT).show();
            }

        } else if (spinnerTipoFornecedor.getSelectedItemPosition() == 2) { // Internacional
            String pais = edtPais.getText().toString();
            String numeroRegistro = edtNumeroRegistro.getText().toString();

            if (nome.isEmpty() || pais.isEmpty() || numeroRegistro.isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isInserted = dbHelper.addFornecedorInternacional(nome, pais, numeroRegistro);
            if (isInserted) {
                Toast.makeText(getContext(), "Fornecedor Internacional adicionado!", Toast.LENGTH_SHORT).show();
                edtNome.setText("");
                edtPais.setText("");
                edtNumeroRegistro.setText("");
            } else {
                Toast.makeText(getContext(), "Erro ao adicionar fornecedor!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
