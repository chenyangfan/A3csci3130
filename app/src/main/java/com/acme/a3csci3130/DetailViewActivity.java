



package com.acme.a3csci3130;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**

 *
 * @author Yangfan Chen
 * <div>Note,This activity that will not be executed if there is no data existed in the database.</div>
 * <p>when the app executes , and mouse clicks on one of the values from the listView, it will jump
 * to this activity.it will starts executing the {@code com.acme.a3csci3130.DetailViewActivity{...}};
 * then you will see
 *  @value name
 *  @value email
 *  @value number
 *  @value primaryBusinessField
 *  @value address
 *  @value province.
 *  for more information about these fields, and their corresponding ids.
 *  see{@code com.acme.a3csci3130.R.layout.activity_detail_view.xml} for more design information.</p>
 *
 * <div class="Reference">
 * <h3>Developer's guide</h3>
 * <p>For more information about connecting to firebase databse,
 * see <a href ="https://firebase.google.com/docs/database/"></a> </p>
 * </div>
 *
 *
 */
public class DetailViewActivity extends Activity {
    // uses MyApplicationData and Contact from the same directory to create objects.
    private EditText nameField, emailField,numberField,primaryField,addressField,provinceField;
    private MyApplicationData delete;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        numberField = (EditText)findViewById(R.id.number);
        primaryField = (EditText)findViewById(R.id.primaryBusiness);
        addressField = (EditText)findViewById(R.id.address);
        provinceField = (EditText)findViewById(R.id.province) ;
        //long num = receivedPersonInfo.number;
        //String str = Long.toString(num);
        delete = (MyApplicationData)getApplicationContext();
        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            numberField.setText (receivedPersonInfo.number);
            primaryField.setText(receivedPersonInfo.primary_Business);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     *
     * @param v
     * the onclick Fields from xml file will use this method
     */
    public void updateContact(View v){
        //TODO: Update contact funcionality
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String number = numberField.getText().toString();
        String primary = primaryField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        String uid = receivedPersonInfo.uid;
        Contact updateContact = new Contact(uid,name,email,number,primary,address,province);
        delete.firebaseReference.child(receivedPersonInfo.uid).setValue(updateContact);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     *
     * @param v
     */
    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        //MyApplicationData delete ;

        //Log.w(TAG, "This contact has been erased.");
        Toast.makeText(getApplicationContext(),"This account has been removed successfully",Toast.LENGTH_LONG).show();
        delete.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
