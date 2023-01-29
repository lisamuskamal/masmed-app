package com.lisa.masmedapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TrackingList extends AppCompatActivity {

    ListView listView;

    String mTrackingNumber[] = {"ER1234677MY", "6788865333" , "WS12766666" , "OL01019111" , "FL0292222"};
    String mName[] = {"MUHAMMAD SYAZWI", "SITI NUR", "AHMAD", "FATIMAH", "Sir Yuy" };
    int images[] = {R.drawable.poslaju, R.drawable.jnt , R.drawable.shopeeexpress , R.drawable.ninjavan, R.drawable.flashexpress};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_list);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTrackingNumber, mName, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), ParcelDetails.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[0]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTrackingNumber[0]);
                    intent.putExtra("description", mName[0]);
                    // also put your position
                    intent.putExtra("position", ""+0);
                    startActivity(intent);

                }

            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTrackingNumber[];
        String rName[];
        int rImages[];

        MyAdapter (Context c, String trackingNumber[], String name[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, trackingNumber);
            this.context = c;
            this.rTrackingNumber = trackingNumber;
            this.rName = name;
            this.rImages = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.row, parent, false);

            ImageView images = row.findViewById(R.id.image);
            TextView myTrackingNumber = row.findViewById(R.id.textView1);
            TextView myName = row.findViewById(R.id.textView2);

            images.setImageResource(rImages[position]);
            myTrackingNumber.setText(rTrackingNumber[position]);
            myName.setText(rName[position]);

            return row;
        }
    }
}