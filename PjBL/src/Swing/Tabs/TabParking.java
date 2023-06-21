//package Swing.Tabs;
//
//import Excecoes.VehicleExceptionInvalidMaker;
//import Excecoes.VehicleExceptionInvalidModel;
//import Excecoes.VehicleExceptionInvalidPlate;
//import Veiculo.Vehicle;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class TabParking extends Tab {
//    private final int MAX_SMALL_VEHICLES = 20;
//    private ArrayList<Vehicle> smallVehicleList;
//    private ArrayList<Vehicle> mediumVehicleList;
//    private ArrayList<Vehicle> largeVehicleList;
//    private final int MAX_MEDIUM_VEHICLES = 30;
//    private final int MAX_LARGE_VEHICLES = 10;
//    private final String GREEN = "#A4E675";
//    private final String RED = "#E68075";
//
//    public TabParking() {
//        smallVehicleList = new ArrayList<>(MAX_SMALL_VEHICLES);
//        mediumVehicleList = new ArrayList<>(MAX_MEDIUM_VEHICLES);
//        largeVehicleList = new ArrayList<>(MAX_LARGE_VEHICLES);
//        setLayout(null);
//        int marginX = 10;
//        int marginY = 10;
//        int anchorX = marginX;
//        int anchorY = marginY;
//        int gapX = 10;
//        int gapY = 5;
//
//        JPanel panel = new JPanel();
//
//        Dimension buttonSize = new Dimension(70, 30);
//        Font font = new Font("DIALOG", Font.BOLD, 20);
//        JLabel smallVehicleLabel = new JLabel("Veiculos pequenos");
//        smallVehicleLabel.setFont(font);
//        smallVehicleLabel.setBounds(anchorX, anchorY, 240, 30);
//        add(smallVehicleLabel);
//        for (int i = 0; i < MAX_SMALL_VEHICLES; i++) {
//            JButton parkButton = new JButton(String.format("%02d", i + 1));
//            if (i % 2 == 0) {
//                anchorX = marginX;
//                anchorY = marginY + smallVehicleLabel.getHeight() + ((i / 2) * (gapY + buttonSize.height));
//            } else {
//                anchorX = marginX + buttonSize.width + gapX;
//                anchorY = marginY + smallVehicleLabel.getHeight() + ((i / 2) * (gapY + buttonSize.height));
//            }
//            parkButton.addActionListener(accessButtonSmallVehicle());
//            parkButton.putClientProperty("id", i);
//            //parkButton.putClientProperty("size", "small");
//            parkButton.setBounds(anchorX, anchorY, buttonSize.width, buttonSize.height);
//            parkButton.setBackground(Color.decode(GREEN));
//            add(parkButton);
//        }
//
//        anchorX = smallVehicleLabel.getX() + smallVehicleLabel.getWidth() + gapX;
//        anchorY = marginY;
//        JLabel mediumVehicleLabel = new JLabel("Veiculos medios");
//        mediumVehicleLabel.setFont(font);
//        mediumVehicleLabel.setBounds(anchorX, anchorY, 240, 30);
//        add(mediumVehicleLabel);
//        for (int i = 0; i < MAX_MEDIUM_VEHICLES; i++) {
//            JButton parkButton = new JButton(String.format("%02d", i + 1));
//            if (i % 2 == 0) {
//                anchorX = mediumVehicleLabel.getX();
//                anchorY = marginY + mediumVehicleLabel.getHeight() + ((i / 2) * (gapY + buttonSize.height));
//            } else {
//                anchorX = mediumVehicleLabel.getX() + buttonSize.width + gapX;
//                anchorY = marginY + mediumVehicleLabel.getHeight() + ((i / 2) * (gapY + buttonSize.height));
//            }
//            parkButton.setBounds(anchorX, anchorY, buttonSize.width, buttonSize.height);
//            parkButton.setBackground(Color.decode(GREEN));
//            add(parkButton);
//        }
//
//        anchorX = mediumVehicleLabel.getX() + smallVehicleLabel.getWidth() + gapX;
//        anchorY = marginY;
//        JLabel largeVehicleLabel = new JLabel("Veiculos grandes");
//        largeVehicleLabel.setFont(font);
//        largeVehicleLabel.setBounds(anchorX, anchorY, 240, 30);
//        add(largeVehicleLabel);
//        for (int i = 0; i < MAX_LARGE_VEHICLES; i++) {
//            JButton parkButton = new JButton(String.format("%02d", i + 1));
//            if (i % 2 == 0) {
//                anchorX = largeVehicleLabel.getX();
//                anchorY = marginY + largeVehicleLabel.getHeight() + ((i / 2) * (gapY + buttonSize.height));
//            } else {
//                anchorX = largeVehicleLabel.getX() + buttonSize.width + gapX;
//                anchorY = marginY + largeVehicleLabel.getHeight() + ((i / 2) * (gapY + buttonSize.height));
//            }
//            parkButton.setBounds(anchorX, anchorY, buttonSize.width, buttonSize.height);
//            parkButton.setBackground(Color.decode(GREEN));
//            add(parkButton);
//        }
//
//    }
//
//    private ActionListener accessButtonSmallVehicle() {
//        return new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JButton source = (JButton) e.getSource();
//                JFrame frame = new JFrame("Vaga");
//                frame.setLayout(new GridBagLayout());
//                GridBagConstraints gbc = new GridBagConstraints();
//                gbc.fill = GridBagConstraints.BOTH;
//                gbc.insets = new Insets(5, 5, 5, 5);
//                JLabel maker = new JLabel("Fabricante:");
//                JLabel model = new JLabel("Modelo:");
//                JLabel plate = new JLabel("Placa:");
//                frame.add(maker, gbc);
//                gbc.gridy = 1;
//                frame.add(model, gbc);
//                gbc.gridy = 2;
//                frame.add(plate, gbc);
//                gbc.gridx = 1;
//                if (source.getClientProperty("vehicle") == null) {
//                    JTextField makerTF = new JTextField();
//                    gbc.gridy = 0;
//                    makerTF.setPreferredSize(new Dimension(120, 20));
//                    frame.add(makerTF, gbc);
//                    JTextField modelTF = new JTextField();
//                    gbc.gridy = 1;
//                    modelTF.setPreferredSize(new Dimension(120, 20));
//                    frame.add(modelTF, gbc);
//                    JTextField plateTF = new JTextField();
//                    gbc.gridy = 2;
//                    plateTF.setPreferredSize(new Dimension(120, 20));
//                    frame.add(plateTF, gbc);
//
//                    gbc.gridwidth = 2;
//                    gbc.gridx = 0;
//                    gbc.gridy = 3;
//                    JPanel buttonPanel = new JPanel();
//                    JButton confirmButton = new JButton("Confirmar");
//                    confirmButton.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            String maker = makerTF.getText();
//                            String model = modelTF.getText();
//                            String plate = plateTF.getText();
//                            try {
//                                source.putClientProperty("vehicle", new Vehicle(maker, model, plate));
//                            } catch (VehicleExceptionInvalidMaker exception) {
//                                popError("Coloque o fabricante");
//                            } catch (VehicleExceptionInvalidModel exception) {
//                                popError("Coloque o modelo");
//                            } catch (VehicleExceptionInvalidPlate exception) {
//                                popError("Coloque uma placa");
//                            }
//                            source.setBackground(Color.decode(RED));
//                            source.setToolTipText("<html>Fabricante: " + maker + "<br>Modelo: " + model + "<br>Placa: " + plate + "</html>");
//                            frame.dispose();
//                        }
//                    });
//                    buttonPanel.add(confirmButton);
//                    JButton cancelButton = new JButton("Cancelar");
//                    cancelButton.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            frame.dispose();
//                        }
//                    });
//                    buttonPanel.add(cancelButton);
//                    frame.add(buttonPanel, gbc);
//                    System.out.println(source.getClientProperty("id"));
//                    source.setToolTipText("text");
//                }
//                else{
//                    maker = new JLabel("Fabricante:");
//                    model = new JLabel("Modelo:");
//                    plate = new JLabel("Placa:");
//                    frame.add(maker, gbc);
//                    gbc.gridy = 1;
//                    frame.add(model, gbc);
//                    gbc.gridy = 2;
//                    frame.add(plate, gbc);
//                    gbc.gridx = 1;
//                }
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//        };
//    }
//}
