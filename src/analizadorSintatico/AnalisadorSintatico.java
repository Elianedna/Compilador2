package analizadorSintatico;

import constantes.CONSTANTES;
import constantes.TOKEN;
import java.util.ArrayList;

public class AnalisadorSintatico {

    public int posicao = 0;
    public CONSTANTES token = null;
    public ArrayList<String> erro = new ArrayList<>();
    public ArrayList<CONSTANTES> tokens = new ArrayList<>();

    public AnalisadorSintatico(ArrayList<CONSTANTES> tokens) {
        this.tokens = tokens;
        this.verificaArrayTokens();
        this.verificaTokens();
    }

    public void verificaArrayTokens() {
        if (tokens.isEmpty()) {
            erro.add("A lista de tokens está vazia.");
        }
    }

    public void verificaTokens() {
        // Start parsing the tokens
        while (posicao < tokens.size() && currentToken().token != TOKEN.TOKEN_END) {
            parseStatement();
        }
    }

    private CONSTANTES currentToken() {
        if (posicao < tokens.size()) {
            return tokens.get(posicao);
        } else {
            return new CONSTANTES("", TOKEN.TOKEN_END, -1); // End of file token
        }
    } 

    private void consumeToken() {
        posicao++;
    }
  
    private void parseStatement() {
        switch (currentToken().token) {
            case TOKEN.TOKEN_PACKAGE:
                parsePackageDeclaration();
                break;
            case TOKEN.TOKEN_IMPORT:
                parseImportStatement();
                break;
            case TOKEN.TOKEN_PUBLIC:
                parseMethodDeclaration();
                break;
            case TOKEN.TOKEN_CLASS: // Adicionamos o caso para classe
                parseClassDeclaration();
                break;
            case TOKEN.TOKEN_INT:
                parseVariableDeclaration();
                break;
            case TOKEN.TOKEN_ID:  
                parseAssignment();
                break;
            case TOKEN.TOKEN_IF:
                parseIfStatement();
                break;
            default:
                erro.add("Erro de sintaxe na linha " + currentToken().linha + ": token inesperado " + currentToken().lexema);
                consumeToken(); // Skip the unexpected token to continue parsing
                break;
        }
    }

    private void parsePackageDeclaration() {
        int lineNumber = currentToken().linha;
        consumeToken(); // Consume 'package'
        if (currentToken().token == TOKEN.TOKEN_ID) {
            while (currentToken().token == TOKEN.TOKEN_ID || currentToken().token == TOKEN.TOKEN_P) {
                consumeToken(); // Consume package name parts and dots
            }
            if (currentToken().token == TOKEN.TOKEN_PVG) {
                consumeToken(); // Consume ';'
            } else {
                erro.add("Esperado ';' na linha " + lineNumber);
            }
        } else {
            erro.add("Esperado identificador de pacote na linha " + lineNumber);
        }
    }

    private void parseImportStatement() {
        int lineNumber = currentToken().linha;
        consumeToken(); // Consume 'import'
        if (currentToken().token == TOKEN.TOKEN_ID) {
            while (currentToken().token == TOKEN.TOKEN_ID || currentToken().token == TOKEN.TOKEN_P || currentToken().token == TOKEN.TOKEN_AST) {
                consumeToken(); // Consume import name parts, dots, and asterisk
            }
            if (currentToken().token == TOKEN.TOKEN_PVG) {
                consumeToken(); // Consume ';'
            } else {
                erro.add("Esperado ';' na linha " + lineNumber);
            }
        } else {
            erro.add("Esperado identificador de importação na linha " + lineNumber);
        }
    }

