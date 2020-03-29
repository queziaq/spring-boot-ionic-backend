package com.quezia.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quezia.cursomc.domain.Categoria;
import com.quezia.cursomc.domain.Cidade;
import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.domain.Endereco;
import com.quezia.cursomc.domain.Estado;
import com.quezia.cursomc.domain.Pagamento;
import com.quezia.cursomc.domain.PagamentoComBoleto;
import com.quezia.cursomc.domain.PagamentoComCartao;
import com.quezia.cursomc.domain.Pedido;
import com.quezia.cursomc.domain.Produto;
import com.quezia.cursomc.domain.enums.EstadoPagamento;
import com.quezia.cursomc.domain.enums.TipoCliente;
import com.quezia.cursomc.repositories.CategoriaRepository;
import com.quezia.cursomc.repositories.CidadeRepository;
import com.quezia.cursomc.repositories.ClienteRepository;
import com.quezia.cursomc.repositories.EnderecoRepository;
import com.quezia.cursomc.repositories.EstadoRepository;
import com.quezia.cursomc.repositories.PagamentoRepository;
import com.quezia.cursomc.repositories.PedidoRepository;
import com.quezia.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computer", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 20.00);
		
		//associacao produto com categoria
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		//associacao estado cidade
		Cidade cid1 = new Cidade(null,"Uberlândia",est1);
		Cidade cid2 = new Cidade(null,"Poá",est2);
		Cidade cid3 = new Cidade(null,"São Paulo",est2);
		
		//associacao estado cidade
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		//Associacao cliente endereco cidade
		Cliente cli1 = new Cliente(null, "Quezia Abrahao","quezia@gmail.com","4652212530",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("25525420", "902531523"));
		
		Endereco end1 = new Endereco(null, "Rua Flores","300","Apto 203","Jardim", "08558000",cid1,cli1);
		Endereco end2 = new Endereco(null, "Avenida Matos","105","Sala 800","Centro", "02335202",cid3, cli1);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));

		clienteRepository.saveAll(Arrays.asList(cli1,cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("11/11/2019 00:00"),cli1,end1);
		Pedido ped2 = new Pedido(null,sdf.parse("11/12/2019 00:00"),cli1,end2);
		
		Pagamento pag1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
	}

}
