package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todo.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etItem;
    private Button btnAdd;
    private ListView lvItems;

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        setupListeners();

        setAdapter();
    }

    private void initWidgets() {
        etItem = findViewById(R.id.etItem);
        btnAdd = findViewById(R.id.btnAdd);
        lvItems = findViewById(R.id.lvItems);
    }

    private void setupListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        etItem.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addItem();
                    return  true;
                }

                return false;
            }
        });
    }

    private void addItem() {
        if (etItem.getText().toString().trim().isEmpty()) {
            etItem.setError((getString(R.string.please_insert)));
            etItem.requestFocus();
            return;
        }

        Item item = new Item(etItem.getText().toString().trim(), false);
        adapter.add(item);
        clearForm();

    }

    private void clearForm() {
        etItem.getText().clear();
        etItem.requestFocus();
    }

    private void setAdapter() {

        adapter = new ItemAdapter(this, -1, new ArrayList<Item>());
        lvItems.setAdapter(adapter);
    }
}
