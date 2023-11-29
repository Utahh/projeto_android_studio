package com.example.projetobeta.Venda;

import android.provider.BaseColumns;

public class VendaContract {

    public static class Venda implements BaseColumns {
        public static final String TABELA = "venda";
        public static final String COLUNA_ID_CLIENTE = "id_cliente";
        public static final String COLUNA_TOTAL_VENDA = "total_venda";
    }
}

