package myfifebaseprojects.t.firebaselistviewretrive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
       private ListView listView;
        private DatabaseReference mdatabase;
        ArrayList<String> userName=new ArrayList<>();
        ArrayList<String> mKey=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.id_listView);
        mdatabase= FirebaseDatabase.getInstance().getReference();
       final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userName);
                    listView.setAdapter(adapter);

            mdatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot,String s) {
                            String value=dataSnapshot.getValue(String.class);
                            userName.add(value);

                            String key=dataSnapshot.getKey();
                            mKey.add(key);
                            adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot,String s) {
                            String changekey=dataSnapshot.getValue(String.class);
                            String key=dataSnapshot.getKey();

                            int index=mKey.indexOf(key);
                            userName.set(index,changekey);
                            adapter.notifyDataSetChanged();



                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot,String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }
}
