package com.example.asus.admin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Cuisine extends AppCompatActivity {
    EditText name, desc, recipe, s_name, cat, price, ingre, s_desc;
    Button update, cancel;
    int countp=0;
    String key1;
    String[] splitting;
    ImageView image;
    public static final int GET_FROM_GALLERY = 3;
    Uri selectedImage;
    DatabaseReference mref;
    int p = 0;
    Bitmap m1;
    String url;
    int flag = 0;
    String n1;
    int count = 0;

    String c, n;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);

        progress = new ProgressDialog(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            n1 = b.getString("village");
        }

        name = (EditText) findViewById(R.id.textName);
        desc = (EditText) findViewById(R.id.textDes);
        recipe = (EditText) findViewById(R.id.textrecipe);
        s_name = (EditText) findViewById(R.id.textShop);
        cat = (EditText) findViewById(R.id.textCategory);
        price = (EditText) findViewById(R.id.textPrice);
        ingre = (EditText) findViewById(R.id.textIngredients);
        s_desc = (EditText) findViewById(R.id.textSdesc);


        image = (ImageView) findViewById(R.id.images);

        update = (Button) findViewById(R.id.btn_signup);
       // cancel = (Button) findViewById(R.id.btn_cancel);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                flag++;
            }
        });

        mref = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(n1).child("CuisineRelation");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progress.setMessage("Uploading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setProgress(0);
                progress.setCancelable(false);
                progress.show();

                //int count=0;
                if (flag >= 1) {

                    n = name.getText().toString();
                    splitting = n.split(" ");
                    String temp = "";
                    for (int i = 0; i < splitting.length; i++)
                        temp += splitting[i];
                    n = temp;
                    Toast.makeText(Cuisine.this, n, Toast.LENGTH_LONG).show();


                    String d = desc.getText().toString();
                    String r = recipe.getText().toString();
                    String s = s_name.getText().toString();
                    if (cat.getText().toString().equalsIgnoreCase("veg")) {
                        c = "0";
                    } else
                        c = "1";
                    String p = price.getText().toString();
                    String i = ingre.getText().toString();
                    String s_d = s_desc.getText().toString();

                    if (!n.equals(null)) {
                        count++;
                    }
                    if (!d.equals(null)) {
                        count++;
                    }
                    if (!r.equals(null)) {
                        count++;
                    }
                    if (!s.equals(null)) {
                        count++;
                    }
                    if (!c.equals(null)) {
                        count++;
                    }
                    if (!p.equals(null)) {
                        count++;
                    }
                    if (!i.equals(null)) {
                        count++;
                    }
                    if (!s_d.equals(null)) {
                        count++;
                    }


                    if (count == 8) {
                        StorageReference ref = FirebaseStorage.getInstance().getReference();

                        key1 = "VillageRelation/" + n1 + "/CuisineRelation/" + n + "image1.jpg";
                        ref.child("VillageRelation").child(n1).child("CuisineRelation").child(n).child("image1").putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                url = downloadUrl.toString();
                                mref.child(n).child("images").setValue(key1);
                                countp++;
                                Toast.makeText(Cuisine.this, "Image added", Toast.LENGTH_SHORT).show();

                                if(countp>=1) {
                                    progress.dismiss();
                                    Intent i = new Intent(Cuisine.this, Village_Present.class);
                                    i.putExtra("village",n1);
                                    startActivity(i);
                                }

                            }
                        });


                        //mref = mref.child(n);

                        mref.child(n).child("description").setValue(d);
                        mref.child(n).child("recipe").setValue(r);
                        mref.child(n).child("shopName").setValue(s);

                        mref.child(n).child("price").setValue(p);
                        mref.child(n).child("ingredients").setValue(i);
                        mref.child(n).child("shortDescription").setValue(s_d);
                        mref.child(n).child("rating").setValue("3.9");
                        //mref.child(n).child("images").setValue(image);
                        mref.child(n).child("category").setValue(c);

//                        Toast.makeText(Cuisine.this, "Done Updating", Toast.LENGTH_SHORT).show();
//                        Intent inte = new Intent(Cuisine.this, Village_Present.class);
//                        startActivity(inte);

                    } else {
                        Snackbar snack = Snackbar.make(desc, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        snack.show();
                    }
                } else {
                    Toast.makeText(Cuisine.this, "Upload all Images from gallery to the application", Toast.LENGTH_SHORT).show();
                    if (count != 8) {
                        Snackbar snack = Snackbar.make(desc, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        snack.show();
                    }
                }
            }
        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Cuisine.this, No_Village.class);
//                startActivity(i);
//            }
//        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            selectedImage = data.getData();
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                m1 = bitmap;
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}