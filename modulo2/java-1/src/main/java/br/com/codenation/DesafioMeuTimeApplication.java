package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private final List<Time> times = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (id == null || nome == null || dataCriacao == null || corUniformePrincipal == null || corUniformeSecundario == null) {
			throw new IllegalArgumentException("Todos os parâmetros são obrigatórios");
		}
		if(times.stream().anyMatch(time -> time.getId().equals(id)))
		{
			throw new IdentificadorUtilizadoException();
		}

		times.add(new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (id == null || idTime == null || nome == null || dataNascimento == null || nivelHabilidade == null || salario == null) {
			throw new IllegalArgumentException("Todos os parâmetros são obrigatórios");
		}
		validaNivelHabilidade(nivelHabilidade);
		Time time = getTimeById(idTime);
		if(times.stream()
				.map(Time::getJogadores)
				.flatMap(Collection::stream)
				.anyMatch(jogador -> jogador.getId().equals(id))) {

			throw new IdentificadorUtilizadoException();

		}
		if(time.getJogadores().stream().anyMatch(j -> j.getId().equals(id))) {
			throw new IdentificadorUtilizadoException();
		}

		time.getJogadores().add(new Jogador(id,idTime,nome,dataNascimento,nivelHabilidade,salario));
	}


	private void validaNivelHabilidade(Integer nivelHabilidade){
		if (nivelHabilidade < 0 || nivelHabilidade > 100) {
			throw new IllegalArgumentException("Nível de habilidade deve estar entre 0 e 100");
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
	    Jogador jogador = getJogadorById(idJogador);

        times.stream()
                .filter(t -> t.getId().equals(jogador.getIdTime()))
                .findFirst()
                .orElseThrow(TimeNaoEncontradoException::new)
                .setIdCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = getTimeById(idTime);

		return time.getJogadores().stream()
				.filter(j -> j.getId().equals(time.getIdCapitao()))
				.findFirst()
				.orElseThrow(CapitaoNaoInformadoException::new)
				.getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador){
		return times.stream()
                .map(Time::getJogadores)
                .flatMap(Collection::stream)
                .filter(j -> j.getId().equals(idJogador))
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new)
                .getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return times.stream()
				.filter(t -> t.getId().equals(idTime))
				.findFirst()
				.orElseThrow(TimeNaoEncontradoException::new)
				.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
        Time time = getTimeById(idTime);

		return time.getJogadores().stream()
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
        Time time = getTimeById(idTime);

		return time.getJogadores().stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparingInt(Jogador::getNivelHabilidade))
				.orElseThrow(JogadorNaoEncontradoException::new)
				.getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
        Time time = getTimeById(idTime);

		return time.getJogadores().stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.min(Comparator.comparing(Jogador::getDataNascimento))
				.orElseThrow(JogadorNaoEncontradoException::new)
				.getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream()
				.map(Time::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
        Time time = getTimeById(idTime);

		return time.getJogadores().stream()
				.filter(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getSalario))
				.orElseThrow(JogadorNaoEncontradoException::new)
				.getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		return times.stream()
                .map(Time::getJogadores)
                .flatMap(Collection::stream)
				.filter(j -> j.getId().equals(idJogador))
				.findFirst()
				.orElseThrow(JogadorNaoEncontradoException::new)
				.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return times.stream()
                .map(Time::getJogadores)
                .flatMap(Collection::stream)
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = getTimeById(timeDaCasa);
		Time timeFora = getTimeById(timeDeFora);

		if(timeFora.getCorUniformePrincipal().toLowerCase()
				.equals(timeCasa.getCorUniformePrincipal().toLowerCase())){
			return timeFora.getCorUniformeSecundario();
		}
		return  timeFora.getCorUniformePrincipal();
	}

	public Time getTimeById(Long idTime){
		return times.stream()
				.filter(t -> t.getId().equals(idTime))
				.findFirst()
				.orElseThrow(TimeNaoEncontradoException::new);
	}
	public Jogador getJogadorById(Long idJogador){
		return times.stream()
                .map(Time::getJogadores)
                .flatMap(Collection::stream)
                .filter(j -> j.getId().equals(idJogador))
                .findFirst()
                .orElseThrow(JogadorNaoEncontradoException::new);
	}


}
