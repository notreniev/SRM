package com.srm.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.srm.entity.Cliente;
import com.srm.infra.Erro;
import com.srm.utils.Utils;

/**
 * 
 * @author Everton Canez
 * @hidden
 */
@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ClienteRest {

	@PersistenceContext
	EntityManager em;

	@Inject
	ClienteDAO clienteDAO;

	private List<Erro> listaErros;
	private Map<String, Object> map;

	@GET
	public Response listar() {
		TypedQuery<Cliente> query = em.createQuery("SELECT c From Cliente c ", Cliente.class);
		query.setMaxResults(300);
		List<Cliente> lista = query.getResultList();

		if (lista == null || lista.size() == 0) {
			map = new HashMap<String, Object>();
			map.put("mensagem", "Nenhum registro encontrado!");
			return Response.ok().entity(map).build();
		}

		return Response.ok().entity(lista).build();
	}

	@GET
	@Path("{id}")
	public Response consultar(@PathParam("id") String id) {
		listaErros = new ArrayList<Erro>();
		map = new HashMap<String, Object>();

		if (id == null || id.length() == 0) {
			listaErros.add(new Erro("id", "Parâmetro não pode ser nulo!"));
			map.put("erros", listaErros);
		}

		if (!Utils.isNumber(id)) {
			listaErros.add(new Erro("id", "Parâmetro ID precisa ser numérico!"));
			map.put("erros", listaErros);
		}

		Cliente cliente = clienteDAO.consultar(new Cliente(Integer.parseInt(id)));

		if (cliente == null) {
			map.put("erros", "Nenhum cliente encontrado!");
		}

		if (listaErros.size() > 0) {
			return Response.status(Status.BAD_REQUEST).entity(map).build();
		} else {
			map.put("nomeCliente", cliente.getNomeCliente());
			map.put("limiteCredito", cliente.getLimiteCredito());
			map.put("risco", cliente.getRisco());
			map.put("taxa", cliente.getTaxa());
		}

		return Response.ok().entity(map).build();
	}

	@POST
	public Response cadastrar(@FormParam("nome_cliente") String nomeCliente,
			@FormParam("limite_credito") String limiteCredito, @FormParam("risco") String risco) {
		listaErros = new ArrayList<Erro>();
		map = new HashMap<String, Object>();
		Cliente cliente = new Cliente();

		if (nomeCliente == null || nomeCliente.length() == 0) {
			listaErros.add(new Erro("nomeCliente", "Nome do cliente não pode ser nulo!"));
		}

		if (limiteCredito == null || limiteCredito.length() == 0) {
			listaErros.add(new Erro("limiteCredito", "Limite de crédito não pode ser nulo!"));
		}

		if (risco == null || risco.length() == 0) {
			listaErros.add(new Erro("risco", "Risco não pode ser nulo!"));
		}

		if (listaErros.size() == 0) {
			float limite = Float.parseFloat(limiteCredito);
			float taxa = Utils.calculaTaxaconformeRisco(risco);
			cliente = clienteDAO.inserir(new Cliente(nomeCliente, limite, risco, taxa));
		} else {
			map.put("erros", listaErros);
			return Response.status(Status.BAD_REQUEST).entity(map).build();
		}

		map = new HashMap<String, Object>();
		map.put("cliente", cliente);
		map.put("mensagem", "Cliente cadastrado com sucesso!");
		return Response.ok().entity(map).build();
	}

	@PUT
	@Path("{id}")
	public Response alterar(@PathParam("id") String id, @FormParam("nome_cliente") String nomeCliente,
			@FormParam("limite_credito") String limiteCredito, @FormParam("risco") String risco) {

		listaErros = new ArrayList<Erro>();
		map = new HashMap<String, Object>();
		Cliente cliente = new Cliente();

		if (id == null || id.length() == 0) {
			listaErros.add(new Erro("idCliente", "ID do cliente não pode ser nulo!"));
		}

		if (nomeCliente == null || nomeCliente.length() == 0) {
			listaErros.add(new Erro("nomeCliente", "Nome do cliente não pode ser nulo!"));
		}

		if (limiteCredito == null || limiteCredito.length() == 0) {
			listaErros.add(new Erro("limiteCredito", "Limite de crédito não pode ser nulo!"));
		}

		if (risco == null || risco.length() == 0) {
			listaErros.add(new Erro("risco", "Risco não pode ser nulo!"));
		}

		if (listaErros.size() == 0) {
			float limite = Float.parseFloat(limiteCredito);
			Integer idNum = Integer.parseInt(id);
			float taxa = Utils.calculaTaxaconformeRisco(risco);
			cliente = clienteDAO.alterar(new Cliente(idNum, nomeCliente, limite, risco, taxa));
		} else {
			map.put("erros", listaErros);
			return Response.status(Status.BAD_REQUEST).entity(map).build();
		}

		map = new HashMap<String, Object>();
		map.put("cliente", cliente);
		map.put("mensagem", "Cliente alterado com sucesso!");
		return Response.ok().entity(map).build();
	}

	@DELETE
	@Path("{id}")
	public Response deletar(@PathParam("id") int id) {

		listaErros = new ArrayList<Erro>();
		map = new HashMap<String, Object>();

		if (id == 0) {
			listaErros.add(new Erro("id", "Parâmetro ID não pode ser nulo!"));
		} else {
			if (listaErros.size() > 0) {
				map.put("erros", listaErros);
				return Response.status(Status.BAD_REQUEST).entity(map).build();
			}

			clienteDAO.deletar(new Cliente(id));
		}

		map = new HashMap<String, Object>();
		map.put("mensagem", "Registro deletado com sucesso!");
		return Response.ok().entity(map).build();
	}

}
