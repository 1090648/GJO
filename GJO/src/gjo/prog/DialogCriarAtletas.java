package gjo.prog;

import bibliotecagjo.Atleta;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;

public class DialogCriarAtletas extends JDialog {

    private JButton btnOk, btnCancelar;
    private JFileChooser fichMod;
    private int retorno;
    private JTextField txtNomeAt;
    private JComboBox sexoAt, paisAt;
    private String[] sexo = {"Masculino","Feminino"};
    private Atleta atleta;
        
    
    public DialogCriarAtletas(JFrame pai, String titulo) {
        super(pai, titulo, true);

        JPanel p1 = new JPanel(new GridLayout(3, 1));

        TrataEvento t = new TrataEvento();
        
        JPanel linha1 = new JPanel();
        JLabel lbl = new JLabel("Nome:");
        txtNomeAt = new JTextField(20);
        linha1.add(lbl);
        linha1.add(txtNomeAt);
        p1.add(linha1);

        JPanel linha2 = new JPanel();
        JLabel lbl2 = new JLabel("Sexo:");
        sexoAt = new JComboBox(sexo);
        linha2.add(lbl2);
        linha2.add(sexoAt);
        p1.add(linha2);

        JPanel linha4 = new JPanel();
        JLabel lbl4 = new JLabel("País:");
        paisAt = new JComboBox(GJOPROG.getPais().toArray());    
        linha4.add(lbl4);
        linha4.add(paisAt);
        p1.add(linha4);

        
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
    
    public Atleta getAtleta(){
        return atleta;
    }
    
    public void setAtleta(Atleta atleta){
        paisAt.setSelectedItem(atleta.getPais());
        txtNomeAt.setText(atleta.getNome());
        sexoAt.setSelectedItem(atleta.getSexo());        
    }
    
    private class TrataEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancelar) {
                dispose();
            }  else{
                if(txtNomeAt.getText().isEmpty()){
                JOptionPane.showMessageDialog(DialogCriarAtletas.this,"Introduza o nome do atleta!","Introdução de Atletas",JOptionPane.WARNING_MESSAGE);
                txtNomeAt.requestFocus();
            } else {
                    try{
                        atleta = new Atleta(paisAt.getSelectedItem().toString() ,txtNomeAt.getText(), (String)sexoAt.getSelectedItem());
                        dispose();
                         GJOPROG.getAtleta().add(new Atleta(atleta));
                         GJOPROG.gravarFicheiroAtletas();
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(DialogCriarAtletas.this, ex.getMessage(), "Introdução de Atleta", JOptionPane.WARNING_MESSAGE);
                    }
                }
        }
    }
}
}