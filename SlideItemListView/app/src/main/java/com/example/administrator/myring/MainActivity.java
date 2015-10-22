package com.example.administrator.myring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<ItemData> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listview);


        datas = new ArrayList<>();
        for (int i=0;i<50;i++){

            if((i%10) == 0){
                datas.add(new ItemData(true));
            }else{
                datas.add(new ItemData(false));

            }
        }


        listView.setAdapter(new MyAdapter());
    }

    class ItemData{
        public ItemData(boolean b){
            this.bOpen = b;
        }
        public boolean bOpen = false;
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return new Integer(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vvv;
            if(convertView != null){
                vvv=  convertView;
            }else{
                vvv =   LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false);
            }
            final ItemData itemdd = datas.get(position);
            MySlipeItem ring = (MySlipeItem) vvv.findViewById(R.id.myring);
            ring.setOpenListner(new MySlipeItem.OpenListner() {
                @Override
                public void OnOpen(boolean b) {
                    itemdd.bOpen = b;
                }
            });
            if(itemdd.bOpen){
                ring.setOpen(true);
            }else{
                ring.setOpen(false);
            }

            ring.findViewById(R.id.icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "点击了头像",0).show();
                }
            });

            return vvv;
        }
    }

}
