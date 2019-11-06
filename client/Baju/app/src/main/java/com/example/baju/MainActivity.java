package com.example.baju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.baju.Adapter.SepatuAdapter;
import com.example.baju.Model.GetSepatu;
import com.example.baju.Model.Sepatu;
import com.example.baju.Rest.ApiClient;
import com.example.baju.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIns = (Button) findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();
    }

    public void refresh() {
        Call<GetSepatu> SepatuCall = mApiInterface.getSepatu();
        SepatuCall.enqueue(new Callback<GetSepatu>() {
            @Override
            public void onResponse(Call<GetSepatu> call, Response<GetSepatu>
                    response) {
                List<Sepatu> SepatuList = response.body().getListDataSepatu();
                Log.d("Retrofit Get", "Jumlah data Sepatu: " +
                        String.valueOf(SepatuList.size()));
                mAdapter = new SepatuAdapter(SepatuList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetSepatu> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
