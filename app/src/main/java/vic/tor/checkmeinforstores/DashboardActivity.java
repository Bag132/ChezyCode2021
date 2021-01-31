package vic.tor.checkmeinforstores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

@SuppressLint("SetTextI18n")
public class DashboardActivity extends AppCompatActivity {
    private TextView mCustomersInStoreText, mLineCountText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mCustomersInStoreText = findViewById(R.id.text_customer_status);
        mLineCountText = findViewById(R.id.text_line_status);

        final RecyclerView lineRecycler = findViewById(R.id.recycler_line);


        ConstraintLayout customersInsideButton = findViewById(R.id.button_customers_inside),
                closeStoreButton = findViewById(R.id.button_close_store);

        lineRecycler.setLayoutManager(new LinearLayoutManager(this));
        final LineCustomerAdapter adapter = new LineCustomerAdapter(this, Line.getInstance().getCustomers());
        lineRecycler.setAdapter(adapter);

        mCustomersInStoreText.setText(("Customers In Store: " + Store.getInstance().getCustomers().size() + " / " + Store.getInstance().getCapacity()));
        mLineCountText.setText((Line.getInstance().getCustomers().size() + " customer(s) in line"));


        customersInsideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, CustomersSearchActivity.class));
            }
        });

        closeStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store.getInstance().closeStore();
                startActivity(new Intent(DashboardActivity.this, StoreSetupActivity.class));
            }
        });

        Store.getInstance().getStoreReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (final DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals("new-customer") && dataSnapshot.getValue() != null) {
                        HashMap<String, Object> newUser = (HashMap<String, Object>) dataSnapshot.getValue();
                        Customer c = new Customer((String) newUser.get("name"), ((Long)newUser.get("size")).intValue());
                        if (!Line.getInstance().containsCustomer(c.getName()))
                            Line.getInstance().addCustomer(c);

                        dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                adapter.notifyDataSetChanged();
                                startActivity(new Intent(DashboardActivity.this, LoadActivity.class));
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
