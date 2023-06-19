package Swing;

import Excecoes.VehicleExceptionInvalidMaker;
import Excecoes.VehicleExceptionInvalidModel;
import Excecoes.VehicleExceptionInvalidPlate;
import Functions.Serializer;
import Functions.Sort;
import Veiculo.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TabVehicle extends Tab {
    private DefaultListModel<Vehicle> vehicleDefaultListModel;
    private JList vehicleList;
    private JScrollPane vehicleScrollPane;
    private JButton registerVehicleButton;
    private JButton removeVehicleButton;

    public TabVehicle() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(15, 15, 0, 0);

        vehicleDefaultListModel = new DefaultListModel<Vehicle>();
        try {
            vehicleDefaultListModel = (DefaultListModel<Vehicle>) Serializer.read("vehicles.vhc");
            vehicleDefaultListModel = Sort.sortVehicle(vehicleDefaultListModel);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        vehicleList = new JList<Vehicle>(vehicleDefaultListModel);
        vehicleScrollPane = new JScrollPane(vehicleList);
        vehicleScrollPane.createHorizontalScrollBar();
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.gridheight = 2;
        add(vehicleScrollPane, gridBagConstraints);

        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 1;
        registerVehicleButton = new JButton("Adicionar veiculo");
        registerVehicleButton.addActionListener(addVehicleAction());
        registerVehicleButton.setToolTipText("tooltip");
        add(registerVehicleButton, gridBagConstraints);

        removeVehicleButton = new JButton("Excluir veiculo");
        removeVehicleButton.addActionListener(removeVehicleAction());
        gridBagConstraints.gridy = 1;
        add(removeVehicleButton, gridBagConstraints);


    }

    private ActionListener addVehicleAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Adicionar veiculo");
                frame.setResizable(false);
                GridBagLayout gridBagLayout = new GridBagLayout();
                frame.setLayout(gridBagLayout);
                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
                gridBagConstraints.insets = new Insets(5, 5, 5, 5);

                JLabel makerLabel = new JLabel("Fabricante:");
                frame.add(makerLabel, gridBagConstraints);
                JLabel modelLabel = new JLabel("Modelo:");
                gridBagConstraints.gridy = 1;
                frame.add(modelLabel, gridBagConstraints);
                JLabel plateLabel = new JLabel("Placa:");
                gridBagConstraints.gridy = 2;
                frame.add(plateLabel, gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 0;
                JTextField makerTF = new JTextField(15);
                frame.add(makerTF, gridBagConstraints);
                JTextField modelTF = new JTextField(15);
                gridBagConstraints.gridy = 1;
                frame.add(modelTF, gridBagConstraints);
                JTextField plateTF = new JTextField(15);
                gridBagConstraints.gridy = 2;
                frame.add(plateTF, gridBagConstraints);

                gridBagConstraints.anchor = GridBagConstraints.CENTER;
                gridBagConstraints.gridwidth = 2;
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 3;

                JPanel radioButtonPanel = new JPanel();
                GridLayout radioButtonGridLayout = new GridLayout(1, 3);
                radioButtonPanel.setLayout(radioButtonGridLayout);
                ButtonGroup sizeGroup = new ButtonGroup();
                JRadioButton smallRadioButton = new JRadioButton("Pequeno");
                radioButtonPanel.add(smallRadioButton);
                sizeGroup.add(smallRadioButton);
                JRadioButton mediumRadioButton = new JRadioButton("Medio");
                radioButtonPanel.add(mediumRadioButton);
                sizeGroup.add(mediumRadioButton);
                JRadioButton largeRadioButton = new JRadioButton("Grande");
                radioButtonPanel.add(largeRadioButton);
                sizeGroup.add(largeRadioButton);
                frame.add(radioButtonPanel, gridBagConstraints);

                gridBagConstraints.gridy = 4;
                gridBagConstraints.insets = new Insets(5, 15, 5, 15);
                JPanel buttonPanel = new JPanel();
                GridLayout buttonGridLayout = new GridLayout(1, 2);
                buttonGridLayout.setHgap(5);
                buttonPanel.setLayout(buttonGridLayout);
                JButton confirmButton = new JButton("Confirmar");
                InputMap confirmInputMap = confirmButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                ActionMap confirmActionMap = confirmButton.getActionMap();
                KeyStroke enterKeyStroke = KeyStroke.getKeyStroke("ENTER");
                Action confirmAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maker = makerTF.getText();
                        String model = modelTF.getText();
                        String plate = plateTF.getText();
                        ButtonModel buttonModel = sizeGroup.getSelection();
                        System.out.println(buttonModel);
                        if (buttonModel == null) {
                            popError("Escolha uma categoria de tamanho");
                        } else {
                            try {
                                vehicleDefaultListModel.addElement(new Vehicle(maker, model, plate));
                                Serializer.write("vehicles.vhc", vehicleDefaultListModel);
                                frame.dispose();

                            } catch (VehicleExceptionInvalidMaker exceptionInvalidMaker) {
                                popError("Fabricante invalido");
                            } catch (VehicleExceptionInvalidModel exception) {
                                popError("Modelo Invalido");
                            } catch (VehicleExceptionInvalidPlate exception) {
                                popError("Placa invalida");
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                };
                confirmInputMap.put(enterKeyStroke, "ENTER");
                confirmActionMap.put("ENTER", confirmAction);
                confirmButton.addActionListener(confirmAction);
                buttonPanel.add(confirmButton);

                JButton cancelButton = new JButton("Cancelar");
                InputMap cancelInputMap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                ActionMap cancelActionMap = cancelButton.getActionMap();
                KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
                Action cancelAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                };
                cancelInputMap.put(escapeKeyStroke, "escape");
                cancelActionMap.put("escape", cancelAction);
                cancelButton.addActionListener(cancelAction);

                buttonPanel.add(cancelButton);
                frame.add(buttonPanel, gridBagConstraints);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        };
    }

    private ActionListener removeVehicleAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = vehicleList.getSelectedIndex();
                if (selectedIndex != - 1) {
                    vehicleDefaultListModel.remove(selectedIndex);
                    try {
                        Serializer.write("vehicle.vhc", vehicleDefaultListModel);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
    }


}
