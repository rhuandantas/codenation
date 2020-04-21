package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;
		int size = elements.length;
		for(int i : elements){
			soma += i;
		}

		return soma/size;
	}

	public static int mode(int[] elements) {
		int nVezes;
		int moda = 0;
		int comparaV = 0;

		for (int p = 0; p < elements.length; p++) {
			nVezes = 1;

			for (int k = p + 1; k < elements.length; k++) {
				if (elements[p] == elements[k]) {
					++nVezes;
				}
			}
			if (nVezes > comparaV) {
				moda = elements[p];
				comparaV = nVezes;
			}
		}
		return moda;

	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		if (elements.length % 2 != 0)
			return elements[elements.length / 2];

		return (elements[(elements.length - 1) / 2] + elements[elements.length / 2]) / 2;
	}
}