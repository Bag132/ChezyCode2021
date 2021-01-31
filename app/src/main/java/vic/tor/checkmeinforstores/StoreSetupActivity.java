package vic.tor.checkmeinforstores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

@SuppressLint("ClickableViewAccessibility")
public class StoreSetupActivity extends AppCompatActivity {
    private String storeName = "", capacity = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setup);


        final ConstraintLayout openStoreButton = findViewById(R.id.open_store_button),
                createCodeButton = findViewById(R.id.button_create_code),
                downloadCodeButton = findViewById(R.id.button_download_code);
        final EditText storeNameInput = findViewById(R.id.field_store_name),
                storeCapacityInput = findViewById(R.id.field_store_capacity);

        storeNameInput.setText(Store.getInstance().getName());
        storeCapacityInput.setText(Integer.toString(Store.getInstance().getCapacity()));

        openStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeName = storeNameInput.getText().toString().trim();
                capacity = storeCapacityInput.getText().toString().trim();

                if (storeName == null || capacity == null) {
                    return;
                }
                Log.d("Setup", "Store Name: " + storeName);
                Log.d("Setup", "Store cap:" + capacity);
                Store.getInstance().setName(storeName).setCapacity(Integer.parseInt(capacity));
                createStore();
                startActivity(new Intent(StoreSetupActivity.this, DashboardActivity.class));
            }
        });

        createCodeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        downloadCodeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void createStore() {
        Store.getInstance().initDatabase();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
