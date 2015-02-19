 package gjo.prog;

import bibliotecagjo.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() throws HeadlessException {
        super("Gestão Jogos Olímpicos");


        PainelFundo pf = new PainelFundo();
        add(pf);

        JMenuBar mb = new JMenuBar();


        setJMenuBar(mb);

        JMenu menu = new JMenu("Gestão");
        mb.add(menu);


        //Menu gestão -> modalidades
        JMenu subMenuMod = new JMenu("Modalidades");
        menu.add(subMenuMod);

        //Menu gestão -> modalidades -> criar ... criar modalidade
        JMenuItem criarMod = new JMenuItem("Criar");
        subMenuMod.add(criarMod);
        criarMod.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCriarModalidade dialog = new DialogCriarModalidade(JanelaPrincipal.this, "Criação de Modalidade");
                dialog.setVisible(true);
                Modalidade modalidade = dialog.getModalidade();
                if (modalidade != null) {
                    GJOPROG.getModalidade().add(modalidade);
                }
            }
        });


        //Menu gestão -> modalidades -> editar  .... editar modalidades
        JMenuItem editarMod = new JMenuItem("Editar");
        subMenuMod.add(editarMod);
        editarMod.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getModalidade().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Modalidades", "Gestão de Jogos Olimpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a modalidade:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getModalidade().toArray(), GJOPROG.getModalidade().get(0));
                    if (obj != null) {
                        DialogCriarModalidade dialog = new DialogCriarModalidade(JanelaPrincipal.this, "Edição de Modalidade");
                        dialog.setModalidade((Modalidade) obj);
                        dialog.setVisible(true);
                        Modalidade mod = dialog.getModalidade();
                        if (mod != null) {
                            GJOPROG.getModalidade().set(GJOPROG.getModalidade().indexOf(obj), mod);
                        }
                    }
                }
            }
        });

        //Menu gestão -> modalidades -> apagar  ... apagar modalidades
        JMenuItem apagarMod = new JMenuItem("Apagar");
        subMenuMod.add(apagarMod);
        apagarMod.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getModalidade().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Modalidades", "Gestão de Jogos Olímpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a modalidade:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getModalidade().toArray(), GJOPROG.getModalidade().get(0));
                    if (obj != null) {
                        Object[] opSimNao = {"Sim", "Não"};
                        if (JOptionPane.showOptionDialog(JanelaPrincipal.this, "Tem a certeza?", "Eliminação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opSimNao, opSimNao[1]) == JOptionPane.YES_OPTION) {
                            GJOPROG.getModalidade().remove(obj);
                            GJOPROG.gravarFicheiroModalidades();
                        }

                    }
                }
            }
        });


        //Menu gestão -> provas
        JMenu subMenuProv = new JMenu("Provas");
        menu.add(subMenuProv);

        //Menu gestão -> provas -> criar ... criar provas
        JMenuItem criarProv = new JMenuItem("Criar");
        subMenuProv.add(criarProv);
        criarProv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCriarProva dialog = new DialogCriarProva(JanelaPrincipal.this, "Criação de Prova");
                dialog.setVisible(true);
                Prova provas = dialog.getProva();
                if (provas != null) {
                    GJOPROG.getProva().add(provas);
                }
            }
        });


        //Menu gestão -> provas -> editar  .... editar provas
        JMenuItem editarProv = new JMenuItem("Editar");
        subMenuProv.add(editarProv);
        editarProv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getProva().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Provas", "Gestão de Jogos Olimpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a prova:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getProva().toArray(), GJOPROG.getProva().get(0));
                    if (obj != null) {
                        DialogCriarProva dialog = new DialogCriarProva(JanelaPrincipal.this, "Edição de Prova");
                        dialog.setProva((Prova) obj);
                        dialog.setVisible(true);
                        Prova prov = dialog.getProva();
                        if (prov != null) {
                            GJOPROG.getProva().set(GJOPROG.getProva().indexOf(obj), prov);
                        }
                    }
                }
            }
        });

        //Menu gestão -> provas -> apagar  ... apagar provas
        JMenuItem apagarProv = new JMenuItem("Apagar");
        subMenuProv.add(apagarProv);
        apagarProv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getProva().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Provas", "Gestão de Jogos Olímpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a Prova:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getProva().toArray(), GJOPROG.getProva().get(0));
                    if (obj != null) {
                        Object[] opSimNao = {"Sim", "Não"};
                        if (JOptionPane.showOptionDialog(JanelaPrincipal.this, "Tem a certeza?", "Eliminação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opSimNao, opSimNao[1]) == JOptionPane.YES_OPTION) {
                            GJOPROG.getProva().remove(obj);
                            GJOPROG.gravarFicheiroProva();
                        }

                    }
                }
            }
        });

        //Menu gestão -> países 
        JMenu subMenuPais = new JMenu("Países");
        menu.add(subMenuPais);

        //Menu gestão -> países -> criar  .. criar país
        JMenuItem criarPais = new JMenuItem("Criar");
        subMenuPais.add(criarPais);
        criarPais.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCriarPaís dialog = new DialogCriarPaís(JanelaPrincipal.this, "Criação de País");
                dialog.setVisible(true);
                Pais paises = dialog.getPais();
                if (paises != null) {
                    GJOPROG.getPais().add(paises);
                }
            }
        });

        //Menu gestão -> países -> editar ... editar país
        JMenuItem editarPais = new JMenuItem("Editar");
        subMenuPais.add(editarPais);
        editarPais.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getPais().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Países", "Gestão de Jogos Olimpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha o país:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getPais().toArray(), GJOPROG.getPais().get(0));
                    if (obj != null) {
                        DialogCriarPaís dialog = new DialogCriarPaís(JanelaPrincipal.this, "Edição de País");
                        dialog.setPais((Pais) obj);
                        dialog.setVisible(true);
                        Pais pais = dialog.getPais();
                        if (pais != null) {
                            GJOPROG.getPais().set(GJOPROG.getPais().indexOf(obj), pais);
                        }
                    }
                }
            }
        });

        //Menu gestão -> países -> apagar ... apagar país
        JMenuItem apagarPais = new JMenuItem("Apagar");
        subMenuPais.add(apagarPais);
        apagarPais.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getPais().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Países", "Gestão de Jogos Olímpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha o País:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getPais().toArray(), GJOPROG.getPais().get(0));
                    if (obj != null) {
                        Object[] opSimNao = {"Sim", "Não"};
                        if (JOptionPane.showOptionDialog(JanelaPrincipal.this, "Tem a certeza?", "Eliminação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opSimNao, opSimNao[1]) == JOptionPane.YES_OPTION) {
                            GJOPROG.getPais().remove(obj);
                            GJOPROG.gravarFicheiroPaises();
                        }

                    }
                }
            }
        });



        //Menu gestão -> introdução de dados
        JMenu subMenuRes = new JMenu("Intoduzir resultados");
        menu.add(subMenuRes);

        //Menu gestão -> introdução de dados -> atletas
        JMenu subMenuAt = new JMenu("Atletas");
        subMenuRes.add(subMenuAt);

        //Menu gestão -> introdução de dados -> atletas -> introduzir dados  ... para carregar os resultados das provas de atletas 
        JMenuItem criarAt = new JMenuItem("Introduzir dados");
        subMenuAt.add(criarAt);
        criarAt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCriarAtletas dialog = new DialogCriarAtletas(JanelaPrincipal.this, "Introdução de Atletas");
                dialog.setVisible(true);
                Atleta atletas = dialog.getAtleta();
                if (atletas != null) {
                    GJOPROG.getAtleta().add(atletas);
                    System.out.println(atletas);
                }
            }
        });


        //Menu gestão -> introdução de dados -> atletas -> editar  ... para editar os resultados das provas de atletas 
        JMenuItem editarAt = new JMenuItem("Editar");
        subMenuAt.add(editarAt);
        editarAt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getAtleta().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Atletas", "Gestão de Jogos Olimpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha o atleta:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getAtleta().toArray(), GJOPROG.getAtleta().get(0));
                    if (obj != null) {
                        DialogCriarAtletas dialog = new DialogCriarAtletas(JanelaPrincipal.this, "Edição de Atleta");
                        dialog.setAtleta((Atleta) obj);
                        dialog.setVisible(true);
                        Atleta At = dialog.getAtleta();
                        if (At != null) {
                            GJOPROG.getAtleta().set(GJOPROG.getAtleta().indexOf(obj), At);
                        }
                    }
                }
            }
        });

        //Menu gestão -> introdução de dados -> atletas -> apagar  ... para apagar resultados das provas de atletas 
        JMenuItem apagarAt = new JMenuItem("Apagar");
        subMenuAt.add(apagarAt);
        apagarAt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getAtleta().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há atletas", "Gestão de Jogos Olímpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha o atleta:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getAtleta().toArray(), GJOPROG.getAtleta().get(0));
                    if (obj != null) {
                        Object[] opSimNao = {"Sim", "Não"};
                        if (JOptionPane.showOptionDialog(JanelaPrincipal.this, "Tem a certeza?", "Eliminação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opSimNao, opSimNao[1]) == JOptionPane.YES_OPTION) {
                            GJOPROG.getAtleta().remove(obj);
                        }

                    }
                }
            }
        });



        //Menu gestão -> introdução de dados -> equipas
        JMenu subMenuEq = new JMenu("Equipas");
        subMenuRes.add(subMenuEq);

        //Menu gestão -> introdução de dados -> equipas -> introduzir dados  ... para carregar os resultados das provas de equipas
        JMenuItem criarEq = new JMenuItem("Introduzir dados");
        subMenuEq.add(criarEq);
        criarEq.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogCriarEquipa dialog = new DialogCriarEquipa(JanelaPrincipal.this, "Introdução de Equipa");
                dialog.setVisible(true);
