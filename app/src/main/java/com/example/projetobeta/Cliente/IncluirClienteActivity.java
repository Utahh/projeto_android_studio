package com.example.projetobeta.Cliente;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetobeta.DbHelper;
import com.example.projetobeta.MainActivity;
import com.example.projetobeta.R;

public class IncluirClienteActivity extends AppCompatActivity {

    private DbHelper base;
    private EditText nome, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_cliente);

        base = new DbHelper(getApplicationContext());
        nome = findViewById(R.id.edNome);
        email = findViewById(R.id.edEmail);
    }

    public void salvarCliente(View view) {
        String nomeCliente = nome.getText().toString();
        String emailCliente = email.getText().toString();

        if (verificarDados(nomeCliente, emailCliente)) {
            Cliente cliente = new Cliente(nomeCliente, emailCliente);
            long resultado = base.salvarCliente(cliente);

            if (resultado != -1) {
                // Cliente cadastrado com sucesso
                exibirPopupCadastroSucesso();
                nome.setText("");
                email.setText("");
            } else {
                // Falha ao cadastrar o cliente
                Toast.makeText(this, "Falha ao cadastrar o cliente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean verificarDados(String nome, String email) {
        if (TextUtils.isEmpty(nome) || nome.length() <= 5 || !email.contains("@")) {
            // Dados incorretos
            Toast.makeText(this, "Nome deve ter mais de 5 caracteres e o e-mail deve conter '@'", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void exibirPopupCadastroSucesso() {
        Toast.makeText(this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void visualizaClientes(View view){
        Intent intent = new Intent(getApplicationContext(), ListarClienteActivity.class);
        startActivity(intent);
    }


        // Adicione este método para voltar à MainActivity ao clicar no botão "btVoltar"
    public void voltarParaMainActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
