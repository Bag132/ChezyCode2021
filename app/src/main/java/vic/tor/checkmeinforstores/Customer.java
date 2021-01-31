package vic.tor.checkmeinforstores;

public class Customer {
    private final String mName;
    private final int mGroupSize;

    public Customer(String groupName, int groupSize) {
        this.mName = groupName;
        this.mGroupSize = groupSize;
    }

    public String getName() {
        return this.mName;
    }

    public int getGroupSize() {
        return this.mGroupSize;
    }
}
