package com.example.estoque;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.estoque.view.AtualizarFornecedorFragment;
import com.example.estoque.view.AtualizarProdutoFragment;
import com.example.estoque.view.CadastrarFornecedorFragment;
import com.example.estoque.view.CadastrarProdutoFragment;
import com.example.estoque.view.ListarFornecedorFragment;
import com.example.estoque.view.ListarProdutoFragment;
import com.example.estoque.view.RemoverFornecedorFragment;
import com.example.estoque.view.RemoverProdutoFragment;


//Otávio Gabriel Ribeiro Scabio - RA: 1110482223043
public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            carregaFragment(bundle);
        }
    }

    private void carregaFragment(Bundle bundle) {
        String tipoOperacao = bundle.getString("tipoOperacao");
        if (tipoOperacao.equals("Cadastrar")){
            fragment = new CadastrarProdutoFragment();
        }
        if (tipoOperacao.equals("Listar")){
            fragment = new ListarProdutoFragment();
        }
        if (tipoOperacao.equals("Atualizar")){
            fragment = new AtualizarProdutoFragment();
        }
        if (tipoOperacao.equals("Remover")){
            fragment = new RemoverProdutoFragment();
        }
        if (tipoOperacao.equals("CadastrarForne")){
            fragment = new CadastrarFornecedorFragment();
        }
        if (tipoOperacao.equals("ListarForne")){
            fragment = new ListarFornecedorFragment();
        }
        if (tipoOperacao.equals("AtualizarForne")){
            fragment = new AtualizarFornecedorFragment();
        }
        if (tipoOperacao.equals("RemoverForne")){
            fragment = new RemoverFornecedorFragment();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if (id == R.id.ICadProd){
            bundle.putString("tipoOperacao", "Cadastrar");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.IConsProd){
            bundle.putString("tipoOperacao", "Listar");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.IAttProd){
            bundle.putString("tipoOperacao", "Atualizar");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.IRemovProd){
            bundle.putString("tipoOperacao", "Remover");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.ICadForne){
            bundle.putString("tipoOperacao", "CadastrarForne");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.IAttForne){
            bundle.putString("tipoOperacao", "AtualizarForne");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.IConsForne){
            bundle.putString("tipoOperacao", "ListarForne");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        if (id == R.id.IRemovForne){
            bundle.putString("tipoOperacao", "RemoverForne");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}