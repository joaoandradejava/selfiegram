package com.joaoandradejava.selfiegram.api.exceptionhandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.joaoandradejava.selfiegram.domain.exception.ErroInternoServidorException;
import com.joaoandradejava.selfiegram.domain.exception.FalhaNaAutenticacaoException;
import com.joaoandradejava.selfiegram.domain.exception.NegocioException;
import com.joaoandradejava.selfiegram.domain.exception.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {

	private final String MENSAGEM_ERRO_PADRAO = "Ocorreu um erro inesperado, se o problema persistir recomendo que entrem em contato com o desenvolvedor da API.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		Error error = Error.NEGOCIO_EXCEPTION;
		HttpStatus status = HttpStatus.BAD_REQUEST;

		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), ex.getMessage());

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(FalhaNaAutenticacaoException.class)
	public ResponseEntity<Object> handleFalhaNaAutenticacao(FalhaNaAutenticacaoException ex, WebRequest request) {
		Error error = Error.FALHA_NA_AUTENTICACAO;
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), ex.getMessage());

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<Object> handleObjetoNaoEncontrado(ObjetoNaoEncontradoException ex, WebRequest request) {
		Error error = Error.OBJETO_NAO_ENCONTRADO_EXCEPTION;
		HttpStatus status = HttpStatus.NOT_FOUND;

		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), ex.getMessage());

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ErroInternoServidorException.class)
	public ResponseEntity<Object> handleErroInternoServidor(ErroInternoServidorException ex, WebRequest request) {
		Error error = Error.ERRO_INTERNO_SERVIDOR;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), ex.getMessage(), MENSAGEM_ERRO_PADRAO);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {

		Throwable cause = ex.getCause();

		if (cause instanceof ConstraintViolationException) {
			return handleConstraintViolation((ConstraintViolationException) cause, request);
		}

		Error error = Error.ERRO_INTEGRIDADE_BANCO_DADOS;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String mensagem = "Ocorreu um erro de integridade do banco de dados";
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_ERRO_PADRAO);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);

	}

	private ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		ConstraintViolation constraintViolation = ConstraintViolation.getConstraint(ex.getConstraintName());
		Error error = Error.ERRO_CONSTRAINT_BANCO_DADOS;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String mensagem = constraintViolation.getMensagemConstraint();
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Error error = Error.ERRO_NAS_VARIAVEIS_DE_PARAMETRO_DA_URI;
		String mensagem = String.format("Está faltando a variavel de parametro '%s' do tipo %s", ex.getVariableName(),
				ex.getParameter().getGenericParameterType().getTypeName());
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getTitle(), error.getType(),
				status.value(), mensagem, MENSAGEM_ERRO_PADRAO);
		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Error error = Error.ERRO_VALIDACAO_DADOS;
		String mensagem = "Ocorreu um erro de validação dos dados, verifique se os dados foram inseridos corretamente";
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getTitle(), error.getType(),
				status.value(), mensagem);

		for (ObjectError objectError : ex.getAllErrors()) {
			String field = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				field = ((FieldError) objectError).getField();
			}

			String userMessage = this.messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			problemDetail.adicionarError(field, userMessage);
		}

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Error error = Error.ENDPOINT_NAO_ENCONTRADO;
		String mensagem = String.format("Não existe o endpoint [%s%s] na aplicação", ex.getHttpMethod(),
				ex.getRequestURL());
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getTitle(), error.getType(),
				status.value(), mensagem, MENSAGEM_ERRO_PADRAO);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Error error = Error.METODO_REQUISICAO_NAO_SUPORTADO;
		String message = String.format("A requisição feita para o metodo %s não é suportada", ex.getMethod());
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getTitle(), error.getType(),
				status.value(), message, MENSAGEM_ERRO_PADRAO);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Throwable cause = ex.getCause();

		if (cause instanceof UnrecognizedPropertyException) {
			return this.handleUnrecognizedProperty((UnrecognizedPropertyException) cause, headers, status, request);
		}

		Error error = Error.ERRO_TENTATIVA_DESSERIALIZACAO_JSON;
		String mensagem = "Ocorreu um erro na tentativa da desserialização do JSON. Verifique se os dados foram inseridos corretamente no corpo da requisição";
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getTitle(), error.getType(),
				status.value(), mensagem, MENSAGEM_ERRO_PADRAO);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Error error = Error.PROPRIEDADE_INEXISTENTE;
		String mensagem = String.format("A propriedade '%s' é inexistente.", ex.getPropertyName());

		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getTitle(), error.getType(),
				status.value(), mensagem, MENSAGEM_ERRO_PADRAO);
		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {

		if (body == null) {
			body = new ProblemDetail(null, null, statusCode.value(), MENSAGEM_ERRO_PADRAO, MENSAGEM_ERRO_PADRAO);
		}

		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
}