    private void parseVariableDeclaration() {
        int lineNumber = currentToken().linha;
        consumeToken(); // Consume 'int'
        if (currentToken().token == TOKEN.TOKEN_ID) {
            consumeToken(); // Consume identifier
            if (currentToken().token == TOKEN.TOKEN_IGUAL) {
                consumeToken(); // Consume '='
                parseExpression();
            }
            if (currentToken().token == TOKEN.TOKEN_PVG) {
                consumeToken(); // Consume ';'
            } else {
                erro.add("Esperado ';' na linha " + lineNumber);
            }
        } else {
            erro.add("Esperado identificador na linha " + lineNumber);
        }
    }

private void parseMethodDeclaration() {
    consumeToken(); // Consume 'public'
    if (currentToken().token == TOKEN.TOKEN_STATIC) {
        consumeToken(); // Consume 'static'
        if (currentToken().token == TOKEN.TOKEN_VOID) {
            consumeToken(); // Consume 'void'
            if (currentToken().token == TOKEN.TOKEN_ID && currentToken().lexema.equals("main")) {
                consumeToken(); // Consume 'main'
                if (currentToken().token == TOKEN.TOKEN_AP) {
                    consumeToken(); // Consume '('
                    if (currentToken().token == TOKEN.TOKEN_FP) {
                        consumeToken(); // Consume ')'
                        if (currentToken().token == TOKEN.TOKEN_AC) {
                            consumeToken(); // Consume '{'
                            while (currentToken().token != TOKEN.TOKEN_FC) {
                                parseStatement(); // Parse statements within method
                            }
                            consumeToken(); // Consume '}'
                        } else {
                            erro.add("Esperado '{' na linha " + currentToken().linha);
                        }
                    } else {
                        erro.add("Esperado ')' na linha " + currentToken().linha);
                    }
                } else {
                    erro.add("Esperado '(' na linha " + currentToken().linha);
                }
            } else {
                erro.add("Esperado 'main' na linha " + currentToken().linha);
            }
        } else {
            erro.add("Esperado 'void' na linha " + currentToken().linha);
        }
    } else {
        erro.add("Esperado 'static' na linha " + currentToken().linha);
    }
}


    private void parseClassDeclaration() {
        consumeToken(); // Consume 'class'
        if (currentToken().token == TOKEN.TOKEN_ID) {
            consumeToken(); // Consume class name
            if (currentToken().token == TOKEN.TOKEN_AC) {
                consumeToken(); // Consume '{'
                while (currentToken().token != TOKEN.TOKEN_FC) {
                    parseStatement(); // Parse statements within class
                }
                consumeToken(); // Consume '}'
            } else {
                erro.add("Esperado '{' na linha " + currentToken().linha);
            }
        } else {
            erro.add("Esperado identificador de classe na linha " + currentToken().linha);
        }
    }

    private void parseAssignment() {
        int lineNumber = currentToken().linha;
        consumeToken(); // Consume identifier
        if (currentToken().token == TOKEN.TOKEN_IGUAL) {
            consumeToken(); // Consume '='
            parseExpression();
            if (currentToken().token == TOKEN.TOKEN_PVG) {
                consumeToken(); // Consume ';'
            } else {
                erro.add("Esperado ';' na linha " + lineNumber);
            }
        } else {
            erro.add("Esperado '=' na linha " + lineNumber);
        }
    }
    private void parseIfStatement() {
        int lineNumber = currentToken().linha;
        consumeToken(); // Consume 'if'
        if (currentToken().token == TOKEN.TOKEN_AP) {
            consumeToken(); // Consume '('
            parseExpression();
            if (currentToken().token == TOKEN.TOKEN_FP) {
                consumeToken(); // Consume ')'
                parseStatement();
                if (currentToken().token == TOKEN.TOKEN_ELSE) {
                    consumeToken(); // Consume 'else'
                    parseStatement();
                }
            } else {
                erro.add("Esperado ')' na linha " + lineNumber);
            }
        } else {
            erro.add("Esperado '(' na linha " + lineNumber);
        }
    }

    private void parseExpression() {
        parseTerm();
        while (currentToken().token == TOKEN.TOKEN_MAIS || currentToken().token == TOKEN.TOKEN_MENOS) {
            consumeToken(); // Consume '+' or '-'
            parseTerm();
        }
    }
    private void parseTerm() {
        parseFactor();
        while (currentToken().token == TOKEN.TOKEN_AST || currentToken().token == TOKEN.TOKEN_BARRA) {
            consumeToken(); // Consume '*' or '/'
            parseFactor();
        }
    }

    private void parseFactor() {
        if (currentToken().token == TOKEN.TOKEN_NUMERO) {
            consumeToken(); // Consume number
        } else if (currentToken().token == TOKEN.TOKEN_ID) {
            consumeToken(); // Consume identifier
        } else if (currentToken().token == TOKEN.TOKEN_AP) {
            consumeToken(); // Consume '('
            parseExpression();
            if (currentToken().token == TOKEN.TOKEN_FP) {
                consumeToken(); // Consume ')'
            } else {
                erro.add("Esperado ')' na linha " + currentToken().linha);
            }
        } else {
            erro.add("Esperado número ou identificador na linha " + currentToken().linha);
        }
    }
}
