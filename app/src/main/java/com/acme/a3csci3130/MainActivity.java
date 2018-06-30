/**
 * @author Yangfan Chen
 * <div>Note,This is first activity that will always be executed.</div>
 * <p>When the app starts executing , it will connect to the Firebase database, and display names
 * of all the contacts, otherwise display nothing.
 * two actions exist on this page, when you click the the createContact button
 * {@code createContactButton}, it will jump to the {@link com.acme.a3csci3130.CreateContactAcitivity};
 * when you click one of the names that has been retrieved from the firebase databse, it will jump
 * to {@link com.acme.a3csci3130.DetailViewActivity}.</p>
 *
 * <div class="Reference">
 * <h3>Developer's guide</h3>
 * <p>For more information about connecting to firebase databse,
 * see <a href ="https://firebase.google.com/docs/database/"></a> </p>
 * </div>
 *
 * @see android.support.test.rule.UiThreadTestRule#runOnUiThread(Runnable)
 */
package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends Activity {

    //will create a contact ListView along with firebaseAdatper.
    private ListView contactListView;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    @Override
    /**
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("contacts");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Contact>(this, Contact.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Contact model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact person = (Contact) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    /**
     * @param v of Object View. For more information about View
     * @see View from the external library.
     *
     */
    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateContactAcitivity.class);
        startActivity(intent);
    }

    /**
     * @param person of Object Contact. For more information about Contact method
     * @see Contact in the same Root directory
     *
     */
    private void showDetailView(Contact person)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Contact", person);
        startActivity(intent);
    }



}
