  /**
 * 
 */
package br.com.fucapi.OrganizadorTest;

import java.util.Calendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.startup.fucapi.dao.CongressoDAO;
import br.com.startup.fucapi.dao.OrganizadorDAO;
import br.com.startup.fucapi.model.Cadastro;
import br.com.startup.fucapi.model.Congresso;
import br.com.startup.fucapi.model.Organizador;

/**
 * @author JhimmyLiborio
 * @version 1
 * @since 28/01/2015
 *
 */
public class CadastroOrganizadorTeste {
	
	
	
	@Test
	@Ignore
	public void cadastrar(){
		Cadastro cadastro = new Cadastro();
		cadastro.setEmailCadastro("claraabenatar@gmail.com");
		cadastro.setSenha("12345678");
		Organizador organizador = new Organizador();
		organizador.setCadastro(cadastro);
		
		OrganizadorDAO odao = new OrganizadorDAO();
		odao.cadastrarOrganizador(organizador);
	}//OK
	
	@Test
	@Ignore
	
	//(NO BEAN) setar o id de quem esta na sessao no objeto Organizador.setCadastro
	public void cadastrarCongresso(){
		Cadastro cadastro = new Cadastro();
		cadastro.setId(2L); // id de um organizador que esta na sessao
		
		Congresso congresso = new Congresso(); 
		congresso.setNome("3º Encontro dos Deseperados");
		congresso.setCategoria("Projeto");
		congresso.setDescricao("Definicao de Layout ");
		
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.FEBRUARY, 10);
		
		congresso.setData_inicio(c.getTime());
		congresso.setHora_icnio("19:00");
		
		c.set(2015, Calendar.MARCH, 15);
		congresso.setData_fim(c.getTime());
		congresso.setHora_fim("22:00");
		
		congresso.setLocal("Rua Fucapi, Trio , n3");
		congresso.setValor(20.00);
		
		congresso.setCadastro(cadastro);
		
		CongressoDAO cdao = new CongressoDAO();
		cdao.salvarCongresso(congresso);
		
	}//OK
	
	
	@Test // AUTENTICAÇÃO
	public void autenticar(){
		Cadastro cadastro = new Cadastro();
		cadastro.setEmailCadastro("liboriojhimmy@gmail.com");
		cadastro.setSenha("12345678");
		
		Organizador organizador = new Organizador();
		organizador.setCadastro(cadastro);
		
		// vai pegar uma instancia da conexaoManager no Construtor
		OrganizadorDAO oDao = new OrganizadorDAO();
		
		organizador =  oDao.autenticar(organizador);
		
		if (organizador == null){
			System.out.println("Email e senha errado ");
		}
		else{
			System.out.println("Autenticado ");
			System.out.println(organizador.getCadastro().getId());
			System.out.println("--------------------------------");
		}
	}//OK

	@Test //lista de congresso de um organizador autenticado
	public void listarCongressoDoOrganizador(){
		
		Cadastro cadastro = new Cadastro(); // suposicao pega o id da sessao do cadastro  do Organizador na plataforma
		cadastro.setId(1L);
		
		Organizador organizador = new Organizador();
		organizador.setCadastro(cadastro); // setei o id no Objeto Organizador.cadastro
		
		// vai pegar uma instancia da conexaoManager no Construtor
		CongressoDAO congressoDAO  = new CongressoDAO(); 
		
		
		List<Congresso> list = congressoDAO.listarCongresso(organizador);
		
		if (!list.isEmpty())
		for (Congresso congresso : list) {
			System.out.println(congresso.getId());
			System.out.println(congresso.getNome());
			System.out.println(congresso.getDescricao());
			System.out.println(congresso.getCategoria());
			System.out.println(congresso.getData_inicio());
			System.out.println(congresso.getData_fim());
			System.out.println(congresso.getHora_icnio());
			System.out.println(congresso.getHora_fim());
			System.out.println(congresso.getLocal());
			System.out.println(congresso.getValor());
			
		}
		else{
			System.out.println("Você não Congressos ->> redirecione para a pagina de criação");
		}
	}
	
}
