package br.usp.main;

import br.usp.lexico.Lexico;
import br.usp.lexico.Simbolo;
import br.usp.sintatico.Sintatico;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author nathalia
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lexico.setAsciiTable();
        Lexico.setPalavrasReservadas();
        Lexico.setTabelaTransicoes();
        Lexico.setTabelaEstadosAceitacao();
        compileArchive();
    }

    private static void compileArchive()
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
                    readAndWriteArchive(archive);
                    System.exit(0);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Não foi possível ler o arquivo!");
        }
    }

    private static void readAndWriteArchive(String archive)
    {
        Sintatico sintatico = new Sintatico();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(archive));
            System.out.println("Lendo " + archive);
            OutputStream os = new FileOutputStream("saida.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String pilha = "";
            int other = -1;
            int codigo = 0;
            String tipo = "";

            while (in.ready())
            {
                int letra;
                if(other == -1)
                {
                    letra = in.read();
                }
                else
                {
                    letra = other;
                    other = -1;
                }
                
                //System.out.println("Símbolo: " + Lexico.getAsciiTable().get(letra));
                String acao = Lexico.getMaquinaLexico().transita((String) Lexico.getAsciiTable().get(letra));
                //System.out.println("Ação a ser tomada: " + acao);
                
                if(acao.equals("empilha"))
                {
                    pilha = pilha.concat((String) Lexico.getAsciiTable().get(letra));
                }
                else if(acao.equals("colocaTabelaSimbolos"))
                {
                    pilha = pilha.concat((String) Lexico.getAsciiTable().get(letra));
                    Simbolo simbolo = new Simbolo(-1, null, null);
                    Boolean result = false;
                    if(!Lexico.getTabelaSimbolos().containsKey(pilha))
                    {
                        if(!Lexico.getPalavrasReservadas().containsValue(pilha))
                        {
                            tipo = "identificador";
                            simbolo.setCodigo(codigo);
                            simbolo.setNome(pilha);
                            simbolo.setTipo(tipo);
                            Lexico.getTabelaSimbolos().put(pilha, simbolo);
                            codigo++;
                        }
                    }
                    if(simbolo.getCodigo() != -1)
                    {
                        sintatico.processaToken(simbolo);
                    }
                    if(result)
                    {
                        bw.write("true");
                    }
                    else
                    {
                        bw.write("false");
                    }

                    //bw.write(pilha);
                    bw.newLine();
                    pilha = "";
                }
                else if(acao.equals("desempilha"))
                {
                    other = letra;
                    Simbolo simbolo = new Simbolo(-1, null, null);
                    Boolean result = false;
                    if(!Lexico.getTabelaSimbolos().containsKey(pilha))
                    {
                        if(!Lexico.getPalavrasReservadas().containsValue(pilha))
                        {
                            tipo = "identificador";
                            simbolo.setCodigo(codigo);
                            simbolo.setNome(pilha);
                            simbolo.setTipo(tipo);
                            Lexico.getTabelaSimbolos().put(pilha, simbolo);
                            codigo++;
                        }
                    }
                    if(simbolo.getCodigo() != -1)
                    {
                        result = sintatico.processaToken(simbolo);
                    }
                    if(result)
                    {
                        bw.write("true");
                    }
                    else
                    {
                        bw.write("false");
                    }

                    if(!pilha.equals(""))
                    {
                        bw.newLine();
                    }
                    pilha = "";
                }
                else if(acao.equals("desempilhaNumero"))
                {
                    other = letra;
                    Simbolo simbolo = new Simbolo(-1, null, null);
                    Boolean result = false;
                    if(!Lexico.getTabelaSimbolos().containsKey(pilha))
                    {
                        if(!Lexico.getPalavrasReservadas().containsValue(pilha))
                        {
                            tipo = "numero";
                            simbolo.setCodigo(codigo);
                            simbolo.setNome(pilha);
                            simbolo.setTipo(tipo);
                            Lexico.getTabelaSimbolos().put(pilha, simbolo);
                            codigo++;
                        }
                    }
                    //bw.write(pilha);
                    if(simbolo.getCodigo() != -1)
                    {
                        result = sintatico.processaToken(simbolo);
                    }
                    if(result)
                    {
                        bw.write("true");
                    }
                    else
                    {
                        bw.write("false");
                    }

                    if(!pilha.equals(""))
                    {
                        bw.newLine();
                    }
                    pilha = "";
                }
                else if(acao.equals("ignora"))
                {

                }
                else
                {
                    System.out.println("Não existe ação definida!");
                }
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
