import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<LineItem> lineItems;

    public Invoice() {
        lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double calculateTotalAmountDue() {
        double total = 0.0;
        for (LineItem item : lineItems) {
            total += item.calculateTotal();
        }
        return total;
    }
}