package br.usp.lexico;

import br.usp.maquinaestados.MaquinaEstados;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Hashtable;

/**
 *
 * @author nathalia
 */
public class ManipulacaoArquivo {

    private static Hashtable asciiTable = new Hashtable();
    
    public static Hashtable getAsciiTable() {
        return asciiTable;
    }

    public static void setAsciiTable() {
        asciiTable.put(10, "LF");
        asciiTable.put(32, "SPACE");
        asciiTable.put(33, "!");
        asciiTable.put(34, "\"");
        asciiTable.put(35, "#");
        asciiTable.put(36, "$");
        asciiTable.put(37, "%");
        asciiTable.put(38, "&");
        asciiTable.put(39, "");
        asciiTable.put(40, "(");
        asciiTable.put(41, ")");
        asciiTable.put(42, "*");
        asciiTable.put(43, "+");
        asciiTable.put(44, ",");
        asciiTable.put(45, "-");
        asciiTable.put(46, ".");
        asciiTable.put(47, "/");
        asciiTable.put(48, "0");
        asciiTable.put(49, "1");
        asciiTable.put(50, "2");
        asciiTable.put(51, "3");
        asciiTable.put(52, "4");
        asciiTable.put(53, "5");
        asciiTable.put(54, "6");
        asciiTable.put(55, "7");
        asciiTable.put(56, "8");
        asciiTable.put(57, "9");
        asciiTable.put(58, ":");
        asciiTable.put(59, ";");
        asciiTable.put(60, "<");
        asciiTable.put(61, "=");
        asciiTable.put(62, ">");
        asciiTable.put(63, "?");
        asciiTable.put(64, "@");
        asciiTable.put(65, "A");
        asciiTable.put(66, "B");
        asciiTable.put(67, "C");
        asciiTable.put(68, "D");
        asciiTable.put(69, "E");
        asciiTable.put(70, "F");
        asciiTable.put(71, "G");
        asciiTable.put(72, "H");
        asciiTable.put(73, "I");
        asciiTable.put(74, "J");
        asciiTable.put(75, "K");
        asciiTable.put(76, "L");
        asciiTable.put(77, "M");
        asciiTable.put(78, "N");
        asciiTable.put(79, "O");
        asciiTable.put(80, "P");
        asciiTable.put(81, "Q");
        asciiTable.put(82, "R");
        asciiTable.put(83, "S");
        asciiTable.put(84, "T");
        asciiTable.put(85, "U");
        asciiTable.put(86, "V");
        asciiTable.put(87, "W");
        asciiTable.put(88, "X");
        asciiTable.put(89, "Y");
        asciiTable.put(90, "Z");
        asciiTable.put(91, "[");
        asciiTable.put(92, "\\");
        asciiTable.put(93, "]");
        asciiTable.put(94, "^");
        asciiTable.put(95, "_");
        asciiTable.put(96, "`");
        asciiTable.put(97, "a");
        asciiTable.put(98, "b");
        asciiTable.put(99, "c");
        asciiTable.put(100, "d");
        asciiTable.put(101, "e");
        asciiTable.put(102, "f");
        asciiTable.put(103, "g");
        asciiTable.put(104, "h");
        asciiTable.put(105, "i");
        asciiTable.put(106, "j");
        asciiTable.put(107, "k");
        asciiTable.put(108, "l");
        asciiTable.put(109, "m");
        asciiTable.put(110, "n");
        asciiTable.put(111, "o");
        asciiTable.put(112, "p");
        asciiTable.put(113, "q");
        asciiTable.put(114, "r");
        asciiTable.put(115, "s");
        asciiTable.put(116, "t");
        asciiTable.put(117, "u");
        asciiTable.put(118, "v");
        asciiTable.put(119, "w");
        asciiTable.put(120, "x");
        asciiTable.put(121, "y");
        asciiTable.put(122, "z");
        asciiTable.put(123, "{");
        asciiTable.put(124, "|");
        asciiTable.put(125, "}");
        asciiTable.put(126, "~");
        asciiTable.put(127, "DEL");
    }

    public static void compileArchive(MaquinaEstados maquina)
    {
        String archive = null;
        try
        {
            System.out.print("Digite o nome do arquivo: ");
            while(true)
            {
                BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                while (buf.ready())
                {
                    archive = buf.readLine();
                    readAndWriteArchive(archive, maquina);
                    System.exit(0);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Não foi possível ler o arquivo!");
        }
    }

    public static void readAndWriteArchive(String archive, MaquinaEstados maquina)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(archive));
            System.out.println("Lendo " + archive);
            OutputStream os = new FileOutputStream("leituraesaida.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            while (in.ready())
            {
                int letra = in.read();
                System.out.println("Símbolo: " + asciiTable.get(letra));
                bw.write((String) asciiTable.get(letra));
                bw.newLine();
                String acao = maquina.transita((String) asciiTable.get(letra));
                System.out.println("Ação a ser tomada: " + acao);
            }
            in.close();
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Não foi possível ler o arquivo!");
        }
    }

}