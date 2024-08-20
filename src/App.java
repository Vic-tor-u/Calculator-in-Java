import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

    public class App implements Runnable, ActionListener {

    public String inBox;
    public String lastAction;
    public double firstNumber;
    private boolean justEqualed;
    public double doBy;
    
    
    public App() {
        inBox = new String("0");
        lastAction = new String("");
        firstNumber = 0.0;
        justEqualed = false;
        doBy = 0.0;
        }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new App());
    }

    @Override
    public void run() {


        //Creating the panel: sizing, displaying, and color
        JFrame pane = new JFrame("Calculator");
        pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pane.pack();
        pane.setLocationByPlatform(true);
        pane.setSize(380, 500);
        pane.setVisible(true);
        pane.setMinimumSize(new Dimension(400, 550));

        pane.add(createMainPanel(), BorderLayout.WEST);
    }

    private JPanel createMainPanel() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(385, 500));

        JTextField box = new JTextField();
        ((AbstractDocument) box.getDocument()).setDocumentFilter(new LetterFilter());
        box.addActionListener(this);
        box.setPreferredSize(new Dimension((int) p.getPreferredSize().getWidth() - 30, 50)); //change so the text box actually resizes when window size is changed
        Font myFont = new Font("Comic Sans", Font.BOLD, 20);
        box.setHorizontalAlignment(SwingConstants.LEFT);
        box.setFont(myFont);
        box.setVisible(true);
        p.add(box);

        //buttons
        Font buttonFont = new Font("Comic Sans", Font.BOLD, 35);

        //Key bindings
        InputMap inputMap = p.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = p.getActionMap();

        JButton clear = new JButton("C");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    box.setText(new String("")); 
            }
        });
        clear.setPreferredSize(new Dimension(85, 85));
        clear.setFont(buttonFont);
        clear.setBackground(Color.lightGray);
        clear.setVisible(true);
        p.add(clear);

        JButton plusMinus = new JButton("+/-");
        plusMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String currentText = box.getText();
                if (!currentText.isEmpty()) {
                    try{
                        double value = Double.parseDouble(currentText);

                        value = -value;

                        //temporarily disable filter
                        AbstractDocument doc = (AbstractDocument) box.getDocument();
                        DocumentFilter filter = doc.getDocumentFilter();
                        doc.setDocumentFilter(null);

                        box.setText(Double.toString(value));

                        //re-enable filter
                        doc.setDocumentFilter(filter);
                    } catch (NumberFormatException ex) {
                        System.out.println("invalid number format");
                    }
                }
            }
        });
        plusMinus.setPreferredSize(new Dimension(85, 85));
        plusMinus.setFont(buttonFont);
        plusMinus.setBackground(Color.lightGray);
        plusMinus.setVisible(true);
        p.add(plusMinus);

        JButton percent = new JButton("%");
        percent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check if valid number and then divide by 100
                String inBox = new String((Double.valueOf(box.getText()) / 100.0) + "");
                box.setText((inBox));
            }
        });
        percent.setPreferredSize(new Dimension(85, 85));
        percent.setFont(buttonFont);
        percent.setBackground(Color.lightGray);
        percent.setVisible(true);
        p.add(percent);

        JButton divide = new JButton("/");
        divide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "/";
                box.setText("");
                divide.setBackground(null);
            }
        });
        //times key binding
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "divideAction");
        actionMap.put("divideAction", new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "/";
                box.setText("");
           } 
        });
        divide.setPreferredSize(new Dimension(85, 85));
        divide.setFont(buttonFont);
        divide.setBackground(Color.lightGray);
        divide.setVisible(true);
        p.add(divide);

        JButton seven = new JButton("7");
        seven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "7";
                box.setText((inBox));
            }
        });
        seven.setPreferredSize(new Dimension(85, 85));
        seven.setFont(buttonFont);
        seven.setBackground(Color.lightGray);
        seven.setVisible(true);
        p.add(seven);

        JButton eight = new JButton("8");
        eight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "8";
                box.setText((inBox));
            }
        });
        eight.setPreferredSize(new Dimension(85, 85));
        eight.setFont(buttonFont);
        eight.setBackground(Color.lightGray);
        eight.setVisible(true);
        p.add(eight);
        
        JButton nine = new JButton("9");
        nine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "9";
                box.setText((inBox));
            }
        });
        nine.setPreferredSize(new Dimension(85, 85));
        nine.setFont(buttonFont);
        nine.setBackground(Color.lightGray);
        nine.setVisible(true);
        p.add(nine);

        JButton multiply = new JButton("*");
        multiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "*";
                box.setText("");
                multiply.setBackground(null);
            }
        });
        //times key binding
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "multiplyAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK), "multiplyAction");
        actionMap.put("multiplyAction", new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "*";
                box.setText("");
           } 
        });
        multiply.setPreferredSize(new Dimension(85, 85));
        multiply.setFont(buttonFont);
        multiply.setBackground(Color.lightGray);
        multiply.setVisible(true);
        p.add(multiply);

        JButton four = new JButton("4");
        four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "4";
                box.setText((inBox));
            }
        });
        four.setPreferredSize(new Dimension(85, 85));
        four.setFont(buttonFont);
        four.setBackground(Color.lightGray);
        four.setVisible(true);
        p.add(four);
        
        JButton five = new JButton("5");
        five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "5";
                box.setText((inBox));
            }
        });
        five.setPreferredSize(new Dimension(85, 85));
        five.setFont(buttonFont);
        five.setBackground(Color.lightGray);
        five.setVisible(true);
        p.add(five);
        
        JButton six = new JButton("6");
        six.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "6";
                box.setText((inBox));
            }
        });
        six.setPreferredSize(new Dimension(85, 85));
        six.setFont(buttonFont);
        six.setBackground(Color.lightGray);
        six.setVisible(true);
        p.add(six);

        JButton subtract = new JButton("-");
        ActionListener sub = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "-";
                box.setText("");
                subtract.setBackground(null);
            }
        };
        subtract.addActionListener(sub);
        
        //Minus key binding
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "minusAction");
        actionMap.put("minusAction", new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "-";
                box.setText("");
           } 
        });

        subtract.setPreferredSize(new Dimension(85, 85));
        subtract.setFont(buttonFont);
        subtract.setBackground(Color.lightGray);
        subtract.setVisible(true);
        p.add(subtract);

        JButton one = new JButton("1");
        one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "1";
                box.setText((inBox));
            }
        });
        one.setPreferredSize(new Dimension(85, 85));
        one.setFont(buttonFont);
        one.setBackground(Color.lightGray);
        one.setVisible(true);
        p.add(one);
        
        JButton two = new JButton("2");
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "2";
                box.setText(inBox);
            }
        });
        two.setPreferredSize(new Dimension(85, 85));
        two.setFont(buttonFont);
        two.setBackground(Color.lightGray);
        two.setVisible(true);
        p.add(two);

        JButton three = new JButton("3");
        three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "3";
                box.setText(inBox);
            }
        });
        three.setPreferredSize(new Dimension(85, 85));
        three.setFont(buttonFont);
        three.setBackground(Color.lightGray);
        three.setVisible(true);
        p.add(three);

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "+";
                box.setText("");
                plus.setBackground(null);
            }
        });
        //Plus key binding
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.SHIFT_DOWN_MASK), "plusAction");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "plusAction");
        actionMap.put("plusAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.valueOf(box.getText());
                lastAction = "+";
                box.setText("");
            }
        });
        plus.setPreferredSize(new Dimension(85, 85));
        plus.setFont(buttonFont);
        plus.setBackground(Color.lightGray);
        plus.setVisible(true);
        p.add(plus);

        JButton zero = new JButton("0");
        zero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (justEqualed == true) {
                    inBox = "";
                    box.setText((inBox));
                    justEqualed = false;
                }
                inBox = box.getText() + "0";
                box.setText(inBox);
            }
        });
        zero.setPreferredSize(new Dimension(175, 85));
        zero.setFont(buttonFont);
        zero.setBackground(Color.lightGray);
        zero.setVisible(true);
        p.add(zero);

        JButton dot = new JButton(".");
        dot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inBox = box.getText() + ".";
                box.setText((inBox));
            }
        });
        dot.setPreferredSize(new Dimension(85, 85));
        dot.setFont(buttonFont);
        dot.setBackground(Color.lightGray);
        dot.setVisible(true);
        p.add(dot);

        


        JButton result = new JButton("=");
        result.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //                                        
                String currentText = box.getText();
                
                //temporarily disable filter
                AbstractDocument doc = (AbstractDocument) box.getDocument();
                DocumentFilter filter = doc.getDocumentFilter();
                doc.setDocumentFilter(null);  
                
                if (lastAction.equals("+")) {
                    if (justEqualed == false) {
                        doBy = Double.valueOf(box.getText());
                    }
                    String ret = new String(firstNumber + doBy + "");
                    firstNumber = Double.valueOf(ret);
                    box.setText((ret));
                    plus.setBackground(Color.lightGray);
                    justEqualed = true;
                } else if (lastAction.equals("-")) {
                    if (justEqualed == false) {
                        doBy = Double.valueOf(box.getText());
                    }
                    String ret = new String(firstNumber - doBy + "");
                    box.setText((ret));
                    firstNumber = Double.valueOf(ret);
                    subtract.setBackground(Color.lightGray);
                    justEqualed = true;
                } else if (lastAction.equals("*")) {
                    if (justEqualed == false) {
                        doBy = Double.valueOf(box.getText());
                    }
                    String ret = new String(firstNumber * doBy + "");
                    box.setText((ret));
                    firstNumber = Double.valueOf(ret);
                    multiply.setBackground(Color.lightGray);
                    justEqualed = true;
                } else if (lastAction.equals("/")) {
                    if (justEqualed == false) {
                        doBy = Double.valueOf(box.getText());
                    }
                    String ret = new String(firstNumber / doBy + "");
                    box.setText((ret));
                    firstNumber = Double.valueOf(ret);
                    divide.setBackground(Color.lightGray);
                    justEqualed = true;
                }
                //re-enable filter
                doc.setDocumentFilter(filter);
            }
            
        });
        result.setPreferredSize(new Dimension(85, 85));
        result.setFont(buttonFont);
        result.setBackground(Color.lightGray);
        result.setVisible(true);
        p.add(result);

        return p;

        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}


