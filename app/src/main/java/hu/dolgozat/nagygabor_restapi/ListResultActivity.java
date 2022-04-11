package hu.dolgozat.nagygabor_restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListResultActivity extends AppCompatActivity{
    private ListView listaz_view;
    private Button vissza_btn;

    List<Varos> varosLista = new ArrayList<>();

    private String URL = "https://retoolapi.dev/XqXBFR/varosok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        ReqestTask reqestTask = new ReqestTask();
        reqestTask.execute();

        vissza_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        listaz_view = findViewById(R.id.listaz_view);
        vissza_btn = findViewById(R.id.vissza_btn);
    }

    private class ReqestTask extends AsyncTask<Void, Void, Response>{

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                response = RequestHandler.get(URL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Gson gson = new Gson();
            if (response == null || response.getResponseCode() >= 400){
                Toast.makeText(ListResultActivity.this, "Sajnos nem sikerült, kérlek ellenőrizz le mindent.", Toast.LENGTH_SHORT).show();
            }
            else {
                Varos[] locations = gson.fromJson(response.getContent(), Varos[].class);
                varosLista.clear();
                varosLista.addAll(Arrays.asList(locations));
                ArrayAdapter<Varos> arrayAdapter = new ArrayAdapter<>(ListResultActivity.this, R.layout.varos_list_item, R.id.textview_elemek, varosLista);
                listaz_view.setAdapter(arrayAdapter);
            }
        }
    }
}
