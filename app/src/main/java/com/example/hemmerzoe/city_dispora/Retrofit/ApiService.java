package com.example.hemmerzoe.city_dispora.Retrofit;

import com.example.hemmerzoe.city_dispora.Response.BerandaResponse;
import com.example.hemmerzoe.city_dispora.Response.DetailKategoriResponse;
import com.example.hemmerzoe.city_dispora.Response.ListKategoriResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by hemmerzoe on 16/10/2017.
 */

public interface ApiService {

//    @FormUrlEncoded
//    @GET("aduanmember")
//    Call<LoginResponse> allmember();

    @GET("beranda")
    Call<BerandaResponse> berandaRequest();

    @GET("detailkategori")
    Call<DetailKategoriResponse> detailkategoriRequest(@Query("id_detailkategori") String id_detailkategori);

    @GET("listkategori")
    Call<ListKategoriResponse> listkategoriRequest(@Query("id_kategori") String id_kategori);

//    @FormUrlEncoded
//    @POST("registrasipengguna")
//    Call<RegisterResponse> registerRequest(@Field("nik") String nik,
//                                           @Field("nama") String nama_lngkp,
//                                           @Field("tanggal") String tgl_lhir,
//                                           @Field("password") String pwd,
//                                           @Field("no_hp") String no_hp);
//    @FormUrlEncoded
//    @PUT("updatepasspengguna")
//    Call<PwdMemberResponse> updatepwdRequest(@Field("nik") String nik,
//                                             @Field("pwd_lama") String pwd_lama,
//                                             @Field("pwd_baru") String pwd_baru);
////
//    @FormUrlEncoded
//    @POST("simpanpesan")
//    Call<PesanResponse> pesanRequest(@Field("nik") String nik,
//                                     @Field("nama_pngrim") String nama_pngrim,
//                                     @Field("tujuan_aduan") int tujuan_aduan,
//                                     @Field("kategori_aduan") int kategori_aduan,
//                                     @Field("topik_aduan") int topik_aduan,
//                                     @Field("pesan") String pesan);
//
//    @GET("listaduanpengguna")
//    Call<ListAduanResponse> listaduanRequest(@Query("nik") String nik);
}
