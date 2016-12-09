package operationresearch.AHP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class main_swing extends JPanel {

    // design components 
    JTextField text1;
    JTextField text2;
    JLabel label1;
    JLabel label2;
    JPanel panel;
    JTable table;
    JButton run, calculate, next, newtask;
    
    JLabel label;

    // variables 
    int cri;
    int alt;
// increase after each small table alternative
    int change = 0;
    
    double[] main_array;
    double[][] child_array;
    
    boolean test = true;
    
    double multi = 1;
    double sum = 0;
    
    public main_swing() {
        
        init();
        
    }

  
    public void init() {
        
        this.setSize(800, 500);
        this.setLayout(null);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        
        text1 = new JTextField();
        text2 = new JTextField();
        text1.setBounds(200, 50, 100, 30);
        text2.setBounds(200, 100, 100, 30);
        text1.setUI(new JTextFieldHintUI("Cariteria", Color.gray));
        text2.setUI(new JTextFieldHintUI("alternative", Color.gray));
        text1.setFont(new Font("", Font.BOLD, 20));
        text2.setFont(new Font("", Font.BOLD, 20));
        
        label1 = new JLabel("Criteria");
        label2 = new JLabel("alternative");
        label1.setBounds(130, 50, 50, 30);
        label2.setBounds(130, 100, 80, 30);
        
        label = new JLabel("Ahp project");
        label.setFont(new Font("", Font.BOLD, 25));
        label.setBounds(330, 5, 200, 30);
        // panel 
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 150, getWidth(), 350);

        // table
        table = new JTable();
        table.setForeground(Color.WHITE);
        table.setBackground(Color.BLACK);
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor(Color.BLUE);
        table.setSelectionBackground(Color.GREEN);
        table.setSelectionForeground(Color.black);
        table.setRowHeight(30);
//tabel.setcol
//tabel.setRowSelectionAllowed(true);

        // buttons
        
//        Icon i=new ImageIcon(getClass().getResource("next.png"));
        run = new JButton("Run");
        calculate = new JButton("Calculate");
        next = new JButton("next");
        newtask = new JButton("newtask");
        run.setBounds(350, 90, 100, 50);
        calculate.setBounds(460, 90, 100, 50);
        next.setBounds(570, 90, 100, 50);
        newtask.setBounds(680, 90, 100, 50);
//        next.setIcon(new ImageIcon(getClass().getResource("next.png")));
        
        // actions 
        text1.addKeyListener(new action());
        text2.addKeyListener(new action());
        run.addActionListener(new action());
        calculate.addActionListener(new action());
        newtask.addActionListener(new action());
        next.addActionListener(new action());
        
        this.add(text1);
        this.add(text2);
        this.add(label1);
        this.add(label2);
        this.add(panel);
        this.add(run);
        this.add(calculate);
        this.add(next);
        this.add(newtask);
        this.add(label);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        this.setVisible(true);
    }
    
 
    
    class action implements ActionListener, KeyListener {

        // buttons action 
        @Override
        public void actionPerformed(ActionEvent e) {

            // run button 
            if (e.getSource() == run) {
// if any textfields is empty
                if (text1.getText().equals("")
                        || text2.getText().equals("")) {
                    if (text1.getText().equals("")) {
                        
                        JOptionPane.showMessageDialog(null, "Enter criteria");
                    } else if (text2.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter alternative");
                        
                    }
                    
                } else {
                    
                    DefaultTableModel model = new DefaultTableModel();
                    
                    cri = Integer.parseInt(text1.getText());
                    alt = Integer.parseInt(text2.getText());
                    text1.setEnabled(false);
                    text2.setEnabled(false);
                    
                    String[] rows = new String[cri];

                    // addcolumn 
                    for (int i = 1; i <= cri; i++) {
                        
                        model.addColumn("criteria " + i);
                        
                    }

                    // add rows 
                    for (int x = 1; x <= cri; x++) {
                        
                        model.addRow(rows);
                        
                    }
                    // add model to table
                    table.setModel(model);
                    
                    child_array = new double[cri][alt];
                    run.setEnabled(false);
                    
                }
                
            } // if next button action
            else if (e.getSource() == calculate) {
                // if any component is empty
                if (text1.getText().equals("")
                        || text2.getText().equals("")) {
                    if (text1.getText().equals("")) {
                        
                        JOptionPane.showMessageDialog(null, "Enter criteria");
                    } else if (text2.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter alternative");
                        
                    }
                    
                } // if component is fill
                else {

                    // if deal with the total table (big table)
                    if (test == true) {
//                        tabel.setEnabled(false);
                        main_array = new double[cri];
                        for (int i = 0; i < cri; i++) {
                            
                            for (int y = 0; y < cri; y++) {
                                
                                String values = table.getValueAt(i, y).toString();
                                multi *= Double.parseDouble(values);
                            }
                            multi = Math.pow(multi, cri);
                            System.out.println(multi);
                            main_array[i] = multi;
                            sum += multi;
                            multi = 1;
                            
                        }

                        // divid all values inside geometric / sum of them
                        for (int x = 0; x < cri; x++) {
                            
                            main_array[x] = main_array[x] / sum;
                            
                        }
                        multi = 1;
                        sum = 0;
                        test = false;
                        
                    } // deal with small table 
                    else if (test == false) {
                        
                        if (change == cri) {
                            double max = 0, s = 0;
                            for (int x = 0; x < alt; x++) {
                                for (int i = 0; i < cri; i++) {
                                    s += (main_array[i] * child_array[i][x]);
                                }
                                if (s > max) {
                                    max = s;
                                }
                                
                                s = 0;
                            }
                            JOptionPane.showMessageDialog(null, "solution is " + max);
                            
                        } else {
                            
                            for (int i = 0; i < alt; i++) {
                                for (int y = 0; y < alt; y++) {
                                    String values = table.getValueAt(i, y).toString();
                                    multi *= Double.parseDouble(values);
                                    
                                }
                                multi = Math.pow(multi, (1.0 / alt));
                                child_array[change][i] = multi;
                                sum += multi;
                                multi = 1;
                                
                            }
                            
                            for (int i = 0; i < alt; i++) {
                                
                                child_array[change][i] /= sum;
                                
                            }
                            
                            multi = 1;
                            sum = 0;
                            change++;
                            
                        }
                    }
                    
                }
                
            } // button next action
            else if (e.getSource() == next) {

                // to add alternative column 
                DefaultTableModel mo = new DefaultTableModel();
                String ar[] = new String[alt];
                
                for (int x = 1; x <= alt; x++) {
                    mo.addColumn("criteria" + x);
                }
                for (int x = 0; x < alt; x++) {
                    mo.addRow(ar);
                }
                
                table.setModel(mo);
                
            } else if (e.getSource() == newtask) {
                DefaultTableModel mm = new DefaultTableModel();
                text1.setEnabled(true);
                text2.setEnabled(true);
                
                text1.setText("");
                text2.setText("");
                
                table.setModel(mm);
                
                change = 0;
                sum = 0;
                run.setEnabled(true);
                test = true;
                
            }
            
        }

        //-----------------------------------------------------------------
        // key listener for texts 
        @Override
        public void keyTyped(KeyEvent e) {

            // if text1 typing
            if (e.getSource() == text1) {
                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    
                    e.consume();
                    
                }
                
            } // if text2 typing
            else if (e.getSource() == text2) {
                char c = e.getKeyChar();
                
                if (!Character.isDigit(c)) {
                    
                    e.consume();
                    
                }
            }
            
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
}

class JTextFieldHintUI extends BasicTextFieldUI implements FocusListener {
    
    private String hint;
    private Color hintColor;
    
    public JTextFieldHintUI(String hint, Color hintColor) {
        this.hint = hint;
        this.hintColor = hintColor;
    }
    
    private void repaint() {
        if (getComponent() != null) {
            getComponent().repaint();
        }
    }
    
    @Override
    protected void paintSafely(Graphics g) {
        // Render the default text field UI
        super.paintSafely(g);
        // Render the hint text
        JTextComponent component = getComponent();
        if (component.getText().length() == 0 && !component.hasFocus()) {
            g.setColor(hintColor);
            int padding = (component.getHeight() - component.getFont().getSize()) / 2;
            int inset = 3;
            g.drawString(hint, inset, component.getHeight() - padding - inset);
        }
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        repaint();
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        repaint();
    }
    
    @Override
    public void installListeners() {
        super.installListeners();
        getComponent().addFocusListener(this);
    }
    
    @Override
    public void uninstallListeners() {
        super.uninstallListeners();
        getComponent().removeFocusListener(this);
    }
}
