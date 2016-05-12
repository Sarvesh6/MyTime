package com.example.sarvesh.mytime;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    ArrayList<Subject> subject;

    RecyclerView recyclerView;
    public final int Vertical_Space=24;
    SubjectRecyclerViewAdapter fAdapter;
    private RecyclerView.LayoutManager layoutManager;
int cnt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        b=(Button)findViewById(R.id.button);
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        subject=getjoblist();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new VerticalItemSpaceDecoration(Vertical_Space));

//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        RecyclerView.ItemDecoration itemDecoration = new
//                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(layoutManager);

         fAdapter = new SubjectRecyclerViewAdapter(this, subject);
        recyclerView.setAdapter(fAdapter);

//

    }


    public ArrayList<Subject> getjoblist ()
    {
        TimeSQLHelper sqlHelper = new TimeSQLHelper(this, 1);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
//

        Cursor c = db.query(true, TimeSQLHelper.FAV_TABLE_NAME, null, null, null,
                null, null, null, null/*FileManagerSQlHelper.FAV_TABLE_DATE_ADDED + " DESC"*/, null);
//
        ArrayList<Subject> jobs = new ArrayList<>();

            c.moveToFirst();
//
            while (!c.isAfterLast()) {
                String path = c.getString(c.getColumnIndex(TimeSQLHelper.FAV_TABLE_TITLE));
                String sath = c.getString(c.getColumnIndex(TimeSQLHelper.ATTENDENCE));
                String cath = c.getString(c.getColumnIndex(TimeSQLHelper.TOTAL));
                String rath = c.getString(c.getColumnIndex(TimeSQLHelper.att));
                Subject j = new Subject();
                    j.setName(path);
//                Toast.makeText(ScrollingActivity.this,path+sath+cath+rath+" ",Toast.LENGTH_LONG).show();
                    double d = Double.parseDouble(sath);
                    j.setAtts(d);
//                Toast.makeText(ScrollingActivity.this,d+" ",Toast.LENGTH_LONG).show();
                    d = Double.parseDouble(cath);
                    j.setTotal(d);
                    d = Double.parseDouble(rath);
                    j.setAtt(d);
                    jobs.add(jobs.size(), j);
                    c.moveToNext();
                cnt++;
//                    h.moveToNext();
//                    m.moveToNext();
//                    p.moveToNext();
                }
//        Toast.makeText(ScrollingActivity.this,cnt+"",Toast.LENGTH_LONG).show();
//
        c.close();
        db.close();

        return jobs;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//
//s.setAtt(100);
//            s.setName(" ");

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ScrollingActivity.this);

            //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Subject");

            // Setting Dialog Message
            alertDialog.setMessage("Enter Subject Name");
            final EditText input = new EditText(this);
            alertDialog.setView(input);

            // Setting Icon to Dialog
//            alertDialog.setIcon(R.drawable.key);

            // Setting Positive "Yes" Button

          alertDialog.setPositiveButton("YES",
                  new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                          Subject s = new Subject();
                          // Write your code here to execute after dialog
                          Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                          s.setName(input.getText().toString());
                          s.setTotal(0);
                          s.setAtt(100);
                          s.setAtts(0);
                          TimeSQLHelper sqlHelper = new TimeSQLHelper(ScrollingActivity.this, 1);
                          SQLiteDatabase db = sqlHelper.getWritableDatabase();
                          ContentValues cv = new ContentValues();
                          cv.put(TimeSQLHelper.FAV_TABLE_TITLE, s.getName());
                          cv.put(TimeSQLHelper.ATTENDENCE, s.getAtts()+"");
                          cv.put(TimeSQLHelper.att, s.getAtt()+"");
//                        Date date = new Date();
//                        j.bate=date;
                          cv.put(TimeSQLHelper.TOTAL, s.getTotal()+"");
                          db.insert(TimeSQLHelper.FAV_TABLE_NAME, null, cv);
                          subject.add(subject.size(),s);
                          fAdapter.notifyItemRangeChanged(0, fAdapter.getItemCount());
                          db.close();

                      }
                  });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            dialog.cancel();
                        }
                    });

            // closed

            // Showing Alert Message
            alertDialog.show();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

