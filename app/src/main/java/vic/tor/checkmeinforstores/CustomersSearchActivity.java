package vic.tor.checkmeinforstores;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomersSearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customers);

        RecyclerView customerRecycler = findViewById(R.id.recycler_inside_search);
        customerRecycler.setLayoutManager(new LinearLayoutManager(this));
        final CustomerAdapter customerAdapter = new CustomerAdapter(this, Store.getInstance().getCustomers());
        customerRecycler.setAdapter(customerAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
