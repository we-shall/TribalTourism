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
import android.widget.TextView;
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
import java.util.ArrayList;

public class Description extends AppCompatActivity
{
    EditText name, history, culture, speciality, desc,s_desc;
    Button update, cancel;
    private int flag=0;
    ArrayList<Integer> counter=new ArrayList<>();
    ImageView image1,image2,image3,image4;
    String key1,key2,key3,key4;
    DatabaseReference mref;
    public static final int GET_FROM_GALLERY=3;
    public static final int GET_FROM_GALLERY2=4;
    public static final int GET_FROM_GALLERY3=5;
    public static final int GET_FROM_GALLERY4=6;
    Uri selectedImage1,selectedImage2,selectedImage3,selectedImage4;
    int p=0,count=0,x=0;
    ProgressDialog progress;
    String url;
    Bitmap m1,m2,m3,m4;
    String n1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);




        progress = new ProgressDialog(this);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            n1 = b.getString("village");
        }


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        name = (EditText) findViewById(R.id.textName);
        history = (EditText) findViewById(R.id.textHistory);
        culture = (EditText) findViewById(R.id.textCulture);
        speciality = (EditText) findViewById(R.id.textSpeciality);
        desc = (EditText) findViewById(R.id.desc);
        s_desc = (EditText)findViewById(R.id.shortdesc);
        update = (Button) findViewById(R.id.btn_signup);
       // cancel = (Button) findViewById(R.id.btn_cancel);
        image1=(ImageView)findViewById(R.id.images1);
        image2=(ImageView)findViewById(R.id.images2);
        image3=(ImageView)findViewById(R.id.images3);
        image4=(ImageView)findViewById(R.id.images4);

       // update.setEnabled(false);


        mref = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(n1);


