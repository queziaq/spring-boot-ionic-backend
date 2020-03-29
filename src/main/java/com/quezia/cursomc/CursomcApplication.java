package com.quezia.cursomc;

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
import com.quezia.cursomc.domain.Produto;
import com.quezia.cursomc.domain.enums.TipoCliente;
import com.quezia.cursomc.repositories.CategoriaRepository;
import com.quezia.cursomc.repositories.CidadeRepository;
import com.quezia.cursomc.repositories.ClienteRepository;
import com.quezia.cursomc.repositories.EnderecoRepository;
import com.quezia.cursomc.repositories.EstadoRepository;
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
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		//associacao estado cidade
		Cidade cid1 = new Cidade(null,"Uberlândia",est1);
		Cidade cid2 = new Cidade(null,"Poá",est2);
		Cidade cid3 = new Cidade(null,"São Paulo",est2);
		
		//associacao estado cidade
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		//Associacao cliente endereco cidade
		Cliente cli1 = new Cliente(null, "Quezia Abrahao","quezia@gmail.com","4652212530",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("25525420", "902531523"));
		
		Endereco end1 = new Endereco(null, "Rua Flores","300","Apto 203","Jardim", "08558000",cid1,cli1);
		Endereco end2 = new Endereco(null, "Avenida Matos","105","Sala 800","Centro", "02335202",cid3, cli1);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
	}

}
