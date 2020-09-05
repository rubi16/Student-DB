package com.example.studentdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBhelper myDB;
    EditText edtFName , edtLName, edtMarks , edtId;
    Button btnAddData,btnViewAll,btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB= new DBhelper(this);

        edtFName= (EditText)findViewById(R.id.ETFname);
        edtLName= (EditText)findViewById(R.id.ETLname);
        edtMarks= (EditText)findViewById(R.id.ETmarks);
        edtId=(EditText)findViewById(R.id.ETID);
        btnAddData=(Button)findViewById(R.id.btn_AddData);
        btnViewAll=(Button)findViewById(R.id.btn_ViewData);
        btnUpdate=(Button)findViewById(R.id.btn_UpdatData);
        btnDelete=(Button)findViewById(R.id.btn_DltData);
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

    }
    public  void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted= myDB.insertdata(edtFName.getText().toString(),
                                edtLName.getText().toString(),
                                edtMarks.getText().toString());

               if(isInserted == true)
                   Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ViewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= myDB.getAllData();
                if(res.getCount() == 0){
                    //Show mesage
                    ShowMessages("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("First Name :"+ res.getString(1)+"\n");
                    buffer.append("Last Name :"+ res.getString(2)+"\n");
                    buffer.append("Marks :"+ res.getString(3)+"\n\n");
                }
                //Show all data
                ShowMessages("Data",buffer.toString());
            }
        });
    }

    public void ShowMessages(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdated= myDB.UpdateData(edtId.getText().toString(),
                        edtFName.getText().toString(),
                        edtLName.getText().toString(),
                        edtMarks.getText().toString());
                if(isupdated==true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedrows= myDB.delData(edtId.getText().toString());
                if(deletedrows>0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
}