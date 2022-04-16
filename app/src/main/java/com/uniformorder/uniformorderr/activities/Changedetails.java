package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.model.Registermodel;
import com.uniformorder.uniformorderr.retrofit.APIClient;
import com.uniformorder.uniformorderr.retrofit.APIInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.airbnb.lottie.L.TAG;

import org.json.JSONException;
import org.json.JSONObject;

public class Changedetails extends BaseAppCompatActivity {
    CircleImageView circleprofileimg;
    EditText edtname, phonenum;
    TextView save;
    ImageView img_back;

    private int PERMISSION_REQUEST_CODE_Storage = 1;
    private int PERMISSION_REQUEST_CODE_WRITE_CAMERA1 = 12;
    private static final int SELECT_FILE1 = 11;
    private static final int REQUEST_CAMERA1 = 5;
    File imgFirstGallery;
    Uri selectedImageUri = Uri.parse("");
    String phne = "", name = "", picuri = "";


    @Override
    public String getActionTitle() {
        return null;
    }

    @Override
    public boolean isHomeButtonEnable() {
        return false;
    }

    @Override
    public int setHomeButtonIcon() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_changedetails);
        circleprofileimg = findViewById(R.id.circleprofileimg);
        phonenum = findViewById(R.id.phonenum);
        edtname = findViewById(R.id.edtname);
        save = findViewById(R.id.save);
        img_back = findViewById(R.id.img_back);
        phne = UserPreference.getInstance(mBaseAppCompatActivity).getPhoneNum();
        name = UserPreference.getInstance(mBaseAppCompatActivity).getUserSignUpName();
        edtname.setText(name);
        phonenum.setText(phne);
        selectedImageUri = Uri.parse(UserPreference.getInstance(mBaseAppCompatActivity).getprofileuri());
        picuri = String.valueOf(selectedImageUri);
        if (picuri.equals("")) {
            circleprofileimg.setImageResource(R.drawable.logo);
        } else {
            Picasso.with(Changedetails.this).load(selectedImageUri).networkPolicy(NetworkPolicy.NO_CACHE).into(circleprofileimg);
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        circleprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadpiccall();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                edtitprofile();
            }
        });
    }

    private void uploadpiccall() {
        if (!hasPhoneCameraPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_REQUEST_CODE_Storage);
        } else {
            showPictureDialog1();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_changedetails;
    }

    private void edtitprofile() {
        showHideProgressDialog(true);
        String loginid = UserPreference.getInstance(Changedetails.this).getLoginId();
        String email = UserPreference.getInstance(Changedetails.this).getEmail();
        name = edtname.getText().toString();
        phne = phonenum.getText().toString();
        LinkedHashMap<String, RequestBody> addPostRequest = new LinkedHashMap<String, RequestBody>();
        addPostRequest.put("login_id", RequestBody.create(MediaType.parse("multipart/form-data"), loginid));
        addPostRequest.put("email", RequestBody.create(MediaType.parse("multipart/form-data"), email));
        addPostRequest.put("name", RequestBody.create(MediaType.parse("multipart/form-data"), name));
        addPostRequest.put("mobile", RequestBody.create(MediaType.parse("multipart/form-data"), phne));

        MultipartBody.Part itemDocumentFile = null;
        RequestBody requestFile;
        if (imgFirstGallery != null) {
            //add
            requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imgFirstGallery);
            itemDocumentFile = MultipartBody.Part.createFormData("profile_pic", imgFirstGallery.getName(), requestFile);
        } else {
            if (picuri != null && !picuri.equalsIgnoreCase("")) {
                requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), picuri);
                itemDocumentFile = MultipartBody.Part.createFormData("profile_pic", "", requestFile);
            } else {
                requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                itemDocumentFile = MultipartBody.Part.createFormData("profile_pic", "", requestFile);
            }
        }


        APIInterface apiInterface = APIClient.getClient(this).create(APIInterface.class);
        Call<Registermodel> editprfile = apiInterface.editprfile(addPostRequest, itemDocumentFile);
        editprfile.enqueue(new Callback<Registermodel>() {
            @Override
            public void onResponse(Call<Registermodel> call, Response<Registermodel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        if (response.body().getStatus().toString().equalsIgnoreCase("true")) {
                            showHideProgressDialog(false);

                            selectedImageUri = Uri.parse(response.body().getData().getProfile());
                            UserPreference.getInstance(mBaseAppCompatActivity).setUserSignUpName(response.body().getData().getName());
                            UserPreference.getInstance(mBaseAppCompatActivity).setprofileuri(response.body().getData().getProfile());
                            UserPreference.getInstance(mBaseAppCompatActivity).setloginUserDetail(response.body().getData());
                            UserPreference.getInstance(mBaseAppCompatActivity).setUserSignUpName(response.body().getData().getName());
                            UserPreference.getInstance(mBaseAppCompatActivity).setPhoneNum(response.body().getData().getMobile());
                            UserPreference.getInstance(mBaseAppCompatActivity).setEmail(response.body().getData().getEmail());

                            Toast.makeText(Changedetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            showHideProgressDialog(false);
                            Toast.makeText(Changedetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        showHideProgressDialog(false);
                        Toast.makeText(Changedetails.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showHideProgressDialog(false);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Changedetails.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Registermodel> call, Throwable t) {
                showHideProgressDialog(false);
                Toast.makeText(Changedetails.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean hasPhoneCameraPermission(String permission) {
        boolean ret = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasPermission = ContextCompat.checkSelfPermission(mBaseAppCompatActivity, permission);
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                ret = true;
            }
        } else {
            ret = true;
        }
        return ret;

    }

    private void requestPermission(String permission, int requestCode) {
        String requestPermissionArray[] = {permission};
        ActivityCompat.requestPermissions(mBaseAppCompatActivity, requestPermissionArray, requestCode);
    }


    private void showPictureDialog1() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(mBaseAppCompatActivity);

        String[] pictureDialogItems = {
                "Choose from gallery",
                "Take Photo"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                galleryIntent1();
                                break;
                            case 1:
                                if (!hasPhoneCameraPermission(Manifest.permission.CAMERA)) {
                                    requestPermission(Manifest.permission.CAMERA, PERMISSION_REQUEST_CODE_WRITE_CAMERA1);
                                } else {
                                    cameraIntent1();
                                }
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    // First Camera
    private void cameraIntent1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA1);
    }

    private void galleryIntent1() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_FILE1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE1) {
                Uri selectedImageUri = data.getData();
                imgFirstGallery = new File(getRealPathFromURI(selectedImageUri));
                circleprofileimg.setImageURI(selectedImageUri);
            } else if (requestCode == REQUEST_CAMERA1) {
                onCaptureImageResult1(data);
            }
        }
    }

    // Get path form gallery
    public String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        }
    }

    // for capture image second
    private void onCaptureImageResult1(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleprofileimg.setImageBitmap(thumbnail);
        // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
        Uri tempUri = getImageUri(this, thumbnail);
        // CALL THIS METHOD TO GET THE ACTUAL PATH
        imgFirstGallery = new File(getRealPathFromURICamera(tempUri));
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    // get path from camera
    public String getRealPathFromURICamera(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);

                cursor.close();
            }
        }
        return path;
    }
}