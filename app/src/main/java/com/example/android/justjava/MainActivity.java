package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    int baseCoffeePrice = 4;
    int tax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayPrice(calculatePrice(hasTopping(R.id.checkbox_whippedcream), hasTopping(R.id.checkbox_chocolate)));
    }

    /**
     * Add save instance
     */

    /**
     * Add restore instance
     */

    /**
     * Add checkbox listener to update price total when checkbox is
     * toggled and quantity is > 0.
     */

    /**
     * This method is called when clicking the plus button
     */
    public void increment(View view) {
        //Do not allow quantity over 100.
        if (quantity == 100) {
            //Notify the user of the upper limit for orders
            Toast.makeText(getApplicationContext(), "Order limit reached", Toast.LENGTH_SHORT).show();
            //Exit method
            return;
        }

        quantity += 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(hasTopping(R.id.checkbox_whippedcream), hasTopping(R.id.checkbox_chocolate)));
    }

    /**
     * This method is called when clicking the minus button
     */
    public void decrement(View view) {
        //Do not allow quantity less than 1.
        if (quantity == 1) {
            //Notify user of the lower limit for orders
            Toast.makeText(getApplicationContext(), "Must order at least 1 coffee", Toast.LENGTH_SHORT).show();
            //Exit method
            return;
        }

        quantity -= 1;
        displayQuantity(quantity);
        displayPrice(calculatePrice(hasTopping(R.id.checkbox_whippedcream), hasTopping(R.id.checkbox_chocolate)));
    }

    /**
     * This method is called to determine whether a topping is selected
     * and returns true or false.
     *
     * @param checkBoxID ID number of the topping checkbox
     * @return addTopping the boolean state of the topping checkbox
     */
    private boolean hasTopping(int checkBoxID) {
        CheckBox toppingCheckBox = (CheckBox) findViewById(checkBoxID);
        boolean addTopping = toppingCheckBox.isChecked();

        return addTopping;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.edittext_name_input);
        String nameEntered = nameEditText.getText().toString();

        boolean hasWhippedCream = hasTopping(R.id.checkbox_whippedcream);
        boolean hasChocolate = hasTopping(R.id.checkbox_chocolate);

        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        displayMessage(createOrderSummary(nameEntered, hasWhippedCream, hasChocolate, totalPrice));
    }

    /**
     * Calculates the price of the order.
     *
     * @return grandTotal total price of the order.
     */
    private int calculatePrice(boolean addWhip, boolean addChocolate) {
        int grandTotal;
        int subTotal = baseCoffeePrice;

        //Add $1 to price if user wants whipped cream
        if (addWhip) {
            subTotal += 1;
        }

        //Add $2 to price if user wants chocolate
        if (addChocolate) {
            subTotal += 2;
        }

        // Calculate the total price based on the number ordered and tax, if any.
        grandTotal = (subTotal * quantity) * (1 + tax);

        return grandTotal;
    }


    /**
     * This method is called to build the order summary message when
     * the order button is clicked.
     *
     * @param custName
     * @param addWhippedCream
     * @param addChocolate
     * @param totalPrice
     * @return orderSummary message displayed summarizing the user's order
     */
    private String createOrderSummary(String custName, boolean addWhippedCream, boolean addChocolate, int totalPrice) {
        String orderSummary = "";
        orderSummary += "Name: " + custName;

        if (addWhippedCream) {
            orderSummary += "\nAdd whip";
        } else {
            orderSummary += "\nNo whip";
        }

        if (addChocolate) {
            orderSummary += "\nAdd chocolate";
        } else {
            orderSummary += "\nNo chocolate";
        }

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
    private void uncheckToppings() {
        CheckBox whippedCreamTopping, chocolateTopping;

        whippedCreamTopping = (CheckBox) findViewById(R.id.checkbox_whippedcream);
        chocolateTopping = (CheckBox) findViewById(R.id.checkbox_chocolate);

        if (whippedCreamTopping.isChecked()) {
            whippedCreamTopping.toggle();
        }

        if (chocolateTopping.isChecked()) {
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
