package com.example.projetobeta.Produto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetobeta.DbHelper;
import com.example.projetobeta.R;

public class IncluirProdutoActivity extends AppCompatActivity {

    private DbHelper base;
    private EditText descricao, quantidade, valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_produto);

        base = new DbHelper(getApplicationContext());
        descricao = findViewById(R.id.edDescricao);
        quantidade = findViewById(R.id.edQuantidade);
        valor = findViewById(R.id.edValor);
    }

    public void salvarProduto(View view) {
        String descricaoProduto = descricao.getText().toString();
        String quantidadeProdutoStr = quantidade.getText().toString();
        String valorProdutoStr = valor.getText().toString();

        try {
            Integer quantidadeProduto = Integer.parseInt(quantidadeProdutoStr);
            Double valorProduto = Double.parseDouble(valorProdutoStr);

            Produto produto = new Produto(descricaoProduto, quantidadeProduto, valorProduto);
            long resultado = base.salvarProduto(produto);

            if (resultado != -1) {
                // Produto cadastrado com sucesso
                exibirPopupCadastroSucesso();
                descricao.setText("");
                quantidade.setText("");
                valor.setText("");
            } else {
                // Falha ao cadastrar o produto
                Toast.makeText(this, "Falha ao cadastrar o produto", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            // Lidar com a exceção se a conversão falhar
            e.printStackTrace();
            Toast.makeText(this, "Erro ao converter quantidade ou valor para número", Toast.LENGTH_SHORT).show();
        }
    }

    private void exibirPopupCadastroSucesso() {
        Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    // Adicione este método para ir para ListaProdutoActivity ao clicar no botão "btAtualizar"
    public void irParaListaProdutos(View view) {
        Intent intent = new Intent(getApplicationContext(), ListarProdutoActivity.class);
        startActivity(intent);
    }
}
