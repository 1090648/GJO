package gjo.prog;

import bibliotecagjo.Atleta;
import bibliotecagjo.Pais;
import bibliotecagjo.Prova;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class DialogCriarPaís extends JDialog {

    private JButton btnOk, btnCancelar;
    private JTextField txtNomePais;
    private JFileChooser fichMod;
    private int retorno;
    private Pais pais;

    public DialogCriarPaís(JFrame pai, String titulo) {
        super(pai, titulo, true);

        JPanel p1 = new JPanel(new GridLayout(1, 1));

        TrataEvento t = new TrataEvento();


        JPanel linha1 = new JPanel();
        JLabel lbl = new JLabel("Nome:");
        txtNomePais = new JTextField(20);
        linha1.add(lbl);
        linha1.add(txtNomePais);
        p1.add(linha1);


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

    public Pais getPais(){
        return pais;
    }
    
    public void setPais(Pais pais){
        txtNomePais.setText(pais.getPais());
    }
    
      
    private class TrataEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancelar) {
                dispose();
            }  else {
                if(txtNomePais.getText().isEmpty()){
                    JOptionPane.showMessageDialog(DialogCriarPaís.this, "Introduza o nome do país!", "Criação de País", JOptionPane.WARNING_MESSAGE);
                    txtNomePais.requestFocus();
                }else{
                    String pais = txtNomePais.getText();
                 try{
                        dispose();
                        GJOPROG.getPais().add(new Pais(pais));
                        GJOPROG.gravarFicheiroPaises();
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(DialogCriarPaís.this, ex.getMessage(), "Criação de País", JOptionPane.WARNING_MESSAGE);
                    }
            }
        }
    }
}}
