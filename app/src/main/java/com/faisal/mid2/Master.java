package com.faisal.mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Master extends AppCompatActivity {
    DatabaseHelper DB ;

    EditText pID,pName , pSName , pNID;

    String pIDTXT ,pNameTXT , pSNameTXT , pNIDTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        DB = new DatabaseHelper(this);

        pID = (EditText) findViewById(R.id.idETXT);
        pName = (EditText) findViewById(R.id.pName);
        pSName = (EditText) findViewById(R.id.pQuantity);
        pNID = (EditText) findViewById(R.id.pReview);


        Button addBtn = (Button) findViewById(R.id.button3);

        Button nextBtn2 = (Button) findViewById(R.id.nextBtn2);
        Button backBtn = (Button) findViewById(R.id.backBtn);

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Master.this , Master2.class));

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Master.this , MainActivity.class));

            }
        });



       /* viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = DB.getListContents();
                StringBuffer buffer = new StringBuffer();

                while(cur.moveToNext()){
                    buffer.append("id: "+cur.getString(0)+"\n");
                    buffer.append("Name: "+cur.getString(1)+"\n");
                    buffer.append("Salary: "+cur.getString(2)+"\n\n");
                }

                viewTXT.setText(buffer.toString());
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setCancelable(true);
//            builder.setTitle("All Emplpyees");
//            builder.setMessage(buffer.toString());
//            builder.show();


            }
        });*/
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pIDTXT = pID.getText().toString();
                pNameTXT = pName.getText().toString();
                pSNameTXT = pSName.getText().toString();
                pNIDTXT = pNID.getText().toString();

                if(pNameTXT.equals("") || pSNameTXT.equals("") || pNIDTXT.equals("") || pIDTXT.equals("")){

                    Toast.makeText(Master.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    Log.d("AddButton","FIELD EMPTY");
                    return;

                }

                if (!DB.addData(pNameTXT, pSNameTXT , pNIDTXT , pIDTXT)){
                    Toast.makeText(Master.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    Log.d("AddButton","INSERT FAILED");

                } else {
                    Toast.makeText(Master.this, "Insertion Success", Toast.LENGTH_SHORT).show();
                    Log.d("AddButton","INSERT SUCCESS");

                }
            }
        });

        /*findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pNameTXT = pName.getText().toString();

                if (pNameTXT.equals("")){
                    Toast.makeText(Master.this, "Name field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor = DB.structuredQuery(pNameTXT);
                String curID = cursor.getString(0);
                String curName = cursor.getString(1);
                String curPQuantity = cursor.getString(2);
                String curReview = cursor.getString(3);

                pID.setText(curID);
                pEmail.setText(curPQuantity);
                pPhone.setText(curReview);
            }
        });*/

       /* delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pIDTXT = pID.getText().toString();

                if(pIDTXT.equals("NaN") || pIDTXT.equals("")){
                    Toast.makeText(Master.this, "Find the product first", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (DB.deleteData(pIDTXT)){
                    Toast.makeText(Master.this, "Product got deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Master.this, "Could not delete product", Toast.LENGTH_SHORT).show();
                }

                pID.setText("");
                pName.setText("");
                pEmail.setText("");
                pPhone.setText("");

            }
        });*/


    }
}