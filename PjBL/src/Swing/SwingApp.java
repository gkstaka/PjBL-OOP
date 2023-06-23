package Swing;

import Excecoes.*;
import Functions.ReaderTextFile;
import Functions.Serializer;
import Functions.Sort;
import Parking.Parking;
import Pessoa.Client;
import Swing.CustomComponents.CustomButton;
import Swing.CustomComponents.CustomGridBagConstraints;
import Swing.CustomComponents.CustomLabel;
import Swing.CustomComponents.CustomTextField;
import Veiculo.LargeCar;
import Veiculo.MediumCar;
import Veiculo.Motorcycle;
import Veiculo.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SwingApp extends JFrame {
    private DefaultListModel<Client> clients;
    private DefaultListModel<Vehicle> vehicles;
    private Parking parking;
    private final String YELLOW = "#E6DC75";
    private final String GREEN = "#A4E675";
    private final String RED = "#E68075";
    //6JList<Vehicle> vehiclesList;
    Container contentPane;

    private JButton clientsButton;
    private JButton vehiclesButton;
    private JButton parkingSpotsButton;
    private JPanel clientsPanel;
    private JPanel vehiclesPanel;
    private JPanel parkingSpotPanel;
    JList<Client> clientsList;
    private boolean setActionParkingButton;

    public SwingApp() {
        setActionParkingButton = true;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        readObjects();
        clientsList = new JList<>(clients);
        vehicles = new DefaultListModel<>();
        setTitle("ParkWash");
        setSize(750, 750);
        setResizable(false);
        ToolTipManager.sharedInstance().setInitialDelay(500);
        UIManager.put("ToolTip.background", YELLOW);

        Font tabFont = new Font("DIALOG", Font.BOLD, 18);
        setLayout(new BorderLayout());
        contentPane = getContentPane();

        JPanel buttonHolderPanel = new JPanel();
        clientsButton = new JButton("Clientes");
        clientsButton.setFont(tabFont);
        clientsButton.addActionListener(showClients());
        buttonHolderPanel.add(clientsButton);

        parkingSpotsButton = new JButton("Vagas");
        parkingSpotsButton.setFont(tabFont);
        parkingSpotsButton.addActionListener(showParkingSpots());
        buttonHolderPanel.add(parkingSpotsButton);

        contentPane.add(buttonHolderPanel, BorderLayout.NORTH);
        clientsPanel = new JPanel();
        parkingSpotPanel = new JPanel();

    }

    private ActionListener showClients() {
        return showClients -> {
//                contentPane.remove(vehiclesPanel);
            contentPane.remove(clientsPanel);
            contentPane.remove(parkingSpotPanel);
            clientsPanel = new JPanel();
            clientsPanel.setLayout(new GridBagLayout());
            CustomGridBagConstraints gbc = new CustomGridBagConstraints();
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 10, 10);
            JScrollPane clientsScrollPane = new JScrollPane(clientsList);
            clientsScrollPane.setPreferredSize(new Dimension(200, 500));
            gbc.gridheight = 6;

            clientsPanel.add(clientsScrollPane, gbc);

            //labers com informacao
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
            labelPanel.setPreferredSize(new Dimension(200, 400));
            Font font = new Font("DIALOG", Font.BOLD, 14);
            JLabel nameLabel = new JLabel("Nome:");
            nameLabel.setFont(font);
            labelPanel.add(nameLabel);
            JLabel cpfLabel = new JLabel("CPF:");
            cpfLabel.setFont(font);
            labelPanel.add(cpfLabel);
            JLabel phoneLabel = new JLabel("Telefone:");
            phoneLabel.setFont(font);
            labelPanel.add(phoneLabel);
            JLabel vehiclesLabel = new JLabel("Veiculos:");
            vehiclesLabel.setFont(font);
            labelPanel.add(vehiclesLabel);
            //acao ao selecionar um item da lista
            clientsList.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    updateClientLabels(nameLabel, cpfLabel, phoneLabel, vehiclesLabel);
                }
            });
            clientsList.addListSelectionListener(update -> updateClientLabels(nameLabel, cpfLabel, phoneLabel, vehiclesLabel));

            gbc.gridheight = 5;
            gbc.cell(1, 0);
            clientsPanel.add(labelPanel, gbc);

            gbc.gridheight = 1;
            gbc.cell(2, 0);
            JButton addClientButton = new JButton("Adicionar cliente");
            addClientButton.addActionListener(registerClient());
            clientsPanel.add(addClientButton, gbc);

            gbc.cell(2, 1);
            JButton removeClientButton = new JButton("Remover cliente");
            removeClientButton.addActionListener(remove -> {
                int selectedIndex = clientsList.getSelectedIndex();
                if (selectedIndex != - 1) {
                    clients.removeElementAt(selectedIndex);
                    nameLabel.setText("Nome:");
                    cpfLabel.setText("Cpf:");
                    phoneLabel.setText("Telefone:");
                    vehiclesLabel.setText("Veiculos:");
                    try {
                        Serializer.write("clients.clt", clients);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            });
            clientsPanel.add(removeClientButton, gbc);


            gbc.cell(2, 2);
            JButton addVehicleButton = new JButton("Adicionar veiculo");
            addVehicleButton.addActionListener(addVehicle -> {
                System.out.println("cliente clicado");
                int selectedIndex = clientsList.getSelectedIndex();
                if (selectedIndex != - 1) {
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
                    JRadioButton smallRadioButton = new JRadioButton("Moto");
                    radioButtonPanel.add(smallRadioButton);
                    sizeGroup.add(smallRadioButton);
                    JRadioButton mediumRadioButton = new JRadioButton("Carro");
                    radioButtonPanel.add(mediumRadioButton);
                    sizeGroup.add(mediumRadioButton);
                    JRadioButton largeRadioButton = new JRadioButton("Carro grande");
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
                    confirmButton.addActionListener(confirm -> {
                        String maker = makerTF.getText();
                        String model = modelTF.getText();
                        String plate = plateTF.getText();
                        ButtonModel buttonModel = sizeGroup.getSelection();
                        if (buttonModel == null) {
                            popError("Escolha uma categoria de tamanho");
                        } else {
                            try {
                                Client client = clients.getElementAt(selectedIndex);
                                Vehicle newVehicle;
                                if (smallRadioButton.isSelected()) {
                                    newVehicle = new Motorcycle(maker, model, plate);
                                } else if (mediumRadioButton.isSelected()) {
                                    newVehicle = new MediumCar(maker, model, plate);
                                } else {
                                    newVehicle = new LargeCar(maker, model, plate);
                                }
                                client.addVehicle(newVehicle);
                                Serializer.write("clients.clt", clients);
                                updateClientLabels(nameLabel, cpfLabel, phoneLabel, vehiclesLabel);
                                frame.dispose();
                                contentPane.revalidate();
                                contentPane.repaint();

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
                    });

                    buttonPanel.add(confirmButton);

                    JButton cancelButton = new JButton("Cancelar");
                    cancelButton.addActionListener(close -> frame.dispose());

                    buttonPanel.add(cancelButton);
                    frame.add(buttonPanel, gridBagConstraints);

                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
            clientsPanel.add(addVehicleButton, gbc);

            gbc.cell(2, 3);
            JButton removeVehicleButton = new JButton("Remover veiculo");
            removeVehicleButton.addActionListener(removeVehicle -> {
                JFrame frame = new JFrame("Remover veiculo");
                Container contentPane = frame.getContentPane();
                int selectedIndex = clientsList.getSelectedIndex();

                if (selectedIndex != - 1) {
                    frame.setLayout(new GridBagLayout());
                    CustomGridBagConstraints gbc1 = new CustomGridBagConstraints();
                    gbc1.insets = new Insets(5, 5, 5, 5);
                    gbc1.cell(0, 0);
                    Client client = clients.getElementAt(selectedIndex);
                    Vehicle[] vehicleArray = client.getVehiclesArray();
                    JComboBox<Vehicle> vehicleComboBox = new JComboBox<Vehicle>(vehicleArray);
                    gbc1.gridwidth = 2;
                    contentPane.add(vehicleComboBox, gbc1);
                    gbc1.gridwidth = 1;
                    gbc1.cell(0, 1);
                    JButton confirmButton = new JButton("Confirmar");
                    confirmButton.addActionListener(confirm -> {
                        int comboBoxSelectedIndex = vehicleComboBox.getSelectedIndex();
                        if (comboBoxSelectedIndex != - 1) {
                            Vehicle vehicle = vehicleComboBox.getItemAt(comboBoxSelectedIndex);
                            client.removeVehicle(vehicle);
                            updateClientLabels(nameLabel, cpfLabel, phoneLabel, vehiclesLabel);
                            revalidate();
                            repaint();
                            frame.dispose();
                        }
                    });
                    contentPane.add(confirmButton, gbc1);
                    gbc1.cell(1, 1);
                    addCancelButton(frame, contentPane, gbc1);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
            clientsPanel.add(removeVehicleButton, gbc);

            contentPane.add(clientsPanel);
            contentPane.revalidate();
            contentPane.repaint();
        };
    }

    private void updateClientLabels(JLabel nameLabel, JLabel cpfLabel, JLabel phoneLabel, JLabel vehiclesLabel) {
        int selectedIndex = clientsList.getSelectedIndex();
        if (selectedIndex != - 1) {
            nameLabel.setText("<html>Nome: " + clients.getElementAt(selectedIndex).getName() + "</html>");
            cpfLabel.setText("Cpf: " + clients.getElementAt(selectedIndex).getCpf());
            phoneLabel.setText("Telefone: " + clients.getElementAt(selectedIndex).getPhone());
            Client client = clients.getElementAt(selectedIndex);
            String vehiclesOwnedFString = "<html> Veiculos:<br>";
            for (int i = 0; i < client.getVehicles().getSize(); i++) {
                vehiclesOwnedFString += (i + 1) + ") " + client.getVehicles().getElementAt(i) + "<br>";
            }
            vehiclesOwnedFString += "</html>";
            System.out.println(vehiclesLabel.getPreferredSize());
            System.out.println(vehiclesLabel.getSize());
            vehiclesLabel.setText(vehiclesOwnedFString);
        }
    }


    private ActionListener registerClient() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Adicionar cliente");
                frame.setSize(250, 195);
                frame.setLayout(null);
                int height = 25;

                //labels
                CustomLabel nameLabel = new CustomLabel("Nome:", height);
                nameLabel.setAnchor(10, 10);
                frame.add(nameLabel);
                CustomLabel cpfLabel = new CustomLabel("CPF:", height);
                cpfLabel.setAnchor(nameLabel, 'V', 10);
                frame.add(cpfLabel);
                CustomLabel phoneLabel = new CustomLabel("Telefone:", height);
                phoneLabel.setAnchor(cpfLabel, 'V', 10);
                frame.add(phoneLabel);
                //textfields
                CustomTextField nameTF = new CustomTextField("Nome");
                nameTF.setPreferredSize(new Dimension(frame.getWidth() - (40 + phoneLabel.getWidth()), height));
                nameTF.setAnchor(nameLabel, 'H', 20);
                frame.add(nameTF);
                CustomTextField cpfTF = new CustomTextField("CPF");
                cpfTF.setPreferredSize(new Dimension(nameTF.getWidth(), height));
                cpfTF.setAnchor(nameTF, 'V', 10);
                frame.add(cpfTF);
                CustomTextField phoneTF = new CustomTextField("Telefone");
                phoneTF.setPreferredSize(new Dimension(nameTF.getWidth(), height));
                phoneTF.setAnchor(cpfTF, 'V', 10);
                frame.add(phoneTF);
                //buttons
                CustomButton confirmButton = new CustomButton("Confirmar");
                confirmButton.setPreferredSize(new Dimension(100, 30));
                confirmButton.setAnchor(phoneLabel, 'V', 10);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameTF.getText();
                        String cpf = cpfTF.getText();
                        String phone = phoneTF.getText();
                        createClient(name, cpf, phone, frame);
                    }
                });
                frame.add(confirmButton);
                CustomButton cancelButton = new CustomButton("Cancelar");
                cancelButton.setAnchor(confirmButton, 'H', 10);
                cancelButton.setSize(new Dimension(100, 30));
                cancelButton.addActionListener(close -> frame.dispose());

                frame.add(cancelButton);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

            private void createClient(String name, String cpf, String phone, JFrame frame) {
                try {
                    Client newClient = new Client(name, cpf, phone);
                    boolean isNewClient = false;
                    for (int i = 0; i < clients.getSize(); i++) {
                        isNewClient = clients.getElementAt(i).equals(newClient);
                    }
                    if (! isNewClient) {
                        clients.addElement(new Client(name, cpf, phone));
                        Sort.sortClientListModel(clients);
                        Serializer.write("clients.clt", clients);
                        frame.dispose();
                        contentPane.revalidate();
                        contentPane.repaint();
                    } else {
                        throw new PersonExceptionCPFUsed();
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                } catch (PersonExceptionInvalidCPF exception) {
                    popError("CPF invalido");
                } catch (PersonExceptionInvalidPhone exception) {
                    popError("Telefone invalido");
                } catch (PersonExceptionCPFUsed exception) {
                    popError("CPF ja cadastrado");
                }
            }

        };
    }

    private ActionListener showParkingSpots() {
        return e -> {
            contentPane.remove(clientsPanel);
            contentPane.remove(parkingSpotPanel);

            parkingSpotPanel = new JPanel();
            parkingSpotPanel.setLayout(new GridBagLayout());
            CustomGridBagConstraints gbc = new CustomGridBagConstraints();
            gbc.anchor = GridBagConstraints.NORTHWEST;
            Font font = new Font("DIALOG", Font.BOLD, 20);
            JLabel motorcycleLabel = new JLabel("Motos");
            JLabel mediumCarLabel = new JLabel("Carros");
            JLabel largeCarLabel = new JLabel("Carros grandes");
            motorcycleLabel.setFont(font);
            mediumCarLabel.setFont(font);
            largeCarLabel.setFont(font);

            gbc.insets = new Insets(0, 0, 0, 100);
            gbc.gridwidth = 2;
            gbc.cell(0, 0);
            parkingSpotPanel.add(motorcycleLabel, gbc);
            gbc.insets = new Insets(0, 0, 0, 80);
            gbc.gridwidth = 2;
            gbc.cell(2, 0);
            parkingSpotPanel.add(mediumCarLabel, gbc);
            gbc.insets = new Insets(0, 20, 0, 0);
            gbc.gridwidth = 2;
            gbc.cell(6, 0);
            parkingSpotPanel.add(largeCarLabel, gbc);

            // Button creation
            gbc.gridwidth = 1;
            gbc.insets = new Insets(5, 0, 0, 5);
            ArrayList<JButton> motorcycleSpotButton = parking.getMotorcylesParkedButton();
            addMotorcycleButtons(gbc, motorcycleSpotButton);

            ArrayList<JButton> mediumCarSpotButton = parking.getMediumCarsParkedButton();
            addMediumCarButtons(gbc, mediumCarSpotButton);

            ArrayList<JButton> largeCarSpotButton = parking.getLargeCarsParkedButton();
            addLargeCarButtons(gbc, largeCarSpotButton);


            // Add actionListener to button
            if (setActionParkingButton) {
                for (int i = 0; i < motorcycleSpotButton.size(); i++) {
                    int finalI = i;
                    motorcycleSpotButton.get(i).addActionListener(parking -> {
                        JButton source = (JButton) parking.getSource();
                        if (source.getBackground().equals(Color.decode(GREEN))) {
                            vacantMotorcycleParkingSpotAction(source);
                        } else {
                            payAction(parking, source);
                        }
                    });
                }
                for (int i = 0; i < mediumCarSpotButton.size(); i++) {
                    mediumCarSpotButton.get(i).addActionListener(parking -> {
                        JButton source = (JButton) parking.getSource();
                        if (source.getBackground().equals(Color.decode(GREEN))) {
                            vacantMediumCarParkingSpotAction(source);
                        } else {
                            payAction(parking, source);
                        }
                    });
                }
                for (int i = 0; i < largeCarSpotButton.size(); i++) {
                    largeCarSpotButton.get(i).addActionListener(parking -> {
                        JButton source = (JButton) parking.getSource();
                        if (source.getBackground().equals(Color.decode(GREEN))) {
                            vacantLargeCarParkingSpotAction(source);
                        } else {
                            payAction(parking, source);
                        }
                    });
                }
            }
            setActionParkingButton = false;
            contentPane.add(parkingSpotPanel);
            revalidate();
            repaint();

        };
    }

    private void vacantMotorcycleParkingSpotAction(JButton source) {
        JFrame frame = new JFrame("Estacionar");
        Container contentPane = frame.getContentPane();
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        CustomGridBagConstraints gbc = new CustomGridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        DefaultListModel<Motorcycle> motorcycleDefaultListModel = parking.getMotorcyclesDLM(clients);
        JList<Motorcycle> motorcycleList = new JList<>(motorcycleDefaultListModel);
        JScrollPane motorcycleScrollPane = new JScrollPane(motorcycleList);
        gbc.gridwidth = 2;
        contentPane.add(motorcycleScrollPane, gbc);
        gbc.gridwidth = 1;
        JButton parkButton = new JButton("Estacionar");
        parkButton.addActionListener(park -> {
            int selectedIndex = motorcycleList.getSelectedIndex();
            if (selectedIndex != - 1) {
                try {
                    source.setBackground(Color.decode(RED));
                    LocalDateTime dateTime = LocalDateTime.now();
                    System.out.println(dateTime);
                    source.putClientProperty("vehicle", motorcycleDefaultListModel.getElementAt(selectedIndex));
                    ((Motorcycle) source.getClientProperty("vehicle")).setParkedTime(dateTime);
                    Serializer.write("parking.spt", parking);
                    Serializer.write("clients.clt", clients);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
                source.setToolTipText(source.getClientProperty("vehicle").toString());
                frame.dispose();
            }

        });
        gbc.cell(0, 1);
        contentPane.add(parkButton, gbc);
        gbc.cell(1, 1);
        addCancelButton(frame, contentPane, gbc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void vacantMediumCarParkingSpotAction(JButton source) {
        JFrame frame = new JFrame("Estacionar");
        Container contentPane = frame.getContentPane();
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        CustomGridBagConstraints gbc = new CustomGridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        DefaultListModel<MediumCar> mediumCarDefaultListModel = parking.getMediumCarsDLM(clients);
        JList<MediumCar> mediumCarList = new JList<>(mediumCarDefaultListModel);
        JScrollPane mediumCarScrollPane = new JScrollPane(mediumCarList);
        gbc.gridwidth = 2;
        contentPane.add(mediumCarScrollPane, gbc);
        gbc.gridwidth = 1;
        JButton parkButton = new JButton("Estacionar");
        parkButton.addActionListener(park -> {
            int selectedIndex = mediumCarList.getSelectedIndex();
            if (selectedIndex != - 1) {
                try {
                    source.setBackground(Color.decode(RED));
                    LocalDateTime dateTime = LocalDateTime.now();
                    System.out.println(dateTime);
                    source.putClientProperty("vehicle", mediumCarDefaultListModel.getElementAt(selectedIndex));
                    ((MediumCar) source.getClientProperty("vehicle")).setParkedTime(dateTime);
                    Serializer.write("parking.spt", parking);
                    Serializer.write("clients.clt", clients);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
                source.setToolTipText(source.getClientProperty("vehicle").toString());
                frame.dispose();
            }

        });
        gbc.cell(0, 1);
        contentPane.add(parkButton, gbc);
        gbc.cell(1, 1);
        addCancelButton(frame, contentPane, gbc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void vacantLargeCarParkingSpotAction(JButton source) {
        JFrame frame = new JFrame("Estacionar");
        Container contentPane = frame.getContentPane();
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        CustomGridBagConstraints gbc = new CustomGridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        DefaultListModel<LargeCar> largeCarDefaultListModel = parking.getLargeCarsDLM(clients);
        JList<LargeCar> largeCarList = new JList<>(largeCarDefaultListModel);
        JScrollPane largeCarScrollPane = new JScrollPane(largeCarList);
        gbc.gridwidth = 2;
        contentPane.add(largeCarScrollPane, gbc);
        gbc.gridwidth = 1;
        JButton parkButton = new JButton("Estacionar");
        parkButton.addActionListener(park -> {
            int selectedIndex = largeCarList.getSelectedIndex();
            if (selectedIndex != - 1) {
                try {
                    source.setBackground(Color.decode(RED));
                    LocalDateTime dateTime = LocalDateTime.now();
                    System.out.println(dateTime);
                    source.putClientProperty("vehicle", largeCarDefaultListModel.getElementAt(selectedIndex));
                    ((LargeCar) source.getClientProperty("vehicle")).setParkedTime(dateTime);
                    Serializer.write("parking.spt", parking);
                    Serializer.write("clients.clt", clients);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
                source.setToolTipText(source.getClientProperty("vehicle").toString());
                frame.dispose();
            }

        });
        gbc.cell(0, 1);
        contentPane.add(parkButton, gbc);
        gbc.cell(1, 1);
        addCancelButton(frame, contentPane, gbc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addMotorcycleButtons(CustomGridBagConstraints gbc, ArrayList<JButton> motorcycleSpotButton) {
        for (int i = 0; i < parking.getMaxMotorcycleSpot(); i++) {
            if (i < parking.getMaxMotorcycleSpot() / 2) {
                gbc.cell(0, i + 1);
            } else {
                gbc.cell(1, (i + 1) - (parking.getMaxMotorcycleSpot() / 2));
            }
            parkingSpotPanel.add(motorcycleSpotButton.get(i), gbc);
        }
    }

    private void addMediumCarButtons(CustomGridBagConstraints gbc, ArrayList<JButton> mediumCarSpotButton) {
        for (int i = 0; i < parking.getMaxMediumCarSpot(); i++) {
            if (i < parking.getMaxMediumCarSpot() / 2) {
                if (i % 2 == 0) {
                    gbc.cell(2, 1 + i / 2);
                } else gbc.cell(3, 1 + i / 2);
            } else {
                if (i % 2 == 0) {
                    gbc.cell(4, 1 + (i - parking.getMaxMediumCarSpot() / 2) / 2);
                } else {
                    gbc.cell(5, 1 + (i - parking.getMaxMediumCarSpot() / 2) / 2);
                }
            }
            parkingSpotPanel.add(mediumCarSpotButton.get(i), gbc);
        }
    }

    private void addLargeCarButtons(CustomGridBagConstraints gbc, ArrayList<JButton> largeCarSpotButton) {
        for (int i = 0; i < parking.getMaxLargeCarSpot(); i++) {
            if (i < parking.getMaxLargeCarSpot() / 2) {
                gbc.insets = new Insets(5, 20, 0, 5);
                gbc.cell(6, i + 1);
            } else {
                gbc.insets = new Insets(5, 0, 0, 5);
                gbc.cell(7, (i + 1) - (parking.getMaxLargeCarSpot() / 2));
            }
            parkingSpotPanel.add(largeCarSpotButton.get(i), gbc);
        }
    }

    private static void addCancelButton(JFrame frame, Container contentPane, CustomGridBagConstraints gbc) {
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(cancel -> frame.dispose());
        contentPane.add(cancelButton, gbc);
    }

    private void payAction(ActionEvent e, JButton source) {
        Vehicle vehicle = (Vehicle) ((JButton) e.getSource()).getClientProperty("vehicle");
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        Duration durationParked = Duration.between(((Vehicle) source.getClientProperty("vehicle")).getParkedTime(), LocalDateTime.now());
        System.out.println(durationParked.toSeconds());
        String hours = Double.toString(durationParked.toHoursPart());
        String days = Double.toString(durationParked.toDaysPart());
        String minutes = Double.toString(durationParked.toMinutesPart());
        frame.setLayout(new GridBagLayout());
        CustomGridBagConstraints gbc = new CustomGridBagConstraints();
        Font font1 = new Font("DIALOG", Font.BOLD, 16);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridwidth = 2;
        JLabel vehicleLabel = new JLabel(vehicle.toString());
        vehicleLabel.setFont(font1);
        contentPane.add(vehicleLabel, gbc);
        gbc.cell(0, 1);
        JLabel durationLabel = new JLabel(days + " dia(s), " + hours + "hora(s) e " + minutes + " minuto(s)");
        durationLabel.setFont(font1);
        contentPane.add(durationLabel, gbc);
        JLabel priceLabel = new JLabel("R$" + vehicle.payParking());
        priceLabel.setFont(font1);
        gbc.cell(0, 2);
        contentPane.add(priceLabel, gbc);
        gbc.gridwidth = 1;

        gbc.cell(0, 3);
        JButton payButton = new JButton("Pagar");
        payButton.addActionListener(pay -> {
            source.setBackground(Color.decode(GREEN));
            source.putClientProperty("vehicle", null);
            frame.dispose();
        });
        contentPane.add(payButton, gbc);

        gbc.cell(1, 3);
        addCancelButton(frame, contentPane, gbc);
//        JButton cancelButton = new JButton("Cancelar");
//        cancelButton.addActionListener(cancel -> frame.dispose());
//        contentPane.add(cancelButton, gbc);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println(source.getClientProperty("vehicle"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingApp app = new SwingApp();
                app.setLocationRelativeTo(null);
                app.setVisible(true);
            }
        });
    }

    private void readObjects() {
        try {
            clients = (DefaultListModel<Client>) Serializer.read("clients.clt");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Arquivo client.clt nao encontrado");
            System.out.println("Criacao dos primeiros clientes a partir do arquivo txt");
            clients = ReaderTextFile.selectClientsFromList();
            Sort.sortClientListModel(clients);
            ReaderTextFile.putVehiclesToClients(clients);
        }
        try {
            parking = (Parking) Serializer.read("parking.spt");
            System.out.println("try");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("catch");
            parking = new Parking(20, 40, 12);
        }

    }


    private void popError(String message) {
        JFrame frame = new JFrame(message);
        JLabel label = new JLabel(message);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) frame.dispose();
            }
        });
        frame.setVisible(true);
    }
}


