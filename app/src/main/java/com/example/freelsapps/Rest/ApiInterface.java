package com.example.freelsapps.Rest;

import com.example.freelsfragment.Model.CUDlowongan;
import com.example.freelsfragment.Model.GetLowongan;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("perusahaan")
    Call<GetLowongan> getLowongan();

    @GET("perusahaan")
    Call<GetLowongan> getLowongan(@Query("id") int id);

    @Multipart
    @POST("perusahaan")
    Call<CUDlowongan> postLowongan(
            @Part("namaPerusahaan") RequestBody namaPerusahaan,
            @Part("pekerjaan") RequestBody pekerjaan,
            @Part("lokasi") RequestBody lokasi,
            @Part("jenisPekerjaan") RequestBody jenisPekerjaan,
            @Part("gajiMinimum") RequestBody gajiMinimum,
            @Part("gajiMaksimum") RequestBody gajiMaksimum,
            @Part("ringkasanPekerjaan") RequestBody ringkasanPekerjaan,
            @Part("kualifikasiPekerjaan") RequestBody kualifikasiPekerjaan,
//            @Part ("logoPerusahaan") RequestBody logo_perusahaan
            @Part MultipartBody.Part logoPerusahaan
    );

    @FormUrlEncoded
    @PUT("perusahaan")
    Call<CUDlowongan> putLowongan(
            @Field("id") int id,
            @Field("namaPerusahaan") String nama_perusahaan,
            @Field("pekerjaan") String pekerjaan,
            @Field("lokasi") String lokasi,
            @Field("jenisPekerjaan") String jenis_pekerjaan,
            @Field("gajiMinimum") int gaji_minimum,
            @Field("gajiMaksimum") int gaji_maksimum,
            @Field("ringkasanPekerjaan") String ringkasan_pekerjaan,
            @Field("kualifikasiPekerjaan") String kualifikasi_pekerjaan
    );

    @FormUrlEncoded
    @DELETE("perusahaan")
    Call<CUDlowongan> deleteLowongan(@Field("id") int id);

}
