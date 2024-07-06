/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constantes;

/**
 *
 * @author watel
 */
public class TOKEN
{
    //DELIMITADORES
    public static final String TOKEN_P = "PONTO";
    public static final String TOKEN_DOIS_PONTOS = "DOIS_PONTO";
    public static final String TOKEN_REFERENCIA_METODO = "REFERENCIA_METODO";
    public static final String TOKEN_VG = "VIRGULA";
    public static final String TOKEN_DP = "DOIS_PONTOS";
    public static final String TOKEN_PVG = "PONTO_VIRGULA";
    public static final String TOKEN_AP  = "Abrir_Parenteses";
    public static final String TOKEN_FP  = "Fechar_Parenteses";
    public static final String TOKEN_FCOL = "Fechar_COLCHETES";
    public static final String TOKEN_ACOL = "Abrir_COLCHETES";
    public static final String TOKEN_AC   = "ABRIR_CHAVES";
    public static final String TOKEN_FC   = "FECHAR_CHAVES";
    public static final String TOKEN_ASPAS = "ASPAS DUPLAS";
    public static final String TOKEN_ASPA = "ASPAS SIMPLES";
    
    
    
    //OPERACOES
    public static final String TOKEN_BG  = "Inicio_Arquivo";
    public static final String TOKEN_END = "Final_Arquivo";
    public static final String TOKEN_ID = "IDENTIFICADOR";
    public static final String TOKEN_KW = "KEYWORD";
    public static final String TOKEN_OPTER  = "OPERADOR_TERNARIO";
    public static final String TOKEN_INTERROGACAO  = "INTERROGACAO";
    public static final String TOKEN_BARRA = "BARRA";
    public static final String TOKEN_NUMERO = "NUMERO";
    public static final String TOKEN_N = "BARRA_N";   
    public static final String TOKEN_T = "BARRA_T";
    
    //OPERADOR ARITMETICO
    public static final String TOKEN_IGUAL = "IGUAL ";
    public static final String TOKEN_IGUAL_IGUAL = "IGUAL_IGUAL";
    public static final String TOKEN_MAIS = "MAIS";
    public static final String TOKEN_MENOS  = "MENOS";
    public static final String TOKEN_AST = "ASTERISCO";
    
    //OPERADORES LOGICOS
    public static final String TOKEN_NEG = "NEGECACAO";
    public static final String TOKEN_DIF = "DIFERENTE";
    public static final String TOKEN_MOD = "MODULO";
    public static final String TOKEN_CIFRAO = "CIFRAO";
    
    //ATRIBUICAO
    public static final String TOKEN_AMAIS = "ATRIBUICAO_ADICAO";
    public static final String TOKEN_AMENOS= "ATRIBUICAO_SUBTRACAO";
    public static final String TOKEN_AVEZES  = "ATRIBUICAO_MULTIPLICACAO";
    public static final String TOKEN_ADIV = "ATRIBUICAO_DIVISAO";
    public static final String TOKEN_AMODULO = "ATRIBUICAO_MODULO";
    public static final String TOKEN_NOTI = "DIFERENTE";
    
    //BITS
    public static final String TOKEN_BESQ  = "BITS_PARA_ESQUERDA";
    public static final String TOKEN_BDIR = "BITS_PARA_DIREITA";
    public static final String TOKEN_BD  = "BITS_SEM SINAL_PARA_DIREITA";
    public static final String TOKEN_OPREF = "REFERENCIA_METODO";
    
    //OPERADOR BITWISE
    public static final String TOKEN_A = "ARROBA";
    public static final String TOKEN_UNDER = "UNDERSCORE";
    public static final String TOKEN_DESLOC_ESQ = "DESLOC_ESQ";
    public static final String TOKEN_NOT_BITWISE = "NOT_BITWISE";
    public static final String TOKEN_XOR_BITWISE  = "XOR_BITWISE";
    public static final String TOKEN_AND_BITWISE  = "AND_BITWISE";
    public static final String TOKEN_OR_BITWISE = "OR_BITWISE";
    public static final String TOKEN_AND_LOGICO = "AND_LOGICO";
    public static final String TOKEN_OR_LOGICO = "OR_LOGICO"; 
    public static final String TOKEN_CARD = "CARDINAL";
    public static final String TOKEN_DESLOC_DIR_ZERO = "DESLOC_DIR_ZERO";
    public static final String TOKEN_DESLOC_DIR = "DESLOC_DIR";
     
