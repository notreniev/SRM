package com.srm.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.srm.entity.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	EntityManager em;

	public List<Cliente> listar(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Query query = em.createNativeQuery("SELECT * from SRM_API_CLIENTE ");
		clientes = query.getResultList();
		return clientes;
	}
	
	/**
	 * @param c do tipo Cliente
	 * @return Objeto Cliente consultado
	 */
	public Cliente consultar(Cliente c) {
		Cliente clienteRetorno = null;
		
		try {
			clienteRetorno = em.find(Cliente.class, c.getIdCliente());
			System.out.println("clienteRetorno: " + clienteRetorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clienteRetorno;
	}
	
	/**
	 * 
	 * @param Objeto cliente a ser persistido
	 * @return Dados do cliente persistido
	 */
	public Cliente inserir(Cliente c) {
		em.persist(c);
		em.flush();

		return c;
	}
	
	/**
	 * 
	 * @param Objeto Cliente a ser alterado
	 * @return Objeto Cliente alterado
	 */
	public Cliente alterar(Cliente c) {
		Cliente cliente = em.find(Cliente.class, c.getIdCliente());
		
		cliente.setLimiteCredito(c.getLimiteCredito());
		cliente.setNomeCliente(c.getNomeCliente());
		cliente.setRisco(c.getRisco());
		cliente.setTaxa(c.getTaxa());
		em.merge(cliente);
		
		return cliente;
	}
	
	/**
	 * 
	 * @param Objeto cliente a ser deletado
	 */
	public void deletar(Cliente c) {
		Cliente cli = em.find(Cliente.class, c.getIdCliente());
		em.remove(cli);
	}
	
}
