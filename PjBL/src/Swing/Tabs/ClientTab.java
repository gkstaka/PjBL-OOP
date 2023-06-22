//package Swing.Tabs;
//
//import Excecoes.*;
//import Pessoa.Client;
//import Functions.*;
//import Swing.CustomComponents.CustomButton;
//import Swing.CustomComponents.CustomLabel;
//import Swing.CustomComponents.CustomTextField;
//import Veiculo.Vehicle;
//
//import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.plaf.BorderUIResource;
//import javax.swing.plaf.basic.BasicListUI;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//public class ClientTab extends Tab {
//
//    private JList<Client> clientList;
//    private JScrollPane clientsScrollPane;
//
//    private JPanel clientJPanel;
//    private CustomLabel nameDisplay;
//    private CustomLabel cpfDisplay;
//    private CustomLabel phoneDisplay;
//    private CustomLabel vehicleDisplay;
//    private CustomButton registerClientButton;
//    private CustomButton removeClientButton;
//    private CustomButton updateClientButton;
//
//    public ClientTab(DefaultListModel<Client> clientsDefaultListModel, DefaultListModel<Vehicle> vehiclesDefaultListModel) {
//        super(clientsDefaultListModel, vehiclesDefaultListModel);
//        setLayout(null);
//
//        clientList = new JList<Client>(clientsDefaultListModel);
//        clientList.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
//        clientList.addListSelectionListener(selectUpdate());
//        clientsScrollPane = new JScrollPane(clientList);
//        clientsScrollPane.setBounds(10, 10, 240, 650);
//        add(clientsScrollPane);
//
//        Font font = new Font("DIALOG", Font.BOLD, 14);
//        clientJPanel = new JPanel();
//        clientJPanel.setLayout(null);
//        clientJPanel.setBounds(clientsScrollPane.getX() + clientsScrollPane.getWidth() + 10, 10, 210, 105);
//        clientJPanel.setBorder(BorderUIResource.getBlackLineBorderUIResource());
//        nameDisplay = new CustomLabel("Nome:");
//        nameDisplay.setFont(font);
//        nameDisplay.setAnchor(5, 5);
//        clientJPanel.add(nameDisplay);
//
//        cpfDisplay = new CustomLabel("CPF:");
//        cpfDisplay.setFont(font);
//        cpfDisplay.setAnchor(nameDisplay, 'V', + nameDisplay.getHeight() + 10);
//        clientJPanel.add(cpfDisplay);
//
//        phoneDisplay = new CustomLabel("Telefone:");
//        phoneDisplay.setFont(font);
//        phoneDisplay.setAnchor(cpfDisplay, 'V', 10);
//        clientJPanel.add(phoneDisplay);
//
//        add(clientJPanel);
//        clientJPanel.setVisible(false);
//        registerClientButton = new CustomButton("Adicionar cliente");
//        registerClientButton.setAnchor(clientJPanel, 'V', 10);
//        registerClientButton.addActionListener(actionRegister());
//        add(registerClientButton);
//
//        removeClientButton = new CustomButton("Remover cliente");
//        removeClientButton.setAnchor(registerClientButton, 'V', 10);
//        removeClientButton.addActionListener(actionRemove());
//        JLabel jLabel = new JLabel();
//        add(removeClientButton);
//
//        updateClientButton = new CustomButton("Atualizar contato");
//        updateClientButton.setAnchor(removeClientButton, 'V', 10);
//        updateClientButton.addActionListener(actionUpdate());
//        add(updateClientButton);
//
//
//    }
//
//    private ActionListener actionRegister() {
//        return new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFrame frame = new JFrame("Adicionar cliente");
//                frame.setSize(250, 195);
//                frame.setLayout(null);
//                int height = 25;
//
//                //labels
//                CustomLabel nameLabel = new CustomLabel("Nome:", height);
//                nameLabel.setAnchor(10, 10);
//                frame.add(nameLabel);
//                CustomLabel cpfLabel = new CustomLabel("CPF:", height);
//                cpfLabel.setAnchor(nameLabel, 'V', 10);
//                frame.add(cpfLabel);
//                CustomLabel phoneLabel = new CustomLabel("Telefone:", height);
//                phoneLabel.setAnchor(cpfLabel, 'V', 10);
//                frame.add(phoneLabel);
//                //textfields
//                CustomTextField nameTF = new CustomTextField("Nome");
//                nameTF.setPreferredSize(new Dimension(frame.getWidth() - (40 + phoneLabel.getWidth()), height));
//                nameTF.setAnchor(nameLabel, 'H', 20);
//                frame.add(nameTF);
//                CustomTextField cpfTF = new CustomTextField("CPF");
//                cpfTF.setPreferredSize(new Dimension(nameTF.getWidth(), height));
//                cpfTF.setAnchor(nameTF, 'V', 10);
//                frame.add(cpfTF);
//                CustomTextField phoneTF = new CustomTextField("Telefone");
//                phoneTF.setPreferredSize(new Dimension(nameTF.getWidth(), height));
//                phoneTF.setAnchor(cpfTF, 'V', 10);
//                frame.add(phoneTF);
//                //buttons
//                CustomButton confirmButton = new CustomButton("Confirmar");
//                confirmButton.setPreferredSize(new Dimension(100, 30));
//                confirmButton.setAnchor(phoneLabel, 'V', 10);
//                confirmButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        String name = nameTF.getText();
//                        String cpf = cpfTF.getText();
//                        String phone = phoneTF.getText();
//                        try {
//                            Client newClient = new Client(name, cpf, phone);
//                            boolean isNewClient = false;
//                            for (int i = 0; i < clientsDefaultListModel.getSize(); i++) {
//                                isNewClient = clientsDefaultListModel.getElementAt(i).equals(newClient);
//                            }
//                            if (! isNewClient) {
//                                clientsDefaultListModel.addElement(new Client(name, cpf, phone));
//                                ClientTab.sortClientListModel(clientsDefaultListModel);
//                                Serializer.write("clients.clt", clientsDefaultListModel);
//                                frame.dispose();
//                            } else {
//                                throw new PersonExceptionCPFUsed();
//                            }
//                        } catch (IOException exception) {
//                            exception.printStackTrace();
//                        } catch (PersonExceptionInvalidCPF exception) {
//                            popError("CPF invalido");
//                        } catch (PersonExceptionInvalidPhone exception) {
//                            popError("Telefone invalido");
//                        } catch (PersonExceptionCPFUsed exception) {
//                            popError("CPF ja cadastrado");
//                        }
//                    }
//                });
//                frame.add(confirmButton);
//                CustomButton cancelButton = new CustomButton("Cancelar");
//                cancelButton.setAnchor(confirmButton, 'H', 10);
//                cancelButton.setSize(new Dimension(100, 30));
//                cancelButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        frame.dispose();
//                    }
//                });
//
//                frame.add(cancelButton);
//                frame.setResizable(false);
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//
//        };
//    }
//
//    private ActionListener actionRemove() {
//        return new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedIndex = clientList.getSelectedIndex();
//                if (selectedIndex != - 1) {
//                    clientsDefaultListModel.removeElementAt(selectedIndex);
//                    try {
//                        Serializer.write("clients.clt", clientsDefaultListModel);
//                    } catch (IOException exception) {
//                        exception.printStackTrace();
//                    }
//                }
//            }
//        };
//    }
//
//    private ActionListener actionUpdate() {
//        return new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedIndex = clientList.getSelectedIndex();
//                if (selectedIndex != - 1) {
//                    JFrame frame = new JFrame();
//                    frame.setLayout(null);
//                    frame.setSize(250, 125);
//                    int height = 25;
//                    CustomLabel phoneLabel = new CustomLabel("Novo telefone:", height);
//                    phoneLabel.setAnchor(10, 10);
//                    frame.add(phoneLabel);
//
//                    CustomTextField phoneTF = new CustomTextField("Telefone");
//                    phoneTF.setPreferredSize(new Dimension(frame.getWidth() - (45 + phoneLabel.getWidth()), height));
//                    phoneTF.setAnchor(phoneLabel, 'H', 10);
//                    frame.add(phoneTF);
//
//                    CustomButton confirmButton = new CustomButton("Confirmar");
//                    confirmButton.setPreferredSize(new Dimension(100, 30));
//                    confirmButton.setAnchor(phoneLabel, 'V', 10);
//                    confirmButton.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            String phone = phoneTF.getText();
//                            try {
//                                clientsDefaultListModel.getElementAt(selectedIndex).setPhone(phone);
//                                Serializer.write("clients.clt", clientsDefaultListModel);
//                                infoUpdate();
//                                frame.dispose();
//
//                            } catch (IOException exception) {
//                                exception.printStackTrace();
//                            } catch (PersonExceptionInvalidPhone exception) {
//                                popError("Telefone invalido");
//                            }
//                        }
//                    });
//                    frame.add(confirmButton);
//
//                    CustomButton cancelButton = new CustomButton("Cancelar");
//                    cancelButton.setAnchor(confirmButton, 'H', 10);
//                    cancelButton.setSize(new Dimension(100, 30));
//                    cancelButton.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            frame.dispose();
//                        }
//                    });
//                    frame.add(cancelButton);
//
//                    frame.setLocationRelativeTo(null);
//                    frame.setVisible(true);
//                }
//            }
//        };
//    }
//
//    private ListSelectionListener selectUpdate(){
//        return new ListSelectionListener(){
//          @Override
//          public void valueChanged(ListSelectionEvent e){
//        int selectedIndex=clientList.getSelectedIndex();
//        if(selectedIndex!=-1){
//        Client client=clientsDefaultListModel.getElementAt(selectedIndex);
//        nameDisplay.setText("<html>Nome:<br>"+client.getName()+"</html>");
//        nameDisplay.setSize(200,nameDisplay.getPreferredSize().height);
//        cpfDisplay.setText("CPF: "+client.getCpf());
//        cpfDisplay.setSize(cpfDisplay.getPreferredSize());
//        cpfDisplay.setAnchor(nameDisplay,'V',10);
//        phoneDisplay.setText("Telefone: "+client.getPhone());
//        phoneDisplay.setSize(phoneDisplay.getPreferredSize());
//        phoneDisplay.setAnchor(cpfDisplay,'V',10);
//
//        }
//        }
//        };
//
//        }
//
//    private void infoUpdate() {
//
//    }
//
//
//}
