package br.usp.es;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Classe que cuida da escrita dos dados em um arquivo de saída
 * especificado através do método construtor.
 * @author Bruno Grisi
 */
public class Saida {
    
    String nomeArquivo;
    File file;
    FileOutputStream outputStream;
    PrintWriter printer;
    
    /**
     * Cria um arquivo de saída.
     * @param _nomeArquivo corresponde ao caminho do arquivo de saída
     */
    public Saida(String _nomeArquivo) {
        nomeArquivo = _nomeArquivo;
        openFile();
    }
    
    private void openFile() {
        try {
            file = new File(nomeArquivo);
            outputStream = new FileOutputStream(file, true);
            printer = new PrintWriter(outputStream);
        } catch(Exception e) {            
        }
    }
    
    /**
     * Verifica se o arquivo já existe.
     * @return <B>true</B>: arquivo já existe<BR>
     * <B>false</B>: arquivo n�o existe
     */
    public boolean exists() {
        return file.exists();
    }
    /**
     * Verifica se o arquivo pode ser lido.
     * @return <B>true</B>: pode ser lido<BR>
     * <B>false</B>: não pode ser lido
     */
    public boolean canRead() {
        return file.canRead();
    }
    /**
     * Verifica se o arquivo pode ser escrito.
     * @return <B>true</B>: pode ser escrito<BR>
     * <B>false</B>: não pode ser escrito
     */
    public boolean canWrite() {
        return file.canWrite();
    }
    /**
     * Deleta o arquivo.
     * @return <B>true</B>: arquivo deletado com sucesso<BR>
     * <B>false</B>: arquivo não deletado
     */
    public boolean delete() {
        closeFile();
        return file.delete();
    }
    
    /**
     * Escreve uma string no arquivo de saída.
     * @param texto string que cont�m o texto a ser gravado no arquivo de saída
     */
    public void write(String texto) {
        openFile();
        printer.write(texto + "\r\n");        
        closeFile();
    }
    
    /**
     * Fecha o arquivo.
     */
    private void closeFile() {
        try {
            printer.close();
            outputStream.close();
        } catch (Exception e) {            
        }
    }
}