    //OPERADOR RELACIONAL
    public static final String TOKEN_MAIOR  = "MAIOR";
    public static final String TOKEN_MENOR = "MENOR";
    public static final String TOKEN_MAIOR_IGUAL  = "MAIOR_Igual";
    public static final String TOKEN_MENOR_IGUAL = "MENOR_Igual";
    
    
    //INCREMENTO E DECREMENTO
    public static final String TOKEN_INCRE = "INCREMENTO";
    public static final String TOKEN_DECRE= "DECREMENTO";
    public static final String TOKEN_FDI = "FINAL_DE_INSTRUCAO";
    
    // PALAVRAS RESERVADAS DO JAVA
    public static final String TOKEN_ABSTRACT = "ABSTRACT";
    public static final String TOKEN_ASSERT = "ASSERT";
    public static final String TOKEN_BOOLEAN = "BOOLEAN";
    public static final String TOKEN_BREAK = "BREAK";
    public static final String TOKEN_BYTE = "BYTE";
    public static final String TOKEN_CASE = "CASE";
    public static final String TOKEN_CATCH = "CATCH";
    public static final String TOKEN_CHAR = "CHAR";
    public static final String TOKEN_CLASS = "CLASS";
    public static final String TOKEN_CONST = "CONST"; // não utilizado
    public static final String TOKEN_CONTINUE = "CONTINUE";
    public static final String TOKEN_DEFAULT = "DEFAULT";
    public static final String TOKEN_DO = "DO";
    public static final String TOKEN_DOUBLE = "DOUBLE";
    public static final String TOKEN_ELSE = "ELSE";
    public static final String TOKEN_ENUM = "ENUM";
    public static final String TOKEN_EXTENDS = "EXTENDS";
    public static final String TOKEN_FINAL = "FINAL";
    public static final String TOKEN_FINALLY = "FINALLY";
    public static final String TOKEN_FLOAT = "FLOAT";
    public static final String TOKEN_FOR = "FOR";
    public static final String TOKEN_GOTO = "GOTO"; // não utilizado
    public static final String TOKEN_IF = "IF";
    public static final String TOKEN_IMPLEMENTS = "IMPLEMENTS";
    public static final String TOKEN_IMPORT = "IMPORT";
    public static final String TOKEN_INSTANCEOF = "INSTANCEOF";
    public static final String TOKEN_INT = "INT";
    public static final String TOKEN_INTERFACE = "INTERFACE";
    public static final String TOKEN_LONG = "LONG";
    public static final String TOKEN_NATIVE = "NATIVE";
    public static final String TOKEN_NEW = "NEW";
    public static final String TOKEN_NULL = "NULL"; // literal especial
    public static final String TOKEN_PACKAGE = "PACKAGE";
    public static final String TOKEN_PRIVATE = "PRIVATE";
    public static final String TOKEN_PROTECTED = "PROTECTED";
    public static final String TOKEN_PUBLIC = "PUBLIC";
    public static final String TOKEN_RETURN = "RETURN";
    public static final String TOKEN_SHORT = "SHORT";
    public static final String TOKEN_STATIC = "STATIC";
    public static final String TOKEN_STRICTFP = "STRICTFP";
    public static final String TOKEN_SUPER = "SUPER";
    public static final String TOKEN_SWITCH = "SWITCH";
    public static final String TOKEN_SYNCHRONIZED = "SYNCHRONIZED";
    public static final String TOKEN_THIS = "THIS";
    public static final String TOKEN_THROW = "THROW";
    public static final String TOKEN_THROWS = "THROWS";
    public static final String TOKEN_TRANSIENT = "TRANSIENT";
    public static final String TOKEN_TRY = "TRY";
    public static final String TOKEN_STRING = "STRING";
    public static final String TOKEN_VOID = "VOID";
    public static final String TOKEN_VOLATILE = "VOLATILE";
    public static final String TOKEN_WHILE = "WHILE";
  
}
