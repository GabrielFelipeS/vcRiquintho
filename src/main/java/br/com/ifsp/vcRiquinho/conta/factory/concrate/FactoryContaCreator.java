package br.com.ifsp.vcRiquinho.conta.factory.concrate;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class FactoryContaCreator implements IFactoryContaCreator{
	private Produto produto;
	private Map<String, IFactoryConta> map;

	public FactoryContaCreator(Produto produto) {
		this.produto = produto;
		this.map = createMap();
	}

	private Map<String, IFactoryConta> createMap() {
		Map<String, IFactoryConta> map = new HashMap<>();
		map.put("cdi", new ContaCdiFactory());
		map.put("corrente", new ContaCorrenteFactory());
		map.put("invesimento_automatico", new ContaInvestimentoAutomaticoFactory(produto));
		return map;
	}

	@Override
	public IFactoryConta createBy(String str) {		
		return map.getOrDefault(str, new ContaCorrenteFactory());
	}


	@Override
	public String convert(IFactoryConta obj) {
		return obj.toString();
	}

	
	public void setProduto(Produto produto) {
		this.produto = produto;
		map.put("invesimento_automatico", new ContaInvestimentoAutomaticoFactory(produto));
	}
}