//        mref.child("DescriptionRelation").child("culture").setValue("0");
//        mref.child("DescriptionRelation").child("description").setValue("0");
//        mref.child("DescriptionRelation").child("galleryImage1").setValue("VillageRelation/no-image-available-md.png");
//        mref.child("DescriptionRelation").child("galleryImage2").setValue("VillageRelation/no-image-available-md.png");
//        mref.child("DescriptionRelation").child("galleryImage3").setValue("VillageRelation/no-image-available-md.png");
//        mref.child("DescriptionRelation").child("galleryImage4").setValue("VillageRelation/no-image-available-md.png");
//        mref.child("DescriptionRelation").child("history").setValue("0");
//        mref.child("DescriptionRelation").child("nameOfVillage").setValue("0");
//        mref.child("DescriptionRelation").child("rating").setValue("0");
//        mref.child("DescriptionRelation").child("shortdes").setValue("0");
//        mref.child("DescriptionRelation").child("speciality").setValue("0");
//
//        mref.child("CuisineRelation").child("Dish1").child("category").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("description").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("images").setValue("VillageRelation/no-image-available-md.png");
//        mref.child("CuisineRelation").child("Dish1").child("ingredients").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("price").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("rating").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("recipe").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("shopName").setValue("0");
//        mref.child("CuisineRelation").child("Dish1").child("shortDescription").setValue("0");
//
//        mref.child("ClothingRelation").child("kapda").child("cost").setValue("0");
//        mref.child("ClothingRelation").child("kapda").child("fabricUsed").setValue("0");
//        mref.child("ClothingRelation").child("kapda").child("imageOfCloth").setValue("VillageRelation/no-image-available-md.png");
//        mref.child("ClothingRelation").child("kapda").child("rating").setValue("0");
//        mref.child("ClothingRelation").child("kapda").child("shopName").setValue("0");
//
//        mref.child("AccessibilityRelation").child("Airport").child("airportDistance").setValue("0");
//        mref.child("AccessibilityRelation").child("Airport").child("nameAirport").setValue("0");
//        mref.child("AccessibilityRelation").child("Railway").child("railwayDistance").setValue("0");
//        mref.child("AccessibilityRelation").child("Railway").child("nameRailway").setValue("0");
//        mref.child("AccessibilityRelation").child("Busstop").child("busDistance").setValue("0");
//        mref.child("AccessibilityRelation").child("Busstop").child("nameBusstop").setValue("0");
//
//
//
//        mref.child("HostRelation").setValue("0");
//        mref.child("TraderRelation").setValue("0");
//        mref.child("GuideRelation").setValue("0");



        image1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            flag++;
            }
        });
        image2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                flag++;
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                flag++;
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                flag++;
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY4);
            }
        });





        mref = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(n1).child("DescriptionRelation");



        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                progress.setMessage("Uploading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setProgress(0);
                progress.setCancelable(false);
                progress.show();



                if(flag>=4)
                {
//
//                    String n = name.getText().toString();
//                    if(!n.equals(null))
//
//                        p++;
                    String h = history.getText().toString();
                    if(!h.equals(null))

                        p++;
                    String c = culture.getText().toString();
                    if(!c.equals(null))
//
                       p++;
                    String s = speciality.getText().toString();
                   if(!s.equals(null))
//
                       p++;
                    String d = desc.getText().toString();
                    if(!d.equals(null))
                    {
                        p++;
                    }
                    String s_d = s_desc.getText().toString();
                    if(!s_d.equals(null))
                    {
                        p++;
                    }


                    if(p==5) {
                       // mref = mref.child(n1).child("DescriptionRelation");
                        StorageReference ref = FirebaseStorage.getInstance().getReference();
                        key1 = "VillageRelation/" + n1 + "/DescriptionRelation/image1.jpg";
                        ref.child("VillageRelation").child(n1).child("DescriptionRelation").child("image1").putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            url = downloadUrl.toString();
                            mref.child("galleryImage1").setValue(key1);
                            count++;
                            Toast.makeText(Description.this, "Image1 added", Toast.LENGTH_SHORT).show();


                            if(count>=4) {
                                progress.dismiss();


                                Intent i = new Intent(Description.this, Village_Present.class);
                                i.putExtra("village",n1);
                                startActivity(i);
                            }


                        }
                         });
                        key2 = "VillageRelation/" + n1 + "/DescriptionRelation/image2.jpg";
                        ref.child("VillageRelation").child(n1).child("DescriptionRelation").child("image2").putFile(selectedImage2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                url = downloadUrl.toString();
                                mref.child("galleryImage2").setValue(key2);
                                count++;
                                Toast.makeText(Description.this, "Image2 added", Toast.LENGTH_SHORT).show();


                                if(count>=4) {
                                    progress.dismiss();


                                    Intent i = new Intent(Description.this, Village_Present.class);
                                    i.putExtra("village",n1);
                                    startActivity(i);
                                }


                            }
                        });
                        key3 = "VillageRelation/" + n1 + "/DescriptionRelation/image3.jpg";
                        ref.child("VillageRelation").child(n1).child("DescriptionRelation").child("image3").putFile(selectedImage3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                url = downloadUrl.toString();
                                mref.child("galleryImage3").setValue(key3);
                                count++;
                                Toast.makeText(Description.this, "Image3 added", Toast.LENGTH_SHORT).show();


                                if(count>=4)
                                {
                                    progress.dismiss();


                                    Intent i = new Intent(Description.this, Village_Present.class);
                                    i.putExtra("village",n1);
                                    startActivity(i);
                                }


                            }
                        });
                        key4 = "VillageRelation/" + n1 + "/DescriptionRelation/image4.jpg";
                        ref.child("VillageRelation").child(n1).child("DescriptionRelation").child("image4").putFile(selectedImage4).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                url = downloadUrl.toString();
                                mref.child("galleryImage4").setValue(key4);
                                count++;
                                Toast.makeText(Description.this, "Image4 added", Toast.LENGTH_SHORT).show();


                                if(count>=4)
                                {
                                    progress.dismiss();


                                    Intent i = new Intent(Description.this, Village_Present.class);
                                    i.putExtra("village",n1);
                                    startActivity(i);
                                }



                            }
                        });




                        mref.child("history").setValue(h);
                        mref.child("nameOfVillage").setValue(n1);
                        mref.child("culture").setValue(c);
                        mref.child("speciality").setValue(s);
                        mref.child("description").setValue(d);
                        mref.child("rating").setValue("3.8");
                        mref.child("shortdes").setValue(s_d);
                        //Toast.makeText(Description.this, "Done updating", Toast.LENGTH_SHORT).show();




                    }
                    else
                    {
                        Snackbar snack = Snackbar.make(desc, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        snack.show();
                    }
                }
                else
                {
                    Toast.makeText(Description.this, "Upload all Images from gallery to the application", Toast.LENGTH_SHORT).show();
                    if(p!=5)
                    {
                        Snackbar snack = Snackbar.make(desc, "All fields are Mandatory", Snackbar.LENGTH_LONG);
                        snack.show();
                    }

                }




            }
        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Description.this,No_Village.class);
//                startActivity(i);
//            }
//        });
//        while(true)
//        {
//            String h = history.getText().toString();
//                    if(!h.equals(null))
//                        counter.add(1);
//                    String c = culture.getText().toString();
//                    if(!c.equals(null))
//                        counter.add(1);
//                    String s = speciality.getText().toString();
//                    if(!s.equals(null))
//                        counter.add(1);
//                    String d = desc.getText().toString();
//                    if(!d.equals(null))
//                        counter.add(1);
//                    String s_d = s_desc.getText().toString();
//                    if(!s_d.equals(null))
//                        counter.add(1);
//            for(int i=0;i<counter.size();i++)
//            {
//                if(counter.get(i)==1)
//                {
//                    x++;
//                }
//            }
//            if(x==5)
//            {
//                update.setEnabled(true);
//                break;
//            }
//
//        }


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            selectedImage1 = data.getData();
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage1);
                m1=bitmap;
                image1.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(requestCode==GET_FROM_GALLERY2 && resultCode == Activity.RESULT_OK) {

            selectedImage2 = data.getData();
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage2);
                m2=bitmap;
                image2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(requestCode==GET_FROM_GALLERY3 && resultCode == Activity.RESULT_OK) {

            selectedImage3 = data.getData();
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage3);
                m3=bitmap;
                image3.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(requestCode==GET_FROM_GALLERY4 && resultCode == Activity.RESULT_OK) {

            selectedImage4 = data.getData();
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage4);
                m4=bitmap;
                image4.setImageBitmap(bitmap);
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
