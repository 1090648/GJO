package gjo.prog;

import bibliotecagjo.Atleta;
import bibliotecagjo.Modalidade;
import bibliotecagjo.Prova;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class DialogCriarProva extends JDialog {

    private JButton btnOk, btnCancelar;
    private JFileChooser fichMod;
    private int retorno;
    private JTextField txtNomeProv;
    private JComboBox sexoProv, tipoClass, modProv;
    private String[] sexo = {"Masculino", "Feminino"};
    private String[] classifica = {"Tempo", "Pontos", "Comprimento"};
    private Prova prova;

    public DialogCriarProva(JFrame pai, String titulo) {
        super(pai, titulo, true);

        JPanel p1 = new JPanel(new GridLayout(4, 1));

        TrataEvento t = new TrataEvento();

     
        JPanel linha1 = new JPanel();
        JLabel lbl = new JLabel("Nome:");
        txtNomeProv = new JTextField(20);
        linha1.add(lbl);
        linha1.add(txtNomeProv);
        p1.add(linha1);

        JPanel linha2 = new JPanel();
        JLabel lbl2 = new JLabel("Sexo:");
        sexoProv = new JComboBox(sexo);
        linha2.add(lbl2);
        linha2.add(sexoProv);
        p1.add(linha2);

        JPanel linha4 = new JPanel();
        JLabel lbl4 = new JLabel("Tipo de classificação");
        tipoClass = new JComboBox(classifica);
        linha4.add(lbl4);
        linha4.add(tipoClass);
        p1.add(linha4);

        JPanel linha5 = new JPanel();
        JLabel lbl5 = new JLabel("Modalidade da prova");
        modProv = new JComboBox(GJOPROG.getModalidade().toArray());
        linha5.add(lbl5);
        linha5.add(modProv);
        p1.add(linha5);


        JPanel p2 = new JPanel();
        btnOk = new JButton("OK");
        btnOk.addActionListener(t);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(t);
        p2.add(btnOk);
        p2.add(btnCancelar);

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocation(pai.getX() + 100, pai.getY() + 100);
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        modProv.setSelectedItem(prova.getModalidade());
        txtNomeProv.setText(prova.getProva());
        sexoProv.setSelectedItem(prova.getSexo());
        tipoClass.setSelectedItem(prova.getTipoClassificacao());
    }

    private class TrataEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancelar) {
                dispose();
            } else {
                if (txtNomeProv.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(DialogCriarProva.this, "Introduza o nome da prova!", "Criação de Prova", JOptionPane.WARNING_MESSAGE);
                    txtNomeProv.requestFocus();
                } else {
                    String modalidade = modProv.getSelectedItem().toString();
                    String prova = txtNomeProv.getText();
                    String sexo = (String)sexoProv.getSelectedItem();
                    String tipoClassificacao = (String) tipoClass.getSelectedItem();
//                    prova = new Prova(modProv.getSelectedItem().toString(), txtNomeProv.getText(), (String) sexoProv.getSelectedItem(), (String) tipoClass.getSelectedItem());   //FALHA NO CAST DA MODALIDADE
                    try {
                        dispose();
                        GJOPROG.getProva().add(new Prova(modalidade, prova, sexo, tipoClassificacao));
                        GJOPROG.gravarFicheiroProva();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DialogCriarProva.this, ex.getMessage(), "Criação de Prova", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    private static void CarFich() {
    }
}
