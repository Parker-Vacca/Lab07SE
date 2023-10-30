import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceApp extends Invoice {

    private JFrame frame;
    private JTextField productNameField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextArea displayArea;
    private JButton addButton;
    private JButton displayButton;
    private Invoice invoice;

    public InvoiceApp() {
        invoice = new Invoice();

        frame = new JFrame("Invoice App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        productNameField = new JTextField(20);
        unitPriceField = new JTextField(10);
        quantityField = new JTextField(5);

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        addButton = new JButton("Add Product");
        displayButton = new JButton("Display Invoice");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayInvoice();
            }
        });

        frame.add(new JLabel("Product Name:"));
        frame.add(productNameField);
        frame.add(new JLabel("Unit Price:"));
        frame.add(unitPriceField);
        frame.add(new JLabel("Quantity:"));
        frame.add(quantityField);
        frame.add(addButton);
        frame.add(displayButton);
        frame.add(new JScrollPane(displayArea));

        frame.setVisible(true);
    }

    private void addProduct() {
        String name = productNameField.getText();
        double price = Double.parseDouble(unitPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(name, price);
        LineItem item = new LineItem(product, quantity);

        invoice.addLineItem(item);

        productNameField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
    }

    private void displayInvoice() {
        StringBuilder builder = new StringBuilder();
        for (LineItem item : invoice.getLineItems()) {
            builder.append(item.getProduct().getName())
                    .append(" - $")
                    .append(item.getProduct().getUnitPrice())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = $")
                    .append(item.calculateTotal())
                    .append("\n");
        }
        builder.append("\nTotal Amount Due: $").append(invoice.calculateTotalAmountDue());

        String output = builder.toString();
        displayArea.setText(output);
        System.out.println(output);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvoiceApp());
    }
}
