package br.com.codenation.calculadora;


public class CalculadoraSalario {
	private double PRIMEIRA_FAIXA = 0.0;
	private double SEGUNDA_FAIXA = 0.0;
	private double DESCONTO_PRIMEIRA_FAIXA= 0.0;
	private double DESCONTO_SEGUNDA_FAIXA= 0.0;
	private double DESCONTO_TERCEIRA_FAIXA= 0.0;

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido = 0.0;
		if(salarioBase > 1039.00){
			salarioLiquido = salarioBase - calcularInss(salarioBase);
			salarioLiquido = salarioLiquido - calcularIRRF(salarioLiquido);
		}
		return Math.round(salarioLiquido);
	}


	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		PRIMEIRA_FAIXA = 1500.00;
		SEGUNDA_FAIXA = 4000.00;
		DESCONTO_PRIMEIRA_FAIXA= 0.08;
		DESCONTO_SEGUNDA_FAIXA= 0.09;
		DESCONTO_TERCEIRA_FAIXA= 0.11;
		double desconto = 0.0;
		if(salarioBase <= PRIMEIRA_FAIXA){
			desconto = salarioBase * DESCONTO_PRIMEIRA_FAIXA;
		}else if(salarioBase > PRIMEIRA_FAIXA && salarioBase <= SEGUNDA_FAIXA){
			desconto = salarioBase * DESCONTO_SEGUNDA_FAIXA;
		}else if(salarioBase > SEGUNDA_FAIXA){
			desconto = salarioBase * DESCONTO_TERCEIRA_FAIXA;
		}

		return desconto;
	}
	private double calcularIRRF(double salarioBase) {
		PRIMEIRA_FAIXA = 3000.00;
		SEGUNDA_FAIXA = 6000.00;
		DESCONTO_SEGUNDA_FAIXA= 0.075;
		DESCONTO_TERCEIRA_FAIXA= 0.15;
		double desconto = 0.0;

		if(salarioBase > PRIMEIRA_FAIXA && salarioBase <= SEGUNDA_FAIXA){
			desconto = salarioBase * DESCONTO_SEGUNDA_FAIXA;
		}
		else if(salarioBase > SEGUNDA_FAIXA){
			desconto = salarioBase * DESCONTO_TERCEIRA_FAIXA;
		}

		return desconto;
	}
}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/