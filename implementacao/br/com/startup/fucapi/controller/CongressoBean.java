/**
 * 
 */
package br.com.startup.fucapi.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.startup.fucapi.dao.CongressoDAO;
import br.com.startup.fucapi.model.Congresso;
import br.com.startup.fucapi.util.Util;

/**
 * @author JhimmyLiborio , Clara aben-athar
 * @version 1
 * @since 28/01/2015
 */

@ManagedBean
@ViewScoped
public class CongressoBean  implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	
	private List<Congresso> listCongresso;
	private List<Congresso> listFiltroTabelaCongresso;
	private Congresso congresso;
	
	
	@PostConstruct
	public void carregarListaCongresso(){
		CongressoDAO cDao = new CongressoDAO();
		listCongresso = cDao.listarCongresso(Util.pegarOrganizadorSessao());
	}
	
	
	/**
	 * @return the listCongresso
	 */
	public List<Congresso> getListCongresso() {
		return listCongresso;
	}
	/**
	 * @param listCongresso the listCongresso to set
	 */
	public void setListCongresso(List<Congresso> listCongresso) {
		this.listCongresso = listCongresso;
	}
	/**
	 * @return the listFiltroTabelaCongresso
	 */
	public List<Congresso> getListFiltroTabelaCongresso() {
		return listFiltroTabelaCongresso;
	}
	/**
	 * @param listFiltroTabelaCongresso the listFiltroTabelaCongresso to set
	 */
	public void setListFiltroTabelaCongresso(
			List<Congresso> listFiltroTabelaCongresso) {
		this.listFiltroTabelaCongresso = listFiltroTabelaCongresso;
	}
	/**
	 * @return the congresso
	 */
	public Congresso getCongresso() {
		return congresso;
	}
	/**
	 * @param congresso the congresso to set
	 */
	public void setCongresso(Congresso congresso) {
		this.congresso = congresso;
	}
	

	
}
