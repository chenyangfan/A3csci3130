package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @see Activity for more information about how to use Activity.
 */
public class CreateContactAcitivity extends Activity {
    /**
     * <p>This method imports ui elements and can create contact objects on firebase </p>
     * It used<ul>
     *     @see Button,EditText,MyApplicationData,com.google.firebase.database.FirebaseDatabase
     *     <li>button</li>
     *     <li>EditText</li>
     *     <li>MyApplicationData</li>
     * </ul>
     */
    private Button submitButton;
    private EditText nameField, emailField,numberField,primaryField,addressField,provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        /**
         * Note: The following 7 lines will locate the buttons and EditText Fields based on id
         */
        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        primaryField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
        emailField = (EditText) findViewById(R.id.email);

    }

    //The reason why we use number as a String is that
    //we want to be consistent with the input type even though number is supposed tot
    //be of type int, we can force this by having the user typing only digits.
    //@see R.id.number
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String number = numberField.getText().toString();
        //int num =(int) Integer.parseInt(number);
        String primary = primaryField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(personID, name, email,number,primary,address,province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
