package com.techview.ayodeji.attendance;

import android.content.Context;
import android.support.design.theme.MaterialComponentsViewInflater;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapter extends  BaseAdapter {

    //private final EditText Cos;
    private TextView course;
    private TextView txtId;
    private ArrayList<MatricNumber> matricNumbers;
    private LayoutInflater mInflater;
    //private Object cos;
    //private String coss;


    public ListAdapter(Context context, ArrayList<MatricNumber> matricNumbers){
        //course = (EditText) findViewById(R.id.Course);
        this.matricNumbers = matricNumbers;
        //this.course = course;
//        Context context1 = context;
        mInflater =  LayoutInflater.from(context);
        //String coss = course.getText().toString();
    }

    @Override
    public int getCount() {
        return matricNumbers.size();
    }

    @Override
    public Object getItem(int position) {
        return matricNumbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return matricNumbers.get(position).getId();
    }

    //public String getItemCourse(int position) {return matricNumbers.get(position).getCourse();}

    //public String getPosition(String position) {return course.getText()}

   // @Override
    //public String getCourse(){
    //    return coss;
    //}
   // String cos = course.getText().toString();
    //String getText(String position ){return course.getText();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //String cos = course.getText().toString();
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_view, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.txtId);
            holder.matricNumber = (TextView) convertView.findViewById(R.id.txtMatricNumber);
            //holder.cos = (TextView) convertView.findViewById(R.id.txtCos);
            //String coss=cos.getText().toString();
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.matricNumber.setText(matricNumbers.get(position).getMatricNo());
        holder.id.setText(String.valueOf(matricNumbers.get(position).getId()));
        //holder.cos.setText(matricNumbers.get(position).getCourse());
        //holder.cos.setText(course(position).getCourse());
        //holder.cos.setText(course.getText());
       // holder.cos.setText(cos.getText());
        //holder.cos.setText(course.getText());
        return convertView;
    }

    //private MatricNumber course(int position) {
    //}

    class ViewHolder{
        public TextView id;
        public TextView matricNumber;
        //public EditText course;
        //public TextView cos;
    }
}
