package com.example.hp.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=0;
    Boolean isWCChecked;
    String priceMessage="";



    public void submitOrder(View view) {

//        //displayPrice(quantity*5);
//        int price=calculatePrice();
//        //displayMessage("Total:$"+price+"\nThank you");
//        String d=createOrderSummary(price);
//        displayMessage(d);
        EditText et1=(EditText) findViewById(R.id.editText1);
        String  name=et1.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order by:"+name);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(5));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

   /* private void displayMessage(String message) {
        TextView message_text_view=(TextView)findViewById(R.id.message);
        message_text_view.setText(message);
    }*/


    public void decrement(View view) {
        if (quantity>0) {
            quantity = quantity - 1;
        }
        else {
            Toast.makeText(this,"You Cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
        }
        TextView t1=(TextView)findViewById(R.id.quantity_text_view);
        t1.setText(Integer.toString(quantity));
    }

    public void increment(View view) {
        if (quantity<100){
        quantity=quantity+1;
        }
        else{
            Toast.makeText(this,"YOU CANNOT HAVE MORE THAN 100 coffees",Toast.LENGTH_SHORT).show();
        }
        TextView t1=(TextView)findViewById(R.id.quantity_text_view);
        t1.setText(Integer.toString(quantity));
    }

    public int calculatePrice()
    {
        int calcPrice=0;
        calcPrice=quantity*5;
        return calcPrice;
    }

    public String createOrderSummary(int price)
    {
        CheckBox cb=(CheckBox)findViewById(R.id.checkbox1);
        CheckBox cb2=(CheckBox)findViewById(R.id.checkbox2);
        EditText et1=(EditText)findViewById(R.id.editText1);
        String name;

        name=et1.getText().toString();
        priceMessage="Name: "+name;
        priceMessage=priceMessage+"\nQuantity: "+quantity;
        if (cb.isChecked() && cb2.isChecked())
        {
            priceMessage=priceMessage+"\nBoth cream and choclate selected";
            price=price+(7*quantity);
        }
        else if (isWCChecked=cb2.isChecked()){
            priceMessage=priceMessage+"\nChoclate is selected";
            price=price+5*quantity;
        }
        else if (cb.isChecked()){

            priceMessage=priceMessage+"\nWhipped Cream Selcted";
            price=price+2*quantity;
        }
        else {
            priceMessage=priceMessage+"\nSimple coffee";
        }
        priceMessage=priceMessage+"\nTotal: $"+price+"\nThank you";


        return priceMessage;
    }
}
