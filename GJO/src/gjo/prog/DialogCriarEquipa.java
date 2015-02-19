package gjo.prog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class DialogCriarEquipa extends JDialog {

    private JButton btnCarregar, btnOk, btnCancelar;
    private JFileChooser fichMod;
    private int retorno;

    public DialogCriarEquipa(JFrame pai, String titulo) {
        super(pai, titulo, true);

        JPanel p1 = new JPanel(new GridLayout(4, 1));

        TrataEvento t = new TrataEvento();

        JPanel linha = new JPanel();
        JLabel lbl3 = new JLabel("Carregar por ficheiro:");
        btnCarregar = new JButton("Carregar");
        btnCarregar.addActionListener(t);
        linha.add(lbl3);
        linha.add(btnCarregar);
        p1.add(linha);


        JPanel p2 = new JPanel();
        btnOk = new JButton("OK");
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

    private class TrataEvento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancelar) {
                dispose();
            } else if (e.getSource() == btnCarregar) {
                fichMod = new JFileChooser();
                retorno = fichMod.showOpenDialog(btnCarregar);
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    File ficheiro = fichMod.getSelectedFile();
                }
            }
        }
    }
}