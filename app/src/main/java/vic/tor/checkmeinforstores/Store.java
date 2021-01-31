package vic.tor.checkmeinforstores;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Store {
    private static final String IN_LINE = "in-line", IN_STORE = "in-store";
    private static Store instance;
    private String mName = "";
    private int mMaxcap = 0,
            mMaxPartySize = 10;
    private ArrayList<Customer> customers = new ArrayList<>();
    private DatabaseReference storeReference;


    private Store() {
    }

    public static Store getInstance() {
        return instance = instance == null ? new Store() : instance;
    }

    public void initDatabase() {
        storeReference = FirebaseDatabase.getInstance().getReference().child("stores").child(getName());
        storeReference.child("max-capacity").setValue(getCapacity());
    }

    public void closeStore() {
        customers.clear();
        storeReference.removeValue();
    }

    public Store addCustomer(Customer customer) { //ToDo: Use variadic
        Line.getInstance().removeCustomer(customer.getName());
        this.customers.add(customer);
        storeReference.child(IN_STORE).setValue(this.customers);  // workaround for time
        return this;
    }

    public Store removeCustomer(final String customerName) {
        for (int i = 0; i < customers.size(); i++) {  // ToDo: Use removeIf()
            if (customers.get(i).getName().equals(customerName)) {
                customers.remove(i);
                storeReference.child(IN_STORE).setValue(this.customers);
                break;
            }
        }

        return this;
    }

    public ArrayList<Customer> getCustomers() {
        return (ArrayList<Customer>) this.customers.clone(); // C++ vibes
    }

    public String getName() {
        return mName;
    }

    public Store setName(String name) {
        this.mName = name;
        return this;
    }

    public int getCapacity() {
        return this.mMaxcap;
    }

    public Store setCapacity(int cap) {
        this.mMaxcap = cap;
        return this;
    }

    public int getMaxPartySize() {
        return this.mMaxPartySize;
    }

    public Store setMaxPartySize(int partySize) {
        this.mMaxPartySize = partySize;
        return this;
    }

    public DatabaseReference getStoreReference() {
        return storeReference;
    }
}
