package com.example.projetobeta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetobeta.Cliente.IncluirClienteActivity;
import com.example.projetobeta.Produto.IncluirProdutoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btCliente = findViewById(R.id.btCliente);
        Button btProduto = findViewById(R.id.btProduto);

        btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Criar uma intent para iniciar a IncluirClienteActivity
                Intent intent = new Intent(MainActivity.this, IncluirClienteActivity.class);

                // Iniciar a atividade
                startActivity(intent);
            }
        });

        btProduto.setOnClickListener(view -> {
            // Criar uma intent para iniciar a IncluirClienteActivity
            Intent intent = new Intent(MainActivity.this, IncluirProdutoActivity.class);

            // Iniciar a atividade
            startActivity(intent);
        });
    }
}
