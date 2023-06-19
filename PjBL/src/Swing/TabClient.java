package Swing;

import Excecoes.CPFInvalido;
import Excecoes.TelefoneInvalido;
import Pessoa.Client;
import Functions.*;
import Veiculo.Vehicle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TabClient extends Tab {
    private DefaultListModel<Client> clientDefaultListModel;
    private JList<Client> clientList;
    private JScrollPane clientsScrollPane;
    private JButton registerClientButton;
    private JButton removeClientButton;
    private JButton updateClientButton;

    public TabClient() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbcLayout = new GridBagConstraints();
        gbcLayout.fill = GridBagConstraints.NONE;
        gbcLayout.anchor = GridBagConstraints.WEST;
        gbcLayout.insets = new Insets(10, 10, 10, 10);
        setLayout(layout);

        clientDefaultListModel = new DefaultListModel<Client>();
        try {
            clientDefaultListModel = (DefaultListModel<Client>) Serializer.read("clients.clt");
            clientDefaultListModel = Sort.sortClient(clientDefaultListModel);
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("erro");
            exception.printStackTrace();
        }
        clientList = new JList<Client>(clientDefaultListModel);
        clientsScrollPane = new JScrollPane(clientList);
        clientsScrollPane.createHorizontalScrollBar();
        gbcLayout.ipadx = 100;
        gbcLayout.ipady = 400;
        cell(gbcLayout, 0, 0);
        gbcLayout.gridheight = 6;
        add(clientsScrollPane, gbcLayout);

        gbcLayout.ipadx = 150;
        gbcLayout.ipady = 0;
        gbcLayout.gridheight = 1;
        JLabel clientNameL = new JLabel("Nome: ");
        JLabel clientCPFL = new JLabel("CPF: ");
        //clientCPFL.setBounds(300, 340, 50, 20);
        JLabel clientPhoneL = new JLabel("Telefone: ");
        //clientPhoneL.setBounds(300, 380, 50, 20);
        clientList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (! e.getValueIsAdjusting()) {
                    Client selectedClient = clientList.getSelectedValue();
                    clientNameL.setText("Nome: " + selectedClient.getName());
                    clientCPFL.setText("CPF: " + selectedClient.getCpf());
                    clientPhoneL.setText("Telefone: " + selectedClient.getPhone());
                }
            }
        });
        JPanel rightComponents = new JPanel();
        GridBagLayout gblRightComponents = new GridBagLayout();
        GridBagConstraints gbcRightComponents = new GridBagConstraints();
        gbcRightComponents.fill = GridBagConstraints.HORIZONTAL;
        gbcRightComponents.anchor = GridBagConstraints.NORTHWEST;
        gbcRightComponents.insets = new Insets(10, 10, 10, 10);
        cell(gbcRightComponents, 0, 0);
        rightComponents.add(clientNameL, gbcRightComponents);
        cell(gbcRightComponents, 0, 1);
        rightComponents.add(clientCPFL, gbcRightComponents);
        cell(gbcRightComponents, 0, 2);
        rightComponents.add(clientPhoneL, gbcRightComponents);

        registerClientButton = new JButton("Adicionar cliente");
        registerClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame pop = new JFrame("Criar cliente");

                pop.setSize(400, 120);
                pop.setResizable(false);
                pop.setLayout(new GridLayout(4, 2));
                pop.setLocationRelativeTo(null);

                JLabel nameL = new JLabel("Nome: ");
                pop.add(nameL);
                JTextField nameTF = new JTextField();
                pop.add(nameTF);

                JLabel cpfL = new JLabel("CPF: ");
                pop.add(cpfL);
                JTextField cpfTF = new JTextField();
                pop.add(cpfTF);

                JLabel phoneL = new JLabel("Telefone: ");
                pop.add(phoneL);
                JTextField phoneTF = new JTextField();
                pop.add(phoneTF);

                JButton confirmB = new JButton("Confirmar");
                confirmB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameTF.getText();
                        String cpf = cpfTF.getText();
                        String phone = phoneTF.getText();
                        try {
                            clientDefaultListModel.addElement(new Client(name, cpf, phone));
                            Serializer.write("clients.clt", clientDefaultListModel);
                            pop.dispose();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } catch (CPFInvalido exception) {
                            popError("CPF invalido");
                        } catch (TelefoneInvalido exception) {
                            popError("Telefone invalido");
                        }
                    }
                });
                pop.add(confirmB);
                JButton cancelB = new JButton("Cancelar");
                cancelB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pop.dispose();
                    }
                });
                pop.add(cancelB);
                pop.setVisible(true);
            }
        });
        cell(gbcRightComponents, 0, 3);
        rightComponents.add(registerClientButton, gbcRightComponents);

        removeClientButton = new JButton("Remover cliente");
        removeClientButton.setBounds(registerClientButton.getX(), registerClientButton.getY() + registerClientButton.getHeight() + 20,
                300, 30);
        removeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = clientList.getSelectedIndex();
                if (selectedIndex != - 1) {
                    clientDefaultListModel.remove(selectedIndex);
                    try {
                        Serializer.write("clients.clt", clientDefaultListModel);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        cell(gbcRightComponents, 0, 4);
        rightComponents.add(removeClientButton, gbcRightComponents);

        updateClientButton = new JButton("Atualizar telefone");
        updateClientButton.setBounds(removeClientButton.getX(), removeClientButton.getY() + removeClientButton.getHeight() + 20,
                300, 30);
        updateClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = clientList.getSelectedIndex();
                System.out.println(selectedIndex);
                if (selectedIndex != - 1) {
                    JFrame pop = new JFrame("Atualizar contato");
                    pop.setSize(400, 120);
                    pop.setResizable(false);
                    GridBagLayout gridBagLayout = new GridBagLayout();
                    GridBagConstraints gbcF = new GridBagConstraints();
                    gbcF.fill = GridBagConstraints.BOTH;
                    gbcF.insets = new Insets(10, 10, 10, 10);
                    gbcF.anchor = GridBagConstraints.CENTER;
                    pop.setLayout(gridBagLayout);

                    gbcF.gridwidth = 2;
                    gbcF.gridheight = 1;
                    JLabel nameL = new JLabel("Nome: " + clientDefaultListModel.getElementAt(selectedIndex).getName());
                    cell(gbcF, 0, 0);
                    pop.add(nameL, gbcF);

                    JLabel cpfL = new JLabel("CPF: " + clientDefaultListModel.getElementAt(selectedIndex).getCpf());
                    cell(gbcF, 0, 1);
                    pop.add(cpfL, gbcF);

                    gbcF.gridwidth = 1;
                    JLabel phoneL = new JLabel("Telefone: ");
                    cell(gbcF, 0, 2);
                    pop.add(phoneL, gbcF);

                    JTextField phoneTF = new JTextField(10);
                    cell(gbcF, 1, 2);
                    pop.add(phoneTF, gbcF);

                    gbcF.gridwidth = 2;
                    cell(gbcF, 0, 3);
                    GridBagConstraints gbcBP = new GridBagConstraints();
                    gbcBP.fill = GridBagConstraints.BOTH;
                    gbcBP.insets = new Insets(10, 10, 10, 10);
                    gbcBP.anchor = GridBagConstraints.CENTER;
                    JPanel buttonP = new JPanel();
                    JButton confirmB = new JButton("Confirmar");
                    confirmB.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String phone = phoneTF.getText();
                            try {
                                clientDefaultListModel.elementAt(selectedIndex).setPhone(phone);
                                Serializer.write("clients.clt", clientDefaultListModel);
                                pop.dispose();
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            } catch (TelefoneInvalido exception) {
                                popError("Telefone invalido");
                            } finally {
                                System.out.println(clientDefaultListModel.elementAt(selectedIndex).getPhone());
                            }
                        }
                    });
                    cell(gbcBP, 0, 0);
                    buttonP.add(confirmB, gbcBP);

                    JButton cancelB = new JButton("Cancelar");
                    cancelB.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pop.dispose();
                        }
                    });
                    cell(gbcBP, 0, 1);
                    buttonP.add(cancelB, gbcBP);
                    pop.add(buttonP, gbcF);
                    pop.pack();
                    pop.setLocationRelativeTo(null);
                    pop.setVisible(true);

                }


            }
        });
        cell(gbcRightComponents, 0, 5);
        rightComponents.add(updateClientButton, gbcRightComponents);
        cell(gbcLayout, 1, 0);
        add(rightComponents, gbcLayout);
    }

    static void cell(GridBagConstraints gridBagConstraints, int x, int y) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
    }


}
