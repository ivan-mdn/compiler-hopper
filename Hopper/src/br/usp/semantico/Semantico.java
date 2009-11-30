package br.usp.semantico;

import br.usp.estrutura.Pilha;
import br.usp.lexico.Simbolo;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author Bruno Grisi
 */
public class Semantico {

    Memoria memoria;
	Hashtable tabelaSimbolos;
	private Pilha		pilhaConstantes;
	private Pilha		pilhaVariaveis;
	private Pilha		pilhaOperandos;
	private Pilha		pilhaOperadores;
	private StringWriter saidaASM;

	// Construtores
    public Semantico(Hashtable tabelaSimbolos) {
		this.memoria = new Memoria();
		this.pilhaConstantes = new Pilha();
		this.pilhaVariaveis = new Pilha();
		this.pilhaOperadores = new Pilha();
		this.pilhaOperandos = new Pilha();
		this.tabelaSimbolos = tabelaSimbolos;
		this.saidaASM = new StringWriter();
    }

	/*******************
	* AÇÕES SEMÂNTICAS
	********************/
	// Inicializar o processamento do código-fonte
	public void Inicializar() {
		// define a área do programa
		this.saidaASM.write("@" + " " + "=0 ");
		this.saidaASM.write("; area do programa ");
		this.saidaASM.write("\n");
	}

	// Declaração
	public boolean Declaracao(Simbolo token) {
		// verifica se o identificador já não foi declarado
		if(token.getDeclarado())
			return false;

		// identificador declarado como variável
		token.setDeclarado();
		token.setCategoria(Simbolo.VARIAVEL);

		// empilha a variável para que no final do processamento seja alocado um espaço de memória a ela
		this.pilhaVariaveis.empilha(token);
		this.setPilhaVariaveis(pilhaVariaveis);

		// neste caso, o código será gerado na etapa de finalização do processamento
		return true;
	}

	// Encerrar programa
	public void Encerrar() {
		// define a área de dados
		this.saidaASM.write("@" + " " + "=2000 ");
		this.saidaASM.write("; area de dados ");
		this.saidaASM.write("\n");

		// reservar espaço de memória para constantes
		int i = 0;
		while(!pilhaConstantes.pilhaVazia()) {
			int valor;
			valor = ((Integer) pilhaConstantes.desempilha());
			this.saidaASM.write("C" + i + " " + "K" + " " + "/" + valor + " ");
			this.saidaASM.write("\n");
			i++;
		}

		// reservar espaço de memória para variáveis
		while(!pilhaVariaveis.pilhaVazia()) {
			Simbolo token;
			String nome;
			int valor;
			token = (Simbolo) pilhaVariaveis.desempilha();
			nome = token.getNome() + token.getCodigo();
//			valor = (Integer) pilhaVariaveis.desempilha();
//			this.saidaASM.write("P" + nome + " " + "K" + " " + nome + " ");
//			this.saidaASM.write("\n");
			this.saidaASM.write(nome + " " + "K" + " " + "/" + "0000" + " ");
			this.saidaASM.write("\n");
		}
	}

	//Getters and Setters
	public Pilha getPilhaConstantes() {
        return pilhaConstantes;
    }

    public void setPilhaConstantes(Pilha pilhaConstantes) {
        this.pilhaConstantes = pilhaConstantes;
    }

	public Pilha getPilhaVariaveis() {
        return pilhaVariaveis;
    }

    public void setPilhaVariaveis(Pilha pilhaVariaveis) {
        this.pilhaVariaveis = pilhaVariaveis;
    }

	public Pilha getPilhaOperandos() {
        return pilhaOperandos;
    }

    public void setPilhaOperandos(Pilha pilhaOperandos) {
        this.pilhaOperandos = pilhaOperandos;
    }

	public Pilha getPilhaOperadores() {
        return pilhaOperadores;
    }

    public void setPilhaOperadores(Pilha pilhaOperadores) {
        this.pilhaOperadores = pilhaOperadores;
    }

