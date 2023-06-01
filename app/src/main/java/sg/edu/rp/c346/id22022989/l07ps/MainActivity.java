package sg.edu.rp.c346.id22022989.l07ps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spn;
    EditText etTxt;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView tvList;
    ArrayList<String> alList;
    ArrayAdapter<String> aaList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTxt = findViewById(R.id.editList);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDel);
        btnClear = findViewById(R.id.buttonClear);
        tvList = findViewById(R.id.listView);
        spn = findViewById(R.id.spinner);
        btnDel.setEnabled(false);

        ArrayList<String> alList;
        alList = new ArrayList<String>();
        aaList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alList);
        tvList.setAdapter(aaList);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTxt.setHint("Type in a new task here");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String text = etTxt.getText().toString();
                                alList.add(text);
                                btnDel.setEnabled(false);
                                aaList.notifyDataSetChanged();
                                etTxt.setText(null);
                            }
                        });


                        btnClear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alList.removeAll(alList);
                                aaList.notifyDataSetChanged();
                                etTxt.setText(null);
                            }

                        });
                        break;
                    case 1:
                        etTxt.setHint("Type in the index of task to be removed");
                        btnDel.setEnabled(true);
                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (alList.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                                }else if(etTxt.getText()!=alList){
                                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                                }else if (alList.isEmpty()==false){
                                    int num = Integer.parseInt(etTxt.getText().toString());
                                    alList.remove(num);
                                    aaList.notifyDataSetChanged();
                                    etTxt.setText(null);
                                } else{

                                }
                            }
                        });
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }
}