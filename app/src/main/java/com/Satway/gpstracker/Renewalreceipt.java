package com.Satway.gpstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//renewal payment receipt
public class Renewalreceipt extends AppCompatActivity {
    private static final String TAG = "reciept";

    TextView autoTextIncreament, serialno,comapnyname,dealername,dates;
    String stringValue="SATWAY";
    Button save;
    TextView date, custName, rupees;
    SharedPreferences erences;
    String value;
    String value1 ;
    String value2 ;
    String value3 ;
    String value4 ;
    String value5 ;
    String value6 ;
    String value7 ;
    String value8 ;
    String name1;
    String imeikeys;
    String iccidkeys;
    String uinkeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewalreceipt);

        erences=getSharedPreferences("cridential", Context.MODE_PRIVATE);
        name1 = erences.getString("dealername","");
        date=findViewById(R.id.date);
        comapnyname=findViewById(R.id.comapnyname);
        dealername=findViewById(R.id.dealername);
        serialno=findViewById(R.id.serialno);
        save = findViewById(R.id.save);
        date = findViewById(R.id.datePicker);
        custName = findViewById(R.id.dealernames);
//        quantityno = findViewById(R.id.quantityno);
        rupees = findViewById(R.id.granttotalnos);
//        priceno = findViewById(R.id.priceno);
//        totalno = findViewById(R.id.totalno);
//        quantitynos = findViewById(R.id.quantitynos);
//        pricenos = findViewById(R.id.pricenos);
//        totalnos = findViewById(R.id.totalnos);
        autoTextIncreament = findViewById(R.id.gstno);

        Intent intent = getIntent();
        imeikeys = intent.getStringExtra("imeikey1");
        iccidkeys = intent.getStringExtra("iccidkey1");
        uinkeys = intent.getStringExtra("upikey1");
        value = intent.getStringExtra("key7");
        value = getIntent().getStringExtra("key");
//        value1 = getIntent().getStringExtra("key1");
//        value2 = getIntent().getStringExtra("key2");
        value3 = getIntent().getStringExtra("key3");
//        value4 = getIntent().getStringExtra("key4");
        value5 = getIntent().getStringExtra("key5");
//        value6 = getIntent().getStringExtra("key6");
        System.out.println(value6);
//        value7 = getIntent().getStringExtra("key7");
        Log.d(TAG, "onCreate: value7"+value7);

        System.out.println(value7);
//        value8 = getIntent().getStringExtra("key8");
        System.out.println(value8);
//        totalno.setText(value3);
        custName.setText(name1);
        Date dateCurrent = Calendar.getInstance().getTime();
        date.setText(dateCurrent.toString());
        Log.d(TAG, "onCreate: date"+date);
//        rupees.setText(value6);
//        quantityno.setText(value1);
//        priceno.setText(value7);
//        quantitynos.setText(value2);
//        pricenos.setText(value4);
//        totalnos.setText(value8);
        autoTextIncreament = findViewById(R.id.gstno);
        autoTextIncreament.setText(getValue());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        String[] parmission={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(parmission,1000);
                    }
                    else savepdf();
                }
                else savepdf();
            }
        });
    }
    private  void savepdf()
    {
        Document doc=new Document();
        String mfile=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        String mfilepath= Environment.getExternalStorageDirectory()+"/"+mfile+"satway payment reciept.pdf";
        Font smallBold=new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
        Font myfont=new Font(Font.FontFamily.TIMES_ROMAN,26,Font.BOLDITALIC);
        try{
            PdfWriter.getInstance(doc,new FileOutputStream(mfilepath));
            doc.open();

            String head="\t\t\t\t\t"+"SATWAY INFOSYSTEMS PVT LTD";
            head=head+"\n\n\n";
            String text="Thank you for making your payment.";
            text = text+"\n\n\n\n";
            String ss="                                           "+"Screenshot this page";
            ss = ss+"\n";
            String ack="Online Payment Transaction"+"\n"+"Acknowledgement";
            ack = ack+"\n\n";
            String mtext=serialno.getText().toString()+"\t";
            mtext = mtext+"                    "+autoTextIncreament.getText().toString()+"\n\n";
            String mtext1=comapnyname.getText().toString()+"\t";
            mtext1 = mtext1+"             "+"SATWAY INFOSYSTEMS PVT LTD"+"\n\n";
            String mtext2=dealername.getText().toString()+"\t";
            mtext2 = mtext2+"                  "+custName.getText().toString()+"\n\n";
            String mtext3="Date"+"\t";
            mtext3 = mtext3+"                               "+date.getText().toString()+"\n\n";
            String mtext4="IMEI"+"\t";
            mtext4 = mtext4+"               "+imeikeys+"\n";
            String mtext5="ICCID"+"\t";
            mtext5 = mtext5+"               "+iccidkeys+"\n";
            String mtext6="ICCID"+"\t";
            mtext6 = mtext6+"               "+uinkeys+"\n";
            doc.addAuthor("");
            doc.add(new Paragraph(head,myfont));
            doc.add(new Paragraph(text,myfont));
            doc.add(new Paragraph(ss,myfont));
            doc.add(new Paragraph(ack,myfont));
            doc.add(new Paragraph(mtext,smallBold));
            doc.add(new Paragraph(mtext1,smallBold));
            doc.add(new Paragraph(mtext2, smallBold));
            doc.add(new Paragraph(mtext3, smallBold));
            doc.add(new Paragraph(mtext4, smallBold));
            doc.add(new Paragraph(mtext5, smallBold));

            doc.close();
            Toast.makeText(this, ""+mfile+".pdf"+" is saved to "+mfilepath, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this,"This is Error msg : " +e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case  1000:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    savepdf();
                }
                else Toast.makeText(this, "permission denied..", Toast.LENGTH_SHORT).show();
        }
    }
    private String getValue() {
        long millis = new Date().getTime();
        return stringValue+String.valueOf(millis);
    }
}

