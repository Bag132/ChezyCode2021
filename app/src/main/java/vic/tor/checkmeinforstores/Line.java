package vic.tor.checkmeinforstores;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Line {
    private static final String IN_LINE = "in-line", IN_STORE = "in-store";

    private static Line instance;
    private ArrayList<Customer> customers = new ArrayList<>();


    private Line() {


    }

    public static Line getInstance() {
        return instance = instance == null ? new Line() : instance;
    }

    public Line addCustomer(Customer customer) { //ToDo: Use variadic
        this.customers.add(customer);
        Store.getInstance().getStoreReference().child(IN_LINE).setValue(customers);
        return this;
    }

    public Line removeCustomer(final String customerName) {
        for (int i = 0; i < customers.size(); i++) {  // ToDo: Use removeIf()
            if (customers.get(i).getName().equals(customerName)) {
                customers.remove(i);
                Store.getInstance().getStoreReference().child(IN_LINE).setValue(customers);
                break;
            }
        }

        return this;
    }

    public ArrayList<Customer> getCustomers() {
        return (ArrayList<Customer>) this.customers.clone(); // C++ vibes
    }

    public boolean containsCustomer(String customerName) {
        for (Customer c : customers) {
            if (c.getName().equals(customerName)) {
                return true;
            }
        }
        return false;
    }

}
