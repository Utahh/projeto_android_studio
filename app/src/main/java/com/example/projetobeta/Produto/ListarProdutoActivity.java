package com.example.projetobeta.Produto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.projetobeta.DbHelper;
import com.example.projetobeta.R;

public class ListarProdutoActivity extends AppCompatActivity {

    private ArrayAdapter<Produto> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produto);

        ListView lista = findViewById(R.id.listViewProduto);
        DbHelper base = new DbHelper(getApplicationContext());
        arrayAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, base.consultarProduto());
        lista.setAdapter(arrayAdapter);

        // Adicionar ouvinte de clique ao ListView
        lista.setOnItemClickListener((adapterView, view, position, id) -> {
            // Obter o cliente selecionado
            Produto produtoSelecionado = (Produto) adapterView.getItemAtPosition(position);

            // Criar e exibir um AlertDialog personalizado para editar os dados
            showEditDialog(produtoSelecionado);
        });

        // Encontrar o botão pelo ID
        Button btVoltar = findViewById(R.id.btVoltar);

        // Adicionar um ouvinte de clique ao botão
        btVoltar.setOnClickListener(v -> {
            // Criar uma intent para ir para a IncluirClienteActivity
            Intent intent = new Intent(ListarProdutoActivity.this, IncluirProdutoActivity.class);

            // Iniciar a atividade
            startActivity(intent);

            // Finalizar a atividade atual (opcional, dependendo do comportamento desejado)
            finish();
        });
    }

    private void showEditDialog(Produto produto) {
        // Criar um LayoutInflater para inflar o layout personalizado do AlertDialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_produto, null);

        // Encontrar os EditTexts no layout personalizado
        EditText editDescricao = dialogView.findViewById(R.id.editDescricao);
        EditText editQuantidade = dialogView.findViewById(R.id.editQuantidade);
        EditText editValor = dialogView.findViewById(R.id.editValor);

        // Preencher os EditTexts com os dados atuais do produto
        editDescricao.setText(produto.getDescricao());
        editQuantidade.setText(String.valueOf(produto.getQuantidade()));
        editValor.setText(String.valueOf(produto.getValor()));

        // Criar um AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Editar Produto")
                .setPositiveButton("Salvar", (dialog, which) -> {
                    // Obter os novos valores do EditText
                    String novaDescricao = editDescricao.getText().toString();
                    String novaQuantidade = editQuantidade.getText().toString();
                    String novoValor = editValor.getText().toString();

                    // Atualizar os dados do produto
                    produto.setDescricao(novaDescricao);
                    produto.setQuantidade(Integer.valueOf(novaQuantidade));
                    produto.setValor(Double.valueOf(novoValor));

                    // Atualizar o ArrayAdapter para refletir as alterações
                    arrayAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancelar a edição
                    }
                });

        // Exibir o AlertDialog
        builder.create().show();
    }

}