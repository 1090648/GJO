package gjo.prog;

import bibliotecagjo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GJOPROG {

    private static ArrayList<Pais> paises = new ArrayList<Pais>();
    private static ArrayList<Atleta> atletas = new ArrayList<Atleta>();
    private static ArrayList<Prova> provas = new ArrayList<Prova>();
    private static ArrayList<Modalidade> modalidades = new ArrayList<Modalidade>();
    private static ArrayList<Resultado> resultado = new ArrayList<Resultado>();
    private static ArrayList<Tempo> tempo = new ArrayList<Tempo>();

    public static void main(String[] args) throws Exception {
        JanelaPrincipal jan = new JanelaPrincipal();
        
        LeituraAtletas();
        LeituraModalidade();
        LeituraPaises();
        LeituraProvas();

    }

    public static ArrayList<Pais> getPais() {
        return paises;
    }

    public static ArrayList<Atleta> getAtleta() {
        return atletas;
    }

    public static ArrayList<Prova> getProva() {
        return provas;
    }

    public static ArrayList<Modalidade> getModalidade() {
        return modalidades;
    }
    public static ArrayList<Resultado> getResultado() {
        return resultado;
    }
    public static ArrayList<Tempo> getTempo() {
        return tempo;
    }

    public static void gravarFicheiroPaises() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("países.bin"));
            out.writeObject(paises);             
            out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ficheiro paises não gravado!", "Gravação de ficheiro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void gravarFicheiroModalidades() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("modalidades.bin"));
                out.writeObject(modalidades);
                out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ficheiro modalidades não gravado!", "Gravação de ficheiro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void gravarFicheiroProva() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("provas.bin"));
            out.writeObject(provas);
            out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ficheiro provas!", "Gravação de ficheiro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void gravarFicheiroAtletas() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("atletas.bin"));
            out.writeObject(atletas);
            out.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ficheiro atletas não gravado!", "Gravação de ficheiro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void LeituraPaises() throws Exception {
        try{
            ObjectInputStream fichPais = new ObjectInputStream(new FileInputStream("países.bin"));
            try{
                paises = (ArrayList<Pais>)fichPais.readObject();
                fichPais.close();
            }catch(ClassNotFoundException df){
                JOptionPane.showMessageDialog(null,"Paises não carregados", "Leitura de ficheiros", JOptionPane.ERROR_MESSAGE);
            }
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Países não inicializados!","Carregamento de ficheiros",JOptionPane.WARNING_MESSAGE);
        }
    }
      
    public static void LeituraProvas() throws Exception {
        try{
            ObjectInputStream fichProvas = new ObjectInputStream(new FileInputStream("provas.bin"));
            try{
                provas = (ArrayList<Prova>)fichProvas.readObject();
                fichProvas.close();
            }catch(ClassNotFoundException df){
                JOptionPane.showMessageDialog(null,"Provas não carregados", "Leitura de ficheiros", JOptionPane.ERROR_MESSAGE);
            }}catch (IOException ex){
             JOptionPane.showMessageDialog(null,"Provas não inicializados!","Carregamento de ficheiros",JOptionPane.WARNING_MESSAGE);
        }
    }
        
          
    
    public static void LeituraModalidade() throws Exception {
        try{
            ObjectInputStream fichModalideades = new ObjectInputStream(new FileInputStream("modalidades.bin"));
            try{
                modalidades = (ArrayList<Modalidade>)fichModalideades.readObject();
                fichModalideades.close();
            }catch(ClassNotFoundException df){
                JOptionPane.showMessageDialog(null,"Modalidades não carregados", "Leitura de ficheiros", JOptionPane.ERROR_MESSAGE);
            
            }}catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Modalidades não inicializados!","Carregamento de ficheiros",JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void LeituraAtletas() throws Exception {
        try{
            ObjectInputStream fichAt = new ObjectInputStream(new FileInputStream("atletas.bin"));
            try{
                atletas = (ArrayList<Atleta>)fichAt.readObject();
                fichAt.close();
            }catch(ClassNotFoundException df){
                JOptionPane.showMessageDialog(null,"Atletas não carregados", "Leitura de ficheiros", JOptionPane.ERROR_MESSAGE);
            }
                }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Atletas não inicializados!","Carregamento de ficheiros",JOptionPane.WARNING_MESSAGE);
        }
    }
    
}


