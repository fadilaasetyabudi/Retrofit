package com.example.baju;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

public class InsertActivity extends AppCompatActivity {
    EditText edtNama, edtNomor;
    Button btInsert, btBack, btUpload;
    ApiInterface mApiInterface;

    private Bitmap imagePath;
    private Uri filePath;

    private static final int PICK_IMAGE_REQUEST = 234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        edtNama = (EditText) findViewById(R.id.edtSize);
        edtNomor = (EditText) findViewById(R.id.edtColor);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert = (Button) findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelSepatu> postSepatuCall = mApiInterface.postSepatu(edtNama.getText().toString(), edtNomor.getText().toString());
                postSepatuCall.enqueue(new Callback<PostPutDelSepatu>() {
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

        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });

        btUpload = (Button) findViewById(R.id.btUpload);
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,  MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            Uri selectedImage = data.getData();

            String[] proj = {MediaStore.Images.Media.DATA};
            CursorLoader loader = new CursorLoader(getApplicationContext(), selectedImage, proj, null, null, null);
            Cursor cursor = loader.loadInBackground();
            if (cursor != null) {
                //blok ini untuk mendapatkan image file path
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                String imagePath = cursor.getString(columnIndex);
                cursor.close();
            }

        }
    }

//    private File createTempFile(Bitmap bitmap) {
//        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//                , System.currentTimeMillis() +"_image.webp");
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        bitmap.compress(Bitmap.CompressFormat.WEBP,0, bos);
//        byte[] bitmapdata = bos.toByteArray();
//        //write the bytes in file
//
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(bitmapdata);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
}
