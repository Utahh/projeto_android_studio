package com.example.projetobeta.Produto;

import android.provider.BaseColumns;

public class ProdutoContract {

    public static class Produto implements BaseColumns{
        public static final String TABELA = "produto";
        public static final String COLUNA_DESCRICAO = "descricao";
        public static final String COLUNA_QUANTIDADE = "quantidade";
        public static final String COLUNA_VALOR = "valor";
    }

}

