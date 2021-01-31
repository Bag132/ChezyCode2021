package vic.tor.checkmeinforstores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class InsideCustomerActivity extends AppCompatActivity {
    public static Customer currentCustomer = new Customer(" ", 0);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_inside);

        ((TextView) findViewById(R.id.text_inside_customer_name)).setText(currentCustomer.getName());
        ((TextView) findViewById(R.id.text_inside_customer_group_pop))
                .setText((currentCustomer.getName() + " population: " + currentCustomer.getGroupSize()));
        ((ConstraintLayout) findViewById(R.id.button_check_out_customer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store.getInstance().removeCustomer(currentCustomer.getName()); // yeesh kinda weirdchamp
                startActivity(new Intent(InsideCustomerActivity.this, CustomersSearchActivity.class));
            }
        });
    }
}
