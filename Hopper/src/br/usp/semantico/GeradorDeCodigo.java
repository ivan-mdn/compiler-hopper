package br.usp.semantico;

import java.util.Iterator;

/**
 *
 * @author Bruno Grisi
 */
public class GeradorDeCodigo {
    
//    Memoria memoria;
//    private TabelaDeSimbolos tabelaSimbolos;
//    private TabelaDeTokens tabelaTokens;
//
//    public GeradorDeCodigo(TabelaDeSimbolos tabelaSimbolos, TabelaDeTokens tabelaTokens) {
//        memoria = new Memoria();
//        this.tabelaSimbolos = tabelaSimbolos;
//        this.tabelaTokens = tabelaTokens;
//    }
//
//    public void roda(){
//        int posicao = 0;
//        int i = 0;
//        Token token;
//        Token tokenTemp;
//        Simbolo simbolo;
//        String parametro[];
//
//        this.incluirConstantes();
//
//        for(posicao = 0; posicao < tabelaTokens.size(); posicao++){
//            token = tabelaTokens.getToken(posicao);
//
//            if(token.getObservacao().equals(Token.DECLARACAO)){
//                parametro = new String[10];
//////////////////////                parametro[0] = tabelaTokens.getToken(posicao).getNome() + "-" + posicao;
//                tokenTemp = tabelaTokens.getToken(posicao);
//                simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                parametro[0] = simbolo.getNome() + "." + simbolo.getPosicao();
//////////////////////
//                posicao++;
//
//                if(!tabelaTokens.getToken(posicao).getObservacao().equals(Token.FIM_DECLARACAO))
//                    parametro[1] = tabelaTokens.getToken(posicao+1).getNome();
//
//
//                if(parametro[1]!=null){
//                    System.out.println("Chama metodo que resolve DECLARACAO DE VETOR, com os seguintes parametros: ");
//                    this.declaracaoVetor(Conversor.zipVetor(parametro));
//                } else{
//                    System.out.println("Chama metodo que resolve DECLARACAO, com os seguintes parametros: ");
//                    this.declaracao(Conversor.zipVetor(parametro));
//                }
//
//                for(i=0; parametro[i]!=null; i++){
//                    System.out.println(parametro[i]);
//                }
//            } else if(token.getTipo().equals(TabelaDeReservadas.WHILE)){
//                parametro = new String[50];
//
//////////////////                parametro[0] = tabelaTokens.getToken(posicao).getTipo() + "-" + posicao;
//                tokenTemp = tabelaTokens.getToken(posicao);
//                parametro[0] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
//////////////////
//                posicao+=2;
//
//                for(i=1; !tabelaTokens.getToken(posicao).getObservacao().equals(Token.FIM_WHILE_PARAMETROS); i++){
////////////////                    parametro[i] = tabelaTokens.getToken(posicao).getNome();
//                    tokenTemp = tabelaTokens.getToken(posicao);
//                    if(tokenTemp.getTipo().equals(Token.IDENTIFICADOR)) {
//                        simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                        parametro[i] = simbolo.getNome() + "." + simbolo.getPosicao();
//                    } else
//                        parametro[i] = tokenTemp.getNome();
////////////////
//                    posicao++;
//                }
//                System.out.println("Chama metodo que resolve WHILE, com os seguintes parametros: ");
//                this.comandoWhile(Conversor.zipVetor(parametro));
//                for(i=0; parametro[i]!=null; i++){
//                    System.out.println(parametro[i]);
//                }
//            } else if(token.getTipo().equals(TabelaDeReservadas.IF)){
//                parametro = new String[50];
//////////////////                parametro[0] = tabelaTokens.getToken(posicao).getTipo() + "-" + posicao;
//                tokenTemp = tabelaTokens.getToken(posicao);
//                parametro[0] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
//////////////////
//                posicao+=2;
//
//                for(i=1; !tabelaTokens.getToken(posicao).getObservacao().equals(Token.FIM_IF_PARAMETROS); i++){
////////////////                    parametro[i] = tabelaTokens.getToken(posicao).getNome();
//                    tokenTemp = tabelaTokens.getToken(posicao);
//                    if(tokenTemp.getTipo().equals(Token.IDENTIFICADOR)) {
//                        simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                        parametro[i] = simbolo.getNome() + "." + simbolo.getPosicao();
//                    } else
//                        parametro[i] = tokenTemp.getNome();
//////////////////
//                    posicao++;
//                }
//                System.out.println("Chama metodo que resolve IF, com os seguintes parametros: ");
//                this.comandoIf(Conversor.zipVetor(parametro));
//                for(i=0; parametro[i]!=null; i++){
//                    System.out.println(parametro[i]);
//                }
//            } else if(token.getTipo().equals(TabelaDeReservadas.ELSIF)){
//                parametro = new String[50];
//                int posicaoId = posicao;
//                while(!tabelaTokens.getToken(posicaoId).getTipo().equals(Token.IF))
//                    posicaoId--;
//////////////////                parametro[0] = tabelaTokens.getToken(posicaoId).getTipo() + "-" + posicaoId;
//                tokenTemp = tabelaTokens.getToken(posicaoId);
//                parametro[0] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
//////////////////
//                tokenTemp = tabelaTokens.getToken(posicao);
//                parametro[1] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
//                posicao+=2;
//
//                for(i=2; !tabelaTokens.getToken(posicao).getObservacao().equals(Token.FIM_IF_PARAMETROS); i++){
//////////////////                    parametro[i] = tabelaTokens.getToken(posicao).getNome();
//                    tokenTemp = tabelaTokens.getToken(posicao);
//                    if(tokenTemp.getTipo().equals(Token.IDENTIFICADOR)) {
//                        simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                        parametro[i] = simbolo.getNome() + "." + simbolo.getPosicao();
//                    } else
//                        parametro[i] = tokenTemp.getNome();
//////////////////
//                    posicao++;
//                }
//                System.out.println("Chama metodo que resolve ELSIF, com os seguintes parametros: ");
//                this.comandoElsif(Conversor.zipVetor(parametro));
//                for(i=0; parametro[i]!=null; i++){
//                    System.out.println(parametro[i]);
//                }
//            }else if(token.getTipo().equals(TabelaDeReservadas.ELSE)){
//                parametro = new String[1];
//                int posicaoId = posicao;
//                while(!tabelaTokens.getToken(posicaoId).getTipo().equals(Token.IF))
//                    posicaoId--;
////////////////                parametro[0] = tabelaTokens.getToken(posicaoId).getTipo() + "-" + posicaoId;
//                tokenTemp = tabelaTokens.getToken(posicaoId);
//                parametro[0] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
////////////////
//                System.out.println("Chama metodo que resolve ELSE, com os seguintes parametros: ");
//                this.comandoElse(Conversor.zipVetor(parametro));
//                System.out.println(parametro[0]);
//
//            } else if(token.getTipo().equals(Token.IGUAL)){
//                parametro = new String[100];
//                if(tabelaTokens.getToken(posicao-1).getTipo().equals(Token.FECHA_COLCHETES)){
//                    int posicaoId=posicao;
//                    posicaoId-=4;
////////////////                    parametro[0] = tabelaTokens.getToken(posicaoId).getNome() + "-" + posicaoId;
//                    tokenTemp = tabelaTokens.getToken(posicaoId);
//                    parametro[0] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
//////////////
//                    posicaoId+=2;
//                    tokenTemp = tabelaTokens.getToken(posicaoId);
//                    parametro[1] = tokenTemp.getNome() + "." + tokenTemp.getPosicao();
//
//                    int posicaoExpressao=posicao+1;
//                    for(i=2; !tabelaTokens.getToken(posicaoExpressao).getObservacao().equals(Token.FIM_GENERICO); i++){
//////////////                        parametro[i] = tabelaTokens.getToken(posicaoExpressao).getNome();
//                        tokenTemp = tabelaTokens.getToken(posicaoExpressao);
//                        if(tokenTemp.getTipo().equals(Token.IDENTIFICADOR)) {
//                            simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                            parametro[i] = simbolo.getNome() + "." + simbolo.getPosicao();
//                        } else
//                            parametro[i] = tokenTemp.getNome();
//////////////
//                        posicaoExpressao++;
//                    }
//                    if(tabelaTokens.getToken(posicao+1).getObservacao().equals(Expressao.ARITMETICA)){
//                        System.out.println("Chama metodo que resolve ATRIBUICAO ARITMETICA DE VETOR, com os seguintes parametros: ");
//                        this.atribuicaoAritmeticaVetor(Conversor.zipVetor(parametro));
//                    }else if(tabelaTokens.getToken(posicao+1).getObservacao().equals(Expressao.BOOLEANA)){
//                        System.out.println("Chama metodo que resolve ATRIBUICAO BOOLEANA DE VETOR, com os seguintes parametros: ");
//                        this.atribuicaoBooleanaVetor(Conversor.zipVetor(parametro));
//                    }
//                } else{
//                    int posicaoId=posicao-1;
//////////////                    parametro[0] = tabelaTokens.getToken(posicaoId).getNome() + "-" + posicaoId;
//                    tokenTemp = tabelaTokens.getToken(posicaoId);
//                    simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                    parametro[0] = simbolo.getNome() + "." + simbolo.getPosicao();
////////////////
//
//                    int posicaoExpressao=posicao+1;
//                    for(i=1; !tabelaTokens.getToken(posicaoExpressao).getObservacao().equals(Token.FIM_GENERICO); i++){
//////////////                        parametro[i] = tabelaTokens.getToken(posicaoExpressao).getNome();
//                        tokenTemp = tabelaTokens.getToken(posicaoExpressao);
//                        if(tokenTemp.getTipo().equals(Token.IDENTIFICADOR)) {
//                            simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                            parametro[i] = simbolo.getNome() + "." + simbolo.getPosicao();
//                        } else
//                            parametro[i] = tokenTemp.getNome();
//////////////
//                        posicaoExpressao++;
//                    }
//                    if(tabelaTokens.getToken(posicao+1).getObservacao().equals(Expressao.ARITMETICA)){
//                        System.out.println("Chama metodo que resolve ATRIBUICAO ARITMETICA, com os seguintes parametros: ");
//                        this.atribuicaoAritmetica(Conversor.zipVetor(parametro));
//                    }else if(tabelaTokens.getToken(posicao+1).getObservacao().equals(Expressao.BOOLEANA)){
//                        System.out.println("Chama metodo que resolve ATRIBUICAO BOOLEANA, com os seguintes parametros: ");
//                        this.atribuicaoBooleana(Conversor.zipVetor(parametro));
//                    }
//                }
//                for(i=0; parametro[i]!=null; i++){
//                    System.out.println(parametro[i]);
//                }
//            }else if(token.getObservacao().equals(Token.FIM_WHILE)){
//                System.out.println("Chama metodo que resolve FIM DE WHILE");
//                this.comandoWhileFim();
//            }else if(token.getObservacao().equals(Token.FIM_IF)){
//                System.out.println("Chama metodo que resolve FIM DE IF");
//                this.comandoIfFim();
//            } else if(token.getTipo().equals(TabelaDeReservadas.PRINT)){
//                posicao+=2;
//                parametro = new String[1];
//////////////                parametro[0] = tabelaTokens.getToken(posicao).getNome() + "-" + posicao;
//                tokenTemp = tabelaTokens.getToken(posicao);
//                simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                parametro[0] = simbolo.getNome() + "." + simbolo.getPosicao();
//////////////
//                System.out.println("Chama metodo que resolve PRINT, com o seguinte parametro: " + parametro[0]);
//                this.comandoSaida(Conversor.zipVetor(parametro));
//            } else if(token.getTipo().equals(TabelaDeReservadas.SCAN)){
//                posicao+=2;
//                parametro = new String[1];
//////////////////                parametro[0] = tabelaTokens.getToken(posicao).getNome() + "-" + posicao;
//                tokenTemp = tabelaTokens.getToken(posicao);
//                simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                parametro[0] = simbolo.getNome() + "." + simbolo.getPosicao();
//////////////////
//                System.out.println("Chama metodo que resolve SCAN, com o seguinte parametro: " + parametro[0]);
//                this.comandoEntrada(Conversor.zipVetor(parametro));
//            } else if(token.getObservacao().equals(Token.CHAMADA_FUNCAO)){
//                parametro = new String[20];
//                tokenTemp = tabelaTokens.getToken(posicao-1);
//                simbolo = tabelaSimbolos.recuperaSimbolo(tokenTemp);
//                parametro[0] = simbolo.getNome() + "." + simbolo.getPosicao();
//                posicao++;
//                for(i=1; !tabelaTokens.getToken(posicao).getObservacao().equals(Token.FIM_PARAMETROS); i++){
//                    parametro[i] = tabelaTokens.getToken(posicao).getNome();
//                    posicao++;
//                    System.out.println("~~~~~~~~~~" + parametro[i]);
//                }
//            }
//        }
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//
//    public void teste1() {
//        /*
//         * boolean varA;
//         * int varB;
//         * varB = 0;
//         * varA = TRUE;
//         * while (varA) {
//         *    varA = FALSE;
//         *    varB = varB + 3;
//         * }
//         */
//
//        this.incluirConstantes();
//        this.declaracao( new String[] {"varA" });
//        this.declaracao( new String[] {"varB" });
//        this.atribuicaoAritmetica( new String[] { "varB", "0" });
//        this.atribuicaoBooleana( new String[] { "varA", "TRUE" });
//        this.comandoWhile( new String[] { "while1", "varA" });
//        this.atribuicaoBooleana( new String[] { "varA", "FALSE" });
//        this.atribuicaoAritmetica( new String[] { "varB", "varB", "+", "3" } );
//        this.comandoWhileFim();
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste2() {
//
//        this.incluirConstantes();
//        this.declaracao( new String[] {"varB" });
//        this.declaracao( new String[] {"varA" });
//        this.atribuicaoAritmetica(new String[] { "varA", "3" });
//
//        this.comandoIf( new String[] { "if1", "FALSE" });
//        this.atribuicaoAritmetica(new String[] { "varA", "5" });
//        this.comandoElse(new String[] { "if1" });
//        this.atribuicaoAritmetica(new String[] { "varA", "7" });
//        this.comandoIfFim();
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste3() {
//
//        this.incluirConstantes();
//
//        this.declaracao(new String[] { "varA" } );
//        this.declaracao(new String[] { "varB" } );
//        this.declaracao(new String[] { "varC" } );
//        this.declaracao(new String[] { "resultado" } );
//        this.atribuicaoAritmetica( new String[] { "varA", "20", "1", "4", "+", "5", "2", "-", "*", "-" });
//        this.atribuicaoAritmetica( new String[] { "varB", "1", "3", "+", "2", "*" });
//        this.atribuicaoAritmetica( new String[] { "varC", "1", "3", "2", "+", "*" });
//        this.atribuicaoAritmetica( new String[] { "resultado", "varA", "varB", "varC", "+", "+" });
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste4() {
//
//        this.incluirConstantes();
//
//        this.declaracaoVetor( new String[] { "varA", "5" } );
//        this.atribuicaoAritmeticaVetor( new String[] { "varA", "0", "5", "2", "+" } );
//        this.atribuicaoAritmeticaVetor( new String[] { "varA", "1", "5", "2", "-" } );
//        this.atribuicaoAritmeticaVetor( new String[] { "varA", "2", "5", "2", "*" } );
//        this.atribuicaoAritmeticaVetor( new String[] { "varA", "3", "6", "2", "/" } );
//        this.atribuicaoAritmeticaVetor( new String[] { "varA", "4", "5", "2", "3", "+", "*" } );
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste5() {
//
//        this.incluirConstantes();
//        this.declaracaoVetor(new String[] { "varB", "5" } );
//        this.atribuicaoBooleanaVetor(new String[] { "varB", "0", "TRUE", "FALSE", "&&", "TRUE", "FALSE", "&&", "||" } );
//        this.atribuicaoBooleanaVetor(new String[] { "varB", "1", "TRUE" });
//        this.atribuicaoBooleanaVetor(new String[] { "varB", "2", "FALSE" });
//        this.atribuicaoBooleanaVetor(new String[] { "varB", "3", "TRUE", "FALSE", "&&" });
//        this.atribuicaoBooleanaVetor(new String[] { "varB", "4", "TRUE", "TRUE", "TRUE", "||", "&&"});
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste6() {
//        //this.incluirConstantes();
//
//        this.declaracao(new String[] { "varA"});
//        this.comandoEntrada(new String[] { "varA" });
//        this.comandoSaida(new String[] { "varA" });
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste7() {
//        this.incluirConstantes();
//
//        this.declaracao(new String[] { "varA"});
//        this.comandoEntrada(new String[] { "varA" });
//        this.comandoSaida(new String[] { "varA" });
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//    public void teste8() {
//        this.incluirConstantes();
//        this.declaracao(new String[] { "varA" } );
//        this.atribuicaoAritmetica(new String[] { "varA", "2" } );
//
//        this.comandoIf(new String[] { "if1", "FALSE" } );
//        this.atribuicaoAritmetica(new String[] { "varA", "3" } );
//        this.comandoElsif(new String[] { "if1", "elsif1", "TRUE" } );
//        this.atribuicaoAritmetica(new String[] { "varA", "5" } );
//        this.comandoElse(new String[] { "if1" } );
//        this.atribuicaoAritmetica(new String[] { "varA", "6" } );
//        this.comandoIfFim();
//
//        this.encerrarPrograma();
//        this.resolverEnderecos();
//    }
//
//
//    private void incluirConstantes() {
//        this.reservarEspacoMemoria("false");
//        this.reservarEspacoMemoria("true");
//        this.constanteParaAcumulador(0);
//        this.acumuladorParaMemoria("false");
//        this.constanteParaAcumulador(1);
//        this.acumuladorParaMemoria("true");
//    }
//
//    private void resolverEnderecos() {
//        Iterator it = memoria.iterator();
//        int enderecoAtual = 0;
//        while(it.hasNext()) {
//            String enderecoAtualHexa = Conversor.int2Hex(enderecoAtual);
//            ((PosicaoMemoria)it.next()).setEndereco(enderecoAtualHexa);
//            enderecoAtual += 2;
//        }
//
//        it = memoria.iterator();
//        while(it.hasNext()) {
//            PosicaoMemoria posicaoAtual = (PosicaoMemoria)it.next();
//            String rotuloOperando = posicaoAtual.getRotuloOperando();
//            String operando;
//            if(rotuloOperando==null) { // se nulo, recebe endere�o da pr�xima instru��o (NOP)
//                operando = Conversor.int2Hex(Conversor.hex2Int(posicaoAtual.getEndereco()) + 2);
//            } else {
//                operando = memoria.getEnderecoDeRotulo(rotuloOperando);
//            }
//            if(operando==null) {
//                operando = rotuloOperando;
//            }
//            posicaoAtual.setOperando(operando);
//        }
//        memoria.imprimirMVNes();
//        memoria.imprimir();
//    }
//
//    //*********************//
//    // M�TODOS BAIXO N�VEL //
//    //*********************//
//    private void reservarEspacoMemoria(String rotulo) {
//        if(memoria.getEnderecoDeRotulo(rotulo)==null)
//            memoria.inserir(new PosicaoMemoria(rotulo, "0", null));
//    }
//    private void desvioIncondicional(String rotuloDesvio) {
//        memoria.inserir(new PosicaoMemoria("desvioIncondicional", MVN.desvioIncondicional(), rotuloDesvio));
//    }
//    private void chamadaSubRotina(String rotuloSubRotina) {
//        memoria.inserir(new PosicaoMemoria("chamadaSubRotina", MVN.desvioParaSubprograma(), rotuloSubRotina));
//    }
//    private void retornoSubRotina(String rotuloSubRotina) {
//        memoria.inserir(new PosicaoMemoria("retornoSubRotina", MVN.retornoDeSubPrograma(), rotuloSubRotina));
//    }
//    private void encerrarPrograma() {
//        memoria.inserir(new PosicaoMemoria("fimDoPrograma", MVN.parada(), "fimDoPrograma"));
//    }
//    private void constanteParaAcumulador(int valorConstante) {
//        memoria.inserir(new PosicaoMemoria("constanteParaACC", MVN.constanteParaAcumulador(), Conversor.int2Hex(valorConstante)));
//    }
//    private void memoriaParaAcumulador(String rotuloMemoria) {
//        memoria.inserir(new PosicaoMemoria("memParaACC", MVN.memoriaParaAcumulador(), rotuloMemoria));
//    }
//    private void acumuladorParaMemoria(String rotuloMemoria) {
//        memoria.inserir(new PosicaoMemoria("ACCparaMem", MVN.acumuladorParaMemoria(), rotuloMemoria));
//    }
//    private void soma(String rotuloParcela) {
//        memoria.inserir(new PosicaoMemoria("soma", MVN.soma(), rotuloParcela));
//    }
//    private void subtracao(String rotuloSubtraendo) {
//        memoria.inserir(new PosicaoMemoria("subtracao", MVN.subtracao(), rotuloSubtraendo));
//    }
//    private void divisao(String rotuloDivisor) {
//        memoria.inserir(new PosicaoMemoria("divisao", MVN.divisao(), rotuloDivisor));
//    }
//    private void multiplicacao(String rotuloMultiplicador) {
//        memoria.inserir(new PosicaoMemoria("mult", MVN.multiplicacao(), rotuloMultiplicador));
//    }
//    private void desvioACCZero(String rotuloDesvio) {
//        memoria.inserir(new PosicaoMemoria("desvioACCZero", MVN.desvioAcumuladorZero(), rotuloDesvio));
//    }
//    private void saida() {
//        memoria.inserir(new PosicaoMemoria("devmonitor0", MVN.saida(), "E100"));
//    }
//    private void entrada() {
//        memoria.inserir(new PosicaoMemoria("devteclado0", MVN.entrada(), "D000"));
//    }
//
//    private void comparacaoIgual(String rotulo, String rotuloVarA, String rotuloVarB) {
//        rotulo = rotulo+"-"+"compIgual"+rotuloVarA+"-"+rotuloVarB;
//        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
//        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
//        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorZero(), rotulo+"10"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "0"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "1"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
//    }
//    private void comparacaoDiferente(String rotulo, String rotuloVarA, String rotuloVarB) {
//        rotulo = rotulo+"-"+"compDif"+rotuloVarA+"-"+rotuloVarB;
//        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
//        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
//        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorZero(), rotulo+"10"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "1"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "0"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
//    }
//    private void comparacaoMaior(String rotulo, String rotuloVarA, String rotuloVarB) {
//        rotulo = rotulo+"-"+"compMaior"+rotuloVarA+"-"+rotuloVarB;
//        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarB));
//        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarA));
//        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"10"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "0"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "1"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
//    }
//    private void comparacaoMenor(String rotulo, String rotuloVarA, String rotuloVarB) {
//        rotulo = rotulo+"-"+"compMenor"+rotuloVarA+"-"+rotuloVarB;
//        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
//        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
//        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"10"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "0"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "1"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
//    }
//    private void comparacaoMaiorOuIgual(String rotulo, String rotuloVarA, String rotuloVarB) {
//        rotulo = rotulo+"-"+"compMaiorIgual"+rotuloVarA+"-"+rotuloVarB;
//        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarB));
//        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarA));
//        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.desvioAcumuladorZero(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.constanteParaAcumulador(), "0"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.desvioIncondicional(), rotulo+"14"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"12", MVN.constanteParaAcumulador(), "1"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"14", "0", null));
//    }
//    private void comparacaoMenorOuIgual(String rotulo, String rotuloVarA, String rotuloVarB) {
//        rotulo = rotulo+"-"+"compMenorIgual"+rotuloVarA+"-"+rotuloVarB;
//        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
//        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
//        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.desvioAcumuladorZero(), rotulo+"12"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.constanteParaAcumulador(), "0"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.desvioIncondicional(), rotulo+"14"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"12", MVN.constanteParaAcumulador(), "1"));
//        memoria.inserir(new PosicaoMemoria(rotulo+"14", "0", null));
//    }
//
//
//    //*****************************//
//    // M�TODOS N�VEL INTERMEDI�RIO //
//    //*****************************//
//
//    public void declaracao(String sentenca[]) {
//        for(int i=0; i>sentenca.length; i++)
//            System.out.println(">>>>>>>>>>>>>>>>> " + sentenca[i]);
//        String rotulo = sentenca[0];
//        this.reservarEspacoMemoria(rotulo);
//    }
//    public void declaracaoVetor(String sentenca[]) {
//        for(int i=0; i>sentenca.length; i++)
//            System.out.println(">>>>>>>>>>>>>>>>> " + sentenca[i]);
//        String rotulo = sentenca[0];
//        int tamanho = Integer.parseInt(sentenca[1]);
//        for(int i=0; i<tamanho; i++) {
//            this.reservarEspacoMemoria(rotulo+"-"+i);
//        }
//    }
//    public void atribuicaoAritmetica(String sentenca[]) {
//        String rotuloRecebe = sentenca[0];
//        String expressao[] = new String[sentenca.length-1];
//        for(int i=1; i<sentenca.length; i++) {
//            expressao[i-1] = new String(sentenca[i]);
//        }
//        expressaoAritmetica(expressao);
//        this.acumuladorParaMemoria(rotuloRecebe);
//    }
//    public void atribuicaoAritmeticaVetor(String sentenca[]) {
//        String rotuloVetorRecebe = sentenca[0];
//        String posicaoVetor = sentenca[1];
//        String expressao[] = new String[sentenca.length-1];
//        expressao[0] = new String(rotuloVetorRecebe+"-"+posicaoVetor);
//        for(int i=1; i<expressao.length; i++) {
//            expressao[i] = sentenca[i+1];
//        }
//        atribuicaoAritmetica(expressao);
//    }
//
//    public void atribuicaoBooleana(String sentenca[]) {
//        String rotuloRecebe = sentenca[0];
//        String expressao[] = new String[sentenca.length-1];
//        for(int i=1; i<sentenca.length; i++) {
//            expressao[i-1] = new String(sentenca[i]);
//        }
//        expressaoBooleana(expressao);
//        this.acumuladorParaMemoria(rotuloRecebe);
//    }
//    public void atribuicaoBooleanaVetor(String sentenca[]) {
//        String rotuloVetorRecebe = sentenca[0];
//        String posicaoVetor = sentenca[1];
//        String expressao[] = new String[sentenca.length-1];
//        expressao[0] = new String(rotuloVetorRecebe+"-"+posicaoVetor);
//        for(int i=1; i<expressao.length; i++) {
//            expressao[i] = sentenca[i+1];
//        }
//        atribuicaoBooleana(expressao);
//    }
//
//    public void expressaoAritmetica(String expressaoNormal[]) {
//        String[] expressao = Conversor.notacaoPolonesa(expressaoNormal);
//
//        int pilha = 0;
//        this.reservarEspacoMemoria("temp");
//        this.reservarEspacoMemoria("temp"+pilha);
//
//        for(int i=0; i<expressao.length; i++) {
//            String atual = expressao[i];
//
//            if(ehNumero(atual)) {
//                this.constanteParaAcumulador(Integer.valueOf(atual));
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            } else if( !atual.equals(Token.MAIS) && !atual.equals(Token.MENOS) && !atual.equals(Token.BARRA) && !atual.equals(Token.ASTERISCO)) {
//                this.memoriaParaAcumulador(atual);
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            } else {
//                pilha--;
//                this.memoriaParaAcumulador("temp"+pilha);
//                pilha--;
//                if(atual.equals(Token.MAIS))
//                    this.soma("temp"+pilha);
//                else if(atual.equals(Token.MENOS)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.memoriaParaAcumulador("temp"+pilha);
//                    this.subtracao("temp");
//                } else if(atual.equals(Token.BARRA)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.memoriaParaAcumulador("temp"+pilha);
//                    this.divisao("temp");
//                } else if(atual.equals(Token.ASTERISCO))
//                    this.multiplicacao("temp"+pilha);
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            }
//        }
//    }
//
//    //*********************************************//
//    //* OBS: EXPRESSAO BOOLEANA INCORRETA PARA || *//
//    //*********************************************//
//    public void expressaoBooleana(String expressaoNormal[]) {
//        String[] expressao = Conversor.notacaoPolonesaBooleana(expressaoNormal);
//        int pilha = 0;
//        this.reservarEspacoMemoria("temp");
//        this.reservarEspacoMemoria("temp"+pilha);
//
//        for(int i=0; i<expressao.length; i++) {
//            String atual = expressao[i];
//
//            if(ehNumero(atual)) {
//                this.constanteParaAcumulador(Integer.parseInt(atual));
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            } else if(atual.equals(TabelaDeReservadas.TRUE)) { // � TRUE
//                this.constanteParaAcumulador(1);
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            } else if(atual.equals(TabelaDeReservadas.FALSE)) { // � FALSE
//                this.constanteParaAcumulador(0);
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            } else if( !atual.equals(Token.ECOMERCIAL_ECOMERCIAL) && !atual.equals(Token.BVERTICAL_BVERTICAL) &&
//                    !atual.equals(Token.MAIOR) && !atual.equals(Token.MAIOR_IGUAL) &&
//                    !atual.equals(Token.MENOR) && !atual.equals(Token.MENOR_IGUAL) &&
//                    !atual.equals(Token.IGUAL_IGUAL) && !atual.equals(Token.EXCLAMACAO_IGUAL)) { // � IDENTIFICADOR
//                this.memoriaParaAcumulador(atual);
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            } else { // � OPERADOR &&, ||, ==, !=, >, <, <=, >=
//                pilha--;
//                this.memoriaParaAcumulador("temp"+pilha);
//                pilha--;
//                if(atual.equals(Token.ECOMERCIAL_ECOMERCIAL)) {
//                    this.multiplicacao("temp"+pilha);
//                } else if(atual.equals(Token.BVERTICAL_BVERTICAL)) {
//                    this.soma("temp"+pilha);
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoDiferente("temp"+pilha, "temp", "false");
//                } else if(atual.equals(Token.IGUAL_IGUAL)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoIgual("temp"+pilha, "temp"+pilha, "temp");
//                } else if(atual.equals(Token.EXCLAMACAO_IGUAL)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoDiferente("temp"+pilha, "temp"+pilha, "temp");
//                } else if(atual.equals(Token.MAIOR)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoMaior("temp"+pilha, "temp"+pilha, "temp");
//                } else if(atual.equals(Token.MENOR)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoMenor("temp"+pilha, "temp"+pilha, "temp");
//                } else if(atual.equals(Token.MAIOR_IGUAL)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoMaiorOuIgual("temp"+pilha, "temp"+pilha, "temp");
//                } else if(atual.equals(Token.MENOR_IGUAL)) {
//                    this.acumuladorParaMemoria("temp");
//                    this.comparacaoMenorOuIgual("temp"+pilha, "temp"+pilha, "temp");
//                }
//                this.acumuladorParaMemoria("temp"+pilha);
//                pilha++;
//                this.reservarEspacoMemoria("temp"+pilha);
//            }
//        }
//    }
//
//    public void comandoWhile(String sentenca[]) {
//        String rotuloWhile = sentenca[0];
//        String rotuloFim = rotuloWhile+"-fim";
//        String rotuloComandos = rotuloWhile+"-comandos";
//        this.reservarEspacoMemoria("temp");
//
//        String expressaoBooleana[] = new String[sentenca.length-1];
//        for(int i=1; i<sentenca.length; i++) {
//            expressaoBooleana[i-1] = sentenca[i];
//        }
//        this.reservarEspacoMemoria(rotuloWhile);
//        this.expressaoBooleana(expressaoBooleana);
//        this.acumuladorParaMemoria("temp");
//        this.comparacaoIgual(rotuloWhile, "temp", "true");
//        this.desvioACCZero(rotuloFim);
//        this.reservarEspacoMemoria(rotuloComandos);
//        this.desvioIncondicional(rotuloWhile);
//        this.reservarEspacoMemoria(rotuloFim);
//        memoria.setInserirEmSeguida(rotuloComandos);
//    }
//    public void comandoWhileFim() {
//        memoria.resetInserirEmSeguida();
//    }
//
//    public void comandoIf(String sentenca[]) {
//        String rotuloIf = sentenca[0];
//        String rotuloFim = rotuloIf+"-fim";
//        String rotuloElsif = rotuloIf+"-elsif";
//        String rotuloElse = rotuloIf+"-else";
//        String rotuloComandosIf = rotuloIf+"-comandos";
//
//        this.reservarEspacoMemoria("temp");
//
//        String expressaoBooleana[] = new String[sentenca.length-1];
//        for(int i=1; i<sentenca.length; i++) {
//            expressaoBooleana[i-1] = sentenca[i];
//        }
//        this.reservarEspacoMemoria(rotuloIf);
//        this.expressaoBooleana(expressaoBooleana);
//        this.acumuladorParaMemoria("temp");
//        this.comparacaoIgual(rotuloIf, "temp", "true");
//        this.desvioACCZero(rotuloElsif);
//        this.reservarEspacoMemoria(rotuloComandosIf);
//        this.desvioIncondicional(rotuloFim);
//        this.reservarEspacoMemoria(rotuloElsif);
//        this.reservarEspacoMemoria(rotuloElse);
//        this.reservarEspacoMemoria(rotuloFim);
//        memoria.setInserirEmSeguida(rotuloComandosIf);
//    }
//    public void comandoElsif(String sentenca[]) {
//        String rotuloIf = sentenca[0];
//        String rotuloElsif = sentenca[1];
//        String rotuloElse = rotuloIf+"-else";
//        String rotuloComandosElsif = rotuloElsif+"-comandos";
//        String rotuloFim = rotuloIf+"-fim";
//
//        String expressaoBooleana[] = new String[sentenca.length-2];
//        for(int i=2; i<sentenca.length; i++) {
//            expressaoBooleana[i-2] = sentenca[i];
//        }
//        memoria.resetInserirEmSeguida();
//        memoria.setInserirEmSeguida(rotuloIf+"-elsif");
//        this.reservarEspacoMemoria(rotuloElsif);
//        this.expressaoBooleana(expressaoBooleana);
//        this.acumuladorParaMemoria("temp");
//        this.comparacaoIgual(rotuloElsif, "temp", "true");
//        this.desvioACCZero(rotuloElse);
//        this.reservarEspacoMemoria(rotuloComandosElsif);
//        this.desvioIncondicional(rotuloFim);
//        memoria.resetInserirEmSeguida();
//        memoria.setInserirEmSeguida(rotuloComandosElsif);
//    }
//    public void comandoElse(String sentenca[]) {
//        String rotuloElse = sentenca[0]+"-else";
//        memoria.resetInserirEmSeguida();
//        memoria.setInserirEmSeguida(rotuloElse);
//    }
//    public void comandoIfFim() {
//        memoria.resetInserirEmSeguida();
//    }
//
////    public void declaracaoFuncao(String parametros[]) {
////        String rotulo = parametros[0];
////        this.reservarEspacoMemoria(rotulo);
////        for(int i=1; i<parametros.length; i++) {
////            this.reservarEspacoMemoria(rotulo+"-par"+(i-1));
////        }
////        memoria.setInserirEmSeguida(rotulo);
////        this.desvioIncondicional(rotulo+"-comandos");
////        memoria.setInserirEmSeguida(parametros[parametros.length-1]);
////        this.reservarEspacoMemoria(rotulo+"-comandos");
////
////    }
////
////    public void declaracaoFuncaoFim(String parametros[]) {
////        this.retornoSubRotina(parametros[0]);
////    }
////
////    public void chamadaSubRotina(String parametros[]) {
////        String rotulo = parametros[0];
////        for(int i=1; i<parametros.length; i++) {
////            String parametroAtual = parametros[i];
////        }
////    }
//
//    public void comandoSaida(String variavel[]) {
//        this.memoriaParaAcumulador(variavel[0]);
//        this.saida();
//    }
//
//    public void comandoEntrada(String variavel[]) {
//        this.entrada();
//        this.acumuladorParaMemoria(variavel[0]);
//    }
//
//
//    private boolean ehNumero(String teste) {
//        try {
//            Integer.valueOf(teste);
//            return true;
//        } catch(Exception e) {
//            return false;
//        }
//    }
}
