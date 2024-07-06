/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analizadorlexico;

import analizadorSintatico.AnalisadorSintatico;
import constantes.CONSTANTES;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author watel
 */
public class Compilador
{

    public static String ler() throws FileNotFoundException, IOException
    {
        String conteudo = "";
        File ficheiro = new File("input.txt");
        if (!ficheiro.exists())
        {
            System.out.println("O arquivo n existe ");
            return conteudo;
        }

        FileInputStream fis = new FileInputStream(ficheiro);
        int letra;
        while ((letra = fis.read()) != -1)
        {
            conteudo += (char) letra;
        }
        fis.close();

        return (conteudo += '\n');
    }

    public static void main(String[] args)
    {
        try
        {

            System.out.println("Lexema\t\t\tToken\t\t\tLinha");
//           
            AnalizadorLexico analisador = new AnalizadorLexico(Compilador.ler());
           

            for (CONSTANTES a : analisador.lerSimbolos())
            {

                System.out.println(a.lexema + "\t\t\t" + a.token + "\t\t\t" + a.linha);

            }
             AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(analisador.tokens);
            
            for (String erros : analisadorSintatico.erro)
            {
                System.out.println(erros);
            }
        }
        catch (IOException ex)
        {
            System.out.println("Impossivel Abrir o Ficheiro!!!");
        }
    }

}
