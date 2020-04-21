package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private final List<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {
      if(carro.getMotorista() == null) throw new EstacionamentoException("Não deve ter carro autonomo");
      if(carro.getMotorista().getIdade() < 18) throw new EstacionamentoException("Motorista menor de idade");
        int totalVagas = 10;
        if(vagas.size() < totalVagas){
          vagas.add(carro);
      }
        else{
            for(int i=0; i < totalVagas;i++){
                if(vagas.get(i).getMotorista().getIdade() <= 55){
                    vagas.remove(i);
                    vagas.add(carro);
                    return;
                }
            }
            throw new EstacionamentoException("Todas as vagas estão ocupadas com Seniors");
        }
    }

    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
       return vagas.stream().anyMatch(c -> c.equals(carro));
    }
}
