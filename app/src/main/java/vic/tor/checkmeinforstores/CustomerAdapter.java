package vic.tor.checkmeinforstores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    AppCompatActivity dashContext;
    private LayoutInflater inflater;
    private ArrayList<Customer> customers;

    public CustomerAdapter(AppCompatActivity context, ArrayList<Customer> customers) {
        inflater = LayoutInflater.from(dashContext = context);
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerViewHolder(inflater.inflate(R.layout.layout_customer_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.setCustomer(customers.get(position));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        Customer customer;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        public void setCustomer(final Customer customer) {
            this.customer = customer;
            ((TextView) itemView.findViewById(R.id.text_group_name)).setText(customer.getName());
            boolean multiple = customer.getGroupSize() > 1;
            ((TextView) itemView.findViewById(R.id.text_group_count)).setText(customer.getGroupSize() + (multiple ? " people" : " person"));

            if (multiple) {
                ((ImageView) itemView.findViewById(R.id.group_image)).setImageResource(R.drawable.multiple_people_icon);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InsideCustomerActivity.currentCustomer = customer;
                    dashContext.startActivity(new Intent(dashContext, InsideCustomerActivity.class));
                }
            });
        }
    }
}

