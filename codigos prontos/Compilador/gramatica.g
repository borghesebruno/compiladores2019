PROG       -> "programa" id DECL BLOCO
DECL       -> "declare" id (",", id)* "."
BLOCO      -> "inicio"  COMANDO+ "fim" "."
COMANDO    -> CMDLEIA | CMDESCREVA | CMDSE
CMDLEIA    -> "leia" id "."
CMDESCREVA -> "escreva" id "."
CMDSE      -> "se" id op id "entao" COMANDO 
              ("senao" COMANDO)? "fimse"
