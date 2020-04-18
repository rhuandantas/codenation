package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fibList = new ArrayList<>();
		fibList.add(0);
		fibList.add(1);
		Integer lastNumber = fibList.get(fibList.size() - 1);
		Integer beforeLastNumber = fibList.get(fibList.size() - 2);
		while(lastNumber < 350){
			fibList.add(lastNumber+beforeLastNumber);
			lastNumber = fibList.get(fibList.size() - 1);
			beforeLastNumber = fibList.get(fibList.size() - 2);
		}

		return fibList;
	}

	public static Boolean isFibonacci(Integer num) {
		return fibonacci().contains(num);
	}

}