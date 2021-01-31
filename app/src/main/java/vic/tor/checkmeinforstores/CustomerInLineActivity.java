package vic.tor.checkmeinforstores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomerInLineActivity extends AppCompatActivity {
    public static Customer currentCustomer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_customer);

        ((TextView) findViewById(R.id.text_inside_customer_name)).setText(currentCustomer.getName());
        ((TextView) findViewById(R.id.text_inside_customer_group_pop)).setText(("Customer Store Population: " + Store.getInstance().getCustomers().size()));
        ((TextView) findViewById(R.id.text_line_customer_pop)).setText((currentCustomer.getName() +  " population: " + currentCustomer.getGroupSize()));

        ((ConstraintLayout) findViewById(R.id.text_check_in_customer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store.getInstance().addCustomer(currentCustomer);
                Line.getInstance().removeCustomer(currentCustomer.getName());
                startActivity(new Intent(CustomerInLineActivity.this, DashboardActivity.class));
            }
        });

        ((ConstraintLayout) findViewById(R.id.button_remove_from_line)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Line.getInstance().removeCustomer(currentCustomer.getName());
                startActivity(new Intent(CustomerInLineActivity.this, DashboardActivity.class));
            }
        });
    }
}
