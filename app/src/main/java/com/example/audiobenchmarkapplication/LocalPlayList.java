package com.example.audiobenchmarkapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
    Student Name: Edward Duffy
    Student Number: 117501529
    Date: 19/01/2021
    Final Year Project:
    Project: Benchmarks for Many Core Smart Phones
    Project Supervisor/Mentor: Dr.Dan Grigoras

    Application: Streaming/Local Audio Player

    Purpose of this feature:
    - To offer the user a range of bit rates of audio that they can stream
    - This is an important feature to test/monitor/benchmark as audio streaming applications are growing in popularity among smartphone owners

 */


public class LocalPlayList extends AppCompatActivity {

    /*
        Listview is initialized
     */
    ListView listView;
    /*
        Creating arrays which contain all the information which will be put into each cell of the Listview, such as title, description & images
        mTitle: Contains the strings of each bit rate available to play locally
        mDescription:
        images: contains an image of musical note
     */
    String mTitle[] = {"64Kbps","128Kbps","192Kbps","256Kbps","320Kbps"};
    String mDescription[] = {"Play audio clip of bit rate 64Kbps","Play audio clip of bit rate 128Kbps","Play audio clip of bit rate 192Kbps ","Play audio clip of bit rate 256Kbps","Play audio clip of bit rate 320Kbps",};
    int images[] = {R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image};


    /*
        OnCreate method is invoked once the local play list activity is started by the end-user
        The user interface is loaded
        Listview variable is assigned the value of the Listview components ID
        Adapter class is created which will contain the title, description & image
        This adapter will be used to populate every cell with the correct data fro the end-user
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_list);

        listView = findViewById(R.id.listview);

        // creating an adapter class
        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);


        /*
            Creating on click listeners for each cell in the Listview for each bit rate available for the end-user to select
            When the user clicks on a cell in the listview, a text bubble is created using toast.MakeText
            this text bubble informs the user of their selection
            An intent is created passing the new activity chosen as a parameter
            intent is passed as a parameter to the startActivity method and the user is brought to the next activity
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(LocalPlayList.this,"Going to play audio of 64Kbps",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(),TwentyFourKBLocalTest.class);
                    startActivity(myIntent);
                }
                if (position ==  1) {
                    Toast.makeText(LocalPlayList.this,"Going to play audio of 128Kbps",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(),OneHundredTwentyKbpsAudioTest.class);
                    startActivity(myIntent);
                }
                if (position ==  2) {
                    Toast.makeText(LocalPlayList.this,"Going to play audio of 192Kbps",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(),OneHundredNinetyTwoKbpsAudioTest.class);
                    startActivity(myIntent);

                }
                if (position ==  3) {
                    Toast.makeText(LocalPlayList.this,"Going to play audio of 256Kbps",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(),TwoHundredFiftySixKbpsAudioTest.class);
                    startActivity(myIntent);
                }

                if (position == 4) {
                    Toast.makeText(LocalPlayList.this,"Going to play audio of 320Kbps",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(),ThreeHundredTwentyKbpsAudioTest.class);
                    startActivity(myIntent);
                }
            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        /*
            Function that adds content to each cell in the Listview
            Gets the row and inserts the image, description & name into each respective cell by using ID's
         */
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // each cell is populated with the appropriate data such as image, title & description
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);



            /*
                Populated cell is returned to the user
             */
            return row;
        }
    }
}




