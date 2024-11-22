package com.example.estoque.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "estoque.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRODUTO = "produto";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_QUANTIDADE = "quantidade";
    private static final String COLUMN_PRECO = "preco";

    public ProdutoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PRODUTO + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_QUANTIDADE + " INTEGER, " +
                COLUMN_PRECO + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        onCreate(db);
    }

    // CRUD
    public boolean addProduto(String nome, int quantidade, double preco) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_QUANTIDADE, quantidade);
        values.put(COLUMN_PRECO, preco);

        long result = db.insert(TABLE_PRODUTO, null, values);
        db.close();
        return result != -1;
    }

    public List<String> getAllProdutos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> produtos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUTO, null);

        if (cursor.moveToFirst()) {
            do {
                String produto = "ID: " + cursor.getInt(0) +
                        ", Nome: " + cursor.getString(1) +
                        ", Quantidade: " + cursor.getInt(2) +
                        ", Preço: " + cursor.getDouble(3);
                produtos.add(produto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return produtos;
    }

    public boolean updateProduto(int id, String nome, int quantidade, double preco) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, nome);
        values.put(COLUMN_QUANTIDADE, quantidade);
        values.put(COLUMN_PRECO, preco);

        int result = db.update(TABLE_PRODUTO, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteProduto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PRODUTO, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}