	public StringWriter getSaidaASM() {
        return saidaASM;
    }

    public void setSaidaASM(StringWriter saidaASM) {
        this.saidaASM = saidaASM;
    }

	// métodos de finalização do programa
    private void incluirConstantes() {
        this.reservarEspacoMemoria("false");
        this.reservarEspacoMemoria("true");
        this.constanteParaAcumulador(0);
        this.acumuladorParaMemoria("false");
        this.constanteParaAcumulador(1);
        this.acumuladorParaMemoria("true");
    }

    private void resolverEnderecos() {
        Iterator it = memoria.iterator();
        int enderecoAtual = 0;
        while(it.hasNext()) {
            String enderecoAtualHexa = Conversor.int2Hex(enderecoAtual);
            ((PosicaoMemoria)it.next()).setEndereco(enderecoAtualHexa);
            enderecoAtual += 2;
        }

        it = memoria.iterator();
        while(it.hasNext()) {
            PosicaoMemoria posicaoAtual = (PosicaoMemoria)it.next();
            String rotuloOperando = posicaoAtual.getRotuloOperando();
            String operando;
            if(rotuloOperando==null) { // se nulo, recebe endereço da próxima instrução (NOP)
                operando = Conversor.int2Hex(Conversor.hex2Int(posicaoAtual.getEndereco()) + 2);
            } else {
                operando = memoria.getEnderecoDeRotulo(rotuloOperando);
            }
            if(operando==null) {
                operando = rotuloOperando;
            }
            posicaoAtual.setOperando(operando);
        }
        memoria.imprimirMVNes();
        memoria.imprimir();
    }

    //*********************//
    // MÉTODOS BAIXO NÍVEL //
    //*********************//
    private void reservarEspacoMemoria(String rotulo) {
        if(memoria.getEnderecoDeRotulo(rotulo)==null)
            memoria.inserir(new PosicaoMemoria(rotulo, "0", null));
    }

    private void desvioIncondicional(String rotuloDesvio) {
        memoria.inserir(new PosicaoMemoria("desvioIncondicional", MVN.desvioIncondicional(), rotuloDesvio));
    }

    private void chamadaSubRotina(String rotuloSubRotina) {
        memoria.inserir(new PosicaoMemoria("chamadaSubRotina", MVN.desvioParaSubprograma(), rotuloSubRotina));
    }

    private void retornoSubRotina(String rotuloSubRotina) {
        memoria.inserir(new PosicaoMemoria("retornoSubRotina", MVN.retornoDeSubPrograma(), rotuloSubRotina));
    }

    private void encerrarPrograma() {
        memoria.inserir(new PosicaoMemoria("fimDoPrograma", MVN.parada(), "fimDoPrograma"));
    }

    private void constanteParaAcumulador(int valorConstante) {
        memoria.inserir(new PosicaoMemoria("constanteParaACC", MVN.constanteParaAcumulador(), Conversor.int2Hex(valorConstante)));
    }

    private void memoriaParaAcumulador(String rotuloMemoria) {
        memoria.inserir(new PosicaoMemoria("memParaACC", MVN.memoriaParaAcumulador(), rotuloMemoria));
    }

    private void acumuladorParaMemoria(String rotuloMemoria) {
        memoria.inserir(new PosicaoMemoria("ACCparaMem", MVN.acumuladorParaMemoria(), rotuloMemoria));
    }
    private void soma(String rotuloParcela) {
        memoria.inserir(new PosicaoMemoria("soma", MVN.soma(), rotuloParcela));
    }
    private void subtracao(String rotuloSubtraendo) {
        memoria.inserir(new PosicaoMemoria("subtracao", MVN.subtracao(), rotuloSubtraendo));
    }
    private void divisao(String rotuloDivisor) {
        memoria.inserir(new PosicaoMemoria("divisao", MVN.divisao(), rotuloDivisor));
    }
    private void multiplicacao(String rotuloMultiplicador) {
        memoria.inserir(new PosicaoMemoria("mult", MVN.multiplicacao(), rotuloMultiplicador));
    }
    private void desvioACCZero(String rotuloDesvio) {
        memoria.inserir(new PosicaoMemoria("desvioACCZero", MVN.desvioAcumuladorZero(), rotuloDesvio));
    }
    private void saida() {
        memoria.inserir(new PosicaoMemoria("devmonitor0", MVN.saida(), "E100"));
    }
    private void entrada() {
        memoria.inserir(new PosicaoMemoria("devteclado0", MVN.entrada(), "D000"));
    }

