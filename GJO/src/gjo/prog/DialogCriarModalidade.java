package gjo.prog;

import bibliotecagjo.Modalidade;
import bibliotecagjo.Pais;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class DialogCriarModalidade extends JDialog {

    private JTextField txtNomeMod;
    private JFileChooser fichMod;
    private int retorno;
    private JButton btnOk, btnCancelar;
    private Modalidade  modalidade;
    
    public DialogCriarModalidade(JFrame pai, String titulo) {
        super(pai, titulo, true);

        JPanel p1 = new JPanel(new GridLayout(1, 1));

        TrataEvento t = new TrataEvento();

        JPanel linha1 = new JPanel();
        JLabel lbl = new JLabel("Nome:");
        txtNomeMod = new JTextField(20);
        linha1.add(lbl);
        linha1.add(txtNomeMod);
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

      public Modalidade getModalidade(){
        return modalidade;
    }
    
    public void setModalidade(Modalidade modalidade){
        txtNomeMod.setText(modalidade.getModalidade());
    }
    
    private class TrataEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancelar) {
                modalidade=null;
                dispose();
            } else{
                if(txtNomeMod.getText().isEmpty()){
                    JOptionPane.showMessageDialog(DialogCriarModalidade.this, "Introduza o nome da Modalidade!", "Criação de Modalidade", JOptionPane.WARNING_MESSAGE);
                    txtNomeMod.requestFocus();
                }else{
                    String modalidade = txtNomeMod.getText();
                try{
                        dispose();
                        GJOPROG.getModalidade().add(new Modalidade(modalidade));
                        GJOPROG.gravarFicheiroModalidades();
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(DialogCriarModalidade.this, ex.getMessage(), "Criação de Modalidade", JOptionPane.WARNING_MESSAGE);
                    }
            }
        }
    }
}
}
