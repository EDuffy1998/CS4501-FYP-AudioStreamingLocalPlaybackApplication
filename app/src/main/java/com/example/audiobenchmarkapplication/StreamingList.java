package com.example.audiobenchmarkapplication;

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
import android.widget.Toast;

/*
    Student Name: Edward Duffy
    Student Number: 117501529
    Date: 19/01/2021
    Final Year Project:
    Project: Benchmarks for Many Core Smart Phones
    Project Mentor/Supervisor: Dr Dan Grigoras

    Application: Audio local/streaming application

    Purpose of this activity:
    - Present the users with a choice of the bit rate of music they would like to listen to
    - I've offered the following bit rates for the user to choose from:
    - Low quality: 24kbits/s
    - Normal quality: 96kbits/s
    - High quality: 160kbits/s
    - Very high quality: 320kbits/s
    - Once the user clicks on whatever bit-rate they would like to listen to that music file in they are re-directed
    to an activity that will play that.


 */
public class StreamingList extends AppCompatActivity {
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
        setContentView(R.layout.activity_streaming_list);

        listView = findViewById(R.id.listview);
        // now create an adapter class

        StreamingList.MyAdapter adapter = new StreamingList.MyAdapter(this, mTitle, mDescription, images);
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
                if (position == 0) {
                    Toast.makeText(StreamingList.this, "Going to play audio of 64Kbps", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), SixtyFourKbpsAudioStreamTest.class);
                    startActivity(myIntent);

                }
                if (position == 1) {
                    Toast.makeText(StreamingList.this, "Going to stream Audio of 128Kbps", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), OneHundredTwentyEightAudioStream.class);
                    startActivity(myIntent);

                }
                if (position == 2) {
                    Toast.makeText(StreamingList.this, "Going to stream audio of 192kbps", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), OneHundredNinetyTwoAudioStreamTest.class);
                    startActivity(myIntent);

                }
                if (position == 3) {
                    Toast.makeText(StreamingList.this, "Going to stream audio of 256Kbps", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), TwoHundredFiftySixAudioStreamTest.class);
                    startActivity(myIntent);
                }
                if (position == 4) {
                    Toast.makeText(StreamingList.this,"Going to stream audio of 320Kbps",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), ThreeHundredTwentyAudioStreamTest.class);
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
