package com.example.projetobeta.Cliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projetobeta.DbHelper;
import com.example.projetobeta.R;

public class ListarClienteActivity extends AppCompatActivity {

    private ArrayAdapter<Cliente> arrayAdapter;
    private DbHelper base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        base = new DbHelper(getApplicationContext());

        ListView listaClientes = findViewById(R.id.listViewClientes);
        arrayAdapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, base.consultarClientes());
        listaClientes.setAdapter(arrayAdapter);

        // Adicionar ouvinte de clique ao ListView
        listaClientes.setOnItemClickListener((adapterView, view, position, id) -> {
            // Obter o cliente selecionado
            Cliente clienteSelecionado = (Cliente) adapterView.getItemAtPosition(position);

            // Exibir o AlertDialog para editar o cliente
            showEditDialog(clienteSelecionado);
        });
    }

    private void showEditDialog(final Cliente cliente) {
        // Criar um LayoutInflater para inflar o layout personalizado do AlertDialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_cliente, null);

        // Encontrar os EditTexts no layout personalizado
        EditText editNome = dialogView.findViewById(R.id.editNome);
        EditText editEmail = dialogView.findViewById(R.id.editEmail);

        // Preencher os EditTexts com os dados atuais do cliente
        editNome.setText(cliente.getNome());
        editEmail.setText(cliente.getEmail());

        // Criar um AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Editar Cliente")
                .setPositiveButton("Salvar", (dialog, which) -> {
                    // Obter os novos valores do EditText
                    String novoNome = editNome.getText().toString();
                    String novoEmail = editEmail.getText().toString();

                    // Atualizar os dados do cliente
                    cliente.setNome(novoNome);
                    cliente.setEmail(novoEmail);

                    // Atualizar o banco de dados
                    int resultado = base.atualizarCliente(cliente);

                    if (resultado > 0) {
                        // Atualizar o ArrayAdapter para refletir as alterações
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(ListarClienteActivity.this, "Cliente atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ListarClienteActivity.this, "Falha ao atualizar o cliente", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    // Cancelar a edição
                    Toast.makeText(ListarClienteActivity.this, "Edição cancelada", Toast.LENGTH_SHORT).show();
                });

        // Exibir o AlertDialog
        builder.create().show();
    }

    // Adicione este método para voltar à IncluirClienteActivity ao clicar no botão "btVoltar"
    public void voltarParaIncluirClienteActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), IncluirClienteActivity.class);
        startActivity(intent);
    }
}