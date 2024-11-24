package com.example.estoque.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class FornecedorDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fornecedores.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela de Fornecedores Nacionais
    private static final String TABLE_NACIONAL = "fornecedor_nacional";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_CNPJ = "cnpj";
    private static final String COLUMN_ESTADO = "estado";

    // Tabela de Fornecedores Internacionais
    private static final String TABLE_INTERNACIONAL = "fornecedor_internacional";
    private static final String COLUMN_PAIS = "pais";
    private static final String COLUMN_NUMERO_REGISTRO = "numero_registro";

    public FornecedorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNacional = "CREATE TABLE " + TABLE_NACIONAL + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_CNPJ + " TEXT, " +
                COLUMN_ESTADO + " TEXT)";
        db.execSQL(createNacional);

        String createInternacional = "CREATE TABLE " + TABLE_INTERNACIONAL + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_PAIS + " TEXT, " +
                COLUMN_NUMERO_REGISTRO + " TEXT)";
        db.execSQL(createInternacional);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NACIONAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERNACIONAL);
        onCreate(db);
    }

    // Adicionar Fornecedor Nacional
    public boolean addFornecedorNacional(String nome, String cnpj, String estado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_CNPJ, cnpj);
        values.put(COLUMN_ESTADO, estado);

        long result = db.insert(TABLE_NACIONAL, null, values);
        db.close();
        return result != -1;
    }

    // Adicionar Fornecedor Internacional
    public boolean addFornecedorInternacional(String nome, String pais, String numeroRegistro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_PAIS, pais);
        values.put(COLUMN_NUMERO_REGISTRO, numeroRegistro);

        long result = db.insert(TABLE_INTERNACIONAL, null, values);
        db.close();
        return result != -1;
    }

    // Listar todos os Fornecedores Nacionais
    public List<String> getAllFornecedoresNacionais() {
        List<String> fornecedores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NACIONAL, null);

        if (cursor.moveToFirst()) {
            do {
                String fornecedorNac = "ID: "+ cursor.getInt(0) +
                        ", Nome: " + cursor.getString(1) +
                        ", CNPJ: " + cursor.getString(2) +
                        ", Estado: " + cursor.getString(3);

                fornecedores.add(fornecedorNac);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return fornecedores;
    }

    // Listar todos os Fornecedores Internacionais
    public List<String> getAllFornecedoresInternacionais() {
        List<String> fornecedores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INTERNACIONAL, null);

        if (cursor.moveToFirst()) {
            do {
                String fornecedorInter = "ID: "+ cursor.getInt(0) +
                        ", Nome: " + cursor.getString(1) +
                        ", País: " + cursor.getString(2) +
                        ", Nmr. Registro: " + cursor.getString(3);

                fornecedores.add(fornecedorInter);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return fornecedores;
    }

    // Excluir fornecedor nacional
    public boolean deleteFornecedorNacional(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NACIONAL, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    // Excluir fornecedor internacional
    public boolean deleteFornecedorInternacional(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_INTERNACIONAL, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    // Atualizar fornecedor nacional
    public boolean updateFornecedorNacional(int id, String nome, String cnpj, String estado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_CNPJ, cnpj);
        values.put(COLUMN_ESTADO, estado);

        int result = db.update(TABLE_NACIONAL, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    // Atualizar fornecedor internacional
    public boolean updateFornecedorInternacional(int id, String nome, String pais, String numeroRegistro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_PAIS, pais);
        values.put(COLUMN_NUMERO_REGISTRO, numeroRegistro);

        int result = db.update(TABLE_INTERNACIONAL, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }


}
