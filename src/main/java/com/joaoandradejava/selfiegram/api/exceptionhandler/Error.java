package com.joaoandradejava.selfiegram.api.exceptionhandler;

public enum Error {
	NEGOCIO_EXCEPTION("negocio-exception", "Ocorreu um erro do lado client-side(front)"),
	OBJETO_NAO_ENCONTRADO_EXCEPTION("objeto-nao-encontrado", "Objeto não encontrada"),
	ERRO_VALIDACAO_DADOS("erro-validacao-dados", "Ocorreu um erro na validação dos dados vindo do client-side(front)"),
	METODO_REQUISICAO_NAO_SUPORTADO("metodo-da-requisicao-nao-suportado", "Metodo da requisição não suportado"),
	ENDPOINT_NAO_ENCONTRADO("endpoint-nao-encontrado", "Endpoint não encontrado"),
	ERRO_TENTATIVA_DESSERIALIZACAO_JSON("erro-tentativa-desserializacao-json",
			"Ocorreu um erro na tentativa desserialização do JSON"),
	PROPRIEDADE_INEXISTENTE("propriedade-inexistente", "Propriedade inexistente"),
	ERRO_INTEGRIDADE_BANCO_DADOS("erro-integridade-banco-dados", "Erro de integridade banco de dados"),
	ERRO_CONSTRAINT_BANCO_DADOS("erro-constraint-banco-dados", "Erro de consntraint banco de dados"),
	ERRO_INTERNO_SERVIDOR("erro-interno-servidor", "Erro interno no servidor"),
	ERRO_NAS_VARIAVEIS_DE_PARAMETRO_DA_URI("erro-nas-variaveis-de-parametro-da-uri",
			"Erro nas variaveis de parâmetro da URI"),
	FALHA_NA_AUTENTICACAO("falha-na-autenticacao", "Falha na autenticação");

	private String type;
	private String title;

	private Error(String type, String title) {
		this.type = "https://www.joaoandradejava.com.br/" + type;
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

}
