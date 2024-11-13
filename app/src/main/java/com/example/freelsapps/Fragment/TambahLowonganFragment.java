package com.example.freelsapps.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.freelsapps.Adapter.Spinner.JenisPekerjaanSpinnerAdapter;
import com.example.freelsapps.Adapter.Spinner.LokasiSpinnerAdapter;
import com.example.freelsapps.Model.CUDlowongan;
import com.example.freelsapps.R;
import com.example.freelsapps.Rest.ApiClient;
import com.example.freelsapps.Rest.ApiInterface;

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

public class TambahLowonganFragment extends Fragment {

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

    public TambahLowonganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_lowongan, container, false);

        this.etNamaPerusahaan = view.findViewById(R.id.etNamaPerusahaan);
        this.etPekerjaan = view.findViewById(R.id.etPekerjaan);
        this.spLokasi = view.findViewById(R.id.spLokasi);
        this.spJenisPekerjaan = view.findViewById(R.id.spJenisPekerjaan);
        this.etGajiMinimum = view.findViewById(R.id.etGajiMinimum);
        this.etGajiMaksimum = view.findViewById(R.id.etGajiMaksimum);
        this.etRingkasanPekerjaan = view.findViewById(R.id.etRingkasanPekerjaan);
        this.etKualifikasiPekerjaan = view.findViewById(R.id.etKualifikasiPekerjaan);
        this.btUnggahLogoPerusahaan = view.findViewById(R.id.btUnggahLogoPerusahaan);
        this.btUnggahLowongan = view.findViewById(R.id.btUnggahLowongan);

        new LokasiSpinnerAdapter(getContext(), spLokasi);
        new JenisPekerjaanSpinnerAdapter(getContext(), spJenisPekerjaan);

        this.btUnggahLogoPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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
                    Toast.makeText(getContext(), "Logo perusahaan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getContext(), "Lowongan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Gagal menambahkan lowongan: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CUDlowongan> call, Throwable t) {
                        Toast.makeText(getContext(), "Gagal menambahkan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    private void pilihGambar() {
        final CharSequence[] options = {"Ambil Gambar", "Pilih dari Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pilih Logo Perusahaan");

        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Ambil Gambar")) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
                        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
                        currentPhotoPath = image.getAbsolutePath();
                        photoFile = image;
                    } catch (IOException ex) {
                        Log.e("ImageFile", "Error creating image file", ex);
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getContext(), "com.example.freels11", photoFile);
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
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                logoFile = new File(currentPhotoPath);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    InputStream inputStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                    logoFile = new File(getContext().getExternalFilesDir(null), "picked_image.jpg"); // Contoh penyimpanan ke file baru
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
                Toast.makeText(getContext(), "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}