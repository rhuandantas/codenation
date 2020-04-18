package challenge;

public class CriptografiaCesariana implements Criptografia {
    int ALPHABET_SIZE = 26;
    @Override
    public String criptografar(String texto) {
        if(texto.isEmpty()) throw new IllegalArgumentException();
        if(texto == null) throw new NullPointerException();

        texto = texto.toLowerCase();
        String decoded = "";
        int num = 3;
        for (int i = 0; i < texto.length(); i++) {
            int charCode = texto.codePointAt(i);
            String c = "";
            if (charCode >= 97 && charCode <= 122) {
                if (charCode + num > 122) {
                    c = new StringBuilder("").appendCodePoint( charCode + num - ALPHABET_SIZE).toString();
                } else {
                    c = new StringBuilder("").appendCodePoint(charCode + num).toString();
                }
            }
            else {
                c = new StringBuilder("").appendCodePoint(texto.codePointAt(i)).toString();
            }
            decoded += c;
        }

        return decoded;
    }

    @Override
    public String descriptografar(String texto) {
        if(texto.isEmpty()) throw new IllegalArgumentException();
        if(texto == null) throw new NullPointerException();
        texto = texto.toLowerCase();
        String decoded = "";
        int num = 3;
        for (int i = 0; i < texto.length(); i++) {
            int charCode = texto.codePointAt(i);
            String c = "";
            if (charCode >= 97 && charCode <= 122) {
                if (charCode - num < 97) {
                    c = new StringBuilder("").appendCodePoint( charCode - num + ALPHABET_SIZE).toString();
                } else {
                    c = new StringBuilder("").appendCodePoint(charCode - num).toString();
                }
            }
            else {
                c = new StringBuilder("").appendCodePoint(texto.codePointAt(i)).toString();
            }
            decoded += c;
        }

        return decoded;
    }
}
