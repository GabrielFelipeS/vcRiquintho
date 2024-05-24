package br.com.ifsp.vcRiquinho.produto.factory.concrate;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;

public class FactoryProdutoCreator implements IFactoryProdutoCreator {
	private static Map<String, IFactoryProduto> map = createMap();

	private static  Map<String, IFactoryProduto> createMap() {
		Map<String, IFactoryProduto> map = new HashMap<>();
		map.put("renda_variavel", new ProdutoRendaVariavelFactory());
		map.put("renda_fixa", new ProdutoRendaFixaFactory());
		return map;
	}

	@Override
	public IFactoryProduto createBy(String str) {
		return map.getOrDefault(str, new NullObjectProtudoFactory());
	}

	@Override
	public String convert(IFactoryProduto factory) {
		return factory.toString();
	}

}
