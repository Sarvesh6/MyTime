package com.example.sarvesh.mytime;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sarvesh on 4/13/2016.
 */
public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.ViewHolder>{
    Context context;
ArrayList<Subject> subject;

    SubjectRecyclerViewAdapter(Context context, ArrayList<Subject> subject){
        this.subject = subject;
        this.context = context;
    }
    //  Usually involves inflating a layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(viewType == 1){
//
//        }
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        //  vh.]]]]
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Subject s = subject.get(position);
        holder.mTextView.setText(s.getName());
        int k = (int) (s.getAtt() * 100);
        double h = ((double) k / 100);


        holder.mEditText.setText(h + "%");
        if(h<75)
            holder.mEditText.setTextColor(Color.RED);
        else
            holder.mEditText.setTextColor(Color.rgb(41, 146, 29));
        holder.mImageView.setTag(holder);
        holder.mImageView2.setTag(holder);
        holder.mTextView.setTag(holder);
holder.button.setTag(holder);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) view.getTag();
                Intent i = new Intent();
                i.setClass(context, MainActivity.class);
                i.putExtra("pos", "yes");
                Toast.makeText(context, "selected item is a file", Toast.LENGTH_SHORT).show();
                context.startActivity(i);
            }
        };
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to delete?");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TimeSQLHelper sqlHelper = new TimeSQLHelper(context, 1);
                        SQLiteDatabase db = sqlHelper.getWritableDatabase();
                        subject.remove(position);
                        try {

                            db.delete(TimeSQLHelper.FAV_TABLE_NAME, "title= '" + s.getName() + "'", null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position,subject.size());
                    }
                });
                builder.create().show();
            }


        };




        holder.mEditText.setOnClickListener(clickListener);
        holder.button.setOnClickListener(click);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               double total= s.getTotal();
                total++;
                s.setTotal(total);
                double att=s.getAtts();
                att++;
                s.setAtts(att);
                s.setAtt(att * 100 / total);
//                Toast.makeText(context,s.getAtt()+" ",Toast.LENGTH_LONG).show();
                TimeSQLHelper  sqlHelper = new TimeSQLHelper (context, 1);
                long id=0;
                SQLiteDatabase gb = sqlHelper.getReadableDatabase();
                try {
                    id=getid(s.getName(),gb);
                } catch (SQLException e) {
                    Toast.makeText(context, "id not get" + id + " ", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                gb.close();

                    ContentValues cv = new ContentValues();

                    cv.put(TimeSQLHelper.ATTENDENCE, s.getAtts() + "");
                    cv.put(TimeSQLHelper.att, s.getAtt() + "");
//
                cv.put(TimeSQLHelper.TOTAL, s.getTotal() + "");
                    SQLiteDatabase db = sqlHelper.getWritableDatabase();
                    db.update(TimeSQLHelper.FAV_TABLE_NAME, cv, "_id="+id, null);
//                    Toast.makeText(context, "id" + id + " ", Toast.LENGTH_LONG).show();
                int k= (int) (s.getAtt()*100);
                double h=  ((double)k/100);
                holder.mEditText.setText(h + "%");
                if(h<75)
                    holder.mEditText.setTextColor(Color.RED);
                else
                    holder.mEditText.setTextColor(Color.rgb(41, 146, 29));
                db.close();
                }

        });
        holder.mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                double total = s.getTotal();
                total++;
                s.setTotal(total);
                s.setAtt(s.getAtts() * 100 / total);


//                Toast.makeText(context,s.getAtt()+"",Toast.LENGTH_LONG).show();
                TimeSQLHelper  sqlHelper = new TimeSQLHelper (context, 1);
                long id = 0;
                SQLiteDatabase gb = sqlHelper.getReadableDatabase();
                try {
                    id=getid(s.getName(),gb);
                } catch (SQLException e) {
                    Toast.makeText(context, "id not get" + id + " ", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                    ContentValues cv = new ContentValues();

                    cv.put(TimeSQLHelper.ATTENDENCE, s.getAtts() + "");
                    cv.put(TimeSQLHelper.att, s.getAtt() + "");
//                        Date date = new Date();
//                        j.bate=date;
                    cv.put(TimeSQLHelper.TOTAL, s.getTotal() + "");
                SQLiteDatabase db = sqlHelper.getWritableDatabase();
                    db.update(TimeSQLHelper.FAV_TABLE_NAME, cv, "_id=" + id, null);
//                    Toast.makeText(context, "id" + id + " ", Toast.LENGTH_LONG).show();
                int k= (int) (s.getAtt()*100);
                double h=  ((double)k/100);
                holder.mEditText.setText(h + "%");
                if(h<75)
                    holder.mEditText.setTextColor(Color.RED);
                else
                    holder.mEditText.setTextColor(Color.rgb(41, 146, 29));
                db.close();
//                }

            }

        });
//
    }

//holder.mEditText.setText(s.getAtt()+" ");


    public long getid(String  heading,SQLiteDatabase db) throws  SQLException
    {


        long recc=0;
        String rec=null;
        Cursor mCursor = db.rawQuery(
                "SELECT _id  FROM time WHERE title= '"+heading+"'" , null);
        if (mCursor.getCount() > 0)
        {
            mCursor.moveToFirst();
            recc=mCursor.getLong(0);
            rec=String.valueOf(recc);
        }
        return recc;
    }

    @Override
    public int getItemCount() {
        return subject.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView mImageView;
        public TextView mEditText;
        public ImageView mImageView2;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView2=(ImageView) itemView.findViewById(R.id.image_icon2);
            mTextView = (TextView) itemView.findViewById(R.id.info_text1);
            mImageView = (ImageView) itemView.findViewById(R.id.image_icon1);
            mEditText = (TextView) itemView.findViewById(R.id.info_text2);
            button=(Button) itemView.findViewById(R.id.button);
        }

    }
}
