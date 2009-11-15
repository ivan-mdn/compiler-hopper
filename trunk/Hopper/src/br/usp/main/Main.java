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

    private static Lexico lexico = new Lexico();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        lexico.setAsciiTable();
        lexico.setPalavrasReservadas();
        lexico.setTabelaTransicoes();
        lexico.setTabelaEstadosAceitacao();
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
                
                System.out.println("Símbolo: " + lexico.getAsciiTable().get(letra));
                String acao = lexico.getMaquinaLexico().transita((String) lexico.getAsciiTable().get(letra));
                //System.out.println("Ação a ser tomada: " + acao);
                
                if(acao.equals("empilha"))
                {
                    pilha = pilha.concat((String) lexico.getAsciiTable().get(letra));
                }
                else if(acao.equals("colocaTabelaSimbolos"))
                {
                    pilha = pilha.concat((String) lexico.getAsciiTable().get(letra));
                    Simbolo simbolo = new Simbolo(-1, null, null);
                    Boolean result = false;
                    if(!lexico.getPalavrasReservadas().containsValue(pilha))
                    {
                        tipo = "identificador";
                        simbolo.setCodigo(codigo);
                        simbolo.setNome(pilha);
                        simbolo.setTipo(tipo);
                        if(!lexico.getTabelaSimbolos().containsKey(pilha))
                        {
                            lexico.getTabelaSimbolos().put(pilha, simbolo);
                        }
                        codigo++;
                    }
                    else
                    {
                        simbolo.setCodigo(codigo);
                        simbolo.setNome(pilha);
                        simbolo.setTipo(pilha);
                        codigo++;
                    }
                
                    if(simbolo.getCodigo() != -1)
                    {
                        System.out.println(simbolo.getTipo() + " [" + simbolo.getNome() + "]");
                        result = sintatico.processaToken(simbolo);
                    }

                    bw.write(pilha);
                    bw.newLine();
                    pilha = "";
                }
                else if(acao.equals("desempilha"))
                {
                    other = letra;
                    Simbolo simbolo = new Simbolo(-1, null, null);
                    Boolean result = false;
                    if(pilha.length() > 0)
                    {
                        if(!lexico.getPalavrasReservadas().containsValue(pilha))
                        {
                            tipo = "identificador";
                            simbolo.setCodigo(codigo);
                            simbolo.setNome(pilha);
                            simbolo.setTipo(tipo);
                            if(!lexico.getTabelaSimbolos().containsKey(pilha))
                            {
                                lexico.getTabelaSimbolos().put(pilha, simbolo);
                            }
                            codigo++;
                        }
                        else
                        {
                            simbolo.setCodigo(codigo);
                            simbolo.setNome(pilha);
                            simbolo.setTipo(pilha);
                            codigo++;
                        }

                        if(simbolo.getCodigo() != -1)
                        {
                            System.out.println(simbolo.getTipo() + " [" + simbolo.getNome() + "]");
                            result = sintatico.processaToken(simbolo);
                        }

                        bw.write(pilha);
                        if(!pilha.equals(""))
                        {
                            bw.newLine();
                        }
                        pilha = "";
                    }
                }
                else if(acao.equals("desempilhaNumero"))
                {
                    other = letra;
                    Simbolo simbolo = new Simbolo(-1, null, null);
                    Boolean result = false;
                    if(!lexico.getPalavrasReservadas().containsValue(pilha))
                    {
                        tipo = "numero";
                        simbolo.setCodigo(codigo);
                        simbolo.setNome(pilha);
                        simbolo.setTipo(tipo);
                        if(!lexico.getTabelaSimbolos().containsKey(pilha))
                        {
                            lexico.getTabelaSimbolos().put(pilha, simbolo);
                        }
                        codigo++;
                    }
                    else
                    {
                        simbolo.setCodigo(codigo);
                        simbolo.setNome(pilha);
                        simbolo.setTipo(pilha);
                        codigo++;
                    }
                    
                    if(simbolo.getCodigo() != -1)
                    {
                        System.out.println(simbolo.getTipo() + " [" + simbolo.getNome() + "]");
                        result = sintatico.processaToken(simbolo);
                    }

                    bw.write(pilha);
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
