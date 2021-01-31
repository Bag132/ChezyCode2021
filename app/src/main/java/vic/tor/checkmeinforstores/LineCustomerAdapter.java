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

public class LineCustomerAdapter extends RecyclerView.Adapter<LineCustomerAdapter.LineCustomerViewHolder> {
    AppCompatActivity dashContext;
    private LayoutInflater inflater;
    private ArrayList<Customer> customers;

    public LineCustomerAdapter(AppCompatActivity context, ArrayList<Customer> customers) {
        inflater = LayoutInflater.from(dashContext = context);
        this.customers = customers;
    }

    @NonNull
    @Override
    public LineCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineCustomerViewHolder(inflater.inflate(R.layout.layout_line_customer_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineCustomerViewHolder holder, int position) {
        holder.setCustomer(customers.get(position));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    class LineCustomerViewHolder extends RecyclerView.ViewHolder {
        Customer customer;

        public LineCustomerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        public void setCustomer(final Customer customer) {
            this.customer = customer;
            ((TextView) itemView.findViewById(R.id.text_line_customer_name)).setText(customer.getName());
            boolean multiple = customer.getGroupSize() > 1;
            ((TextView) itemView.findViewById(R.id.text_line_customer_pop)).setText(customer.getGroupSize() + (multiple ? " people" : " person"));

            if (multiple) {
                ((ImageView) itemView.findViewById(R.id.group_image)).setImageResource(R.drawable.multiple_people_icon);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomerInLineActivity.currentCustomer = customer;
                    dashContext.startActivity(new Intent(dashContext, CustomerInLineActivity.class));
                }
            });
        }
    }
}

