package com.example.baju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baju.Model.PostPutDelSepatu;
import com.example.baju.Rest.ApiClient;
import com.example.baju.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    EditText edtId, edtNama, edtNomor;
    Button btUpdate, btDelete, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtId = (EditText) findViewById(R.id.edtId_spt);
        edtNama = (EditText) findViewById(R.id.edtSize);
        edtNomor = (EditText) findViewById(R.id.edtColor);
        Intent mIntent = getIntent();
        edtId.setText(mIntent.getStringExtra("Id Sepatu"));
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtNama.setText(mIntent.getStringExtra("Size"));
        edtNomor.setText(mIntent.getStringExtra("Color"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelSepatu> updateSepatuCall = mApiInterface.putSepatu(
                        edtId.getText().toString(),
                        edtNama.getText().toString(),
                        edtNomor.getText().toString());
                updateSepatuCall.enqueue(new Callback<PostPutDelSepatu>() {
                    @Override
                    public void onResponse(Call<PostPutDelSepatu> call, Response<PostPutDelSepatu> response) {
                        MainActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelSepatu> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btDelete = (Button) findViewById(R.id.btDelete2);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelSepatu> deleteSepatu = mApiInterface.deleteSepatu(edtId.getText().toString());
                    deleteSepatu.enqueue(new Callback<PostPutDelSepatu>() {
                        @Override
                        public void onResponse(Call<PostPutDelSepatu> call, Response<PostPutDelSepatu> response) {
                            MainActivity.ma.refresh();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelSepatu> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}
