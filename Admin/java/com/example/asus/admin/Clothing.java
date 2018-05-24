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
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Clothing extends AppCompatActivity
{
    EditText name,cost,fabric,s_name;//s_address;
    Button update;// cancel;
    String key1;
    private int flag=0;
    ImageView image;
    String[] splitting;
    String url;
    DatabaseReference mref;
    public static final int GET_FROM_GALLERY=3;
    String n1;
    ProgressDialog progress;
    Uri selectedImage;
    int p,count;
    Bitmap m1;
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        progress = new ProgressDialog(this);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            n1 = b.getString("village");
        }




        name = (EditText)findViewById(R.id.textName);
        cost = (EditText)findViewById(R.id.textCost);
        fabric = (EditText)findViewById(R.id.TextFabric);
        s_name = (EditText)findViewById(R.id.textShop1);
        //s_address = (EditText)findViewById(R.id.textShopAddress);

        image  = (ImageView)findViewById(R.id.images);

        update=(Button)findViewById(R.id.btn_signup);
        //cancel = (Button) findViewById(R.id.btn_cancel);


        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                flag++;
            }
        });
        mref = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(n1).child("ClothingRelation");

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                progress.setMessage("Uploading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setProgress(0);
                progress.setCancelable(false);
                progress.show();
                if(flag>=1)
                {

                     n = name.getText().toString();

                    splitting = n.split(" ");
                    String temp = "";
                    for (int i = 0; i < splitting.length; i++)
                        temp += splitting[i];
                    n = temp;

                    if(!n.equals(null))

                        p++;
                    String c = cost.getText().toString();
                    if(!c.equals(null))

                        p++;
                    String f = fabric.getText().toString();
                    if(!f.equals(null))

                        p++;
                    String s_n = s_name.getText().toString();
                    if(!s_n.equals(null))

                        p++;
//                    String s_a = s_address.getText().toString();
//                    if(!s_a.equals(null))
//                    {
//                        p++;
//                    }


                    if(p==4) {

                        StorageReference ref = FirebaseStorage.getInstance().getReference();

                        key1 = "VillageRelation/" + n1 + "/ClothingRelation/" + n + "image1.jpg";


                        ref.child("VillageRelation").child(n1).child("ClothingRelation").child(n).child("imageOfCloth").putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                url = downloadUrl.toString();
                                count++;
                                mref.child(n).child("imageOfCloth").setValue(key1);
                                Toast.makeText(Clothing.this, "Image added", Toast.LENGTH_SHORT).show();
                                if(count>=1) {
                                    progress.dismiss();


                                    Intent i = new Intent(Clothing.this, Village_Present.class);
                                    i.putExtra("village",n1);
                                    startActivity(i);
                                }



                            }
                        });




                        mref.child(n).child("cost").setValue(c);
                        mref.child(n).child("fabricUsed").setValue(f);
                        mref.child(n).child("shopName").setValue(s_n);
                        //mref.child("shopAddress").setValue(s_a);
                        mref.child(n).child("rating").setValue("4.2");
                        //mref.child(n).child("imageOfCloth").setValue(key1);



                    }
                    else
                    {
                        Snackbar snack = Snackbar.make(s_name, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        snack.show();
                    }
                }
                else {
                    Toast.makeText(Clothing.this, "Upload all Images from gallery to the application", Toast.LENGTH_SHORT).show();
                    if(p!=4)
                    {
                        Snackbar snack = Snackbar.make(s_name, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        snack.show();
                    }

                }




            }
        });


//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Clothing.this,No_Village.class);
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
