/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analizadorlexico;

import constantes.CONSTANTES;
import constantes.KEYS;
import constantes.TOKEN;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AnalizadorLexico
{

    int linha = 1;
    int count = 0;
    private char simbolo;
    private String conteudo;
    public ArrayList<CONSTANTES> tokens = new ArrayList();
    private FileReader file;//vai ler cada simbolo do ficheiro;
    private KEYS keywords = new KEYS();

    public AnalizadorLexico(String conteudo) throws FileNotFoundException, IOException
    {
        this.conteudo = conteudo;
    }

    public int proximoSimbolo() throws IOException
    {
        int proximo = file.read();
        return proximo;
    }

    public ArrayList<CONSTANTES> lerSimbolos() throws IOException
    {
        char[] conjunto = Compilador.ler().toCharArray();

        try
        {
            int estado = 0;
            int i = 0;
            StringBuilder lexema = new StringBuilder();
            boolean dentroComentarioBloco = false;
            boolean dentroString = false; // Adicionando uma flag para rastrear se estamos dentro de uma string
            boolean dentroAspas = false; // Adicionando uma flag para rastrear se estamos dentro de um simbolo

            while (i < conjunto.length)
            {

                simbolo = ' ';
                simbolo = conjunto[i];

                if (dentroComentarioBloco)
                {
                    if (simbolo == '*' && i + 1 < conjunto.length && conjunto[i + 1] == '/')
                    {
                        dentroComentarioBloco = false;
                        i++;
                    }
                    i++;
                    continue;
                }

                switch (estado)
                {
                    case 0:

                        //IDENTIFICADOR :: estado =1
                        if (Pattern.matches("[a-zA-Z]|_", Character.toString(simbolo)) && !dentroString && !dentroAspas)
                        {

                            lexema.append(simbolo);
                            i++;
                            simbolo = conjunto[i];

                            while (true)
                            {
                                if (Pattern.matches("[a-zA-Z]|_|[0-9]", Character.toString(simbolo)) && !dentroString && !dentroAspas)
                                {
                                    lexema.append(simbolo);
                                    i++;
                                    simbolo = conjunto[i];

                                }
                                else
                                {
                                    simbolo = ' ';
                                    i--;

                                    // Verifica se o lexema é uma palavra-chave
                                    String lexemaStr = lexema.toString();
                                    boolean ehPalavraChave = false;
                                    for (String palavraChave : keywords.palavrasChaveJava)
                                    {
                                        if (palavraChave.equals(lexemaStr))
                                        {
                                            ehPalavraChave = true;
                                            break;
                                        }
                                    }

                                    if (ehPalavraChave)
                                    {
                                        // Verifica se o lexema é uma palavra reservada do Java
                                        switch (lexemaStr)
                                        {
                                            case "abstract":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_ABSTRACT, linha));
                                                break;
                                            case "assert":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_ASSERT, linha));
                                                break;
                                            case "boolean":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_BOOLEAN, linha));
                                                break;
                                            case "break":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_BREAK, linha));
                                                break;
                                            case "byte":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_BYTE, linha));
                                                break;
                                            case "case":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_CASE, linha));
                                                break;
                                            case "catch":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_CATCH, linha));
                                                break;
                                            case "char":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_CHAR, linha));
                                                break;
                                            case "class":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_CLASS, linha));
                                                break;
                                            case "const":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_CONST, linha));
                                                break;
                                            case "continue":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_CONTINUE, linha));
                                                break;
                                            case "default":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_DEFAULT, linha));
                                                break;
                                            case "do":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_DO, linha));
                                                break;
                                            case "double":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_DOUBLE, linha));
                                                break;
                                            case "else":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_ELSE, linha));
                                                break;
                                            case "enum":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_ENUM, linha));
                                                break;
                                            case "extends":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_EXTENDS, linha));
                                                break;
                                            case "final":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_FINAL, linha));
                                                break;
                                            case "finally":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_FINALLY, linha));
                                                break;
                                            case "float":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_FLOAT, linha));
                                                break;
                                            case "for":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_FOR, linha));
                                                break;
                                            case "goto":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_GOTO, linha));
                                                break;
                                            case "if":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_IF, linha));
                                                break;
                                            case "implements":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_IMPLEMENTS, linha));
                                                break;
                                            case "import":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_IMPORT, linha));
                                                break;
                                            case "instanceof":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_INSTANCEOF, linha));
                                                break;
                                            case "int":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_INT, linha));
                                                break;
                                            case "interface":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_INTERFACE, linha));
                                                break;
                                            case "long":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_LONG, linha));
                                                break;
                                            case "native":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_NATIVE, linha));
                                                break;
                                            case "new":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_NEW, linha));
                                                break;
                                            case "package":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_PACKAGE, linha));
                                                break;
                                            case "private":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_PRIVATE, linha));
                                                break;
                                            case "protected":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_PROTECTED, linha));
                                                break;
                                            case "public" :
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_PUBLIC, linha));
                                                break;
                                            case "return":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_RETURN, linha));
                                                break;
                                            case "short":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_SHORT, linha));
                                                break;
                                            case "static":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_STATIC, linha));
                                                break;
                                            case "strictfp":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_STRICTFP, linha));
                                                break;
                                            case "super":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_SUPER, linha));
                                                break;
                                            case "switch":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_SWITCH, linha));
                                                break;
                                            case "synchronized":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_SYNCHRONIZED, linha));
                                                break;
                                            case "this":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_THIS, linha));
                                                break;
                                            case "throw":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_THROW, linha));
                                                break;
                                            case "throws":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_THROWS, linha));
                                                break;
                                            case "transient":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_TRANSIENT, linha));
                                                break;
                                            case "try":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_TRY, linha));
                                                break;
                                            case "void" :
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_VOID, linha));
                                                break;
                                            case "volatile":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_VOLATILE, linha));
                                                break;
                                            case "while":
                                                tokens.add(new CONSTANTES(lexemaStr, TOKEN.TOKEN_WHILE, linha));
                                                break;
                                           
                                        }

                                        lexema = new StringBuilder();
                                        break;

                                    }
                                    
                                    tokens.add(new CONSTANTES(lexema.toString(), TOKEN.TOKEN_ID, linha));
                                    lexema = new StringBuilder();
                                    break;
                                }

                            }
                        }

                        //NUMEROS :: estado = 3
                        else if (Character.isDigit(simbolo))
                        {
                            lexema.append(simbolo);
                            int j = i + 1; // Variável para acompanhar o próximo simboloe no array conjunto

                            // Verifica se o próximo simboloe também é um dígito
                            while (j < conjunto.length && Character.isDigit(conjunto[j]))
                            {
                                lexema.append(conjunto[j]);
                                j++; // Avança para o próximo simboloe
                            }

                            // Verifica se o próximo simboloe é um ponto decimal
                            if (j < conjunto.length && conjunto[j] == '.')
                            {
                                lexema.append(conjunto[j]); // Adiciona o ponto decimal ao lexema
                                j++; // Avança para o próximo simboloe após o ponto decimal

                                // Verifica se os próximos simboloes são dígitos
                                while (j < conjunto.length && Character.isDigit(conjunto[j]))
                                {
                                    lexema.append(conjunto[j]);
                                    j++; // Avança para o próximo simboloe
                                }
                            }

                            // Atualiza a variável i para apontar para o próximo simboloe a ser analisado
                            i = j - 1; // Subtrai 1 para compensar o incremento de i no final do loop while
                            tokens.add(new CONSTANTES(lexema.toString(), TOKEN.TOKEN_NUMERO, linha));
                            lexema = new StringBuilder();
                        }

                        //COMENTARIOS estado :: 7
                        else if (simbolo == '/')
                        {
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '/')
                            {
                                // Ignorar comentário de linha
                                while (i < conjunto.length && conjunto[i] != '\n')
                                {
                                    i++;
                                }
                                linha++;
                            }
                            else if (i + 1 < conjunto.length && conjunto[i + 1] == '*')
                            {
                                // Ignorar comentário de bloco
                                dentroComentarioBloco = true;
                                i++;
                            }
                            else if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                // Atribuição de divisão
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES("/=", TOKEN.TOKEN_ADIV, linha));
                                lexema = new StringBuilder();
                                i++; // Avançar para o próximo simboloe
                            }
                            else
                            {
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("/", TOKEN.TOKEN_BARRA, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        // IGUAL :: estado = 15
                        else if (simbolo == '=')
                        {
                            //estado = 16
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES("==", TOKEN.TOKEN_IGUAL_IGUAL, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um simboloe no array
                            }

                            else
                            {
                                //estado = 17
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("=", TOKEN.TOKEN_IGUAL, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        // DIFERENTE :: estado = 18
                        else if (simbolo == '!')
                        {
                            //estado = 19
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES("!=", TOKEN.TOKEN_DIF, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um simboloe no array
                            }

                            else
                            {
                                //estado = 20
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("!", TOKEN.TOKEN_NEG, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        //OPERADORES :: estado = 21
                        else if (simbolo == '&')
                        {
                            //estado = 22
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '&')
                            {
                                lexema.append(simbolo);
                                lexema.append('&');
                                tokens.add(new CONSTANTES("&&", TOKEN.TOKEN_AND_LOGICO, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um caractere no array
                            }
                            else
                            {
                                // estado = 23
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("&", TOKEN.TOKEN_AND_BITWISE, linha));
                                lexema = new StringBuilder();
                            }
                        }
                        // estado = 24
                        else if (simbolo == '|')
                        {
                            // estado = 25
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '|')
                            {
                                lexema.append(simbolo);
                                lexema.append('|');
                                tokens.add(new CONSTANTES("||", TOKEN.TOKEN_OR_LOGICO, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um caractere no array
                            }
                            else
                            {
                                // estado = 26
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("|", TOKEN.TOKEN_OR_BITWISE, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        //estado = 27
                        else if (simbolo == '^')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("^", TOKEN.TOKEN_XOR_BITWISE, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 28
                        else if (simbolo == '~')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("~", TOKEN.TOKEN_NOT_BITWISE, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 29
                        else if (simbolo == '<')
                        {
                            //estado = 30
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '<')
                            {
                                lexema.append(simbolo);
                                lexema.append('<');
                                tokens.add(new CONSTANTES("<<", TOKEN.TOKEN_DESLOC_ESQ, linha));
                                i++; // Avançamos mais um caractere no array
                            }

                            //estado = 31
                            else if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES("<=", TOKEN.TOKEN_MENOR_IGUAL, linha));
                                lexema = new StringBuilder();
                                i++;
                            }

                            //estado = 32
                            else
                            {
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("<", TOKEN.TOKEN_MENOR, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        //estado = 33
                        else if (simbolo == '>')
                        {
                            //estado = 34
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '>')
                            {
                                //estado = 35
                                if (i + 2 < conjunto.length && conjunto[i + 2] == '>')
                                {
                                    lexema.append(simbolo);
                                    lexema.append('>');
                                    lexema.append('>');
                                    tokens.add(new CONSTANTES(">>>", TOKEN.TOKEN_DESLOC_DIR_ZERO, linha));
                                    lexema = new StringBuilder();
                                    i += 2; // Avançamos mais dois caracteres no array
                                }

                                //estado = 36
                                else
                                {
                                    lexema.append(simbolo);
                                    lexema.append('>');
                                    tokens.add(new CONSTANTES(">>", TOKEN.TOKEN_DESLOC_DIR, linha));
                                    lexema = new StringBuilder();
                                    i++; // Avançamos mais um caractere no array
                                }
                            }

                            //estado = 37
                            else if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES(">=", TOKEN.TOKEN_MAIOR_IGUAL, linha));
                                lexema = new StringBuilder();
                                i++;
                            }

                            //estado = 38
                            else
                            {
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES(">", TOKEN.TOKEN_MAIOR, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        //MAIS :: estado = 39
                        else if (simbolo == '+')
                        {
                            //estado = 40
                            if (i + 1 < conjunto.length)
                            {
                                if (conjunto[i + 1] == '+')
                                {
                                    lexema.append(simbolo);
                                    lexema.append('+');
                                    tokens.add(new CONSTANTES("++", TOKEN.TOKEN_INCRE, linha));
                                    lexema = new StringBuilder();
                                    i++; // Avançamos mais um simboloe no array
                                }

                                //estado = 41
                                else if (conjunto[i + 1] == '=')
                                {
                                    lexema.append(simbolo);
                                    lexema.append('=');
                                    tokens.add(new CONSTANTES("+=", TOKEN.TOKEN_AMAIS, linha));
                                    lexema = new StringBuilder();
                                    i++; // Avançamos mais um simboloe no array
                                }

                                //estado = 42
                                else
                                {
                                    lexema.append(simbolo);
                                    tokens.add(new CONSTANTES("+", TOKEN.TOKEN_MAIS, linha));
                                    lexema = new StringBuilder();
                                }
                            }
                        }

                        //MULTIPLICACAO :: estado=43
                        else if (simbolo == '*')
                        {
                            //estado = 44
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES("*=", TOKEN.TOKEN_AST, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um simboloe no array
                            }

                            //estado = 45
                            else
                            {
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("*", TOKEN.TOKEN_AST, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        //MODULO :: //estado = 46
                        else if (simbolo == '%')
                        {
                            //estado = 47
                            if (i + 1 < conjunto.length && conjunto[i + 1] == '=')
                            {
                                lexema.append(simbolo);
                                lexema.append('=');
                                tokens.add(new CONSTANTES("%=", TOKEN.TOKEN_AMODULO, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um simboloe no array
                            }

                            //estado = 48
                            else
                            {
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES("%", TOKEN.TOKEN_MOD, linha));
                                lexema = new StringBuilder();
                            }
                        }

                        // DENTRO DA STRING :: estado = 50
                        else if (simbolo == '"')
                        {
                            if (!dentroString)
                            {
                                // Primeira aspa
                                tokens.add(new CONSTANTES("\"", TOKEN.TOKEN_ASPAS, linha)); // Adiciona uma constante para representar a primeira aspa
                                // Começou uma string
                                lexema = new StringBuilder();
                                dentroString = true;
                                lexema = new StringBuilder(); // Inicializa o StringBuilder para capturar a string

                            }
                            else
                            {
                                tokens.add(new CONSTANTES(lexema.toString(), TOKEN.TOKEN_STRING, linha)); // Adiciona a string aos tokens
                                // ultima aspa
                                lexema = new StringBuilder();
                                tokens.add(new CONSTANTES("\"", TOKEN.TOKEN_ASPAS, linha)); // Adiciona uma constante para representar a ultima aspa
                                // Terminou a string
                                lexema = new StringBuilder();
                                dentroString = false;

                            }
                        }

                        //estado = 50
                        else if (dentroString)
                        {
                            // Adiciona simboloes à string
                            lexema.append(simbolo);
                        }

                        //CARACTERE :: estado = 51
                        else if (simbolo == '\'')
                        {
                            if (!dentroAspas)
                            {
                                // Primeira aspa
                                tokens.add(new CONSTANTES("\'", TOKEN.TOKEN_ASPA, linha)); // Adiciona uma constante para representar a primeira aspa
                                // Começou uma string
                                dentroAspas = true;
                                lexema = new StringBuilder(); // Inicializa o StringBuilder para capturar a string

                            }

                            //estado = 52
                            else
                            {
                                tokens.add(new CONSTANTES(lexema.toString(), TOKEN.TOKEN_CHAR, linha)); // Adiciona a string aos tokens
                                // ultima aspa
                                lexema = new StringBuilder();
                                tokens.add(new CONSTANTES("\'", TOKEN.TOKEN_ASPA, linha)); // Adiciona uma constante para representar a ultima aspa
                                // Terminou a string
                                lexema = new StringBuilder();
                                dentroAspas = false;

                            }
                        }

                        //estado = 53
                        else
                        {
                            if (dentroAspas)
                            {
                                // Adiciona simboloes à string
                                lexema.append(simbolo);
                            }
                        }

                        //MENOS :: estado = 54
                        if (simbolo == '-')
                        {
                            //estado = 55
                            if (i + 1 < conjunto.length)
                            {
                                if (conjunto[i + 1] == '-')
                                {
                                    lexema.append(simbolo);
                                    lexema.append('-');
                                    tokens.add(new CONSTANTES("--", TOKEN.TOKEN_DECRE, linha));
                                    lexema = new StringBuilder();
                                    i++; // Avançamos mais um simboloe no array
                                }

                                //estado = 56
                                else if (conjunto[i + 1] == '=')
                                {
                                    lexema.append(simbolo);
                                    lexema.append('=');
                                    tokens.add(new CONSTANTES("-=", TOKEN.TOKEN_AMENOS, linha));
                                    lexema = new StringBuilder();
                                    i++; // Avançamos mais um simboloe no array
                                }

                                //estado = 57
                                else
                                {
                                    lexema.append(simbolo);
                                    tokens.add(new CONSTANTES("-", TOKEN.TOKEN_MENOS, linha));
                                }
                            }
                        }

                        //estado = 58
                        else if (simbolo == '(')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("(", TOKEN.TOKEN_AP, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 59
                        else if (simbolo == ')')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES(")", TOKEN.TOKEN_FP, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 60
                        else if (simbolo == '{')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("{", TOKEN.TOKEN_AC, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 61
                        else if (simbolo == '}')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("}", TOKEN.TOKEN_FC, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 62
                        else if (simbolo == '[')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("[", TOKEN.TOKEN_ACOL, linha));
                        }

                        //estado = 63
                        else if (simbolo == ']')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("]", TOKEN.TOKEN_FCOL, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 64
                        else if (simbolo == '?')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("?", TOKEN.TOKEN_INTERROGACAO, linha));
                            lexema = new StringBuilder();
                        }

                        //estados = 65
                        else if (simbolo == ':')
                        {
                            //estado = 66
                            if (i + 1 < conjunto.length && conjunto[i + 1] == ':')
                            {
                                lexema.append(simbolo);
                                lexema.append(':');
                                tokens.add(new CONSTANTES("::", TOKEN.TOKEN_REFERENCIA_METODO, linha));
                                lexema = new StringBuilder();
                                i++; // Avançamos mais um simboloe no array
                            }

                            //estado = 67
                            else
                            {
                                lexema.append(simbolo);
                                tokens.add(new CONSTANTES(":", TOKEN.TOKEN_DOIS_PONTOS, linha));
                                lexema = new StringBuilder();
                            }
                        }
                        //estado = 67
                        else if (simbolo == ',')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES(",", TOKEN.TOKEN_VG, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 68
                        else if (simbolo == '.')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES(".", TOKEN.TOKEN_P, linha));
                        }

                        //estado = 69
                        else if (simbolo == ';')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES(";", TOKEN.TOKEN_PVG, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 70
                        else if (simbolo == '@')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("@", TOKEN.TOKEN_A, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 71
                        else if (simbolo == '$')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("$", TOKEN.TOKEN_CIFRAO, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 72
                        else if (simbolo == '#')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("#", TOKEN.TOKEN_CARD, linha));
                            lexema = new StringBuilder();
                        }

                        //estado = 73
                        //estado = 76
                        else if (simbolo == '_')
                        {
                            lexema.append(simbolo);
                            tokens.add(new CONSTANTES("_", TOKEN.TOKEN_UNDER, linha));
                            lexema = new StringBuilder();
                        }
                        break;
                    default:
                        System.out.println("SIMBOLO DESCONHECIDO!!!");
                        break;
                }

                if (simbolo == '\n')
                {

                    linha++;
                    estado = 0;
                }
                i++;
            }

        }
        catch (Exception e)
        {
        }

        return tokens;
    }

    public ArrayList<CONSTANTES> getLexemas()
    {
        return tokens;
    }
}
