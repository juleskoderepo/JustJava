package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int baseCoffeePrice = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when clicking the plus button
     */
    public void increment(View view){
        quantity += 1;

        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    /**
     * This method is called when clicking the minus button
     */
    public void decrement(View view){
        if (quantity > 0)  {
            quantity -= 1;
        }

        displayQuantity(quantity);
        displayPrice(calculatePrice());
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @param view
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.edittext_name_input);
        String nameEntered = nameEditText.getText().toString();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.checkbox_whippedcream);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        boolean hasChocloate = chocolateCheckbox.isChecked();

        int totalPrice = calculatePrice();

        if (quantity >= 1){
            displayMessage(createOrderSummary(nameEntered, hasWhippedCream, hasChocloate, totalPrice));
        } else {
            String message = "Please try again. You must select at least 1 coffee to submit an order.";

            displayMessage(message);
        }
        }

    /**
     * Calculates the price of the order.
     *
     * @return total price of the order.
     */
    private int calculatePrice(){
        return quantity * baseCoffeePrice;
    }


    /**
     * This method is called to build the order summary message when
     * the order button is clicked.
     *
     * @param custName
     * @param addWhippedCream
     * @param addChocolate
     * @param totalPrice
     * @return
     */
    private String createOrderSummary(String custName, boolean addWhippedCream, boolean addChocolate,int totalPrice){
        String orderSummary = "";
        orderSummary += "Name: " + custName;
        orderSummary += "\nAdd whipped cream?: " + addWhippedCream;
        orderSummary += "\nAdd chocolate?: " + addChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: " + NumberFormat.getCurrencyInstance().format(totalPrice);
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method is called when the clear button is clicked.
     */
    public void clearOrder(View view) {
        quantity = 0;
        int price = 0;

        displayQuantity(quantity);
        displayPrice(price);
        uncheckToppings();
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numOfCoffees);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method clears the topping checkboxes
     */
    private void uncheckToppings(){
        CheckBox whippedCreamTopping, chocolateTopping;

        whippedCreamTopping = (CheckBox) findViewById(R.id.checkbox_whippedcream);
        chocolateTopping = (CheckBox) findViewById(R.id.checkbox_chocolate);

        if(whippedCreamTopping.isChecked()){
            whippedCreamTopping.toggle();
        }

        if (chocolateTopping.isChecked()){
            chocolateTopping.toggle();
        }
    }

    /**
     * This method displays the order summary message.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
