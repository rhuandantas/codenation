package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class Runner {
    static DesafioMeuTimeApplication meusTimes = new DesafioMeuTimeApplication();

    public static void main(String[] args) {
        buscarCorCamisaTimeForaUniformePrincipalIgual();
    }
    
    public static void incluirTime() {
        meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
        System.out.println("Incluir 'Meu Time': "+meusTimes.buscarNomeTime(1L).equals("Meu Time"));
    }

    public static void incluirTimeComIdDuplicado() {
        try {
            meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
            meusTimes.incluirTime(1L, "Meu Outro Time", LocalDate.now(), "Verde", "Amarelo");
        }catch(IdentificadorUtilizadoException e){
            System.out.println("Time com Id duplicado "+e);
        }
    }
    public static void incluirJogador() {
        meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000));
        System.out.println("Incluir 'Jogador': "+meusTimes.buscarNomeJogador(1L).equals("Jogador"));
    }

    public static void incluirJogadorDuplicadoNoMesmoTime() {
        try{
            meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
            meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000));
            meusTimes.incluirJogador(1L, 1L, "OutroJogador", LocalDate.now(), 90, BigDecimal.valueOf(250000));
        }catch (IdentificadorUtilizadoException e){
            System.out.println("Jogador com Id duplicado "+e);
        }

    }

    public static void incluirJogadorDuplicadoEmOutroTime() {
        try{
            meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
            meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000));
            meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Vemelho", "Preto");
            meusTimes.incluirJogador(1L, 2L, "OutroJogador", LocalDate.now(), 90, BigDecimal.valueOf(250000));
        }catch (IdentificadorUtilizadoException e){
            System.out.println("Jogador com Id duplicado em outro time"+e);
        }
    }

    public static void incluirJogadorEmTimeInexistente() {
        try{
            meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 90, BigDecimal.valueOf(250000));
        }catch(TimeNaoEncontradoException e){
            System.out.println("Incluir Jogador em time que não existe "+e);
        }
    }

    public static void definirCapitao() {
        incluirTimeA();
        meusTimes.definirCapitao(8L);
        System.out.println("Buscar Capitao id 8 : "+meusTimes.buscarCapitaoDoTime(1L).equals(8L));
        meusTimes.definirCapitao(5L);
        System.out.println("Buscar Capitao id 5 : "+meusTimes.buscarCapitaoDoTime(1L).equals(5L));
    }

    public static void definirCapitaoInexistente() {
        try {
            incluirTimeA();
            meusTimes.definirCapitao(15L);
        }catch (JogadorNaoEncontradoException e){
            System.out.println("Definir capitao que não existe "+e);
        }
    }

    public static void buscarCapitaoDeTimeInexistente() {
        try {
            incluirTimeA();
            meusTimes.definirCapitao(5L);
            System.out.println("Buscar Capitao Time inexistente 2: "+meusTimes.buscarCapitaoDoTime(2L));
        }catch (TimeNaoEncontradoException e){
            System.out.println("Definir capitao em time inexistente "+e);
        }
    }

    public static void buscarNomeJogadorInexistente() {
        try{
            incluirTimeA();
            System.out.println("Buscar Nome inexistente: "+meusTimes.buscarNomeJogador(15L));
        }catch (JogadorNaoEncontradoException e){
            System.out.println("Buscar jogador inexistente "+e);
        }
    }

    public static void buscarNomeTimeInexistente() {
        try {
            incluirTimeA();
            System.out.println("Nome time inexistente 2: "+meusTimes.buscarNomeTime(2L));
        }catch (TimeNaoEncontradoException e){
            System.out.println("time INEXISTENTE "+e);
        }
    }

    public static void buscarJogadoresDoTime() {
        incluirTimeA();
        System.out.println("jogadores time 1: "+meusTimes.buscarJogadoresDoTime(1L));
    }

    public static void buscarMelhorJogadorDoTime() {
        incluirTimeA();
        System.out.println("MELHOR jogador time 1 equals 6: "+meusTimes.buscarMelhorJogadorDoTime(1L));
    }

    public static void buscarJogadorMaisVelho() {
        incluirTimeA();
        System.out.println("jogador VELHO time 1 equals 1: "+meusTimes.buscarJogadorMaisVelho(1L).equals(1L));
    }

    public static void buscarTimes() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Verde", "Amarelo");
        meusTimes.incluirTime(3L, "Meu Time C", LocalDate.now(), "Preto", "Vermelho");
        System.out.println("Time [1,2,3]: "+meusTimes.buscarTimes());
    }

    public static void buscarJogadorMaiorSalario() {
        incluirTimeA();
        System.out.println("Time 1 MAIOR SALARIO 1: "+meusTimes.buscarJogadorMaiorSalario(1L).equals(1L));
    }

    public static void buscarSalarioDoJogador() {
        incluirTimeA();
        System.out.println("SALARIO 180000 : "+meusTimes.buscarSalarioDoJogador(4L).equals(BigDecimal.valueOf(180000)));
    }

    public static void buscarTopJogadores() {
        incluirTimeA();
        incluirTimeB();
        System.out.println("TOP [6,11,1,9,12] : "+meusTimes.buscarTopJogadores(5));
    }

    public static void buscarCorCamisaTimeForaUniformePrincipalDiferente() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Vermelho", "Azul");
        System.out.println("Camisa fora VERMELHA: "+meusTimes.buscarCorCamisaTimeDeFora(1L, 2L).equals("Vermelho"));
    }

    public static void buscarCorCamisaTimeForaUniformePrincipalIgual() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Branco", "Azul");
        System.out.println("Camisa PRINCIPAL IGUAL AZul: "+ meusTimes.buscarCorCamisaTimeDeFora(1L, 2L).equals("Azul"));
    }


    private static void incluirTimeA() {

        meusTimes.incluirTime(1L, "Meu Time A",

                LocalDate.of(1930, Month.JANUARY, 25), "Branco", "Vermelho e preto");


        meusTimes.incluirJogador(1L, 1L, "Goleiro",

                LocalDate.of(1980, Month.JANUARY, 21), 95, BigDecimal.valueOf(450000));

        meusTimes.incluirJogador(2L, 1L, "Zagueiro A",

                LocalDate.of(1982, Month.MARCH, 12), 80, BigDecimal.valueOf(150000));

        meusTimes.incluirJogador(3L, 1L, "Zagueiro B",

                LocalDate.of(1997, Month.DECEMBER, 25), 89, BigDecimal.valueOf(250000));

        meusTimes.incluirJogador(4L, 1L, "Zagueio C",

                LocalDate.of(2002, Month.JULY, 8), 83, BigDecimal.valueOf(180000));

        meusTimes.incluirJogador(5L, 1L, "Zagueiro D",

                LocalDate.of(1994, Month.MAY, 29), 82, BigDecimal.valueOf(270000));

        meusTimes.incluirJogador(6L, 1L, "Lateral A",

                LocalDate.of(1996, Month.AUGUST, 27), 98, BigDecimal.valueOf(400000));

        meusTimes.incluirJogador(7L, 1L, "Lateral B",

                LocalDate.of(2004, Month.APRIL, 17), 90, BigDecimal.valueOf(300000));

        meusTimes.incluirJogador(8L, 1L, "Meio campo A",

                LocalDate.of(1995, Month.MARCH, 2), 87, BigDecimal.valueOf(200000));

        meusTimes.incluirJogador(9L, 1L, "Meio campo B",

                LocalDate.of(1980, Month.JANUARY, 21), 95, BigDecimal.valueOf(320000));

        meusTimes.incluirJogador(10L, 1L, "Centro avante",

                LocalDate.of(2001, Month.NOVEMBER, 14), 90, BigDecimal.valueOf(380000));

        meusTimes.incluirJogador(11L, 1L, "Atacante",

                LocalDate.of(1999, Month.OCTOBER, 16), 98, BigDecimal.valueOf(450000));

    }


    private static void incluirTimeB() {

        meusTimes.incluirTime(2L, "Meu Time B",

                LocalDate.of(1910, Month.SEPTEMBER, 1), "Branco e preto", "Preto");


        meusTimes.incluirJogador(12L, 2L, "Goleiro",

                LocalDate.of(1980, Month.DECEMBER, 2), 92, BigDecimal.valueOf(310000));

        meusTimes.incluirJogador(13L, 2L, "Zagueiro A",

                LocalDate.of(1989, Month.JUNE, 27), 88, BigDecimal.valueOf(190000));

        meusTimes.incluirJogador(14L, 2L, "Zagueiro B",

                LocalDate.of(191, Month.MARCH, 9), 87, BigDecimal.valueOf(220000));

        meusTimes.incluirJogador(15L, 2L, "Zagueio C",

                LocalDate.of(1999, Month.AUGUST, 21), 87, BigDecimal.valueOf(195000));

        meusTimes.incluirJogador(16L, 2L, "Zagueiro D",

                LocalDate.of(1996, Month.SEPTEMBER, 23), 89, BigDecimal.valueOf(250000));

        meusTimes.incluirJogador(17L, 2L, "Lateral A",

                LocalDate.of(1995, Month.JULY, 23), 79, BigDecimal.valueOf(400000));

        meusTimes.incluirJogador(18L, 2L, "Lateral B",

                LocalDate.of(2001, Month.JANUARY, 12), 86, BigDecimal.valueOf(330000));

        meusTimes.incluirJogador(19L, 2L, "Meio campo A",

                LocalDate.of(1997, Month.NOVEMBER, 20), 81, BigDecimal.valueOf(205000));

        meusTimes.incluirJogador(20L, 2L, "Meio campo B",

                LocalDate.of(1987, Month.APRIL, 16), 90, BigDecimal.valueOf(305000));

        meusTimes.incluirJogador(21L, 2L, "Centro avante",

                LocalDate.of(2001, Month.OCTOBER, 1), 86, BigDecimal.valueOf(3450000));

        meusTimes.incluirJogador(22L, 2L, "Atacante",

                LocalDate.of(1997, Month.MAY, 7), 92, BigDecimal.valueOf(310000));

    }
}
