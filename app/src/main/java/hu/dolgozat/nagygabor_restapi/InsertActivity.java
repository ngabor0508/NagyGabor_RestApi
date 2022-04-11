package hu.dolgozat.nagygabor_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity{
    private EditText edit_nev, edit_orszag, edit_lakossag;
    private Button felvetel_btn, vissza_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        felvetel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        edit_nev = findViewById(R.id.edit_nev);
        edit_orszag = findViewById(R.id.edit_orszag);
        edit_lakossag = findViewById(R.id.edit_lakossag);
        felvetel_btn = findViewById(R.id.felvetel_btn);
        vissza_btn = findViewById(R.id.vissza_btn);
    }
}
