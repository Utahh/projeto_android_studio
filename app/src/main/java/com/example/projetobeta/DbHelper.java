package com.example.projetobeta;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projetobeta.Cliente.Cliente;
import com.example.projetobeta.Cliente.ClienteContract;
import com.example.projetobeta.Item.ItemVendaContract;
import com.example.projetobeta.Produto.Produto;
import com.example.projetobeta.Produto.ProdutoContract;
import com.example.projetobeta.Venda.VendaContract;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOME = "basedadados_beta";

    private static final String CREATE_CLIENTE =
            "CREATE TABLE " + ClienteContract.Cliente.TABELA + " (" +
                    ClienteContract.Cliente._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ClienteContract.Cliente.COLUNA_NOME + " TEXT, " +
                    ClienteContract.Cliente.COLUNA_EMAIL + " TEXT)";

    private static final String DROP_CLIENTE =
            "DROP TABLE IF EXISTS " + ClienteContract.Cliente.TABELA;

    //Produto

    private static final String CREATE_PRODUTO =
            "CREATE TABLE " + ProdutoContract.Produto.TABELA + " (" +
                    ProdutoContract.Produto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ProdutoContract.Produto.COLUNA_DESCRICAO + " TEXT, " +
                    ProdutoContract.Produto.COLUNA_QUANTIDADE + " INTEGER, " +
                    ProdutoContract.Produto.COLUNA_VALOR + " REAL) ";

    private static final String DROP_PRODUTO =
            "DROP TABLE IF EXISTS " + ProdutoContract.Produto.TABELA;

    //ItemVenda

    private static final String CREATE_ITEM_VENDA =
            "CREATE TABLE " + ItemVendaContract.ItemVenda.TABELA + " (" +
                    ItemVendaContract.ItemVenda._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ItemVendaContract.ItemVenda.COLUNA_ID_VENDA + " INTEGER, " +
                    ItemVendaContract.ItemVenda.COLUNA_ID_PRODUTO + " INTEGER, " +
                    ItemVendaContract.ItemVenda.COLUNA_QUANTIDADE_ITEM + " INTEGER) ";

    private static final String DROP_ITEM_VENDA =
            "DROP TABLE IF EXISTS " + ItemVendaContract.ItemVenda.TABELA;