//                TrabPeca trabPeca = dialog.getTrabPeca();
//                if (trabPeca != null)
//                    Main.getTrabs().add(trabPeca);
            }
        });

        //Menu gestão -> introdução de dados -> equipas  -> editar  ... para editar os resultados das provas de equipas 
        JMenuItem editarEq = new JMenuItem("Editar");
        subMenuEq.add(editarEq);
//        editarEq.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a equipa:", "Alteração de dados", JOptionPane.PLAIN_MESSAGE, null, ...... , .......);
//            }
//        });


        //Menu gestão -> introdução de dados -> equipas -> apagar  ... para apagar resultados das provas de equipas 
        JMenuItem apagarEq = new JMenuItem("Apagar");
        subMenuEq.add(apagarEq);


        JMenuItem resultados = new JMenuItem("Carregar resultados");
        resultados.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fich = new JFileChooser();
                    int resp = fich.showOpenDialog(fich);
                     File f = null;
                    if (resp == JFileChooser.APPROVE_OPTION) {
                        f = fich.getSelectedFile();
                    }
                     CarregarFicheiros(f);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível abrir o ficheiro!");
                }
            }

        });
        
        menu.add(resultados);


        menu.addSeparator();

        //Menu gestão -> sair  .... para sair
        JMenuItem sair = new JMenuItem("Sair", KeyEvent.VK_S);
        sair.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Object[] opSimNao = {"Sim", "Não"};
                if (JOptionPane.showOptionDialog(JanelaPrincipal.this, "Deseja sair da aplicação?", "Gestão de Vencimentos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opSimNao, opSimNao[1]) == 0) {
                    JanelaPrincipal.this.dispose();
                }
            }
        });

        JMenu listagens = new JMenu("Listagens");
        mb.add(listagens);
        
        JMenuItem modProv = new JMenuItem("Listagem provas de uma modalidade");
        modProv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getModalidade().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Modalidades", "Gestão de Jogos Olimpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                     Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a modalidade:", "Consulta de provas por modalidade", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getModalidade().toArray(), GJOPROG.getModalidade().get(0));
                    if (obj != null) {
                        ArrayList<String> lista = new ArrayList();
                        for(Prova prov : GJOPROG.getProva()){
                            if(prov.getModalidade().equalsIgnoreCase(obj.toString())){
                              lista.add(String.format("%30s||%10s||%10s",prov.getProva(),prov.getSexo(),prov.getTipoClassificacao())); 
                            }}
                            JList lstProva = new JList(lista.toArray());
                            JScrollPane scroll = new JScrollPane(lstProva);
                            JOptionPane.showMessageDialog(JanelaPrincipal.this,scroll,"Provas",JOptionPane.INFORMATION_MESSAGE);
                        
                        
                    }
                }
            }
        });
        listagens.add(modProv);
        
        
        JMenuItem modMed = new JMenuItem("Listagem medalhas por prova");
        modMed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (GJOPROG.getTempo().isEmpty()) {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Não há Tempos", "Gestão de Jogos Olimpicos", JOptionPane.WARNING_MESSAGE);
                } else {
                     Object obj = JOptionPane.showInputDialog(JanelaPrincipal.this, "Escolha a prova:", "Consulta de medalhas", JOptionPane.PLAIN_MESSAGE, null, GJOPROG.getProva().toArray(), GJOPROG.getProva().get(0));
                    if (obj != null) {
                        ArrayList lista = new ArrayList();  
                              lista.add(Ouro());
                              lista.add(Prata());
                              lista.add(Bronze());   
                            JList lstMed = new JList(lista.toArray());
                            JScrollPane scroll = new JScrollPane(lstMed);
                            JOptionPane.showMessageDialog(JanelaPrincipal.this,scroll,"Tempos",JOptionPane.INFORMATION_MESSAGE);
                        
                        
                    }
                }
            }
        });
        listagens.add(modMed);

        menu.add(sair);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                fechar();
            }
        });



        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void fechar() {
        Object opSimNao[] = {"Sim", "Não"};
        if (JOptionPane.showOptionDialog(JanelaPrincipal.this, "Deseja fechar a aplicação?", "Gestão Jogos Olimpicos", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opSimNao, opSimNao[1]) == 0) {
            dispose();
        }
    }
 private void CarregarFicheiros(File f) throws Exception{
        try {
            Scanner in = new Scanner(f);
            String[] linha;
            if (!in.hasNextLine()) {
                JOptionPane.showMessageDialog(rootPane, "Ficheiro vazio!");
            } else {
                while (in.hasNextLine()) {
                    linha = in.nextLine().split(";");
                    String nomModalidade =linha[0];
                    String nomProv = linha[1];
                    String sexoProv = linha[2];
                    String tpClass = linha[3];
                    String nomPais = linha[4];
                    String nomAt = linha[5];
                    String sexoAt = linha[6];
                    int tempH = Integer.parseInt(linha[7]);
                    int tempM = Integer.parseInt(linha[8]);
                    int tempS = Integer.parseInt(linha[9]);
                    
                    Modalidade mod = new Modalidade(nomModalidade);
                    GJOPROG.getModalidade().add(mod);
                    Prova prov = new Prova(nomModalidade, nomProv,sexoProv,tpClass);
                    GJOPROG.getProva().add(prov);
                    Pais pais = new Pais(nomPais);
                    GJOPROG.getPais().add(pais);
                    Atleta at = new Atleta(nomPais, nomAt, sexoAt);
                    GJOPROG.getAtleta().add(at);
                    Tempo tmp = new Tempo(tempH, tempM, tempS);
                    GJOPROG.getTempo().add(tmp);
                    Resultado res = new Resultado(prov, at, tmp);
                    GJOPROG.getResultado().add(res);
                }
            }
            in.close();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(rootPane, "Erro na leitura do ficheiro");

        }
                
            }
    private class PainelFundo extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension dimensaoPainel = getSize();
            double largura = dimensaoPainel.getWidth();
            double altura = dimensaoPainel.getHeight();
            ImageIcon i1 = new ImageIcon("Logotipo.jpg");
            Image i2 = i1.getImage().getScaledInstance((int) largura, (int) altura, Image.SCALE_SMOOTH);
            Image i3 = new ImageIcon(i2).getImage();
            g.drawImage(i3, 0, 0, this);
        }
    }
    private int Ouro(){
        int ouro = 0;
        for(Tempo tmp : GJOPROG.getTempo()){
            if(tmp.tempoSegundos()>ouro){
                ouro=tmp.tempoSegundos();
            }
        }        
        return ouro;
    }
    private int Prata(){
        int prata=0;
        int aMenos=Ouro();
        for(Tempo tmp : GJOPROG.getTempo()){
            if(tmp.tempoSegundos()>prata && prata!=aMenos){
                prata=tmp.tempoSegundos();
            }
        }
        return prata;
    }
    private int Bronze(){
        int bronze=0;
        int aMaior=Ouro();
        int aQMaior=Prata();
        for(Tempo tmp : GJOPROG.getTempo()){
            if(tmp.tempoSegundos()>bronze && bronze!=aMaior && bronze!=aQMaior){
                bronze=tmp.tempoSegundos();
            }
        }
        return bronze;
        
    }
    
}
