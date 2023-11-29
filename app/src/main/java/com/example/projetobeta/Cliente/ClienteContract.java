package com.example.projetobeta.Cliente;

import android.provider.BaseColumns;

public class ClienteContract {
    public static class Cliente implements BaseColumns {
        public static final String TABELA = "cliente";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_EMAIL = "email";
    }
}
