package com.faisal.mid2;

import androidx.appcompat.app.AlertDialog;
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

public class Master2 extends AppCompatActivity {

    EditText input;
    DatabaseHelper DB ;
    TextView viewTXT;

    Button viewBtn , delBtn , backBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master2);

        DB = new DatabaseHelper(this);

        viewBtn = (Button) findViewById(R.id.viewBtn);
        delBtn = (Button) findViewById(R.id.delBtn);
        backBtn2 = (Button) findViewById(R.id.backBtn2);

        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Master2.this , Master.class));

            }
        });
        input = (EditText) findViewById(R.id.input);
        viewTXT = (TextView) findViewById(R.id.viewTXT);


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = DB.getListContents();
                StringBuffer buffer = new StringBuffer();

                while(cur.moveToNext()){
                    buffer.append("id: "+cur.getString(0)+"\n");
                    buffer.append("Name: "+cur.getString(1)+"\n");
                    buffer.append("Surname: "+cur.getString(2)+"\n\n");
                    buffer.append("NationalID: "+cur.getString(3)+"\n\n");

                }

                viewTXT.setText(buffer.toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(Master2.this);
               builder.setCancelable(true);
           builder.setTitle("All Emplpyees");
          builder.setMessage(buffer.toString());
          builder.show();


            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pIDTXT = input.getText().toString();

                if(pIDTXT.equals("NaN") || pIDTXT.equals("")){
                    Toast.makeText(Master2.this, "Find the name first", Toast.LENGTH_SHORT).show();
                    Log.d("DelButton","FIND NAME FIRST");
                    return;
                }
                if (DB.deleteData(pIDTXT)){
                    Toast.makeText(Master2.this, "name got deleted", Toast.LENGTH_SHORT).show();
                    Log.d("DelButton","NAME DELETED");

                }else{
                    Toast.makeText(Master2.this, "Could not delete product", Toast.LENGTH_SHORT).show();
                    Log.d("DelButton","NAME CANT DELETE");
                }

                input.setText("");


            }
        });
    }
}