// Venda

    private static final String CREATE_VENDA =
            "CREATE TABLE " + VendaContract.Venda.TABELA + " (" +
                    VendaContract.Venda._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VendaContract.Venda.COLUNA_ID_CLIENTE + " INTEGER, " +
                    VendaContract.Venda.COLUNA_TOTAL_VENDA + " REAL) ";

    private static final String DROP_VENDA =
            "DROP TABLE IF EXISTS " + VendaContract.Venda.TABELA;

    public DbHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_PRODUTO);
        sqLiteDatabase.execSQL(CREATE_VENDA);
        sqLiteDatabase.execSQL(CREATE_ITEM_VENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_CLIENTE);
        sqLiteDatabase.execSQL(DROP_PRODUTO);
        sqLiteDatabase.execSQL(DROP_VENDA);
        sqLiteDatabase.execSQL(DROP_ITEM_VENDA);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_CLIENTE);
        sqLiteDatabase.execSQL(DROP_PRODUTO);
        onCreate(sqLiteDatabase);
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

        // Operações relacionadas a Cliente

    public long salvarCliente(Cliente cliente) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ClienteContract.Cliente.COLUNA_NOME, cliente.getNome());
        contentValues.put(ClienteContract.Cliente.COLUNA_EMAIL, cliente.getEmail());

        long id = sqLiteDatabase.insert(ClienteContract.Cliente.TABELA, null, contentValues);
        cliente.setId(id);
        return id;
    }

    @SuppressLint("Range")
    public ArrayList<Cliente> consultarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +
                ClienteContract.Cliente.TABELA, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(ClienteContract.Cliente._ID));
            String nome = cursor.getString(cursor.getColumnIndex(ClienteContract.Cliente.COLUNA_NOME));
            String email = cursor.getString(cursor.getColumnIndex(ClienteContract.Cliente.COLUNA_EMAIL));

            lista.add(new Cliente(id, nome, email));
        }

        cursor.close();
        return lista;
    }

    public Cliente obterClientePorId(long clienteId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                ClienteContract.Cliente.TABELA,
                null,
                ClienteContract.Cliente._ID + " = ?",
                new String[]{String.valueOf(clienteId)},
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();

            @SuppressLint("Range") Cliente cliente = new Cliente(
                    cursor.getLong(cursor.getColumnIndex(ClienteContract.Cliente._ID)),
                    cursor.getString(cursor.getColumnIndex(ClienteContract.Cliente.COLUNA_NOME)),
                    cursor.getString(cursor.getColumnIndex(ClienteContract.Cliente.COLUNA_EMAIL))
            );

            cursor.close();
            return cliente;
        } else {
            return null;
        }
    }

    public int atualizarCliente(Cliente cliente) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ClienteContract.Cliente.COLUNA_NOME, cliente.getNome());
        contentValues.put(ClienteContract.Cliente.COLUNA_EMAIL, cliente.getEmail());

        return sqLiteDatabase.update(
                ClienteContract.Cliente.TABELA,
                contentValues,
                ClienteContract.Cliente._ID + " = ?",
                new String[]{String.valueOf(cliente.getId())}
        );
    }

    //Produto

    public long salvarProduto(Produto produto) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProdutoContract.Produto.COLUNA_DESCRICAO, produto.getDescricao());
        contentValues.put(ProdutoContract.Produto.COLUNA_QUANTIDADE, produto.getQuantidade());
        contentValues.put(ProdutoContract.Produto.COLUNA_VALOR, String.valueOf(produto.getValor()));

        long id = sqLiteDatabase.insert(ProdutoContract.Produto.TABELA, null, contentValues);
        produto.setIdProduto(id);
        return id;
    }

    @SuppressLint("Range")
    public ArrayList<Produto> consultarProduto() {
        ArrayList<Produto> lista = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +
                ProdutoContract.Produto.TABELA, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(ProdutoContract.Produto._ID));
            String descricao = cursor.getString(cursor.getColumnIndex(ProdutoContract.Produto.COLUNA_DESCRICAO));
            Integer quantidade = cursor.getInt(cursor.getColumnIndex(ProdutoContract.Produto.COLUNA_QUANTIDADE));
            Double valor= cursor.getDouble(cursor.getColumnIndex(ProdutoContract.Produto.COLUNA_VALOR));

            lista.add(new Produto(id, descricao, quantidade, valor));
        }

        cursor.close();
        return lista;
    }

    @SuppressLint("Range")
    public Produto obterProdutoPorId(long Id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try (Cursor cursor = sqLiteDatabase.query(
                ProdutoContract.Produto.TABELA,
                null,
                ProdutoContract.Produto._ID + " = ?",
                new String[]{String.valueOf(Id)},
                null,
                null,
                null
        )) {
            if (cursor != null && cursor.moveToFirst()) {
                long id = cursor.getLong(cursor.getColumnIndex(ProdutoContract.Produto._ID));
                String descricao = cursor.getString(cursor.getColumnIndex(ProdutoContract.Produto.COLUNA_DESCRICAO));
                int quantidade = cursor.getInt(cursor.getColumnIndex(ProdutoContract.Produto.COLUNA_QUANTIDADE));
                double valor = cursor.getDouble(cursor.getColumnIndex(ProdutoContract.Produto.COLUNA_VALOR));

                return new Produto(id, descricao, quantidade, valor);
            }
        }

        return null;
    }

    public long atualizarProduto(Produto produto) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProdutoContract.Produto.COLUNA_DESCRICAO, produto.getDescricao());
        contentValues.put(ProdutoContract.Produto.COLUNA_QUANTIDADE, produto.getQuantidade());
        contentValues.put(ProdutoContract.Produto.COLUNA_VALOR, produto.getValor());


        return sqLiteDatabase.update(
                ProdutoContract.Produto.TABELA,
                contentValues,
                ProdutoContract.Produto._ID + " = ?",
                new String[]{String.valueOf(produto.getIdProduto())}
        );
    }

}
