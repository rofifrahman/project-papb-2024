package com.example.freelsapps.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.freelsfragment.Adapter.Spinner.JenisPekerjaanSpinnerAdapter;
import com.example.freelsfragment.Adapter.Spinner.LokasiSpinnerAdapter;
import com.example.freelsfragment.Model.CUDlowongan;
import com.example.freelsfragment.R;
import com.example.freelsfragment.Rest.ApiClient;
import com.example.freelsfragment.Rest.ApiInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahLowonganActivity extends AppCompatActivity {

    private EditText etNamaPerusahaan;
    private EditText etPekerjaan;
    private Spinner spLokasi;
    private Spinner spJenisPekerjaan;
    private EditText etGajiMinimum;
    private EditText etGajiMaksimum;
    private EditText etRingkasanPekerjaan;
    private EditText etKualifikasiPekerjaan;
    private Button btUnggahLogoPerusahaan;
    private Button btUnggahLowongan;
    private ApiInterface apiInterface;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int STORAGE_PERMISSION_CODE = 100;
    private File logoFile = null;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_lowongan);

        this.etNamaPerusahaan = findViewById(R.id.etNamaPerusahaan);
        this.etPekerjaan = findViewById(R.id.etPekerjaan);
        this.spLokasi = findViewById(R.id.spLokasi);
        this.spJenisPekerjaan = findViewById(R.id.spJenisPekerjaan);
        this.etGajiMinimum = findViewById(R.id.etGajiMinimum);
        this.etGajiMaksimum = findViewById(R.id.etGajiMaksimum);
        this.etRingkasanPekerjaan = findViewById(R.id.etRingkasanPekerjaan);
        this.etKualifikasiPekerjaan = findViewById(R.id.etKualifikasiPekerjaan);
        this.btUnggahLogoPerusahaan = findViewById(R.id.btUnggahLogoPerusahaan);
        this.btUnggahLowongan = findViewById(R.id.btUnggahLowongan);

        new LokasiSpinnerAdapter(this, spLokasi);
        new JenisPekerjaanSpinnerAdapter(this, spJenisPekerjaan);

        this.btUnggahLogoPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(TambahLowonganActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    pilihGambar();
                } else {
                    String [] Permisions = { Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(Permisions,100);
                }
            }
        });

        this.btUnggahLowongan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface = ApiClient.getClient().create(ApiInterface.class);

                RequestBody namaPerusahaan = RequestBody.create(MediaType.parse("text/plain"), etNamaPerusahaan.getText().toString());
                RequestBody pekerjaan = RequestBody.create(MediaType.parse("text/plain"), etPekerjaan.getText().toString());
                RequestBody lokasi = RequestBody.create(MediaType.parse("text/plain"), spLokasi.getSelectedItem().toString());
                RequestBody jenisPekerjaan = RequestBody.create(MediaType.parse("text/plain"), spJenisPekerjaan.getSelectedItem().toString());
                RequestBody gajiMinimum = RequestBody.create(MediaType.parse("text/plain"), etGajiMinimum.getText().toString());
                RequestBody gajiMaksimum = RequestBody.create(MediaType.parse("text/plain"), etGajiMaksimum.getText().toString());
                RequestBody ringkasanPekerjaan = RequestBody.create(MediaType.parse("text/plain"), etRingkasanPekerjaan.getText().toString());
                RequestBody kualifikasiPekerjaan = RequestBody.create(MediaType.parse("text/plain"), etKualifikasiPekerjaan.getText().toString());

                MultipartBody.Part logoPerusahaan = null;
                if (logoFile != null) {
                    Toast.makeText(TambahLowonganActivity.this, "Logo perusahaan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    RequestBody requestBodyLogo = RequestBody.create(MediaType.parse("image/*"), logoFile);
                    logoPerusahaan = MultipartBody.Part.createFormData("logoPerusahaan", logoFile.getName(), requestBodyLogo);
                }

                Call<CUDlowongan> postLowongan = apiInterface.postLowongan(
                        namaPerusahaan,
                        pekerjaan,
                        lokasi,
                        jenisPekerjaan,
                        gajiMinimum,
                        gajiMaksimum,
                        ringkasanPekerjaan,
                        kualifikasiPekerjaan,
                        logoPerusahaan
                );

                postLowongan.enqueue(new Callback<CUDlowongan>() {
                    @Override
                    public void onResponse(Call<CUDlowongan> call, Response<CUDlowongan> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(TambahLowonganActivity.this, "Lowongan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TambahLowonganActivity.this, "Gagal menambahkan lowongan: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CUDlowongan> call, Throwable t) {
                        Toast.makeText(TambahLowonganActivity.this, "Gagal menambahkan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void pilihGambar() {
        final CharSequence[] options = {"Ambil Gambar", "Pilih dari Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Logo Perusahaan");

        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Ambil Gambar")) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
                        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
                        currentPhotoPath = image.getAbsolutePath();
                        photoFile = image;
                    } catch (IOException ex) {
                        Log.e("ImageFile", "Error creating image file", ex);
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(this, "com.example.freels11", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            } else if (options[item].equals("Pilih dari Gallery")) {
                Intent pickPhoto = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                pickPhoto.addCategory(Intent.CATEGORY_OPENABLE);
                pickPhoto.setType("image/*");
                startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                logoFile = new File(currentPhotoPath);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    InputStream inputStream = this.getContentResolver().openInputStream(selectedImageUri);
                    logoFile = new File(this.getExternalFilesDir(null), "picked_image.jpg"); // Contoh penyimpanan ke file baru
                    OutputStream outputStream = new FileOutputStream(logoFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pilihGambar();
            } else {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
