package inne;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboJumbo extends JFrame{

    JLabel label;
    JComboBox<Customer> combo;

    public static void main(String args[]){
        new ComboJumbo();
    }

    public ComboJumbo(){
        super("Combo Jumbo");
        label = new JLabel("Select a Customer");
        add(label, BorderLayout.NORTH);

        
        
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("Frank", 1));


        combo = new JComboBox(customers.toArray());
        combo.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                Customer c = (Customer)e.getItem();
                label.setText("You selected customer id: " + c.getId());
            }

        });
        JPanel panel = new JPanel();
        panel.add(combo);
        add(panel,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }


    class Customer{
        private String name;
        private int id;

        public Customer(String name, int id){
            this.name = name;
            this.id = id;
        }

        public String toString(){
            return getName();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
 }