    private void comparacaoIgual(String rotulo, String rotuloVarA, String rotuloVarB) {
        rotulo = rotulo+"-"+"compIgual"+rotuloVarA+"-"+rotuloVarB;
        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorZero(), rotulo+"10"));
        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "0"));
        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "1"));
        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
    }
    private void comparacaoDiferente(String rotulo, String rotuloVarA, String rotuloVarB) {
        rotulo = rotulo+"-"+"compDif"+rotuloVarA+"-"+rotuloVarB;
        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorZero(), rotulo+"10"));
        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "1"));
        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "0"));
        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
    }
    private void comparacaoMaior(String rotulo, String rotuloVarA, String rotuloVarB) {
        rotulo = rotulo+"-"+"compMaior"+rotuloVarA+"-"+rotuloVarB;
        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarB));
        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarA));
        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"10"));
        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "0"));
        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "1"));
        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
    }
    private void comparacaoMenor(String rotulo, String rotuloVarA, String rotuloVarB) {
        rotulo = rotulo+"-"+"compMenor"+rotuloVarA+"-"+rotuloVarB;
        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"10"));
        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.constanteParaAcumulador(), "0"));
        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.desvioIncondicional(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.constanteParaAcumulador(), "1"));
        memoria.inserir(new PosicaoMemoria(rotulo+"12", "0", null));
    }
    private void comparacaoMaiorOuIgual(String rotulo, String rotuloVarA, String rotuloVarB) {
        rotulo = rotulo+"-"+"compMaiorIgual"+rotuloVarA+"-"+rotuloVarB;
        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarB));
        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarA));
        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.desvioAcumuladorZero(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.constanteParaAcumulador(), "0"));
        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.desvioIncondicional(), rotulo+"14"));
        memoria.inserir(new PosicaoMemoria(rotulo+"12", MVN.constanteParaAcumulador(), "1"));
        memoria.inserir(new PosicaoMemoria(rotulo+"14", "0", null));
    }
    private void comparacaoMenorOuIgual(String rotulo, String rotuloVarA, String rotuloVarB) {
        rotulo = rotulo+"-"+"compMenorIgual"+rotuloVarA+"-"+rotuloVarB;
        memoria.inserir(new PosicaoMemoria(rotulo, MVN.memoriaParaAcumulador(), rotuloVarA));
        memoria.inserir(new PosicaoMemoria(rotulo+"2", MVN.subtracao(), rotuloVarB));
        memoria.inserir(new PosicaoMemoria(rotulo+"4", MVN.desvioAcumuladorNegativo(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"6", MVN.desvioAcumuladorZero(), rotulo+"12"));
        memoria.inserir(new PosicaoMemoria(rotulo+"8", MVN.constanteParaAcumulador(), "0"));
        memoria.inserir(new PosicaoMemoria(rotulo+"10", MVN.desvioIncondicional(), rotulo+"14"));
        memoria.inserir(new PosicaoMemoria(rotulo+"12", MVN.constanteParaAcumulador(), "1"));
        memoria.inserir(new PosicaoMemoria(rotulo+"14", "0", null));
    }


    //*****************************//
    // MÉTODOS NÍVEL INTERMEDIÁRIO //
    //*****************************//

    public void declaracao(String sentenca[]) {
        for(int i=0; i>sentenca.length; i++)
            System.out.println(">>>>>>>>>>>>>>>>> " + sentenca[i]);
        String rotulo = sentenca[0];
        this.reservarEspacoMemoria(rotulo);
    }
    public void declaracaoVetor(String sentenca[]) {
        for(int i=0; i>sentenca.length; i++)
            System.out.println(">>>>>>>>>>>>>>>>> " + sentenca[i]);
        String rotulo = sentenca[0];
        int tamanho = Integer.parseInt(sentenca[1]);
        for(int i=0; i<tamanho; i++) {
            this.reservarEspacoMemoria(rotulo+"-"+i);
        }
    }
    public void atribuicaoAritmetica(String sentenca[]) {
        String rotuloRecebe = sentenca[0];
        String expressao[] = new String[sentenca.length-1];
        for(int i=1; i<sentenca.length; i++) {
            expressao[i-1] = new String(sentenca[i]);
        }
        expressaoAritmetica(expressao);
        this.acumuladorParaMemoria(rotuloRecebe);
    }
    public void atribuicaoAritmeticaVetor(String sentenca[]) {
        String rotuloVetorRecebe = sentenca[0];
        String posicaoVetor = sentenca[1];
        String expressao[] = new String[sentenca.length-1];
        expressao[0] = new String(rotuloVetorRecebe+"-"+posicaoVetor);
        for(int i=1; i<expressao.length; i++) {
            expressao[i] = sentenca[i+1];
        }
        atribuicaoAritmetica(expressao);
    }

    public void atribuicaoBooleana(String sentenca[]) {
        String rotuloRecebe = sentenca[0];
        String expressao[] = new String[sentenca.length-1];
        for(int i=1; i<sentenca.length; i++) {
            expressao[i-1] = new String(sentenca[i]);
        }
        expressaoBooleana(expressao);
        this.acumuladorParaMemoria(rotuloRecebe);
    }
    public void atribuicaoBooleanaVetor(String sentenca[]) {
        String rotuloVetorRecebe = sentenca[0];
        String posicaoVetor = sentenca[1];
        String expressao[] = new String[sentenca.length-1];
        expressao[0] = new String(rotuloVetorRecebe+"-"+posicaoVetor);
        for(int i=1; i<expressao.length; i++) {
            expressao[i] = sentenca[i+1];
        }
        atribuicaoBooleana(expressao);
    }

    public void expressaoAritmetica(String expressaoNormal[]) {
        String[] expressao = Conversor.notacaoPolonesa(expressaoNormal);

        int pilha = 0;
        this.reservarEspacoMemoria("temp");
        this.reservarEspacoMemoria("temp"+pilha);

        for(int i=0; i<expressao.length; i++) {
            String atual = expressao[i];

            if(ehNumero(atual)) {
                this.constanteParaAcumulador(Integer.valueOf(atual));
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            } else if( !atual.equals(Token.MAIS) && !atual.equals(Token.MENOS) && !atual.equals(Token.BARRA) && !atual.equals(Token.ASTERISCO)) {
                this.memoriaParaAcumulador(atual);
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            } else {
                pilha--;
                this.memoriaParaAcumulador("temp"+pilha);
                pilha--;
                if(atual.equals(Token.MAIS))
                    this.soma("temp"+pilha);
                else if(atual.equals(Token.MENOS)) {
                    this.acumuladorParaMemoria("temp");
                    this.memoriaParaAcumulador("temp"+pilha);
                    this.subtracao("temp");
                } else if(atual.equals(Token.BARRA)) {
                    this.acumuladorParaMemoria("temp");
                    this.memoriaParaAcumulador("temp"+pilha);
                    this.divisao("temp");
                } else if(atual.equals(Token.ASTERISCO))
                    this.multiplicacao("temp"+pilha);
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            }
        }
    }

    //*********************************************//
    //* OBS: EXPRESSAO BOOLEANA INCORRETA PARA || *//
    //*********************************************//
    public void expressaoBooleana(String expressaoNormal[]) {
        String[] expressao = Conversor.notacaoPolonesaBooleana(expressaoNormal);
        int pilha = 0;
        this.reservarEspacoMemoria("temp");
        this.reservarEspacoMemoria("temp"+pilha);

        for(int i=0; i<expressao.length; i++) {
            String atual = expressao[i];

            if(ehNumero(atual)) {
                this.constanteParaAcumulador(Integer.parseInt(atual));
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            } else if(atual.equals("TRUE")) {					// é TRUE
                this.constanteParaAcumulador(1);
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            } else if(atual.equals("FALSE")) {					// é FALSE
                this.constanteParaAcumulador(0);
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            } else if( !atual.equals(Token.ECOMERCIAL_ECOMERCIAL) && !atual.equals(Token.BVERTICAL_BVERTICAL) &&
                    !atual.equals(Token.MAIOR) && !atual.equals(Token.MAIOR_IGUAL) &&
                    !atual.equals(Token.MENOR) && !atual.equals(Token.MENOR_IGUAL) &&
                    !atual.equals(Token.IGUAL_IGUAL) && !atual.equals(Token.EXCLAMACAO_IGUAL)) { // é IDENTIFICADOR
                this.memoriaParaAcumulador(atual);
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            } else { // é OPERADOR &&, ||, ==, !=, >, <, <=, >=
                pilha--;
                this.memoriaParaAcumulador("temp"+pilha);
                pilha--;
                if(atual.equals(Token.ECOMERCIAL_ECOMERCIAL)) {
                    this.multiplicacao("temp"+pilha);
                } else if(atual.equals(Token.BVERTICAL_BVERTICAL)) {
                    this.soma("temp"+pilha);
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoDiferente("temp"+pilha, "temp", "false");
                } else if(atual.equals(Token.IGUAL_IGUAL)) {
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoIgual("temp"+pilha, "temp"+pilha, "temp");
                } else if(atual.equals(Token.EXCLAMACAO_IGUAL)) {
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoDiferente("temp"+pilha, "temp"+pilha, "temp");
                } else if(atual.equals(Token.MAIOR)) {
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoMaior("temp"+pilha, "temp"+pilha, "temp");
                } else if(atual.equals(Token.MENOR)) {
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoMenor("temp"+pilha, "temp"+pilha, "temp");
                } else if(atual.equals(Token.MAIOR_IGUAL)) {
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoMaiorOuIgual("temp"+pilha, "temp"+pilha, "temp");
                } else if(atual.equals(Token.MENOR_IGUAL)) {
                    this.acumuladorParaMemoria("temp");
                    this.comparacaoMenorOuIgual("temp"+pilha, "temp"+pilha, "temp");
                }
                this.acumuladorParaMemoria("temp"+pilha);
                pilha++;
                this.reservarEspacoMemoria("temp"+pilha);
            }
        }
    }

    public void comandoWhile(String sentenca[]) {
        String rotuloWhile = sentenca[0];
        String rotuloFim = rotuloWhile+"-fim";
        String rotuloComandos = rotuloWhile+"-comandos";
        this.reservarEspacoMemoria("temp");

        String expressaoBooleana[] = new String[sentenca.length-1];
        for(int i=1; i<sentenca.length; i++) {
            expressaoBooleana[i-1] = sentenca[i];
        }
        this.reservarEspacoMemoria(rotuloWhile);
        this.expressaoBooleana(expressaoBooleana);
        this.acumuladorParaMemoria("temp");
        this.comparacaoIgual(rotuloWhile, "temp", "true");
        this.desvioACCZero(rotuloFim);
        this.reservarEspacoMemoria(rotuloComandos);
        this.desvioIncondicional(rotuloWhile);
        this.reservarEspacoMemoria(rotuloFim);
        memoria.setInserirEmSeguida(rotuloComandos);
    }
    public void comandoWhileFim() {
        memoria.resetInserirEmSeguida();
    }

    public void comandoIf(String sentenca[]) {
        String rotuloIf = sentenca[0];
        String rotuloFim = rotuloIf+"-fim";
        String rotuloElsif = rotuloIf+"-elsif";
        String rotuloElse = rotuloIf+"-else";
        String rotuloComandosIf = rotuloIf+"-comandos";

        this.reservarEspacoMemoria("temp");

        String expressaoBooleana[] = new String[sentenca.length-1];
        for(int i=1; i<sentenca.length; i++) {
            expressaoBooleana[i-1] = sentenca[i];
        }
        this.reservarEspacoMemoria(rotuloIf);
        this.expressaoBooleana(expressaoBooleana);
        this.acumuladorParaMemoria("temp");
        this.comparacaoIgual(rotuloIf, "temp", "true");
        this.desvioACCZero(rotuloElsif);
        this.reservarEspacoMemoria(rotuloComandosIf);
        this.desvioIncondicional(rotuloFim);
        this.reservarEspacoMemoria(rotuloElsif);
        this.reservarEspacoMemoria(rotuloElse);
        this.reservarEspacoMemoria(rotuloFim);
        memoria.setInserirEmSeguida(rotuloComandosIf);
    }
    public void comandoElsif(String sentenca[]) {
        String rotuloIf = sentenca[0];
        String rotuloElsif = sentenca[1];
        String rotuloElse = rotuloIf+"-else";
        String rotuloComandosElsif = rotuloElsif+"-comandos";
        String rotuloFim = rotuloIf+"-fim";

        String expressaoBooleana[] = new String[sentenca.length-2];
        for(int i=2; i<sentenca.length; i++) {
            expressaoBooleana[i-2] = sentenca[i];
        }
        memoria.resetInserirEmSeguida();
        memoria.setInserirEmSeguida(rotuloIf+"-elsif");
        this.reservarEspacoMemoria(rotuloElsif);
        this.expressaoBooleana(expressaoBooleana);
        this.acumuladorParaMemoria("temp");
        this.comparacaoIgual(rotuloElsif, "temp", "true");
        this.desvioACCZero(rotuloElse);
        this.reservarEspacoMemoria(rotuloComandosElsif);
        this.desvioIncondicional(rotuloFim);
        memoria.resetInserirEmSeguida();
        memoria.setInserirEmSeguida(rotuloComandosElsif);
    }
    public void comandoElse(String sentenca[]) {
        String rotuloElse = sentenca[0]+"-else";
        memoria.resetInserirEmSeguida();
        memoria.setInserirEmSeguida(rotuloElse);
    }
    public void comandoIfFim() {
        memoria.resetInserirEmSeguida();
    }

//    public void declaracaoFuncao(String parametros[]) {
//        String rotulo = parametros[0];
//        this.reservarEspacoMemoria(rotulo);
//        for(int i=1; i<parametros.length; i++) {
//            this.reservarEspacoMemoria(rotulo+"-par"+(i-1));
//        }
//        memoria.setInserirEmSeguida(rotulo);
//        this.desvioIncondicional(rotulo+"-comandos");
//        memoria.setInserirEmSeguida(parametros[parametros.length-1]);
//        this.reservarEspacoMemoria(rotulo+"-comandos");
//
//    }
//
//    public void declaracaoFuncaoFim(String parametros[]) {
//        this.retornoSubRotina(parametros[0]);
//    }
//
//    public void chamadaSubRotina(String parametros[]) {
//        String rotulo = parametros[0];
//        for(int i=1; i<parametros.length; i++) {
//            String parametroAtual = parametros[i];
//        }
//    }

    public void comandoSaida(String variavel[]) {
        this.memoriaParaAcumulador(variavel[0]);
        this.saida();
    }

    public void comandoEntrada(String variavel[]) {
        this.entrada();
        this.acumuladorParaMemoria(variavel[0]);
    }

    private boolean ehNumero(String teste) {
        try {
            Integer.valueOf(teste);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
