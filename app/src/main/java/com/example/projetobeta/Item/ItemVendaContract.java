package com.example.projetobeta.Item;

import android.provider.BaseColumns;

public class ItemVendaContract {
    public static class ItemVenda implements BaseColumns {
        public static final String TABELA = "item_venda";
        public static final String COLUNA_ID_VENDA = "id_venda";
        public static final String COLUNA_ID_PRODUTO = "id_produto";
        public static final String COLUNA_QUANTIDADE_ITEM = "quantidade_item";
    }
}

