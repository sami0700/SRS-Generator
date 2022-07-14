package com.panshul.srsgenerator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32;
    Button submit,chooseImage;
    String path;
    byte[] hello;
    private static final int STORAGE_CODE=1000;
    private static final int IMAGE_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewBy();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED &&
                            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //permission was not granted, request it!
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,STORAGE_CODE);
                    }
                    else {
                        //permission already granted
                        savepdf();
                    }
                }
                else {
                    //system OS < Marshmallow no required to check runtime permission
                    savepdf();
                }
            }
        });
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), IMAGE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode== IMAGE_CODE && resultCode==RESULT_OK && data!=null) {

            path = data.getData().getPath();
            Uri imageUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            hello = getBytesFromBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public boolean checkEmpty(){
        if (p1.getText().length()==0){
            return false;
        }
        else if (p2.getText().length()==0){
            return false;
        }
        else if (p3.getText().length()==0){
            return false;
        }
        else if (p4.getText().length()==0){
            return false;
        }
        else if (p5.getText().length()==0){
            return false;
        }
        else if (p6.getText().length()==0){
            return false;
        }
        else if (p7.getText().length()==0){
            return false;
        }
        else if (p8.getText().length()==0){
            return false;
        }
        else if (p9.getText().length()==0){
            return false;
        }
        else if (p10.getText().length()==0){
            return false;
        }
        else if (p11.getText().length()==0){
            return false;
        }
        else if (p12.getText().length()==0){
            return false;
        }
        else if (p13.getText().length()==0){
            return false;
        }
        else if (p14.getText().length()==0){
            return false;
        }
        else if (p15.getText().length()==0){
            return false;
        }
        else if (p16.getText().length()==0){
            return false;
        }
        else if (p17.getText().length()==0){
            return false;
        }
        else if (p18.getText().length()==0){
            return false;
        }
        else if (p19.getText().length()==0){
            return false;
        }
        else if (p20.getText().length()==0){
            return false;
        }
        else if (p21.getText().length()==0){
            return false;
        }
        else if (p22.getText().length()==0){
            return false;
        }
        else if (p23.getText().length()==0){
            return false;
        }
        else if (p24.getText().length()==0){
            return false;
        }
        else if (p25.getText().length()==0){
            return false;
        }
        else if (p26.getText().length()==0){
            return false;
        }
        else if (p27.getText().length()==0){
            return false;
        }
        else if (p28.getText().length()==0){
            return false;
        }
        else if (p29.getText().length()==0){
            return false;
        }
        else if (p30.getText().length()==0){
            return false;
        }
        else if (p31.getText().length()==0){
            return false;
        }
        else if (p32.getText().length()==0){
            return false;
        }
        return true;
    }
    private void savepdf() {
        Document document= new Document();
        String fileName = p1.getText().toString()+"_"+ UUID.randomUUID().toString();
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+fileName+".pdf";
        String text1 = "1.1 Project Name: "+p1.getText().toString()
                +"\n1.2 Prepared By: "+p2.getText().toString()
                +"\n1.3 Organisation Name: "+p3.getText().toString();
        String text2="2.1 What is the Goal of the Project?\n"+p4.getText().toString()
                +"\n2.2 What problem does the project solve? \n"+p5.getText().toString()
                +"\n2.3 What is the Vision and Scope? \n"+p6.getText().toString();

        String text3 ="3.1 People Details "
                +"\n    3.1.1 Name: "  +p7.getText().toString()
                +"\n    3.1.2 Age: "  +p8.getText().toString()
                +"\n    3.1.3 Designation: " +p9.getText().toString()
                +"\n    3.1.4 Skills: "+p10.getText().toString()
                +"\n    3.1.5 Job to be Done: "+p11.getText().toString();

        String text4="4.1 What is the origin of the product being specified in this SRS? "+p12.getText().toString()
                +"\n4.2 Product Type: "+p13.getText().toString()
                +"\n4.3 Product Functions"
                +"\n    4.3.1 Product Function: "+p14.getText().toString()
                +"\n    4.3.2 Brief Use of Description: "+p15.getText().toString()
                +"\n    4.3.3 Actions: "+p16.getText().toString()
                +"\n4.4 Operating Environment"
                +"\n    4.4.1 Environment Name: "+p17.getText().toString()
                +"\n    4.4.2 Environment Reason: "+p18.getText().toString()
                +"\n4.5 Development Details"
                +"\n    4.5.1 Technology (Frontend and Backend): "+p19.getText().toString()
                +"\n    4.5.2 Development Methodology: "+p20.getText().toString()
                +"\n    4.5.3 Development Requirement And Security: "+p21.getText().toString();

        String text5="5.1 Function Name: "+p22.getText().toString()
                +"\n5.2 Function Description: "+p23.getText().toString()
                +"\n5.3 System Features: "+p24.getText().toString();

        String text6="6.1 Performance Requirements: "+p25.getText().toString()
                +"\n6.2 Security Requirements: "+p26.getText().toString()
                +"\n6.3 Software Quality Requirements: "+p27.getText().toString()
                +"\n6.4 Other Requirements: "+p28.getText().toString();
        String text7 ="8.1 Timeline: "+p29.getText().toString()
                +"\n8.2 Budget: "+p30.getText().toString()
                +"\n8.3 Risks: "+p31.getText().toString();
        try {

            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
//            Drawable d = getResources().getDrawable(R.drawable.abhinav);
//            BitmapDrawable bitDw = ((BitmapDrawable) d);
//            Bitmap bmp = bitDw.getBitmap();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            Image image = Image.getInstance(stream.toByteArray());
            Image image = Image.getInstance(hello);
            image.scaleAbsolute(550f, 400f);
            Font boldFont = new Font(Font.FontFamily.HELVETICA,45 , Font.BOLD);
            Font font = new Font(Font.FontFamily.HELVETICA,18 , Font.NORMAL);
            Font fontBold = new Font(Font.FontFamily.HELVETICA,22 , Font.BOLD);
            Paragraph para = new Paragraph("SRS Generator\n",boldFont);
            para.setAlignment(Element.ALIGN_MIDDLE);
            document.add(para);
            document.add(new Paragraph("\n1. CoverSection"+"\n",fontBold));
            document.add(new Paragraph(text1,font));
            document.add(new Paragraph("\n2. Project Goal and Problem "+"\n",fontBold));
            document.add(new Paragraph(text2,font));
            document.add(new Paragraph("\n3. User Personas "+"\n",fontBold));
            document.add(new Paragraph(text3,font));
            document.add(new Paragraph("\n4. Project Description"+"\n",fontBold));
            document.add(new Paragraph(text4,font));
            document.add(new Paragraph("\n5. System Features "+"\n",fontBold));
            document.add(new Paragraph(text5,font));
            document.add(new Paragraph("\n6. NON-FUNCTIONAL "+"\n",fontBold));
            document.add(new Paragraph(text6,font));
            document.add(new Paragraph("\n\n\n\n\n7. SRS Diagram "+"\n",fontBold));
            document.add(image);
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("\n8. Timeline , Budget and Risks "+"\n",fontBold));
            document.add(new Paragraph(text7,font));
            document.add(new Paragraph("\n9. Future Plans"+"\n",fontBold));
            document.add(new Paragraph(p32.getText().toString(),font));
            document.close();
            Toast.makeText(this, fileName+".pdf is saved to "+path, Toast.LENGTH_SHORT).show();
            p1.setText("");
            p2.setText("");
            p3.setText("");
            p4.setText("");
            p5.setText("");
            p6.setText("");
            p7.setText("");
            p8.setText("");
            p9.setText("");
            p10.setText("");
            p11.setText("");
            p12.setText("");
            p13.setText("");
            p14.setText("");
            p15.setText("");
            p16.setText("");
            p17.setText("");
            p18.setText("");
            p19.setText("");
            p20.setText("");
            p21.setText("");
            p22.setText("");
            p23.setText("");
            p24.setText("");
            p25.setText("");
            p26.setText("");
            p27.setText("");
            p28.setText("");
            p29.setText("");
            p30.setText("");
            p31.setText("");
            p32.setText("");
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    //permission was granted from popup
                    savepdf();
                }
                else {
                    Toast.makeText(this, "Permission denied....", Toast.LENGTH_SHORT).show();
                }
        }
    }
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    void findViewBy(){
        submit = findViewById(R.id.submitButton);
        chooseImage = findViewById(R.id.SRSbutton);
        p1 = findViewById(R.id.editTextTextPersonName);
        p2 = findViewById(R.id.editTextTextPreparedBy);
        p3 = findViewById(R.id.editTextTextOrgName);
        p4 = findViewById(R.id.editTextTextGoal);
        p5 = findViewById(R.id.editTextTextProblem);
        p6 = findViewById(R.id.editTextTextVision);
        p7 = findViewById(R.id.editTextTextAddPeopleName);
        p8 = findViewById(R.id.editTextTextAddPeopleAge);
        p9 = findViewById(R.id.editTextTextAddPeopleDesignation);
        p10 = findViewById(R.id.editTextTextAddPeopleSkills);
        p11 = findViewById(R.id.editTextTextAddPeopleJob);
        p12 = findViewById(R.id.editTextTextprojDescOrigin);
        p13 = findViewById(R.id.editTextTextProdType);
        p14 = findViewById(R.id.editTextTextProdFuncName);
        p15 = findViewById(R.id.editTextTextProdFuncDesc);
        p16 = findViewById(R.id.editTextTextProdFuncActions);
        p17 = findViewById(R.id.editTextTextEnvName);
        p18 = findViewById(R.id.editTextTextEnvReason);
        p19 = findViewById(R.id.editTextTextDetailsTech);
        p20 = findViewById(R.id.editTextTextDevMethod);
        p21 = findViewById(R.id.editTextTextDevReq);
        p22 = findViewById(R.id.editTextTextSysName);
        p23 = findViewById(R.id.editTextTextFeaturesDesc);
        p24 = findViewById(R.id.editTextTextFeaturesReq);
        p25 = findViewById(R.id.editTextTextPerReq);
        p26 = findViewById(R.id.editTextTextSecReq);
        p27 = findViewById(R.id.editTextTextSoftQuality);
        p28 = findViewById(R.id.editTextTextOthReq);
        p29 = findViewById(R.id.editTextTextTimeline);
        p30 = findViewById(R.id.editTextTextBudget);
        p31 = findViewById(R.id.editTextTextRisks);
        p32 = findViewById(R.id.editTextTextFuturePlans);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(MainActivity.this, Login.class));
    }
}